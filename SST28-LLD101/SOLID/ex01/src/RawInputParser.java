import java.util.*;

//so here i have created a seperate class for parsing the raw input
//so this class has only one responsibility of parsing the raw input
//this is following the single responsibility principle

public class RawInputParser {
    public Map<String, String> parse(String raw) {
        Map<String, String> kv = new LinkedHashMap<>();
        String[] parts = raw.split(";");
        for (String p : parts) {
            String[] t = p.split("=", 2);
            if (t.length == 2)
                kv.put(t[0].trim(), t[1].trim());
        }
        return kv;
    }
}
