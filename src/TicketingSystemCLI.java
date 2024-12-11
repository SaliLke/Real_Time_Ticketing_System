import java.util.Scanner;

public class TicketingSystemCLI {
    private static int totalTicketsInput;
    private static int ticketReleaseRateInput;
    private static int customerRetrievalRateInput;
    private static int maxTicketCapacityInput;
    private static Configuration configuration = new Configuration();

    public static void main(String[] args) {
        String filepath = "C:/Users/LOQ/Desktop/OOP_CW/OOP/CW/TicketingSystemCLI/src/configuration.json";
        Scanner input = new Scanner(System.in);

        System.out.println("Do you want to load the configuration from file? (yes/no): ");
        String choice = input.nextLine().trim().toLowerCase();

        if (choice.equals("yes")) {
            Configuration loadedConfiguration = configuration.loadFromFile(filepath);
            if (loadedConfiguration != null) {
                configuration = loadedConfiguration; // Update the current configuration
                totalTicketsInput = configuration.getTotalTickets();
                ticketReleaseRateInput = configuration.getTicketReleaseRate();
                customerRetrievalRateInput = configuration.getCustomerRetrievalRate();
                maxTicketCapacityInput = configuration.getMaxTicketCapacity();
                System.out.println("Loaded Configuration: " + configuration);
                return;
            } else {
                System.out.println("Failed to load configuration. Please enter manually.");
            }
        }

        getInputs();
        configuration.saveToFile(filepath);
    }

    public static void getInputs() {
        Scanner input = new Scanner(System.in);
        totalTicketsInput = ValidateInputs(input, "Enter the total number of tickets: ", 1, 1000);
        configuration.setTotalTickets(totalTicketsInput);
        ticketReleaseRateInput = ValidateInputs(input, "Enter the ticket release rate: ", 1, 1000);
        configuration.setTicketReleaseRate(ticketReleaseRateInput);
        customerRetrievalRateInput = ValidateInputs(input, "Enter the customer retrieval rate: ", 1, 1000);
        configuration.setCustomerRetrievalRate(customerRetrievalRateInput);
        maxTicketCapacityInput = ValidateInputs(input, "Enter the max ticket capacity: ", totalTicketsInput, 1000);
        configuration.setMaxTicketCapacity(maxTicketCapacityInput);
    }

    public static int ValidateInputs(Scanner scanner, String message, int minValue, int maxValue) {
        int enteredValue;
        while (true) {
            System.out.print(message);
            if (scanner.hasNextInt()) {
                enteredValue = scanner.nextInt();
                if (enteredValue >= minValue && enteredValue <= maxValue) {
                    break;
                } else {
                    System.out.println("Invalid input. The value should be between " + minValue + " and " + maxValue);
                }
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next();
            }
        }
        return enteredValue;
    }

    public static int getTotalTicketsInput() {
        return totalTicketsInput;
    }

    public static int getTicketReleaseRateInput() {
        return ticketReleaseRateInput;
    }

    public static int getCustomerRetrievalRateInput() {
        return customerRetrievalRateInput;
    }

    public static int getMaxTicketCapacityInput() {
        return maxTicketCapacityInput;
    }
}
