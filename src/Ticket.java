public class Ticket {
    private String Event;
    private int ticketId;
    private double ticketPrice;

    public Ticket(int ticketId) {
        this.ticketId = ticketId;
    }

    public Ticket(String Event, int ticketId, double ticketPrice) {
        this.Event = Event;
        this.ticketId = ticketId;
        this.ticketPrice = ticketPrice;
    }


    public String getEvent() {
        return Event;
    }
    public int getTicketId() {
        return ticketId;
    }
    public double getTicketPrice() {
        return ticketPrice;
    }
    @Override
    public String toString() {
        return "Ticket [Event=" + Event + ", ticketId=" + ticketId + ", ticketPrice=" + ticketPrice + "]";
    }
}
