package zest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@ExtendWith(MockitoExtension.class)
public class TicketManagerTest {
    private TicketManager ticketManager;

    @Mock
    private TicketRepository ticketRepository;
    @Mock
    private NotificationService notificationService;
    @Mock
    private LogService logService;

    @BeforeEach
    public void setUp() {
        ticketManager = new TicketManager(notificationService, logService, ticketRepository);
    }

    @ParameterizedTest
    @CsvSource({
            "email@email.com, My computer does not work!", // possible input
            "t@t.de,T", // very short input
            "email@email.com,", // input with normal email address and empty description
            // very long email address and description
            "thisisaverylongemailthatcouldbeusedinwhateverscenario@thisisaverylongdomain.co.uk, This is a very long issue description that could be used in whatever scenario",
            // email address with numbers and description with numbers and special characters
            "a123@x.com, Description#123."
    })
    public void testCreateTicketAndServices(String email, String issueDescription) {
        Ticket ticket = new Ticket(email, issueDescription, TicketPriority.NORMAL);
        doNothing().when(notificationService).notifyCustomer(anyString(), anyString());
        doNothing().when(logService).logTicketCreation(any(Ticket.class));

        ticketManager.createTicket(ticket);

        verify(logService, times(1)).logTicketCreation(ticket);
        verify(notificationService, times(1)).notifyCustomer(eq(email), anyString());
    }

    @Test
    public void testFailureInNotificationService() {
        Ticket ticket = new Ticket("test@example.com", "Urgent issue", TicketPriority.URGENT);
        doThrow(new RuntimeException("Failed to send notification")).when(notificationService).notifyCustomer(eq(ticket.getCustomerEmail()), anyString());

        doNothing().when(logService).logTicketCreation(any(Ticket.class));

        Exception exception = assertThrows(RuntimeException.class, () -> ticketManager.createTicket(ticket));
        assertEquals("Failed to send notification", exception.getMessage());

        verify(logService, times(1)).logTicketCreation(ticket);
        verify(notificationService, times(1)).notifyCustomer(eq(ticket.getCustomerEmail()), anyString());
    }


    @Test
    public void testFailureInLogService() {
        Ticket ticket = new Ticket("test@example.com", "Minor issue", TicketPriority.NORMAL);
        doThrow(new RuntimeException("Log service failure")).when(logService).logTicketCreation(any(Ticket.class));

        Exception exception = assertThrows(RuntimeException.class, () -> ticketManager.createTicket(ticket));
        assertEquals("Log service failure", exception.getMessage());

        verify(notificationService, never()).notifyCustomer(eq(ticket.getCustomerEmail()), anyString());
    }
}
