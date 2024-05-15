package zest;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class TicketManagerTest {

    // use mocks to test the TicketManager class (createTicket method)
    @Test
    public void testCreateTicket() {
        NotificationService nS = Mockito.mock(NotificationService.class);
        LogService lS = Mockito.mock(LogService.class);
        TicketRepository tR = Mockito.mock(TicketRepository.class);

        // create a TicketManager object with the mocks
        TicketManager tm = new TicketManager(nS, lS, tR);

        // create a ticket
        TicketPriority prio = TicketPriority.NORMAL;
        Ticket ticket = new Ticket("email", "description", prio);

        // call the createTicket method
        tm.createTicket(ticket);

        // verify that the logTicketCreation method was called
        Mockito.verify(lS).logTicketCreation(ticket);

        // verify that the notifyCustomer method was called
        Mockito.verify(nS).notifyCustomer("email", "Thank you for your request. Your support ticket has been created and will be processed shortly.");

        // verify that the save method was called
        Mockito.verify(tR).save(ticket);

    }


}
