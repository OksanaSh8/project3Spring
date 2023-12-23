package oksana.internship.lesson3.repository.students;

import oksana.internship.lesson3.db.ConnectionManagerTest;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

@ContextConfiguration(classes = ConnectionManagerTest.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
class StudentsRepositoryTest {

    private final StudentsRepository studentsRepository;

    @Autowired
    StudentsRepositoryTest(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
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
    void findByFullName() {
        var student = studentsRepository.findByFullName("Savchenko Ekaterina Petrovna");
        assertNotNull(student);
    }
}