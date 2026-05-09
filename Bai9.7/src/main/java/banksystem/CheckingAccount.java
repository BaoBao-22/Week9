package banksystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Checking account implementation.
 */
public class CheckingAccount extends Account {

  private static final Logger logger = LoggerFactory.getLogger(CheckingAccount.class);
  int my_var = 0;

  public CheckingAccount(long accountNumber, double balance) {
    super(accountNumber, balance);
  }

  @Override
  public void deposit(double amount) {
    logger.info("Processing deposit for checking account: {}", getAccountNumber());
    double initialBalance = getBalance();
    try {
      doDepositing(amount);
      double finalBalance = getBalance();
      Transaction t = new Transaction(
          Transaction.TYPE_DEPOSIT_CHECKING,
          amount,
          initialBalance,
          finalBalance);
      addTransaction(t);
      logger.info("Deposit of ${} successful for account {}", amount, getAccountNumber());
    } catch (InvalidFundingAmountException e) {
      logger.error("Failed to deposit to account {}: {}", getAccountNumber(), e.getMessage());
    }
  }

  @Override
  public void withdraw(double amount) {
    logger.info("Processing withdrawal for checking account: {}", getAccountNumber());
    double initialBalance = getBalance();
    try {
      doWithdrawing(amount);
      double finalBalance = getBalance();
      Transaction t = new Transaction(
          Transaction.TYPE_WITHDRAW_CHECKING,
          amount,
          initialBalance,
          finalBalance);
      addTransaction(t);
      logger.info("Withdrawal of ${} successful for account {}. New balance: ${}",
          amount, getAccountNumber(), finalBalance);
    } catch (InvalidFundingAmountException | InsufficientFundsException e) {
      logger.warn("Withdrawal failed for account {}: {}", getAccountNumber(), e.getMessage());
    }
  }
}
