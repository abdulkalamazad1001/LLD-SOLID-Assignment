import java.util.List;

//so the main use of the onboarding printer is to print the things here 
//so for this we will be creating the one class which is responsible for printing the things
//this is following the single responsibility principle

public class OnboardingPrinter {
    // so here we are using this method to print the input
    public void printInput(String raw) {
        System.out.println("INPUT: " + raw);
    }

    // so here we are using this method to print the errors
    public void printErrors(List<String> errors) {
        System.out.println("ERROR: cannot register");
        for (String e : errors)
            System.out.println("- " + e);
    }

    // so here we are using this method to print the success message
    public void printSuccess(String id, int totalAfterSave, StudentRecord rec) {
        System.out.println("OK: created student " + id);
        System.out.println("Saved. Total students: " + totalAfterSave);
        System.out.println("CONFIRMATION:");
        System.out.println(rec);
    }
}
