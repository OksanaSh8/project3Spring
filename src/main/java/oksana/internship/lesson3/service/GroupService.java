package oksana.internship.lesson3.service;

import oksana.internship.lesson3.model.Group;
import oksana.internship.lesson3.model.Student;
import oksana.internship.lesson3.repository.groups.GroupsRepository;
import oksana.internship.lesson3.service.dto.GroupDto;
import oksana.internship.lesson3.service.dto.StudentDto;
import oksana.internship.lesson3.service.mapper.GroupMapper;
import oksana.internship.lesson3.service.mapper.StudentMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class GroupService {

    private final GroupsRepository groupsRepository;
    private final GroupMapper groupMapper;

    public GroupService(GroupsRepository groupsRepository, GroupMapper groupMapper) {
        this.groupsRepository = groupsRepository;
        this.groupMapper = groupMapper;
    }

    public List<GroupDto> findAll() {
        List<GroupDto> groupDtoList = new ArrayList<>();
        List<Group> groupsList = groupsRepository.findAll();
        for (Group group: groupsList) {
            groupDtoList.add(GroupMapper.INSTANCE.toDto(group));
        }
        return groupDtoList;
        }

    public List<StudentDto> findAllStudentByGroupName(String nameGroup) {
        List<Student> student = groupsRepository.findAllStudent(nameGroup);
        List<Student> studentList = new ArrayList<>();
        for (Student s : student) {
            studentList.add(new Student(s.getId(), s.getFullName(), s.getYearOfBirth(), s.getGroupName()));
        }
        return StudentMapper.INSTANCE.toDTOList(studentList);
    }

    @Transactional
    public Group create(GroupDto groupDto) {
        return groupsRepository.save(GroupMapper.INSTANCE.toEntity(groupDto));
    }
}
