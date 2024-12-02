//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
//        TicketingSystemCLI ticketingSystemCli = new TicketingSystemCLI();
//        ticketingSystemCli.main(args);


        TicketPool ticketPool = new TicketPool(10);


        Vendor[] vendor = new Vendor[10]; // Adds 5 tickets every 1000ms
//        Thread vendorThread = new Thread(vendor);
        for (int i = 0; i < vendor.length; i++) {
            vendor[i]=new Vendor (ticketPool,20,5);
            Thread vendorThread = new Thread(vendor[i],"vendor "+i);
            vendorThread.start();
        }


        Customer[] customer = new Customer[10];
        for (int i = 0; i < customer.length; i++) {
            customer[i]=new Customer (ticketPool,5,5);
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
//        vendorThread.interrupt();
//        customerThread.interrupt();
    }
}