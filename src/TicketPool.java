import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {
    private Queue<Ticket> ticketsQueue;
    private int maxCapacity;

    public TicketPool(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.ticketsQueue = new LinkedList<Ticket> ();
    }
    public void addTickets(Ticket ticket) throws InterruptedException {
        while (ticketsQueue.size() >= maxCapacity) {
            System.out.println ("Ticket pool is full.Vendor can't add tickets.");
            wait ();
        }
        ticketsQueue.add (Ticket ticket);
        notifyAll ();
    }
    public synchronized void addTickets(int ticketCount) throws InterruptedException {
        for (int i = 0; i < ticketCount; i++) {
            addTicket(new Ticket()); // Add tickets individually
        }
    }

    public synchronized Ticket removeTicket(){
        while (ticketsQueue.isEmpty()){
            wait ();
        }
        Ticket ticket = ticketsQueue.poll ();
        notifyAll ();
        return ticket;
    }

}
