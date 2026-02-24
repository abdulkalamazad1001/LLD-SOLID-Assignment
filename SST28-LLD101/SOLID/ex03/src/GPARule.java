import java.util.Optional;

public class GPARule implements EligibilityRule {
    private final double minCgr;

    public GPARule(double minCgr) {
        this.minCgr = minCgr;
    }

    @Override
    public Optional<String> check(StudentProfile s) {
        if (s.cgr < minCgr) {
            return Optional.of("CGR below " + minCgr);
        }
        return Optional.empty();
    }
}
