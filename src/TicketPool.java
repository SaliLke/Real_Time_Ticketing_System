
import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {
    private Queue<Ticket> ticketsQueue;
    private int maxCapacity;
    private static int nextTicketId=0;

    public TicketPool(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.ticketsQueue = new LinkedList<> ();
    }
    public synchronized void addTickets(Ticket ticket) throws InterruptedException {
        while (ticketsQueue.size() >= maxCapacity) {
            System.out.println ("Ticket pool is does not have enough capacity");
            wait ();
//            try {
//                wait ();
//            }catch(InterruptedException e){
//                e.printStackTrace ();
//                throw new RuntimeException (e.getMessage());
//            }
        }
        ticketsQueue.add (ticket);
        ticket.setTicketId (nextTicketId++);
        System.out.println(Thread.currentThread ().getName ()+" added a ticket "+ nextTicketId +". Current pool size: " + ticketsQueue.size());
        notifyAll ();
    }
//    public synchronized void addTickets(int ticketCount) throws InterruptedException {
//        while (ticketsQueue.size () != maxCapacity) {
//            for (int i = 0; i < ticketCount; i++) {
//                addTickets (new Ticket (i));
//            }
//        }
//    }
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

    //consider the exception handling

}
