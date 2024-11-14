//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int maxCapacity = 10;
        TicketPool ticketPool = new TicketPool(maxCapacity);

        // Create and start a vendor thread
        Vendor vendor = new Vendor(1, 5, 1000,ticketPool); // Adds 5 tickets every 1000ms
        Thread vendorThread = new Thread(vendor);

        // Create and start a customer thread
        Customer customer = new Customer(1, 2000,ticketPool); // Tries to retrieve a ticket every 2000ms
        Thread customerThread = new Thread(customer);

        vendorThread.start();
        customerThread.start();

        // Let it run for a bit and observe output
        try {
            Thread.sleep(10000); // Run the test for 10 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Interrupt the threads to stop the simulation
        vendorThread.interrupt();
        customerThread.interrupt();
    }
}