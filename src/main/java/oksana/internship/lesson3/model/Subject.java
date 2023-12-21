package oksana.internship.lesson3.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "subject")
public class Subject {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "subject_name")
    @NotEmpty(message = "Subject name should not be empty")
    private String subjectName;

    @ManyToMany
    @JoinTable(
            name = "groups_subjects",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private List<Group> groupItem= new ArrayList<>();

    public Subject() {
    }

    public Subject(int id, String subjectName) {
        this.id = id;
        this.subjectName = subjectName;
    }

    public Subject(int id, String subjectName, List<Group> groupItem) {
        this.id = id;
        this.subjectName = subjectName;
        this.groupItem = groupItem;
    }

    public Subject(String subjectName, List<Group> groupItem) {
        this.subjectName = subjectName;
        this.groupItem = groupItem;
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

    public List<Group> getGroupItem() {
        return groupItem;
    }

    public void setGroupItem(List<Group> groupItem) {
        this.groupItem = groupItem;
    }

    public List<Group> getGroup() {
        return groupItem;
    }

    public void addGroup(List<Group> group) {
        for (Group groupNew:group) {
            groupNew.getSubjects().add(this);
        }
    }


    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", subjectName='" + subjectName + '\'' +
                ", groupItem=" + groupItem +
                '}';
    }
}
