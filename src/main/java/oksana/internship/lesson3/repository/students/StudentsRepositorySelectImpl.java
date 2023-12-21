package oksana.internship.lesson3.repository.students;

import oksana.internship.lesson3.model.Student;
import oksana.internship.lesson3.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class StudentsRepositorySelectImpl implements StudentsRepositorySelect {

    private final JdbcTemplate jdbcTemplate;
    private final EntityManager entityManager;

    private static final String FIND_ALL_BY_STUDENT_ID = """
            SELECT  subject.id, students.id, full_name, year_of_birth, groups.group_name, subject_name
            FROM groups
                join groups_subjects ON groups.group_name = groups_subjects.group_id
                join subject ON groups_subjects.subject_id = subject.id
                join students ON groups.group_name = students.group_name
            WHERE students.id = ?
            """;

    @Autowired
    public StudentsRepositorySelectImpl(JdbcTemplate jdbcTemplate, EntityManager entityManager) {
        this.jdbcTemplate = jdbcTemplate;
        this.entityManager = entityManager;
    }


    @Override
    public Optional<Student> findAllByIdStudent(Integer idStudent) {
        List<Subject> subjects;
        Student student;
        subjects=jdbcTemplate.query(FIND_ALL_BY_STUDENT_ID, new Object[]{idStudent},
                (rs, rowNum) -> new Subject(
                        rs.getInt("id"),
                        rs.getString("subject_name")));

        student= jdbcTemplate.query(FIND_ALL_BY_STUDENT_ID, new Object[]{idStudent}, new BeanPropertyRowMapper<>(Student.class))
                .stream().findAny().orElse(null);
student.setSubjects(subjects);
return Optional.of(student);
    }
}


