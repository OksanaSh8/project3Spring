package oksana.internship.lesson3.controllers;

import oksana.internship.lesson3.model.Group;
import oksana.internship.lesson3.model.GroupName;
import oksana.internship.lesson3.service.GroupService;
import oksana.internship.lesson3.service.dto.GroupDto;
import oksana.internship.lesson3.service.dto.StudentDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class GroupControllerTest {

    @Mock
    private GroupService groupService;

    @InjectMocks
    private GroupController groupController;

    @Test
    void shouldFindAllGroup() {
        List<GroupDto> groupDtoList = List.of(new GroupDto(GroupName.IT), new GroupDto(GroupName.AC));
        Mockito.doReturn(groupDtoList).when(groupService).findAll();
        var groupDto = groupController.getGroups();
        var result = groupDtoList.equals(groupDto);
        assertThat(result).isTrue();
    }


    @Test
    void create() {
        Group group = new Group("II");
        GroupDto groupDto = new GroupDto(GroupName.MM);
        Mockito.doReturn(group).when(groupService).create(any());
        var studentNew = groupController.create(groupDto);
        var result = studentNew.getClass().getSimpleName();
        assertEquals("ResponseEntity", result);
    }


    @Test
    void shouldFindAllByGroupName() {
        List<StudentDto> students = new ArrayList<>();
        StudentDto studentDto = new StudentDto(1, "Petrov Oleg Fedorovich", 2001, "IT");
        students.add(studentDto);
        Mockito.doReturn(students).when(groupService).findAllStudentByGroupName("IT");
        var listStudent = groupController.findStudentsByGroupName("IT");
        var result = listStudent.size() == students.size();
        assertThat(result).isTrue();
    }
}