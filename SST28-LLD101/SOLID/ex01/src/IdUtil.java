public class IdUtil {
    public static String nextStudentId(int count) {
        return String.format("SST-2026-%04d", count + 1);
    }
}