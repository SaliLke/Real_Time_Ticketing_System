
import java.util.Scanner;
public class TicketingSystemCLI {
    private static int totalTicketsInput;
    private static int ticketReleaseRateInput;
    private static int customerRetrievalRateInput;
    private static int maxTicketCapacityInput;
    private static Configuration configuration =new Configuration();

    public static void main(String[] args) {
        getInputs ();
        String filepath="E:/IIT Stuff/oop/Mine/CW/TicketingSystemCLI/src/configuration.json";
        configuration.saveToFile (filepath);
        Configuration loadedConfiguration= configuration.loadFromFile(filepath);
        System.out.println("Loaded Configuration: " + loadedConfiguration);
    }

    public static void getInputs(){
        Scanner input = new Scanner(System.in);
        totalTicketsInput= ValidateInputs(input, "Enter the total number of tickets: ",1,Integer.MAX_VALUE);
        configuration.setTotalTickets(totalTicketsInput);
        ticketReleaseRateInput = ValidateInputs(input, "Enter the ticket release rate: ",1, Integer.MAX_VALUE);
        configuration.setTicketReleaseRate (ticketReleaseRateInput);
        customerRetrievalRateInput = ValidateInputs(input, "Enter the customer retrieval rate: ",1, Integer.MAX_VALUE);
        configuration.setCustomerRetrievalRate (customerRetrievalRateInput);
        maxTicketCapacityInput = ValidateInputs(input, "Enter the max ticket capacity: ",totalTicketsInput, 4000);
        configuration.setMaxTicketCapacity (maxTicketCapacityInput);
    }

    public static int ValidateInputs(Scanner scanner, String message, int minValue, int maxValue){
        int EnteredValue;
        while (true){
            System.out.print(message);
            if (scanner.hasNextInt()){
                EnteredValue = scanner.nextInt();
                if (EnteredValue >= minValue && EnteredValue <= maxValue){
                    break;
                }else {
                    System.out.println("Invalid input. The value should be between " + minValue + " and " + maxValue);
                }
            }else {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next ();
            }
        }
        return EnteredValue;
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