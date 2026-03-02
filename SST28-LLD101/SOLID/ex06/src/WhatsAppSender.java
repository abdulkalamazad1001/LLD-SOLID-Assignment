public class WhatsAppSender extends NotificationSender {
    public WhatsAppSender(AuditLog audit) {
        super(audit);
    }

    @Override
    protected void validate(Notification n) {
        if (n.phone == null || !n.phone.startsWith("+")) {
            throw new NotificationException("phone must start with + and country code");
        }
    }

    @Override
    protected void sendInternal(Notification n) {
        System.out.println("WA -> to=" + n.phone + " body=" + n.body);
        audit.add("wa sent");
    }
}
