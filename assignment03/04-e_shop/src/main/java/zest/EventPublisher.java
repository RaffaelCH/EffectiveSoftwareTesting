package zest;

import java.util.ArrayList;
import java.util.List;

public class EventPublisher {
    private List<EventListener> listeners = new ArrayList<>();

    public void subscribe(EventListener listener) {
        listeners.add(listener);
    }

    public void publishOrderToAllListeners(Order order) {
        if (order == null) {
            throw new NullPointerException("Order cannot be null");
        }
        for (EventListener listener : listeners) {
            listener.onOrderPlaced(order);
        }
    }

    public List<EventListener> getListeners() {
        return listeners;
    }
}
