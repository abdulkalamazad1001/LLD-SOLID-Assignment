import java.nio.charset.StandardCharsets;

public class PdfExporter extends Exporter {
    @Override
    public ExportResult export(ExportRequest req) {
        if (req.body != null && req.body.length() > 20) {
            throw new ExportException("PDF cannot handle content > 20 chars");
        }

        String content = "PDF(" + req.title + "):" + req.body;
        return new ExportResult("application/pdf", content.getBytes(StandardCharsets.UTF_8));
    }
}
