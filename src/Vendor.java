import java.math.BigDecimal;

public class Vendor implements Runnable {
    private int vendorId;
    private int totalTickets;
    private int ticketReleaseRate;
    private TicketPool ticketPool;

    public Vendor(TicketPool ticketpool, int ticketsPerRelease, int releaseInterval) {
//        this.vendorId = vendorId;
        totalTickets = ticketsPerRelease;
        ticketReleaseRate = releaseInterval;
        this.ticketPool = ticketpool;
    }
    @Override
    public void run() {
        for (int i = 0; i < totalTickets; i++) {
            Ticket ticket=new Ticket ("name",new BigDecimal (1000));
            try {
                ticketPool.addTickets (ticket);
                Thread.sleep (ticketReleaseRate * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException (e.getMessage ());
            }
        }
    }
}