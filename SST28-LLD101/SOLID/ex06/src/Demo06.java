public class Demo06 {
    public static void main(String[] args) {
        System.out.println("=== Notification Demo ===");
        AuditLog audit = new AuditLog();

        Notification n = new Notification("Welcome", "Hello and welcome to SST!", "riya@sst.edu", "9876543210");

        NotificationSender[] senders = {
                new EmailSender(audit),
                new SmsSender(audit),
                new WhatsAppSender(audit)
        };

        for (NotificationSender sender : senders) {
            try {
                sender.send(n);
            } catch (NotificationException ex) {
                String label = sender.getClass().getSimpleName().replace("Sender", "").equals("WhatsApp") ? "WA"
                        : sender.getClass().getSimpleName().replace("Sender", "").toUpperCase();
                System.out.println(label.toUpperCase() + " ERROR: " + ex.getMessage());
                audit.add(sender.getClass().getSimpleName().toLowerCase().replace("sender", "") + " failed");
            }
        }

        System.out.println("AUDIT entries=" + audit.size());
    }
}
