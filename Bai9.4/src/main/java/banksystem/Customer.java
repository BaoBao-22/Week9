package banksystem;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents a bank customer.
 */
public class Customer {

  private static final Logger logger = LoggerFactory.getLogger(Customer.class);

  private long idNumber;
  private String fullName;
  private List<Account> accountList;

  /**
   * Default constructor.
   */
  public Customer() {
    this(0L, "");
  }

  /**
   * Constructs a Customer with ID and name.
   *
   * @param idNumber the customer ID number
   * @param fullName the customer full name
   */
  public Customer(long idNumber, String fullName) {
    this.idNumber = idNumber;
    this.fullName = fullName;
    this.accountList = new ArrayList<>();
  }

  public long getIdNumber() {
    return idNumber;
  }

  public void setIdNumber(long idNumber) {
    this.idNumber = idNumber;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public List<Account> getAccountList() {
    return accountList;
  }

  /**
   * Sets the account list for the customer.
   *
   * @param accountList the new account list
   */
  public void setAccountList(List<Account> accountList) {
    if (accountList == null) {
      this.accountList = new ArrayList<>();
    } else {
      this.accountList = accountList;
    }
  }

  /**
   * Adds an account to the customer.
   *
   * @param account the account to add
   */
  public void addAccount(Account account) {
    if (account == null) {
      return;
    }
    if (!accountList.contains(account)) {
      accountList.add(account);
      logger.info("Added account {} to customer {}", account.getAccountNumber(), idNumber);
    }
  }

  /**
   * Removes an account from the customer.
   *
   * @param account the account to remove
   */
  public void removeAccount(Account account) {
    if (account == null) {
      return;
    }
    if (accountList.remove(account)) {
      logger.info("Removed account {} from customer {}", account.getAccountNumber(), idNumber);
    }
  }

  /**
   * Returns a summary of customer information.
   *
   * @return customer info string
   */
  public String getCustomerInfo() {
    return "Số CMND: " + idNumber + ". Họ tên: " + fullName + ".";
  }
}
