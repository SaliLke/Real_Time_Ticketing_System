import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class TicketPool {
    private BlockingDeque<Ticket> ticketsQueue;
    private int maxCapacity;

    public TicketPool(int maxCapacity) {
        this.ticketsQueue = new LinkedBlockingDeque<Ticket> (maxCapacity);
        this.maxCapacity = maxCapacity;
    }

//    public void addTickets(int tickets){
//        while (ticketCount + tickets > maxCapacity) {
//            try{
//                System.out.println("Ticket pool is full.Vendor can't add tickets.");
//                wait ();
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }
//        }
//        ticketCount++;
    }
}
