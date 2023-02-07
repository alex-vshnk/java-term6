import java.io.Serializable;
import java.util.ArrayList;

public class Abiturient implements Serializable {
    private String name;
    private String faculty;

    public Abiturient(String name, String faculty) {
        this.name = name;
        this.faculty = faculty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    @Override
    public String toString() {
        return "Abiturient{" +
                "name='" + name + '\'' +
                ", faculty='" + faculty + '\'' +
                '}';
    }
}
