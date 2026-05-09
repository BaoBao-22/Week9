package banksystem;

import java.util.Locale;

/**
 * Exception thrown when a funding amount is invalid (e.g., negative).
 */
public class InvalidFundingAmountException extends BankException {

  /**
   * Constructs a new InvalidFundingAmountException.
   *
   * @param amount the invalid amount
   */
  public InvalidFundingAmountException(double amount) {
    super("Số tiền không hợp lệ: $" + String.format(Locale.US, "%.2f", amount));
  }
}
