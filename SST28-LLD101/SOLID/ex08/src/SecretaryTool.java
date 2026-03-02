public class SecretaryTool implements SecretaryAdmin {
    private final MinutesBook book;

    public SecretaryTool(MinutesBook book) {
        this.book = book;
    }

    @Override
    public void addMinutes(String text) {
        book.add(text);
    }
}
