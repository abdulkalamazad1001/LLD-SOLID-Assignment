import java.util.List;

public class Invoice {
    public final String id;
    public final List<OrderLine> lines;
    public final double subtotal;
    public final double taxPct;
    public final double taxAmount;
    public final double discountAmount;
    public final double total;

    public Invoice(String id, List<OrderLine> lines, double subtotal, double taxPct, double taxAmount,
            double discountAmount, double total) {
        this.id = id;
        this.lines = lines;
        this.subtotal = subtotal;
        this.taxPct = taxPct;
        this.taxAmount = taxAmount;
        this.discountAmount = discountAmount;
        this.total = total;
    }
}
