import java.util.*;

public class AddOnPricingRule implements PricingRule {
    private final Map<AddOn, Double> prices = Map.of(
            AddOn.MESS, 1000.0,
            AddOn.LAUNDRY, 500.0,
            AddOn.GYM, 300.0);

    @Override
    public Money calculate(BookingRequest req) {
        double total = 0;
        for (AddOn addon : req.addOns) {
            total += prices.getOrDefault(addon, 0.0);
        }
        return new Money(total);
    }
}
