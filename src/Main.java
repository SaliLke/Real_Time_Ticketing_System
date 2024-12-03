import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Configuration configuration;
        int numVendor;
        int numCustomer;
        int ticketsPerVendor;
        int ticketsReleaseRate;
        int ticketsPerCustomer = 5;
        int retrievalRate;
        int maxCapacity;

        TicketingSystemCLI ticketingSystemCLI = new TicketingSystemCLI();
        ticketingSystemCLI.main(args);
        ticketsPerVendor = TicketingSystemCLI.getTotalTicketsInput();
        ticketsReleaseRate = ticketingSystemCLI.getTicketReleaseRateInput();
        retrievalRate = ticketingSystemCLI.getCustomerRetrievalRateInput();
        maxCapacity = ticketingSystemCLI.getMaxTicketCapacityInput();

        Vendor[] vendor = null;
        Thread[] vendorThreads = null;
        Customer[] customer = null;
        Thread[] customerThreads = null;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter 'Start' to start the simulation: ");
        String command = scanner.nextLine();

        // Flag to control the simulation loop
        boolean simulationRunning = true;

        while (simulationRunning) {
            if (command.equalsIgnoreCase("stop")) {
                // Handle the stop command (interrupt the threads)
                System.out.println("Stopping simulation...");
                if (vendorThreads != null) {
                    for (int i = 0; i < vendorThreads.length; i++) {
                        vendorThreads[i].interrupt();
                    }
                }
                if (customerThreads != null) {
                    for (int i = 0; i < customerThreads.length; i++) {
                        customerThreads[i].interrupt();
                    }
                }
                simulationRunning = false; // Stop the loop after stopping the threads
            } else if (command.equalsIgnoreCase("start")) {
                System.out.println("Starting simulation...");
                TicketPool ticketPool = new TicketPool(maxCapacity, ticketsPerVendor);
                System.out.println("Enter the no.of Vendors:");
                numVendor = scanner.nextInt();
                System.out.println("Enter the no.of Customers:");
                numCustomer = scanner.nextInt();

                vendor = new Vendor[numVendor];
                vendorThreads = new Thread[numVendor];
                for (int i = 0; i < vendor.length; i++) {
                    vendor[i] = new Vendor(ticketPool, ticketsPerVendor, ticketsReleaseRate);
                    vendorThreads[i] = new Thread(vendor[i], "vendor " + (i + 1));
                    vendorThreads[i].start();
                }

                customer = new Customer[numCustomer];
                customerThreads = new Thread[numCustomer];
                for (int i = 0; i < customer.length; i++) {
                    customer[i] = new Customer(ticketPool, retrievalRate, ticketsPerCustomer);
                    customerThreads[i] = new Thread(customer[i], "customer " + (i + 1));
                    customerThreads[i].start();
                }
                simulationRunning = false; // End the loop once the simulation starts
            } else {
                System.out.println("Invalid command. Type 'start' to begin the simulation or 'stop' to stop.");
            }
            // Only read the next command if the simulation isn't running yet
            if (simulationRunning) {
                command = scanner.nextLine();
            }
        }

        System.out.println("Simulation has ended.");
    }
}
