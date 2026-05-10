package banksystem;

/**
 * Base exception for bank system operations.
 */
public class BankException extends Exception {

  /**
   * Constructs a new BankException.
   *
   * @param message the detail message
   */
  public BankException(String message) {
    super(message);

  }
}
