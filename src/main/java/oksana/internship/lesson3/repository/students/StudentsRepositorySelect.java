package oksana.internship.lesson3.repository.students;

import oksana.internship.lesson3.model.Student;

import java.util.Optional;

public interface StudentsRepositorySelect {
    Optional<Student> findAllByIdStudent(Integer idStudent);
}
