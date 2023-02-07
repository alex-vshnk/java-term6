import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Exam implements Serializable {
    private String name;
    private Abiturient abiturient;
    private Teacher teacher;
    private int mark;

    public Exam(String name, Abiturient abiturient, Teacher teacher, int mark) {
        this.name = name;
        this.abiturient = abiturient;
        this.teacher = teacher;
        this.mark = mark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Abiturient getAbiturient() {
        return abiturient;
    }

    public void setAbiturient(Abiturient abiturient) {
        this.abiturient = abiturient;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "name='" + name + '\'' +
                ", abiturient=" + abiturient +
                ", teacher=" + teacher +
                ", mark=" + mark +
                '}';
    }
}
