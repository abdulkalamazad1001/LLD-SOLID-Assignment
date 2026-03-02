import com.example.tickets.IncidentTicket;
import com.example.tickets.TicketService;

import java.util.List;

/**
 * Demonstration of immutability and the Builder pattern.
 */
public class TryIt {

    public static void main(String[] args) {
        TicketService service = new TicketService();

        // 1. Create a ticket
        IncidentTicket t1 = service.createTicket("TCK-1001", "reporter@example.com", "Payment failing on checkout");
        System.out.println("Created: " + t1);

        // 2. "Update" via service (returns NEW instance)
        IncidentTicket t2 = service.assign(t1, "agent@example.com");
        IncidentTicket t3 = service.escalateToCritical(t2);

        System.out.println("\nOriginal after service calls (unchanged): " + t1);
        System.out.println("Final version (new object): " + t3);

        // 3. Demonstrate tag immutability (Defensive copying)
        try {
            List<String> externalTags = t3.getTags();
            System.out.println("\nAttempting to mutate tags list from outside...");
            externalTags.add("HACKER_ATTEMPT");
        } catch (UnsupportedOperationException e) {
            System.out.println("Caught expected exception: Cannot mutate unmodifiable list!");
        }

        // 4. Demonstrate Validation in Builder
        System.out.println("\nTesting validation (Expect failure for bad email)...");
        try {
            IncidentTicket.builder()
                    .id("VALID-123")
                    .reporterEmail("not-an-email")
                    .title("Valid Title")
                    .build();
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected validation error: " + e.getMessage());
        }

        System.out.println("\nTesting validation (Expect failure for long title)...");
        try {
            IncidentTicket.builder()
                    .id("VALID-123")
                    .reporterEmail("valid@email.com")
                    .title("A".repeat(81))
                    .build();
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected validation error: " + e.getMessage());
        }
    }
}
