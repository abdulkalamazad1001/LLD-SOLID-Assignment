import java.util.*;

//we are creating the student validator for validating the fields 
//this class does only one thing and that is validation..

public class StudentValidator {
    private static final List<String> allowed_programs = Arrays.asList("CSE", "AI", "SWE");

    public List<String> validate(Map<String, String> fields) {
        List<String> errors = new ArrayList<>();

        String name = fields.getOrDefault("name", "");
        String email = fields.getOrDefault("email", "");
        String phone = fields.getOrDefault("phone", "");
        String program = fields.getOrDefault("program", "");

        if (name.isBlank())
            errors.add("name is required");
        if (email.isBlank() || !email.contains("@"))
            errors.add("email is invalid");
        if (phone.isBlank() || !phone.chars().allMatch(Character::isDigit))
            errors.add("phone is invalid");
        if (!allowed_programs.contains(program))
            errors.add("program is invalid");

        return errors;
    }
}
