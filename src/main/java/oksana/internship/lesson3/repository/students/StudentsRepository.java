package oksana.internship.lesson3.repository.students;

import oksana.internship.lesson3.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentsRepository extends JpaRepository<Student, Integer>, StudentsRepositorySelect {

    Optional<Student> findByFullName(String fullName);

}