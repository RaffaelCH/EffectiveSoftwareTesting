package zest;

public class InventoryManager implements EventListener {
    private Order lastProcessedOrder;

    @Override
    public void onOrderPlaced(Order order) {
        // Logic to update inventory based on order would go here
        System.out.println("Inventory updated for order " + order.getOrderId());
        this.lastProcessedOrder = order;
    }

    public Order getLastProcessedOrder() {
        return lastProcessedOrder;
    }
}
