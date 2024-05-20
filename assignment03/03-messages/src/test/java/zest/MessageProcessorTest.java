package zest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MessageProcessorTest {

    @Mock
    private MessageService mockMessageService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test A: Number of invocations
    @Test
    public void testProcessMessages_CallsSendMessageForEveryMessage() {
        List<Message> messages = createMessages(2);
        MessageProcessor processor = new MessageProcessor(mockMessageService);

        processor.processMessages(messages);

        verify(mockMessageService, times(2)).sendMessage(anyString(), anyString());
    }

    // Test B: Content of invocations using ArgumentCaptor
    @Test
    public void testProcessMessages_SendsCorrectMessageContent() {
        List<Message> messages = createMessages(2);
        MessageProcessor processor = new MessageProcessor(mockMessageService);
        ArgumentCaptor<String> receiverCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> contentCaptor = ArgumentCaptor.forClass(String.class);

        processor.processMessages(messages);

        verify(mockMessageService, times(2)).sendMessage(receiverCaptor.capture(), contentCaptor.capture());

        List<String> capturedReceivers = receiverCaptor.getAllValues();
        List<String> capturedContents = contentCaptor.getAllValues();

        for (int i = 0; i < messages.size(); i++) {
            Message message = messages.get(i);
            assertEquals(message.getReceiver(), capturedReceivers.get(i));
            assertEquals(message.getContent(), capturedContents.get(i));
        }
    }

    // Test C: Content of invocations - Increasing observability
    @Test
    public void testProcessMessages_IncreasesObservability() {
        List<Message> messages = createMessages(2);
        MessageProcessor processor = new MessageProcessor(mockMessageService);

        processor.processMessages(messages);

        for (Message message : messages) {
            verify(mockMessageService).sendMessage(eq(message.getReceiver()), eq(message.getContent()));
        }
    }

    // Helper methods
    private List<Message> createMessages(int numMessages) {
        List<Message> messages = new ArrayList<>();
        for (int i = 0; i < numMessages; i++) {
            messages.add(new Message("sender" + i, "receiver" + i, "message content " + i));
        }
        return messages;
    }
}
