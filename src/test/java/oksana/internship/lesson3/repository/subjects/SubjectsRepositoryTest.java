package oksana.internship.lesson3.repository.subjects;

import oksana.internship.lesson3.db.ConnectionManagerTest;
import oksana.internship.lesson3.model.Subject;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

@ContextConfiguration(classes = ConnectionManagerTest.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
class SubjectsRepositoryTest {
    private final SubjectsRepository subjectsRepository;

    @Autowired
    SubjectsRepositoryTest(SubjectsRepository subjectsRepository) {
        this.subjectsRepository = subjectsRepository;
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
    void findAll() {
        List<Subject> subjectList=subjectsRepository.findAll();
        assertNotNull(subjectList);
        assertThat(subjectList).hasSize(9);
    }

    @Test
    void findBySubjectName() {
        var subject = subjectsRepository.findBySubjectName("accounting");
        assertNotNull(subject);
    }

    @Test
    void findSubjectBy() {
        var subjectList = subjectsRepository.findSubjectBy("IT");
        System.out.println(subjectList);
        assertNotNull(subjectList);
        assertThat(subjectList).hasSize(5);
    }
}