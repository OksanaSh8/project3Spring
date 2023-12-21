package oksana.internship.lesson3.service.dto;

import oksana.internship.lesson3.model.Group;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SubjectDto {

    private int id;

    @NotEmpty(message = "Subject name should not be empty")
    private String subjectName;


    private List<Group> groupList = new ArrayList<>();

    public SubjectDto() {
    }

    public SubjectDto(String subjectName) {
        this.subjectName = subjectName;
    }

    public SubjectDto(int id, String subjectName, List<Group> groupList) {
        this.id = id;
        this.subjectName = subjectName;
        this.groupList = groupList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public List<Group> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<Group> groupList) {
        this.groupList = groupList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubjectDto that = (SubjectDto) o;
        return Objects.equals(subjectName, that.subjectName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subjectName);
    }

    @Override
    public String toString() {
        return "SubjectDto{" +
                "id=" + id +
                ", subjectName='" + subjectName + '\'' +
                ", groupList=" + groupList +
                '}';
    }
}


