package oksana.internship.lesson3.repository.subjects;

import oksana.internship.lesson3.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectsRepository extends JpaRepository<Subject, Integer> {


    @Query(value = "select s from Subject s join fetch s.groupItem g")
    List<Subject> findAll();

    Optional<Subject> findBySubjectName(String subjectName);


    @Query(value = "select s from Subject s left join fetch s.groupItem sg " +
            "where sg.groupName =:groupName")
    List<Subject> findSubjectBy(@Param("groupName") String groupName);

}

