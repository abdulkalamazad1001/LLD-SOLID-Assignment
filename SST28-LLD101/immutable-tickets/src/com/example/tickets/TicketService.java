package com.example.tickets;

/**
 * Service layer that creates tickets.
 * Now refactored to stop mutating existing instances.
 */
public class TicketService {

    public IncidentTicket createTicket(String id, String reporterEmail, String title) {
        // Now using Builder and returning a fully formed immutable ticket.
        // Validation is centralized in IncidentTicket.Builder.build()
        return IncidentTicket.builder()
                .id(id)
                .reporterEmail(reporterEmail)
                .title(title)
                .priority("MEDIUM")
                .source("CLI")
                .customerVisible(false)
                .addTag("NEW")
                .build();
    }

    public IncidentTicket escalateToCritical(IncidentTicket t) {
        // Return a NEW instance instead of mutating the old one.
        return t.toBuilder()
                .priority("CRITICAL")
                .addTag("ESCALATED")
                .build();
    }

    public IncidentTicket assign(IncidentTicket t, String assigneeEmail) {
        // Return a NEW instance.
        return t.toBuilder()
                .assigneeEmail(assigneeEmail)
                .build();
    }
}
