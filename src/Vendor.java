public class Vendor implements Runnable {
    private int vendorId;
    private int ticketsPerRelease;
    private int releaseInterval;
    private TicketPool ticketPool;

    public Vendor(int vendorId, int ticketsPerRelease, int releaseInterval,TicketPool ticketpool) {
        this.vendorId = vendorId;
        this.ticketsPerRelease = ticketsPerRelease;
        this.releaseInterval = releaseInterval;
        this.ticketPool = ticketpool;
    }
    @Override
    public void run() {
        try{
            while (true) {
                ticketPool.addTickets (ticketsPerRelease);
                System.out.println("Vendor " + vendorId + " added " + ticketsPerRelease + " tickets.");
                Thread.sleep (releaseInterval);
            }
        }catch (InterruptedException e) {
            System.out.println("Vendor " + vendorId + " interrupted.");
            Thread.currentThread().interrupt();
        }
    }
}
