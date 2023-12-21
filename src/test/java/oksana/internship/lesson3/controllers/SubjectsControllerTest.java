package oksana.internship.lesson3.controllers;

import oksana.internship.lesson3.model.Group;
import oksana.internship.lesson3.model.Subject;
import oksana.internship.lesson3.service.SubjectService;
import oksana.internship.lesson3.service.dto.SubjectDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class SubjectsControllerTest {

    private static final SubjectDto HISTORY = new SubjectDto(6, "history",
            List.of(new Group[]{new Group("AC"),
                    new Group("IT")}));
    @Mock
    private SubjectService subjectService;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private SubjectsController subjectsController;

    @Test
    void shouldDeleteExistedSubject() {
        Set<SubjectDto> subjects = subjectsController.getSubjects();
        subjects.add(HISTORY);
        Mockito.doReturn(true).when(subjectService).delete(HISTORY.getId());
        var deleteResult = subjectsController.delete(HISTORY.getId());
        assertEquals("предмет c id = 1 удален", deleteResult);
    }

    @Test
    void shouldFindAllSubject() {
        List<SubjectDto> subjectDtoList = List.of(HISTORY);
        Mockito.doReturn(subjectDtoList).when(subjectService).findAll();
        var subjects = subjectsController.getSubjects();
        var result = subjectDtoList.equals(subjects);
        assertThat(result).isTrue();
    }

    @Test
    void shouldFindSubjectById() {
        SubjectDto subjectDtoList = HISTORY;
        Mockito.doReturn(subjectDtoList).when(subjectService).findById(1);
        var subjects = subjectsController.findById(1);
        var result = subjectDtoList.equals(subjects);
        assertThat(result).isTrue();
    }

    @Test
    void shouldCreateSubject() {
        Group[] groups = {
                new Group("AC")};
        Subject subject = new Subject(1, "statistics", List.of(groups));
        Mockito.doReturn(subject).when(subjectService).save(any());
        var createResult = subjectsController.create(subject,bindingResult);
        var result = createResult.getClass().getSimpleName();
        assertEquals("ResponseEntity", result);
    }

    @Test
    void shouldUpdateSubject() {
        Group[] groups = {
                new Group("AC")
        };
        Subject subject = new Subject(2, "statistics", List.of(groups));
        SubjectDto subjectDto = new SubjectDto(2, "statistics", List.of(groups));
        Mockito.doReturn(true).when(subjectService).update(any(), any());
        var result = subjectsController.update(2, subjectDto, bindingResult);
        assertEquals("ResponseEntity", result);
    }

    @Test
    void shouldGetSubject() {
        Subject subject = new Subject(1, "mathematics");
        Mockito.doReturn(Optional.of(subject)).when(subjectService).findById(1);
        var subjectDto = subjectsController.findById(1);
        var result = subjectDto.getClass().getSimpleName();
        assertEquals("SubjectDto", result);
    }
}

