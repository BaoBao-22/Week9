package banksystem;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents a bank that manages customers and their accounts.
 */
public class Bank {

  private static final Logger logger = LoggerFactory.getLogger(Bank.class);

  private List<Customer> customerList;

  /**
   * Constructs a new Bank.
   */
  public Bank() {
    this.customerList = new ArrayList<>();
  }

  public List<Customer> getCustomerList() {
    return customerList;
  }

  /**
   * Sets the customer list.
   *
   * @param customerList the new customer list
   */
  public void setCustomerList(List<Customer> customerList) {
    if (customerList == null) {
      this.customerList = new ArrayList<>();
    } else {
      this.customerList = customerList;
    }
  }

  /**
   * Reads customer and account data from an InputStream.
   *
   * @param inputStream the stream to read from
   */
  public void readCustomerList(InputStream inputStream) {
    logger.info("Starting to read customer list from stream");
    if (inputStream == null) {
      logger.warn("Input stream is null, skipping read");
      return;
    }

    try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
      String line;
      Customer current = null;
      while ((line = reader.readLine()) != null) {
        line = line.trim();
        if (line.isEmpty()) {
          continue;
        }

        int lastSpaceIndex = line.lastIndexOf(' ');
        if (lastSpaceIndex <= 0) {
          continue;
        }

        String token = line.substring(lastSpaceIndex + 1).trim();
        if (token.matches("\\d{9}")) {
          String name = line.substring(0, lastSpaceIndex).trim();
          current = new Customer(Long.parseLong(token), name);
          customerList.add(current);
          logger.debug("Added customer: {} (ID: {})", name, token);
        } else if (current != null) {
          processAccountLine(line, current);
        }
      }
      logger.info("Successfully finished reading customer list. Total customers: {}",
          customerList.size());
    } catch (Exception e) {
      logger.error("Error occurred while reading customer list: {}", e.getMessage(), e);
    }
  }

  private void processAccountLine(String line, Customer customer) {
    String[] parts = line.split("\\s+");
    if (parts.length >= 3) {
      try {
        long accountNumber = Long.parseLong(parts[0]);
        String type = parts[1];
        double balance = Double.parseDouble(parts[2]);

        if (Account.CHECKING_TYPE.equals(type)) {
          customer.addAccount(new CheckingAccount(accountNumber, balance));
        } else if (Account.SAVINGS_TYPE.equals(type)) {
          customer.addAccount(new SavingsAccount(accountNumber, balance));
        }
        logger.debug("Added {} account {} to customer {}", type, accountNumber,
            customer.getIdNumber());
      } catch (NumberFormatException e) {
        logger.warn("Skipping invalid account line: {}", line);
      }
    }
  }

  /**
   * Returns customer info sorted by ID.
   *
   * @return sorted customer info string
   */
  public String getCustomersInfoByIdOrder() {
    List<Customer> sortedList = new ArrayList<>(customerList);
    sortedList.sort(Comparator.comparingLong(Customer::getIdNumber));

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < sortedList.size(); i++) {
      sb.append(sortedList.get(i).getCustomerInfo());
      if (i < sortedList.size() - 1) {
        sb.append("\n");
      }
    }
    return sb.toString();
  }

  /**
   * Returns customer info sorted by name (then ID).
   *
   * @return sorted customer info string
   */
  public String getCustomersInfoByNameOrder() {
    List<Customer> sortedList = new ArrayList<>(customerList);
    sortedList.sort((c1, c2) -> {
      int nameComparison = c1.getFullName().compareTo(c2.getFullName());
      if (nameComparison != 0) {
        return nameComparison;
      }
      return Long.compare(c1.getIdNumber(), c2.getIdNumber());
    });

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < sortedList.size(); i++) {
      sb.append(sortedList.get(i).getCustomerInfo());
      if (i < sortedList.size() - 1) {
        sb.append("\n");
      }
    }
    return sb.toString();
  }
}