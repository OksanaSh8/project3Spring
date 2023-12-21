package oksana.internship.lesson3.controllers;

import oksana.internship.lesson3.model.Student;
import oksana.internship.lesson3.model.Subject;
import oksana.internship.lesson3.service.StudentService;
import oksana.internship.lesson3.service.dto.StudentDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;


@ExtendWith(MockitoExtension.class)
class StudentsControllerTest {
    @Mock
    private StudentService studentService;

    @Mock
    private BindingResult bindingResult;


    @InjectMocks
    private StudentsController studentsController;


    private static final StudentDto IVAN = new StudentDto(1, "Petrov Ivan Petrovich", 2001, "IT");




    @Test
    void shouldDeleteExistedStudent() {
        List<StudentDto> students = studentsController.getStudents();
        students.add(IVAN);
        Mockito.doReturn(true).when(studentService).delete(IVAN.getId());
        var deleteResult = studentsController.delete(IVAN.getId());
        assertEquals("студент c id = 1 удален", deleteResult);
    }

    @Test
    void shouldFindStudentById() {
        StudentDto studentDto = new StudentDto(1, "Ivanov Ivan Ivanovich", 2001, "IT");
        Mockito.doReturn(studentDto).when(studentService).findById(1);
        var studentDto2 = studentsController.findById(1);
        var result=studentDto2.equals(studentDto);
        assertThat(result).isTrue();
    }

    @Test
    void shouldFindAllStudent() {
        List<StudentDto> studentDtoList = List.of(IVAN);
        Mockito.doReturn(studentDtoList).when(studentService).findAll();
        var StudentsDto = studentsController.getStudents();
        var result = studentDtoList.equals(StudentsDto);
        assertThat(result).isTrue();
    }

    @Test
    void shouldGetAllAboutStudent() {
        Subject[] subject = {
                new Subject(1, "mathematics"),
                new Subject(2, "physics"),
                new Subject(3, "programming languages"),
                new Subject(4, "database")
        };
        StudentDto student = new StudentDto(1, "Ivanov Ivan Vsevolodovich", 2001, "IT", List.of(subject));
        Mockito.doReturn(Optional.of(student)).when(studentService).findSubjectsByIdStudent(1);
        var studentDto = studentsController.findSubjectByIdStudent(1);
        var result = studentDto.getClass().getSimpleName();
        System.out.println(result);
        assertEquals("StudentDto", result);
    }

    @Test
    void shouldCreateStudent() {
        Student student = new Student(3, "Ivanov Kiril Ivanovich", 2001, "IT");
        StudentDto studentDto = new StudentDto(3, "Ivanov Igor Olegovich", 2001, "IT");
        Mockito.doReturn(student).when(studentService).save(any());
        Mockito.doReturn(false).when(bindingResult).hasErrors();
        var studentNew = studentsController.create(studentDto, bindingResult);
        var result = studentNew.getClass().getSimpleName();
        assertEquals("ResponseEntity", result);
    }

    @Test
    void shouldCreateStudent2() {
        Student student = new Student(3, "Ivanov Kiril Ivanovich", 2001, "IT");
        StudentDto studentDto = new StudentDto(3, "", 2001, "IT");
        Mockito.doReturn(student).when(studentService).save(any());
        //Mockito.doReturn(false).when(bindingResult).hasErrors();
        var studentNew = studentsController.create(studentDto, bindingResult);
        var result = studentNew.getClass().getSimpleName();
        assertEquals("ResponseEntity", result);
    }


    @Test
    void shouldUpdateStudent() {
        Student student = new Student(1, "Ivanov2 Ivan Ivanovich", 2001, "IT");
        StudentDto studentDto = new StudentDto(1, "Ivanov2 Ivan Ivanovich", 2001, "IT");
        Mockito.doReturn(true).when(studentService).update(1, student);
        var updateResult = studentsController.update(1, studentDto, bindingResult);
        var result = updateResult.getClass().getSimpleName();
        assertEquals("ResponseEntity", result);
    }

  }

