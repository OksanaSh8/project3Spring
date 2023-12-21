package oksana.internship.lesson3.repository.groups;

import oksana.internship.lesson3.model.Group;
import oksana.internship.lesson3.model.GroupName;
import oksana.internship.lesson3.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupsRepository extends JpaRepository<Group, String> {
    @Query(value = "select g from Group g ")
    List<Group> findAll();

    @Query(value = "select s from Student s " +
            "where s.groupName=:groupName2")
    List<Student> findAllStudent(@Param("groupName2") String groupName);


}
