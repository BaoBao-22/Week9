package banksystem;

import java.util.Locale;

/**
 * Exception thrown when an account has insufficient funds for an operation.
 */
public class InsufficientFundsException extends BankException {

  /**
   * Constructs a new InsufficientFundsException.
   *
   * @param amount the amount that failed to be withdrawn
   */
  public InsufficientFundsException(double amount) {
    super("Số dư tài khoản không đủ $" + String.format(Locale.US, "%.2f", amount)
        + " để thực hiện giao dịch");
  }
}
