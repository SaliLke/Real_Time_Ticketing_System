import java.math.BigDecimal;

public class Vendor implements Runnable {
    private String vendorId;
    private int totalTickets;
    private int ticketReleaseRate;
    private TicketPool ticketPool;
    public Vendor() {}
    public Vendor(TicketPool ticketpool, int totalTickets, int releaseInterval) {
//        this.vendorId = vendorId;
        this.totalTickets = totalTickets;
        ticketReleaseRate = releaseInterval;
        this.ticketPool = ticketpool;
    }
    @Override
    public void run() {
        vendorId=Thread.currentThread ().getName ();
        for (int i = 0; i < totalTickets; i++) {
            Ticket ticket=new Ticket ("name",new BigDecimal (1000));
            try {
                ticketPool.addTickets (ticket);
//                System.out.println( vendorId+ " adding tickets...");
                Thread.sleep (ticketReleaseRate * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException (e.getMessage ());
            }
        }
    }
}