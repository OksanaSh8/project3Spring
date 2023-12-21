package oksana.internship.lesson3.service;

import oksana.internship.lesson3.db.ConnectionManagerTest;
import oksana.internship.lesson3.db.IT;
import oksana.internship.lesson3.model.Group;
import oksana.internship.lesson3.model.Subject;
import oksana.internship.lesson3.service.dto.SubjectDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@IT
class SubjectServiceTest {
private final SubjectService subjectService;

    @Autowired
    SubjectServiceTest(SubjectService subjectService) {
        this.subjectService = subjectService;
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
    void shouldFindById() {
        var subject = subjectService.findById(1);
        System.out.println(subject);

        var subjectById = new SubjectDto( "mathematics");
        var result = subject.equals(subjectById);
        assertThat(result).isTrue();
    }

    @Test
    @Order(2)
    void shouldFindAll()  {

        Set<SubjectDto> subjects = subjectService.findAll();
        System.out.println(subjects);
        assertEquals(6, subjects.size());
    }

    @Test
    @Order(3)
    void shouldFindByIdWithGroup() {
        var subject = subjectService.findById(1);
        SubjectDto subjectByIdWithGroup = new SubjectDto(1, "mathematics",
                        List.of(new Group[]{new Group("AC"),
                                new Group("IT")}));
        var result = subject.equals(subjectByIdWithGroup);
        assertThat(result).isTrue();
    }

    @Test
    @Order(4)
    void shouldFindSubjectByGroupName() {
        var subjects = subjectService.findByGroupName("IT");
        assertEquals(5, subjects.size());
    }


    @Test
    @Order(5)
    void shouldCreateSubject()  {
        Set<SubjectDto> subjects = subjectService.findAll();
        List<Group> groups=List.of(new Group[]{
                new Group("IT"),
                new Group("AC")
        });
        subjectService.save(new Subject("philosophy", groups));
        Set<SubjectDto> subjectsAfterAdd = subjectService.findAll();
        assertEquals(1, subjectsAfterAdd.size() - subjects.size());
    }

    @Test
    @Order(6)
    void shouldUpdateSubject2()  {
        List<Group> groups=List.of(new Group[]{
                new Group("AC")
        });
        Subject subject = new Subject(1,"history", groups);
        var result = subjectService.update(2, subject);
        assertThat(result).isTrue();
    }

    @Test
    @Order(7)
    void shouldDeleteExistedSubject() {
        Set<SubjectDto> subjects = subjectService.findAll();
        subjectService.delete(1);
        Set<SubjectDto> subjectAfterDelete = subjectService.findAll();
        assertEquals(1, subjects.size() - subjectAfterDelete.size());
    }
}