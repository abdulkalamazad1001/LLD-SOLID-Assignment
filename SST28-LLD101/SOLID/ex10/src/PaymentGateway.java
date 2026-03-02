public class PaymentGateway implements IPaymentGateway {
    public String charge(String studentId, double amount) {
        return "TXN-9001";
    }
}
