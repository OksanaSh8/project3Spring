package oksana.internship.lesson3.service;

import oksana.internship.lesson3.db.ConnectionManagerTest;
import oksana.internship.lesson3.db.IT;
import oksana.internship.lesson3.model.GroupName;
import oksana.internship.lesson3.service.dto.GroupDto;
import oksana.internship.lesson3.service.dto.StudentDto;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@ContextConfiguration(classes = ConnectionManagerTest.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
class GroupServiceTest {
    private final GroupService groupService;

    @Autowired
    GroupServiceTest(GroupService groupService) {
        this.groupService = groupService;
    }

    @BeforeAll
    static void beforeAll() {
        ConnectionManagerTest.container.start();
    }

    @AfterAll
    static void afterAll() {
        ConnectionManagerTest.container.stop();
    }

    @Test
    @Order(1)
    void shouldFindAllByGroupName() {
        var student = groupService.findAllStudentByGroupName("AC");
        List<StudentDto> studentByGroupName = List.of(new StudentDto[]{
                new StudentDto(2, "Petrov Oleg Sergeevich", 2001, "AC"),
                new StudentDto(4, "Savchenko Ekaterina Petrovna", 2001, "AC")
        });
        var result = student.equals(studentByGroupName);
        assertThat(result).isTrue();
    }

    @Test
    @Order(2)
    void shouldFindAll() {
        List<GroupDto> groups = groupService.findAll();
        System.out.println(groups);
        assertEquals(2, groups.size());
    }

    @Test
    void shouldCreateGroup() {
        List<GroupDto> students = groupService.findAll();
        groupService.create(new GroupDto(GroupName.MM));
        List<GroupDto> studentsAfterAdd = groupService.findAll();
        assertEquals(1, studentsAfterAdd.size() - students.size());
    }

}
