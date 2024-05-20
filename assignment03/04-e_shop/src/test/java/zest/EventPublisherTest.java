package zest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class EventPublisherTest {

    @Mock
    private EmailNotificationService mockEmailNotificationService;

    @Mock
    private InventoryManager mockInventoryManager;

    private EventPublisher eventPublisher;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        eventPublisher = new EventPublisher();
        eventPublisher.subscribe(mockEmailNotificationService);
        eventPublisher.subscribe(mockInventoryManager);
    }

    // Test A: Number of invocations
    @Test
    public void testPublishOrderToAllListeners_CallsOnOrderPlacedForEveryListener() {
        Order order = new Order("order1", 100.0);
        
        eventPublisher.publishOrderToAllListeners(order);

        verify(mockEmailNotificationService, times(1)).onOrderPlaced(order);
        verify(mockInventoryManager, times(1)).onOrderPlaced(order);
    }

    // Test B: Content of invocations using ArgumentCaptor
    @Test
    public void testPublishOrderToAllListeners_SendsCorrectOrderContent() {
        Order order = new Order("order1", 100.0);
        ArgumentCaptor<Order> orderCaptor = ArgumentCaptor.forClass(Order.class);

        eventPublisher.publishOrderToAllListeners(order);

        verify(mockEmailNotificationService).onOrderPlaced(orderCaptor.capture());
        verify(mockInventoryManager).onOrderPlaced(orderCaptor.capture());

        List<Order> capturedOrders = orderCaptor.getAllValues();
        for (Order capturedOrder : capturedOrders) {
            assertEquals("order1", capturedOrder.getOrderId());
            assertEquals(100.0, capturedOrder.getAmount());
        }
    }

    // Test C: Content of invocations - Increasing observability
    @Test
    public void testPublishOrderToAllListeners_IncreasingObservability() {
        EmailNotificationService emailNotificationService = new EmailNotificationService();
        InventoryManager inventoryManager = new InventoryManager();
        EventPublisher publisher = new EventPublisher();
        publisher.subscribe(emailNotificationService);
        publisher.subscribe(inventoryManager);

        Order order = new Order("order2", 200.0);
        publisher.publishOrderToAllListeners(order);

        assertEquals(order, emailNotificationService.getLastProcessedOrder());
        assertEquals(order, inventoryManager.getLastProcessedOrder());
    }


    // Test Empty listener list
    @Test
    public void testPublishOrderToAllListeners_EmptyListenerList() {
        eventPublisher.publishOrderToAllListeners(new Order("order3", 300.0)); // No subscribers
        // No verification needed as no listeners to invoke methods on
    }

    // Test Null order
    @Test
    public void testPublishOrderToAllListeners_NullOrder() {
        assertThrows(NullPointerException.class, () -> eventPublisher.publishOrderToAllListeners(null));
    }

    // Test Exception handling (if applicable) - Modify if there's specific exception handling in EventPublisher
    @Test
    public void testPublishOrderToAllListeners_ExceptionHandling() {
        // Modify based on the specific exception handling logic in EventPublisher
        doThrow(new RuntimeException("Simulated exception")).when(mockEmailNotificationService).onOrderPlaced(any(Order.class));

        eventPublisher.subscribe(mockEmailNotificationService);
        eventPublisher.subscribe(mockInventoryManager);

        assertThrows(RuntimeException.class, () -> eventPublisher.publishOrderToAllListeners(new Order("order4", 400.0)));

        // Verify that onOrderPlaced is still called on the non-failing listener (mockInventoryManager)
        // verify(mockInventoryManager, times(1)).onOrderPlaced(any(Order.class));
    }

    
}
