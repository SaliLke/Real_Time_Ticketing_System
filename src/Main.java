import java.util.Scanner;
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        Configuration configuration;
        int numVendor=0;
        int numCustomer=0;
        int totalTickets;
        int ticketsReleaseRate;
        int ticketsPerCustomer = 5;
        int retrievalRate;
        int maxCapacity;

        TicketingSystemCLI ticketingSystemCLI = new TicketingSystemCLI();
        ticketingSystemCLI.main(args);
        totalTickets = TicketingSystemCLI.getTotalTicketsInput();
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

        boolean simulationRunning = true;

        while (simulationRunning) {
            if (command.equalsIgnoreCase("stop")) {
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
                simulationRunning = false;
            } else if (command.equalsIgnoreCase("start")) {
                System.out.println("Starting simulation...");
                TicketPool ticketPool = new TicketPool(maxCapacity, totalTickets);
                for (int i = 1; i <= totalTickets; i++) {
                    BigDecimal ticketPrice=new BigDecimal ("1000");
                    Ticket ticket = new Ticket("name", ticketPrice);
                    try {
                        ticketPool.addTickets(ticket);
                    } catch (InterruptedException e) {
                        throw new RuntimeException (e);
                    }
                }

                System.out.println("TicketPool initialized with total tickets: " + totalTickets);
                while (numVendor <= 0) {
                    System.out.println("Enter the no. of Vendors (positive integer):");
                    if (scanner.hasNextInt()) {
                        numVendor = scanner.nextInt();
                        if (numVendor <= 0) {
                            System.out.println("Please enter a positive number for vendors.");
                        }
                    } else {
                        System.out.println("Invalid input. Please enter a positive integer.");
                        scanner.next();
                    }
                }

                while (numCustomer <= 0) {
                    System.out.println("Enter the no. of Customers (positive integer):");
                    if (scanner.hasNextInt()) {
                        numCustomer = scanner.nextInt();
                        if (numCustomer <= 0) {
                            System.out.println("Please enter a positive number for customers.");
                        }
                    } else {
                        System.out.println("Invalid input. Please enter a positive integer.");
                        scanner.next();
                    }
                }

                vendor = new Vendor[numVendor];
                vendorThreads = new Thread[numVendor];
                for (int i = 0; i < vendor.length; i++) {
                    vendor[i] = new Vendor(ticketPool, totalTickets, ticketsReleaseRate);
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
                simulationRunning = false;
            } else {
                System.out.println("Invalid command. Type 'start' to begin the simulation or 'stop' to stop.");
            }

            if (simulationRunning) {
                command = scanner.nextLine();
            }
        }
    }
}
