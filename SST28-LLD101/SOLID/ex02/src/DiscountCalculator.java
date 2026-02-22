public class DiscountCalculator {
    public double calculate(double subtotal, int distinctLines, String customerType) {
        return DiscountRules.discountAmount(customerType, subtotal, distinctLines);
    }
}
