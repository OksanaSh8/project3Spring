package oksana.internship.lesson3.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import oksana.internship.lesson3.model.Subject;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Objects;

public class StudentDto {


    private Integer id;

    @NotEmpty(message = "Name should not be empty")
    private String fullName;

    @Min(value = 1975, message = "Year of birth should be greater than 1975")
    @Max(value = 2005, message = "Year of birth should be lesser than 2005")
    private int yearOfBirth;


    @NotEmpty(message = "Group should not be empty")
    private String groupName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Subject> subjects;


    public StudentDto() {
    }

    public StudentDto(Integer id, String fullName, int yearOfBirth, String groupName) {
        this.id = id;
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
        this.groupName = groupName;
    }

    public StudentDto(Integer id, String fullName, int yearOfBirth, String groupName, List<Subject> subjects) {
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
        return groupName;
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
        StudentDto that = (StudentDto) o;
        return yearOfBirth == that.yearOfBirth && Objects.equals(id, that.id) && Objects.equals(fullName, that.fullName) && Objects.equals(groupName, that.groupName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, yearOfBirth, groupName);
    }


}

