public class EmailSender extends NotificationSender {
    public EmailSender(AuditLog audit) {
        super(audit);
    }

    @Override
    protected void validate(Notification n) {
        if (n.email == null || !n.email.contains("@")) {
            throw new NotificationException("Invalid email address");
        }
    }

    @Override
    protected void sendInternal(Notification n) {
        System.out.println("EMAIL -> to=" + n.email + " subject=" + n.subject + " body=" + n.body);
        audit.add("email sent");
    }
}
