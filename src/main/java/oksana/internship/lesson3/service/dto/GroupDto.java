package oksana.internship.lesson3.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import oksana.internship.lesson3.model.GroupName;
import oksana.internship.lesson3.model.Subject;

import java.util.List;
import java.util.Objects;

public class GroupDto {

    private GroupName groupName;

    @JsonIgnore
    private List<Subject> subjectsList;

    public GroupDto() {
    }

    public GroupDto(GroupName groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return String.valueOf(groupName);
    }

    public void setGroupName(GroupName groupName) {
        this.groupName = groupName;
    }

    public List<Subject> getSubjectsList() {
        return subjectsList;
    }


    @Override
    public String toString() {
        return "GroupDto{" +
                "groupName='" + groupName + '\'' +

                '}';
    }
}
