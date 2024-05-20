package zest;

public class EmailNotificationService implements EventListener {
    private Order lastProcessedOrder;

    @Override
    public void onOrderPlaced(Order order) {
        // Logic to send email about the order would go here...
        System.out.println("Email sent for order " + order.getOrderId());
        this.lastProcessedOrder = order;
    }

    public Order getLastProcessedOrder() {
        return lastProcessedOrder;
    }
}
