package banksystem;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class BankSystemTest {

    private Bank bank;

    @BeforeEach
    public void setUp() {
        bank = new Bank();
    }

    @Test
    public void testCustomerMethods() {
        Customer customer = new Customer(123456789L, "Nguyen Van A");
        assertEquals(123456789L, customer.getIdNumber());
        assertEquals("Nguyen Van A", customer.getFullName());
        assertTrue(customer.getAccountList().isEmpty());
        
        customer.setIdNumber(987654321L);
        customer.setFullName("Nguyen Van B");
        assertEquals(987654321L, customer.getIdNumber());
        assertEquals("Nguyen Van B", customer.getFullName());
        
        Account acc = new CheckingAccount(1001L, 500.0);
        customer.addAccount(acc);
        assertEquals(1, customer.getAccountList().size());
        customer.addAccount(acc); // Should not add duplicate
        assertEquals(1, customer.getAccountList().size());
        
        customer.removeAccount(acc);
        assertTrue(customer.getAccountList().isEmpty());
        
        assertNotNull(customer.getCustomerInfo());
    }

    @Test
    public void testSavingsAccount() throws InvalidFundingAmountException, InsufficientFundsException {
        SavingsAccount sa = new SavingsAccount(2001L, 6000.0); // Nạp đủ để vượt MIN_BALANCE_REQUIRED (5000)
        sa.deposit(500.0);
        assertEquals(6500.0, sa.getBalance());
        sa.withdraw(200.0);
        assertEquals(6300.0, sa.getBalance());
        assertTrue(sa instanceof Account);
    }

    @Test
    public void testBankReadCustomerList() {
        String data = "Nguyen Van A 123456789\n1001 CHECKING 500.0\n2001 SAVINGS 1000.0\n";
        InputStream inputStream = new ByteArrayInputStream(data.getBytes());
        bank.readCustomerList(inputStream);
        
        assertEquals(1, bank.getCustomerList().size());
        Customer c = bank.getCustomerList().get(0);
        assertEquals(2, c.getAccountList().size());
        
        bank.readCustomerList(null); // Test null input
        bank.setCustomerList(null);
        assertTrue(bank.getCustomerList().isEmpty());
    }

    @Test
    public void testBankInfoSorting() {
        bank.getCustomerList().add(new Customer(222222222L, "B"));
        bank.getCustomerList().add(new Customer(111111111L, "A"));
        
        String infoById = bank.getCustomersInfoByIdOrder();
        assertTrue(infoById.indexOf("111111111") < infoById.indexOf("222222222"));
        
        String infoByName = bank.getCustomersInfoByNameOrder();
        assertTrue(infoByName.indexOf("A") < infoByName.indexOf("B"));
    }

    @Test
    public void testExceptions() {
        BankException be = new BankException("Error");
        assertEquals("Error", be.getMessage());
        
        InsufficientFundsException ife = new InsufficientFundsException(200.0);
        assertTrue(ife.getMessage().contains("200.0"));
        
        InvalidFundingAmountException ifae = new InvalidFundingAmountException(-50.0);
        assertTrue(ifae.getMessage().contains("-50.0"));
    }
    
    @Test
    public void testCustomerEdgeCases() {
        Customer customer = new Customer(); // Test default constructor
        assertEquals(0L, customer.getIdNumber());
        
        customer.addAccount(null); // Should handle null
        customer.removeAccount(null); // Should handle null
        customer.removeAccount(new CheckingAccount(999L, 0)); // Remove non-existent
        
        customer.setAccountList(null); // Should handle null list
        assertTrue(customer.getAccountList().isEmpty());
    }

    @Test
    public void testBankReadEdgeCases() {
        bank.readCustomerList(new ByteArrayInputStream("Invalid Data".getBytes()));
        assertTrue(bank.getCustomerList().isEmpty());
        
        String partialData = "Nguyen Van A 123456789\n1001 INVALID 500.0\n";
        bank.readCustomerList(new ByteArrayInputStream(partialData.getBytes()));
        // Customer was added, but account with "INVALID" type should NOT be added
        assertEquals(0, bank.getCustomerList().get(0).getAccountList().size());
    }

    @Test
    public void testInvalidFunding() {
        Account account = new CheckingAccount(1001L, 1000.0);
        account.deposit(-100.0); // This logs error but doesn't throw
        assertEquals(1000.0, account.getBalance(), "Balance should not change with invalid deposit");
    }

    @Test
    public void testTransactionSummary() {
        Transaction t = new Transaction(Transaction.TYPE_WITHDRAW_CHECKING, 100.0, 500.0, 400.0);
        String summary = t.getTransactionSummary();
        assertTrue(summary.contains("Rút tiền vãng lai"));
        assertTrue(summary.contains("100.00"));
    }

    @Test
    public void testBankSortSameName() {
        bank.getCustomerList().add(new Customer(200L, "Same Name"));
        bank.getCustomerList().add(new Customer(100L, "Same Name"));
        
        String info = bank.getCustomersInfoByNameOrder();
        // Should sort by ID if name is same
        assertTrue(info.indexOf("100") < info.indexOf("200"));
    }

    @Test
    public void testTransactionSummaryTypes() {
        assertEquals("Nạp tiền tiết kiệm", Transaction.getTypeString(Transaction.TYPE_DEPOSIT_SAVINGS));
        assertEquals("Rút tiền tiết kiệm", Transaction.getTypeString(Transaction.TYPE_WITHDRAW_SAVINGS));
    }
}
