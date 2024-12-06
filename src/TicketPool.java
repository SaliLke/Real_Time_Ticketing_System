import java.util.LinkedList;
import java.util.Queue;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TicketPool {
    private Queue<Ticket> ticketsQueue;
    private int maxCapacity;
    private int totalTickets;
    private static int nextTicketId = 0;
    private static String LogFile = "ticketpool.log";

    public TicketPool(int maxCapacity, int totalTickets) {
        this.maxCapacity = maxCapacity;
        this.ticketsQueue = new LinkedList<> ();
        this.totalTickets = totalTickets;
    }

    private void log(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LogFile, true))) {
            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            writer.write("[" + timestamp + "] " + message);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Could not write to file: " + e.getMessage());
        }
    }

    public synchronized void addTickets(Ticket ticket) throws InterruptedException {
        while (ticketsQueue.size () >= maxCapacity)  {
            System.out.println ("Ticket pool is full. Vendor is waiting...");
            log("Ticket pool is full. Vendor is waiting...");
            wait ();
        }
        ticketsQueue.add (ticket);
        ticket.setTicketId (nextTicketId++);
        if (!Thread.currentThread ().getName ().equals ("main")) {
            System.out.println (Thread.currentThread ().getName () + " added a ticket [Id: " + nextTicketId + "]. Current pool size: " + ticketsQueue.size ());
            log(Thread.currentThread().getName() + " added a ticket [Id: " + nextTicketId + "]. Current pool size: " + ticketsQueue.size());
            notifyAll ();
        }
        notifyAll ();
    }

    public synchronized Ticket buyTicket() throws InterruptedException {
        while (ticketsQueue.isEmpty ()) {
            System.out.println ("No tickets available. Customer is waiting...");
            log("No tickets available. Customer is waiting...");
            wait ();
        }
        Ticket ticket = ticketsQueue.poll ();
        System.out.println (Thread.currentThread ().getName () + " bought a ticket. Current pool size: " + ticketsQueue.size () + ". Ticket Details: [" + ticket+"]");
        log(Thread.currentThread().getName() + " bought a ticket. Current pool size: " + ticketsQueue.size() + ". Ticket Details: [" + ticket + "]");
        notifyAll ();
        return ticket;
    }
}