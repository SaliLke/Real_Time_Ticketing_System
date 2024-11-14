public class Customer implements Runnable{
    private int customerId;
    private int retrievalInterval;
    private TicketPool ticketPool;

    public Customer(int customerId, int retrievalInterval, TicketPool ticketPool) {
        this.customerId = customerId;
        this.retrievalInterval = retrievalInterval;
        this.ticketPool = ticketPool;
    }

    @Override
    public void run(){
        try{
            while(true){
                Ticket ticket = ticketPool.removeTicket ();
                System.out.println (customerId + " purchased : " + ticket);
                Thread.sleep (retrievalInterval);
            }
        }catch (InterruptedException e){
            System.out.println (customerId + " interrupted");
            Thread.currentThread().interrupt();
        }
    }
}
