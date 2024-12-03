
import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {
    private Queue<Ticket> ticketsQueue;
    private int maxCapacity;
    private int totalTickets;
    private static int nextTicketId=0;

    public TicketPool(int maxCapacity,int totalTickets) {
        this.maxCapacity = maxCapacity;
        this.ticketsQueue = new LinkedList<> ();
        this.totalTickets=totalTickets;
    }
    public synchronized void addTickets(Ticket ticket) throws InterruptedException {
        while ((ticketsQueue.size () >= maxCapacity) || ( ticketsQueue.size ()>= totalTickets)) {
            System.out.println ("Ticket pool id full. does not have enough capacity");
            wait ();
        }
        ticketsQueue.add (ticket);
        ticket.setTicketId (nextTicketId++);
        System.out.println (Thread.currentThread ().getName () + " added a ticket " + nextTicketId + ". Current pool size: " + ticketsQueue.size ());
        notifyAll ();
    }

    public synchronized Ticket buyTicket() throws InterruptedException{
        while (ticketsQueue.isEmpty()){
            System.out.println("No tickets available. Customer is waiting.");
            wait ();
        }
        Ticket ticket = ticketsQueue.poll ();
        System.out.println(Thread.currentThread ().getName () + " bought a ticket. Current pool size: " + ticketsQueue.size()+". Ticket: "+ticket);
        notifyAll ();
        return ticket;
    }



}
