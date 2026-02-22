import java.util.*;

public class HostelFeeCalculator {
    private final List<PricingRule> rules;

    public HostelFeeCalculator(List<PricingRule> rules) {
        this.rules = rules;
    }

    public Money calculateMonthly(BookingRequest req) {
        Money total = new Money(0);
        for (PricingRule rule : rules) {
            total = total.plus(rule.calculate(req));
        }
        return total;
    }
}
