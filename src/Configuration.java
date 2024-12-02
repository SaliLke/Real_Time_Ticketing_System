import java.io.Serializable;
import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class Configuration implements Serializable {
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;

    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }

    @Override
    public String toString(){
        return "[total Tickets:"+totalTickets+", Ticket Release rate:"+ticketReleaseRate+", customer retrieval rate:"+customerRetrievalRate+", max capacity:"+maxTicketCapacity+"]";
    }

    public void saveToFile(String filepath) {
        try(FileWriter writer=new FileWriter (filepath)){
            Gson gson=new Gson ();
            gson.toJson (this, writer);
            System.out.println("Saved successfully.");
        }catch (IOException e) {
            System.out.println("Failed to save: " + e.getMessage());
        }
    }
    public Configuration loadFromFile(String filepath) {
        try(FileReader reader=new FileReader (filepath)){
            Gson gson=new Gson ();
            return gson.fromJson (reader, Configuration.class);
        }catch (IOException e) {
            System.out.println("Failed to load: " + e.getMessage());
            System.out.println("Failed to load configuration, using default settings.");
            return new Configuration();
        }
    }

//    public static void main(String[] args){
//
//    }
}