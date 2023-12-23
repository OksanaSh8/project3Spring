package oksana.internship.lesson3.service;

import oksana.internship.lesson3.db.ConnectionManagerTest;
import oksana.internship.lesson3.db.IT;
import oksana.internship.lesson3.model.Student;
import oksana.internship.lesson3.model.Subject;
import oksana.internship.lesson3.service.dto.StudentDto;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@ContextConfiguration(classes = ConnectionManagerTest.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
class StudentServiceTest {
    private final StudentService studentService;

    private static final Student IVAN = new Student(1, "Petrov Ivan Petrovich", 2001, "IT");
    private static final StudentDto IVAN2 = new StudentDto(1, "Petrov Ivan Petrovich", 2001, "IT");

    @Autowired
    StudentServiceTest(StudentService studentService) {
        this.studentService = studentService;
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
    void shouldFindAll() {
        List<StudentDto> students = studentService.findAll();
        assertEquals(8, students.size());
    }

    @Test
    @Order(2)
    void shouldFindById() {
        var student = studentService.findById(1);
        StudentDto studentById = new StudentDto(1, "Ivanov Ivan Vsevolodovich", 2001, "IT");
        var result = student.equals(studentById);
        assertThat(result).isTrue();
    }

    @Test
    @Order(3)
    void shouldFindAllById() {
        var student = studentService.findSubjectsByIdStudent(1);
        List<Subject> subject = List.of(new Subject[]{
                new Subject(1, "mathematics"),
                new Subject(2, "physics"),
                new Subject(3, "programming languages"),
                new Subject(4, "database")
        });
        Optional<StudentDto> studentById =
                Optional.of(new StudentDto(1, "Ivanov Ivan Vsevolodovich", 2001, "IT", subject));
        var result = student.equals(studentById);
        assertThat(result).isTrue();
    }

    @Test
    @Order(4)
    void shouldGetStudentByFullName() {
        var result=studentService.findByFullName("Ivanov Ivan Vsevolodovich");
        assertThat(result.get().getFullName().equals("Ivanov Ivan Vsevolodovich")).isTrue();
    }


    @Test
    @Order(5)
    void shouldUpdateStudents() {
        studentService.update(1, IVAN);
        StudentDto studentById = IVAN2;
        var result = studentService.findById(1).equals(studentById);
        assertThat(result).isTrue();
    }

    @Test
    @Order(6)
    void shouldDeleteExistedStudent() {
        List<StudentDto> students = studentService.findAll();
        studentService.delete(1);
        List<StudentDto> studentsAfterDelete = studentService.findAll();
        assertEquals(1, students.size() - studentsAfterDelete.size());
    }

    @Test
    @Order(7)
    void shouldCreateStudents() {
        Student IVAN = new Student(1, "Simonov Ivan Petrovich", 2001, "IT");
        var studentSave = studentService.save(IVAN);
        var studentAfteSave = studentService.findByFullName(studentSave.getFullName());
        assertNotNull(studentAfteSave);
    }
}

