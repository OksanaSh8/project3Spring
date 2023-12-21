package oksana.internship.lesson3.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Objects;

@NamedEntityGraph(
        name = "WithSubjects",
        attributeNodes = @NamedAttributeNode("subjects"))
@Entity
@Table(name = "students")
public class Student {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "full_name")
    @NotEmpty(message = "Name should not be empty")
    private String fullName;

    @Column(name = "year_of_birth")
    @Min(value = 1975, message = "Year of birth should be greater than 1975")
    @Max(value = 2005, message = "Year of birth should be lesser than 2005")
    private int yearOfBirth;

    @Column(name = "group_name")
    @NotEmpty(message = "Group should not be empty")
    private String groupName;

    @OneToMany(mappedBy = "subjectName")
    private List<Subject> subjects;

    public Student() {
    }

    public Student(Integer id, String fullName, int yearOfBirth, String groupName) {
        this.id = id;
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
        this.groupName = groupName;
    }

    public Student(Integer id, String fullName, int yearOfBirth, String groupName, List<Subject> subjects) {
        this.id = id;
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
        this.groupName = groupName;
        this.subjects = subjects;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getGroupName() {
        return String.valueOf(groupName);
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return yearOfBirth == student.yearOfBirth && Objects.equals(id, student.id) && Objects.equals(fullName, student.fullName) && Objects.equals(groupName, student.groupName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, yearOfBirth, groupName);
    }

}
