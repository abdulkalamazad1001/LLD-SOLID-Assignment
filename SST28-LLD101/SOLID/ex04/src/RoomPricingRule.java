import java.util.*;

public class RoomPricingRule implements PricingRule {
    private final Map<Integer, Double> prices = Map.of(
            LegacyRoomTypes.SINGLE, 14000.0,
            LegacyRoomTypes.DOUBLE, 15000.0,
            LegacyRoomTypes.TRIPLE, 12000.0);

    @Override
    public Money calculate(BookingRequest req) {
        double price = prices.getOrDefault(req.roomType, 16000.0);
        return new Money(price);
    }
}
