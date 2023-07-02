package universitiesDatabase_03;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student extends Base{

    public Student() {
        courses = new HashSet<>();
    }

    @Column(name = "average_grade")
    private double averageGrades;

    @Column(name = "attendance")
    private int attendance;

    @ManyToMany(targetEntity = Course.class, mappedBy = "studentSet")
    private Set<Course> courses;

    public double getAverageGrades() {
        return averageGrades;
    }

    public void setAverageGrades(double averageGrades) {
        this.averageGrades = averageGrades;
    }

    public int getAttendance() {
        return attendance;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }
}
