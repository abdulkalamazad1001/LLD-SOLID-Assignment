import java.util.*;

//so here i have created a fake database for storing the student records
//this is following the single responsibility principle
//so here the fakeDb will not be implemented in the OnboardingService class
//instead it will be implemented in the Main class

//we are implementing the student repository interface here using FakeDB
//and tomorrow if we are using the other database even then we can use the other database as well.. just by implenting the interface

public class FakeDb implements StudentRepository {
    private final List<StudentRecord> rows = new ArrayList<>();

    public void save(StudentRecord r) {
        rows.add(r);
    }

    public int count() {
        return rows.size();
    }

    public List<StudentRecord> all() {
        return Collections.unmodifiableList(rows);
    }
}