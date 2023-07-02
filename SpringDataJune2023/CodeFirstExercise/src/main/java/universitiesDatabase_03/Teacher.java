package universitiesDatabase_03;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "teachers")
@Access(value = AccessType.FIELD)
public class Teacher extends Base{

    @Column(name = "email")
    private String email;

    @Column(name = "salary_per_hour")
    private BigDecimal salaryPerHour;

    @OneToMany
    private Set<Course> courses;

    public Teacher() {
        courses = new HashSet<>();
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getSalaryPerHour() {
        return salaryPerHour;
    }

    public void setSalaryPerHour(double salaryPerHour) {
        this.salaryPerHour = BigDecimal.valueOf(salaryPerHour);
    }
}
