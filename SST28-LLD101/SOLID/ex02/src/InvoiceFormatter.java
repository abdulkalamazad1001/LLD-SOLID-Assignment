import java.util.Map;

public class InvoiceFormatter {
    public static String format(Invoice inv, Map<String, MenuItem> menu) {
        StringBuilder out = new StringBuilder();
        out.append("Invoice# ").append(inv.id).append("\n");

        for (OrderLine l : inv.lines) {
            MenuItem item = menu.get(l.itemId);
            double lineTotal = item.price * l.qty;
            out.append(String.format("- %s x%d = %.2f\n", item.name, l.qty, lineTotal));
        }

        out.append(String.format("Subtotal: %.2f\n", inv.subtotal));
        out.append(String.format("Tax(%.0f%%): %.2f\n", inv.taxPct, inv.taxAmount));
        out.append(String.format("Discount: -%.2f\n", inv.discountAmount));
        out.append(String.format("TOTAL: %.2f\n", inv.total));

        return out.toString();
    }

}
