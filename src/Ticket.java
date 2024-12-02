import java.math.BigDecimal;

public class Ticket {
    private String Event;
    private int ticketId;
    private BigDecimal ticketPrice;

    public Ticket(int ticketId) {
        this.ticketId = ticketId;
    }

    public Ticket(String Event, BigDecimal ticketPrice) {
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
    public  BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId+1;
    }

    @Override
    public String toString() {
        return "Ticket [Event=" + Event + ", ticketId=" + ticketId + ", ticketPrice=" + ticketPrice + "]";
    }
}