public abstract class NotificationSender {
    protected final AuditLog audit;

    protected NotificationSender(AuditLog audit) {
        this.audit = audit;
    }

    public final void send(Notification n) {
        validate(n);
        sendInternal(n);
    }

    protected abstract void sendInternal(Notification n);

    protected void validate(Notification n) {
        if (n == null) {
            throw new NotificationException("Notification cannot be null");
        }
    }
}
