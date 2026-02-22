public class InvoiceIdGenerator {
    private int sequence = 1000;

    public String nextId() {
        return "INV-" + (++sequence);
    }
}
