public class TaxCalculator {
    public double calculate(double subtotal, double taxPct) {
        return subtotal * (taxPct / 100.0);
    }
}
