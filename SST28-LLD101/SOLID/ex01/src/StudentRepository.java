import java.util.List;

//so here we are using this for the persistence contract for student records
//OnboardingService depends on this interface, NOT on FakeDb directly.
//so even if tomorrow if we have to use some other interface we can just implement the studentrepository
//so this is how we can just implment the interface

//so here for now in the interface we will have the collection of the methods
public interface StudentRepository {
    void save(StudentRecord record);

    int count();

    List<StudentRecord> all();
}
