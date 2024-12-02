//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Configuration configuration;
        int numVendor=10;
        int numCustomer=10;
        int ticketsPerVendor=20;
        int ticketsReleaseRate=5;
        int ticketsPerCustomer=5;
        int retrievalRate=5;
        int maxCapacity=10;

//        configuration = new Configuration ();
//        configuration.main(args);
//        ticketsPerVendor= configuration.getTotalTickets ();
//        retrievalRate=configuration.getCustomerRetrievalRate ();
//        maxCapacity=configuration.getMaxTicketCapacity ();


        TicketPool ticketPool = new TicketPool(maxCapacity);

        Vendor[] vendor = new Vendor[numVendor]; // Adds 5 tickets every 1000ms
//        Thread vendorThread = new Thread(vendor);
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

        // Let it run for a bit and observe output
//        try {
//            Thread.sleep(10000); // Run the test for 10 seconds
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        // Interrupt the threads to stop the simulation

    }
}