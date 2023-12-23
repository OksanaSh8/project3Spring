package oksana.internship.lesson3.repository.groups;

import oksana.internship.lesson3.db.ConnectionManagerTest;
import oksana.internship.lesson3.model.Group;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.util.List;

@ContextConfiguration(classes = ConnectionManagerTest.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
class GroupsRepositoryTest {
    private final GroupsRepository groupsRepository;

    @Autowired
    GroupsRepositoryTest(GroupsRepository groupsRepository) {
        this.groupsRepository = groupsRepository;
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
        List<Group> result = groupsRepository.findAll();
        assertThat(result).hasSize(2);
    }

    @Test
    void findAllStudent() {
        var students = groupsRepository.findAllStudent("IT");
        assertNotNull(students);
        assertThat(students).hasSize(6);
    }
}