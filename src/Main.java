import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Configuration configuration;
        int numVendor=10;
        int numCustomer=10;
        int ticketsPerVendor;
        int ticketsReleaseRate;
        int ticketsPerCustomer=5;
        int retrievalRate;
        int maxCapacity;

        TicketingSystemCLI ticketingSystemCLI=new TicketingSystemCLI();
        ticketingSystemCLI.main(args);
        ticketsPerVendor= TicketingSystemCLI.getTotalTicketsInput ();
        ticketsReleaseRate=ticketingSystemCLI.getTicketReleaseRateInput ();
        retrievalRate=ticketingSystemCLI.getCustomerRetrievalRateInput ();
        maxCapacity=ticketingSystemCLI.getMaxTicketCapacityInput ();

        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        while (!command.equalsIgnoreCase("start")) {
//            if (command.equalsIgnoreCase("stop")) {
//
//            }
            System.out.println("Invalid command. Type 'start' to begin the simulation.");
            command = scanner.nextLine();
        }
        System.out.println("Starting simulation...");

        TicketPool ticketPool = new TicketPool(maxCapacity,ticketsPerVendor);
        Vendor[] vendor = new Vendor[numVendor];
        for (int i = 0; i < vendor.length; i++) {
            vendor[i]=new Vendor (ticketPool,ticketsPerVendor,ticketsReleaseRate);
            Thread vendorThread = new Thread(vendor[i],"vendor "+i);
            vendorThread.start();
        }

        Customer[] customer = new Customer[numCustomer];
        for (int i = 0; i < customer.length; i++) {
            customer[i]=new Customer (ticketPool,retrievalRate,ticketsPerCustomer);
            Thread cutomerThread = new Thread(customer[i],"customer "+i);
            cutomerThread.start ();
        }
    }
}