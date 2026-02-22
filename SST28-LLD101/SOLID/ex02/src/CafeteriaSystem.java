import java.util.*;

//building the whole cafeteria system
public class CafeteriaSystem {
    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final InvoiceStore store = new FileStore(); // invoice store
    private final InvoiceIdGenerator idgen = new InvoiceIdGenerator();
    private final SubtotalCalculator subtotalCalc = new SubtotalCalculator();
    private final TaxCalculator taxCalc = new TaxCalculator();
    private final DiscountCalculator discountCalc = new DiscountCalculator();
    private final TotalCalculator totalCalc = new TotalCalculator();

    public void addToMenu(MenuItem i) {
        menu.put(i.id, i);
    }

    public void checkout(String customerType, List<OrderLine> lines) {
        String invId = idgen.nextId();
        double subtotal = subtotalCalc.calculate(lines, menu);
        double taxPct = TaxRules.taxPercent(customerType);
        double tax = taxCalc.calculate(subtotal, taxPct);
        double discount = discountCalc.calculate(subtotal, lines.size(), customerType);
        double total = totalCalc.calculate(subtotal, tax, discount);

        Invoice invoice = new Invoice(invId, lines, subtotal, taxPct, tax, discount, total);
        String printable = InvoiceFormatter.format(invoice, menu);

        System.out.print(printable);
        store.save(invId, printable);
        System.out.println("Saved invoice: " + invId + " (lines=" + store.countLines(invId) + ")");
    }
}
