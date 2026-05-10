package banksystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Savings account implementation with withdrawal and balance restrictions.
 */
public class SavingsAccount extends Account {

  private static final Logger logger = LoggerFactory.getLogger(SavingsAccount.class);

  public static final double MAX_WITHDRAWAL_AMOUNT = 1000.0;
  public static final double MIN_BALANCE_REQUIRED = 5000.0;

  public SavingsAccount(long accountNumber, double balance) {
    super(accountNumber, balance);
  }

  @Override
  public void deposit(double amount) {
    logger.info("Processing deposit for savings account: {}", getAccountNumber());
    double initialBalance = getBalance();
    try {
      doDepositing(amount);
      double finalBalance = getBalance();
      Transaction t = new Transaction(
          Transaction.TYPE_DEPOSIT_SAVINGS,
          amount,
          initialBalance,
          finalBalance
      );
      addTransaction(t);
      logger.info("Deposit of ${} successful for account {}", amount, getAccountNumber());
    } catch (InvalidFundingAmountException e) {
      logger.error("Failed to deposit to account {}: {}", getAccountNumber(), e.getMessage());
    }
  }

  @Override
  public void withdraw(double amount) {
    logger.info("Processing withdrawal for savings account: {}", getAccountNumber());
    double initialBalance = getBalance();
    try {
      if (amount > MAX_WITHDRAWAL_AMOUNT) {
        throw new InvalidFundingAmountException(amount);
      }
      if (initialBalance - amount < MIN_BALANCE_REQUIRED) {
        throw new InsufficientFundsException(amount);
      }

      doWithdrawing(amount);
      double finalBalance = getBalance();

      Transaction t = new Transaction(
          Transaction.TYPE_WITHDRAW_SAVINGS,
          amount,
          initialBalance,
          finalBalance
      );
      addTransaction(t);
      logger.info("Withdrawal of ${} successful for account {}. New balance: ${}",
          amount, getAccountNumber(), finalBalance);
    } catch (InvalidFundingAmountException | InsufficientFundsException e) {
      logger.warn("Withdrawal failed for account {}: {}", getAccountNumber(), e.getMessage());
    }
  }
}