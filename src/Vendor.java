import java.math.BigDecimal;

public class Vendor implements Runnable {
    private final TicketPool ticketPool;
    private final int ticketReleaseRate;
    private int timecount=2;
    private volatile boolean isRunning = true;

    public Vendor(TicketPool ticketPool, int releaseInterval) {
        this.ticketPool = ticketPool;
        this.ticketReleaseRate = releaseInterval;
    }

    public void stop() {
        isRunning = false; // Signal thread to stop
    }

    @Override
    public void run() {
        while (isRunning) { // Loop indefinitely
            try {
                Ticket ticket = new Ticket("name", new BigDecimal(1000));
                ticketPool.addTickets(ticket);
                Thread.sleep(ticketReleaseRate * 1000/timecount); // Simulate delay
            } catch (InterruptedException e) {
                // Suppress the interruption message
                return; // Exit on interruption
            }
        }
    }
}

