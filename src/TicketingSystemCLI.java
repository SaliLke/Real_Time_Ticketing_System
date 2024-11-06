
import java.util.Scanner;
public class TicketingSystemCLI {
    private static int totalTickets;
    private static int ticketReleaseRate;
    private static int customerRetrievalRate;
    private static int maxTicketCapacity=10;
    private static Configuration configuration=new Configuration();

    public static void main(String[] args) {
        getInputs ();
        System.out.println ("Data to be stored in the configuration class:");
        System.out.println ("Total tickets: " + configuration.getTotalTickets ());
        System.out.println ("Ticket release rate: " + configuration.getTicketReleaseRate ());
        System.out.println ("Customer retrieval rate: " + configuration.getCustomerRetrievalRate ());
        System.out.println ("Max ticket capacity: " + configuration.getMaxTicketCapacity ());
    }
    public static void getInputs(){
        Scanner input = new Scanner(System.in);
        totalTickets= ValidateInputs(input, "Enter the total number of tickets: ",1, maxTicketCapacity);
        configuration.setTotalTickets(totalTickets);
        ticketReleaseRate= ValidateInputs(input, "Enter the ticket release rate: ",1, Integer.MAX_VALUE);
        configuration.setTicketReleaseRate (ticketReleaseRate);
        customerRetrievalRate= ValidateInputs(input, "Enter the customer retrieval rate: ",1, Integer.MAX_VALUE);
        configuration.setCustomerRetrievalRate (customerRetrievalRate);
        maxTicketCapacity= ValidateInputs(input, "Enter the max ticket capacity: ",1, Integer.MAX_VALUE);
        configuration.setMaxTicketCapacity (maxTicketCapacity);
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
}
