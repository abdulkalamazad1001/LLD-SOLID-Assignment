public class SmsSender extends NotificationSender {
    public SmsSender(AuditLog audit) {
        super(audit);
    }

    @Override
    protected void validate(Notification n) {
        if (n.phone == null || n.phone.length() < 10) {
            throw new NotificationException("Invalid phone number for SMS");
        }
    }

    @Override
    protected void sendInternal(Notification n) {
        System.out.println("SMS -> to=" + n.phone + " body=" + n.body);
        audit.add("sms sent");
    }
}
