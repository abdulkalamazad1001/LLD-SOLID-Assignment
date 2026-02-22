import java.util.List;
import java.util.Map;

//so this is like the class which is handling everything almost..

/*
 * First is it can do the parsing and we will make use of RawInputParser class
 * Second is it can do the validation and we will make use of StudentValidator class
 * Third is it can do the ID generation and we will make use of IdUtil class
 * Fourth is it can do the persistence and we will make use of StudentRepository class
 * Fifth is it can do the printing and we will make use of OnboardingPrinter class     
 */

public class OnboardingService {
    private final RawInputParser parser;
    private final StudentValidator validator;
    private final StudentRepository repo;
    private final OnboardingPrinter printer;

    public OnboardingService(StudentRepository repo, OnboardingPrinter printer) {
        this.parser = new RawInputParser();
        this.validator = new StudentValidator();
        this.repo = repo;
        this.printer = printer;
    }

    // so here we are using this method to register the student from the raw input
    public void registerFromRawInput(String raw) {
        // printing the input
        printer.printInput(raw);

        // parsing the input
        Map<String, String> fields = parser.parse(raw);

        // validating the fields
        List<String> errors = validator.validate(fields);
        if (!errors.isEmpty()) {
            printer.printErrors(errors);
            return;
        }

        // generating the id
        String id = IdUtil.nextStudentId(repo.count());

        // creating the student record
        StudentRecord rec = new StudentRecord(
                id,
                fields.get("name"),
                fields.get("email"),
                fields.get("phone"),
                fields.get("program"));

        // saving the student record
        repo.save(rec);
        // printing the success message
        printer.printSuccess(id, repo.count(), rec);
    }
}