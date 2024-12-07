public class Customer implements Runnable {
    private final TicketPool ticketPool;
    private final int customerRetrievalRate;
    private int timecount=2;
    private volatile boolean isRunning = true;

    public Customer(TicketPool ticketPool, int retrievalInterval) {
        this.ticketPool = ticketPool;
        this.customerRetrievalRate = retrievalInterval;
    }

    public void stop() {
        isRunning = false; // Signal thread to stop
    }

    @Override
    public void run() {
        while (isRunning) { // Loop indefinitely
            try {
                Ticket ticket = ticketPool.buyTicket();
                Thread.sleep(customerRetrievalRate * 1000); // Simulate delay
            } catch (InterruptedException e) {
                // Suppress the interruption message
                return; // Exit on interruption
            }
        }
    }
}
