import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) throws IOException {
        Logger logger = new Logger("System.log");
        Logger ticketPoolLogger = new Logger("ticketpool.log");

        logger.writeLog("System initialized");

        Configuration configuration;
        int numVendor = 0;
        int numCustomer = 0;
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
        boolean simulationRunning = true;
        boolean simulationInitialized = false;

        while (simulationRunning) {
            if (!simulationInitialized) {
                System.out.print("Enter a command ('start' to begin simulation, 'stop' to end simulation): ");
            }

            String command = scanner.nextLine().trim().toLowerCase();
            logger.writeLog("Command received: " + command);

            if (command.equals("stop")) {
                if (simulationInitialized) {
                    System.out.println("Stopping simulation...");
                    logger.writeLog("Simulation stopping...");
                    if (vendorThreads != null) {
                        for (Thread thread : vendorThreads) {
                            thread.interrupt();
                        }
                    }
                    if (customerThreads != null) {
                        for (Thread thread : customerThreads) {
                            thread.interrupt();
                        }
                    }
                    simulationRunning = false;
                    simulationInitialized = false;
                    logger.writeLog("Simulation stopped.");
                    break;
                } else {
                    simulationRunning = false;
                    break;
                }
            } else if (command.equals("start") && !simulationInitialized) {
                System.out.println("Starting simulation...");
                logger.writeLog("Simulation starting...");
                TicketPool ticketPool = new TicketPool(maxCapacity, totalTickets);

                for (int i = 1; i <= totalTickets; i++) {
                    Ticket ticket = new Ticket("name", new BigDecimal("1000"));
                    try {
                        ticketPool.addTickets(ticket);
                    } catch (InterruptedException e) {
                        logger.writeLog("Failed to add tickets: " + e.getMessage());
                        throw new RuntimeException(e);
                    }
                }

                simulationInitialized = true;
                System.out.println("TicketPool initialized with total tickets: " + totalTickets);
                logger.writeLog("TicketPool initialized with total tickets: " + totalTickets);
                logWithTimestamp(ticketPoolLogger, "TicketPool initialized with total tickets: " + totalTickets);

                while (numVendor <= 0) {
                    System.out.println("Enter the no. of Vendors:");
                    if (scanner.hasNextInt()) {
                        numVendor = scanner.nextInt();
                        logger.writeLog("Vendor number: " + numVendor);
                        if (numVendor <= 0) {
                            System.out.println("Please enter a positive number for vendors.");
                            logger.writeLog("Invalid input for vendors: " + numVendor);
                        }
                    } else {
                        System.out.println("Invalid input. Please enter a positive integer.");
                        scanner.next();
                    }
                }

                while (numCustomer <= 0) {
                    System.out.println("Enter the no. of Customers:");
                    if (scanner.hasNextInt()) {
                        numCustomer = scanner.nextInt();
                        logger.writeLog("Customer number: " + numCustomer);
                        if (numCustomer <= 0) {
                            System.out.println("Please enter a positive number for customers.");
                            logger.writeLog("Invalid input for customers: " + numCustomer);
                        }
                    } else {
                        System.out.println("Invalid input. Please enter a positive integer.");
                        logger.writeLog("Non-integer input received.");
                        scanner.next();
                    }
                }

                vendor = new Vendor[numVendor];
                vendorThreads = new Thread[numVendor];
                for (int i = 0; i < vendor.length; i++) {
                    vendor[i] = new Vendor(ticketPool, ticketsReleaseRate);
                    vendorThreads[i] = new Thread(vendor[i], "Vendor " + (i + 1));
                    vendorThreads[i].start();
                    logger.writeLog("Vendor thread " + (i + 1) + " started.");
                }

                customer = new Customer[numCustomer];
                customerThreads = new Thread[numCustomer];
                for (int i = 0; i < customer.length; i++) {
                    customer[i] = new Customer(ticketPool, ticketsPerCustomer);
                    customerThreads[i] = new Thread(customer[i], "Customer " + (i + 1));
                    customerThreads[i].start();
                    logger.writeLog("Customer thread " + (i + 1) + " started.");
                }
            } else if (command.equals("start")) {
                System.out.println("Simulation is already running.");
            } else if (!simulationInitialized) {
                System.out.println("Invalid command. Please type 'start' to begin the simulation or 'stop' to stop it.");
                logger.writeLog("Invalid command received: " + command);
            }
        }
        logger.close();
    }

    private static void logWithTimestamp(Logger ticketPoolLogger, String record) {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        ticketPoolLogger.writeLog("[" + timestamp + "]: " + record);
    }
}

