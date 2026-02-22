import java.util.List;
import java.util.Map;

public class SubtotalCalculator {
    public double calculate(List<OrderLine> lines, Map<String, MenuItem> menu) {
        double subtotal = 0;
        for (OrderLine line : lines) {
            MenuItem item = menu.get(line.itemId);
            subtotal += item.price * line.qty;
        }
        return subtotal;
    }
}
