
import java.util.Scanner;
public class TicketingSystemCLI {
    private static int totalTickets;
    private static int ticketReleaseRate;
    private static int customerRetrievalRate;
    private static int maxTicketCapacity=10;

    public static void main(String[] args) {
        getInputs ();
    }
    public static void getInputs(){
        Scanner input = new Scanner(System.in);
        totalTickets= ValidateInputs(input, "Enter the total number of tickets: ",1, maxTicketCapacity);
        ticketReleaseRate= ValidateInputs(input, "Enter the ticket release rate: ",1, Integer.MAX_VALUE);
        customerRetrievalRate= ValidateInputs(input, "Enter the customer retrieval rate: ",1, Integer.MAX_VALUE);
        maxTicketCapacity= ValidateInputs(input, "Enter the max ticket capacity: ",1, Integer.MAX_VALUE);
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
