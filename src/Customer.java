public class Customer implements Runnable{
    private int customerId;
    private int CustomerRetrievalRate;
    private TicketPool ticketPool;
    private int quantity;

    public Customer( TicketPool ticketPool,int retrievalInterval,int quantity) {
//        this.customerId = customerId;
        CustomerRetrievalRate = retrievalInterval;
        this.ticketPool = ticketPool;
        this.quantity = quantity;
    }

    @Override
    public void run(){
        for (int i=0;i<quantity;i++) {
            try {
                Ticket ticket=ticketPool.buyTicket ();
                System.out.println (Thread.currentThread ().getName () + " purchased : " + ticket);
            } catch (InterruptedException e) {
                throw new RuntimeException (e);
            }
        }
//        try{
//            while(true){
//                Ticket ticket = ticketPool.buyTicket ();
//                System.out.println (customerId + " purchased : " + ticket);
//                Thread.sleep (CustomerRetrievalRate);
//            }
//        }catch (InterruptedException e){
//            System.out.println (customerId + " interrupted");
//            Thread.currentThread().interrupt();
//        }
    }
}