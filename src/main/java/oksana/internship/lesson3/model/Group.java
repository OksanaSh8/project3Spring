package oksana.internship.lesson3.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NamedEntityGraph(
        name = "WithSubjects2",
        attributeNodes = @NamedAttributeNode("subjects"))
@Entity
@Table(name = "groups")
public class Group {

    @Id
    @Column(name = "group_name")
    private String groupName;

    @JsonIgnore
    @ManyToMany(mappedBy = "groupItem")

    private List<Subject> subjects = new ArrayList<>();

    public Group() {
    }

    public Group(String groupName) {
        this.groupName = groupName;
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
    public String toString() {
        return "Group{" +
                "groupName='" + groupName + '\'' +
                '}';
    }
}
