import java.util.*;

public class Demo04 {
    public static void main(String[] args) {
        System.out.println("=== Hostel Fee Calculator ===");

        BookingRequest req = new BookingRequest(LegacyRoomTypes.DOUBLE, List.of(AddOn.LAUNDRY, AddOn.MESS));

        List<PricingRule> rules = List.of(
                new RoomPricingRule(),
                new AddOnPricingRule());

        HostelFeeCalculator calculator = new HostelFeeCalculator(rules);
        Money monthly = calculator.calculateMonthly(req);
        Money deposit = new Money(5000.00);

        ReceiptPrinter.print(req, monthly, deposit);

        FakeBookingRepo repo = new FakeBookingRepo();
        String bookingId = "H-" + (7000 + new Random(1).nextInt(1000));
        repo.save(bookingId, req, monthly, deposit);
    }
}
