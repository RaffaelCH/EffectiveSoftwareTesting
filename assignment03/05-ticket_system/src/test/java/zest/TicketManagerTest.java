package zest;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import org.junit.jupiter.params.ParameterizedTest;


public class TicketManagerTest {
    private TicketManager ticketManager;
    private TicketRepository ticketRepository;
    private NotificationService notificationService;
    private LogService logService;

    @BeforeEach
    public void setUp() {
        notificationService = Mockito.mock(NotificationService.class);
        logService = Mockito.mock(LogService.class);

        ticketRepository = ticket -> {};

        ticketManager = new TicketManager(notificationService, logService, ticketRepository);

    }

    @ParameterizedTest
    @CsvSource({
            "email@email.com,My computer does not work!",",",",My computer does not work!","email@email.com,","t@t.com,Test",
            "thisisaverylongemailthatcouldbeusedinwhateverscenario@thisisaverylongdomain.co.uk,This is a very long issue description that could be used in whatever scenario",
            "a123@x.com,Description"
    })
    public void testLogTicketCreationNormalPriority(String email, String issueDescription) {
        Ticket ticket = new Ticket(email, issueDescription, TicketPriority.NORMAL);

        ticketManager.createTicket(ticket);

        verify(logService, times(1)).logTicketCreation(ticket);
    }

    @ParameterizedTest
    @CsvSource({
            "email@email.com,My computer does not work!",",",",My computer does not work!","email@email.com,","t@t.com,Test",
            "thisisaverylongemailthatcouldbeusedinwhateverscenario@thisisaverylongdomain.co.uk,This is a very long issue description that could be used in whatever scenario",
            "a123@x.com,Description"
    })
    public void testLogTicketCreationUrgentPriority(String email, String issueDescription) {
        Ticket ticket = new Ticket(email, issueDescription, TicketPriority.URGENT);

        ticketManager.createTicket(ticket);

        verify(logService, times(1)).logTicketCreation(ticket);
    }

    @ParameterizedTest
    @CsvSource({
            "email@email.com,My computer does not work!",",",",My computer does not work!","email@email.com,","t@t.com,Test",
            "thisisaverylongemailthatcouldbeusedinwhateverscenario@thisisaverylongdomain.co.uk,This is a very long issue description that could be used in whatever scenario",
            "a123@x.com,Description"
    })
    public void testNotifyCustomerNormalPriority(String email, String issueDescription) {
        Ticket ticket = new Ticket(email, issueDescription, TicketPriority.NORMAL);

        ticketManager.createTicket(ticket);

        verify(notificationService, times(1)).notifyCustomer(ticket.getCustomerEmail(), "Thank you for your request. Your support ticket has been created and will be processed shortly.");
    }

    @ParameterizedTest
    @CsvSource({
            "email@email.com,My computer does not work!",",",",My computer does not work!","email@email.com,","t@t.com,Test",
            "thisisaverylongemailthatcouldbeusedinwhateverscenario@thisisaverylongdomain.co.uk,This is a very long issue description that could be used in whatever scenario",
            "a123@x.com,Description"
    })
    public void testNotifyCustomerUrgentPriority(String email, String issueDescription) {
        Ticket ticket = new Ticket(email, issueDescription, TicketPriority.URGENT);

        ticketManager.createTicket(ticket);

        verify(notificationService, times(1)).notifyCustomer(ticket.getCustomerEmail(), "Thank you for your request. Your support ticket has been created and will be processed shortly.");
    }

}