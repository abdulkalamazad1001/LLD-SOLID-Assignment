public interface PricingRule {
    Money calculate(BookingRequest req);
}
