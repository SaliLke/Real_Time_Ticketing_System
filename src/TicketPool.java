import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {
    private Queue<Ticket> ticketsQueue;
    private int maxCapacity;

    public TicketPool(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.ticketsQueue = new LinkedList<> ();
    }
    public synchronized void addTickets(Ticket ticket) throws InterruptedException {
        while (ticketsQueue.size() >= maxCapacity) {
            System.out.println ("Ticket pool is full.Vendor can't add tickets.");
            wait ();
        }
        ticketsQueue.add (ticket);
        System.out.println("Ticket added. Current pool size: " + ticketsQueue.size());
        notifyAll ();
    }
    public synchronized void addTickets(int ticketCount) throws InterruptedException {
        for (int i = 0; i < ticketCount; i++) {
            addTickets (new Ticket(i));
        }
    }

    public synchronized Ticket removeTicket() throws InterruptedException{
        while (ticketsQueue.isEmpty()){
            System.out.println("No tickets available. Customer is waiting.");
            wait ();
        }
        Ticket ticket = ticketsQueue.poll ();
        System.out.println("Ticket removed (ID: " + ticket.getTicketId() + "). Current pool size: " + ticketsQueue.size());
        notifyAll ();
        return ticket;
    }

}
