package oksana.internship.lesson3.service;

import oksana.internship.lesson3.model.Student;
import oksana.internship.lesson3.model.Subject;
import oksana.internship.lesson3.repository.students.StudentsRepository;
import oksana.internship.lesson3.service.dto.StudentDto;
import oksana.internship.lesson3.service.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(readOnly = true)
public class StudentService {
    private final StudentsRepository studentsRepository;
    private final StudentMapper studentMapper;

    @Autowired
    public StudentService(StudentsRepository studentsRepository, StudentMapper studentMapper) {
        this.studentsRepository = studentsRepository;
        this.studentMapper = studentMapper;
    }

    public List<StudentDto> findAll() {
        List<Student> student = studentsRepository.findAll();
        List<Student> studentList = new ArrayList<>();
        for (Student s : student) {
            studentList.add(new Student(s.getId(), s.getFullName(), s.getYearOfBirth(), s.getGroupName()));
        }
        return StudentMapper.INSTANCE.toDTOList(studentList);
    }



    public StudentDto findById(int id) {
        Optional<Student> student = studentsRepository.findById(id);
        if (student.isPresent()) {
            Student student2 = new Student(student.get().getId(), student.get().getFullName(),
                    student.get().getYearOfBirth(), student.get().getGroupName());
            return StudentMapper.INSTANCE.toDto(student2);
        } else {
            return null;
        }
    }

    public Optional<Student> findByFullName(String fullName) {
        return studentsRepository.findByFullName(fullName);
    }

    @Transactional
    public Student save(Student student) {
        return studentsRepository.save(student);
    }

    @Transactional
    public boolean update(Integer id, Student updateStudent) {
        updateStudent.setId(id);
        studentsRepository.save(updateStudent);
        return true;
    }

    @Transactional
    public boolean delete(Integer id) {
        studentsRepository.deleteById(id);
        return studentsRepository.findById(id)
                .map(entity -> {
                    studentsRepository.delete(entity);
                    studentsRepository.flush();
                    return true;
                })
                .orElse(false);
    }

    public Optional<StudentDto> findSubjectsByIdStudent(Integer id) {
        var allById = studentsRepository.findAllByIdStudent(id);
        List<Subject> subject=  allById.get().getSubjects();
        List<Subject> subjectName =new ArrayList<>();
        for (Subject s:subject) {
            subjectName.add(new Subject(s.getId(), s.getSubjectName()));
   }
        System.out.println(subjectName);
        if (allById.isPresent()) {
            Student student = new Student(allById.get().getId(), allById.get().getFullName(),
                    allById.get().getYearOfBirth(), allById.get().getGroupName(), subjectName);
            return Optional.ofNullable(StudentMapper.INSTANCE.toDto(student));
        } else {
            return null;
        }
    }
}
