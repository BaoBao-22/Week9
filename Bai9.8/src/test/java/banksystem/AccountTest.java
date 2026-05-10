package banksystem;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    @Test
    public void testDeposit() throws InvalidFundingAmountException {
        Account account = new CheckingAccount(1001L, 1000.0);
        
        account.deposit(500.0);
        assertEquals(1500.0, account.getBalance(), 0.001, "Balance should be 1500.0 after depositing 500.0");
    }

    @Test
    public void testWithdrawSuccess() throws InsufficientFundsException {
        Account account = new CheckingAccount(1001L, 1000.0);
        
        account.withdraw(400.0);
        assertEquals(600.0, account.getBalance(), 0.001, "Balance should be 600.0 after withdrawing 400.0");
    }

    @Test
    public void testWithdrawInsufficientFunds() {
        Account account = new CheckingAccount(1001L, 100.0);
        account.withdraw(200.0);
        assertEquals(100.0, account.getBalance(), "Balance should remain 100.0 after failed withdrawal");
    }
}
