package oksana.internship.lesson3.service;

import oksana.internship.lesson3.config.SpringConfig;
import oksana.internship.lesson3.model.Group;
import oksana.internship.lesson3.model.Subject;
import oksana.internship.lesson3.repository.subjects.SubjectsRepository;
import oksana.internship.lesson3.service.dto.SubjectDto;
import oksana.internship.lesson3.service.mapper.SubjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class SubjectService {
    private final SubjectsRepository subjectsRepository;

    @Autowired
    public SubjectService(SubjectsRepository subjectsRepository, SpringConfig springConfig) {
        this.subjectsRepository = subjectsRepository;
    }

    public Set<SubjectDto> findAll() {
        return SubjectMapper.INSTANCE.toDTOList(subjectsRepository.findAll());
    }


    public SubjectDto findById(int id) {
        Optional<Subject> optionalSubject = subjectsRepository.findById(id);
        if(optionalSubject.isPresent()) {
            return SubjectMapper.INSTANCE.toDto(optionalSubject.get());
        } else {
            return null;
        }
    }


    public Set<SubjectDto> findByGroupName(String groupName) {
        var optionalSubject = Optional.ofNullable(subjectsRepository.findSubjectBy(groupName));
        if(optionalSubject.isPresent()) {
            return  SubjectMapper.INSTANCE.toDTOList(optionalSubject.get());
        } else {
            return null;
        }
    }

    @Transactional
    public Subject save(Subject subject) {
        List<Group> groups=new ArrayList<>();
        for (Group groupList:subject.getGroupItem()) {
            groups.add(groupList);
        }
        subject.addGroup(groups);
        return subjectsRepository.save(subject);
    }

    @Transactional
    public boolean update(Integer id, Subject updateSubject) {

        updateSubject.setId(id);
        subjectsRepository.save(updateSubject);
        return true;
    }

    @Transactional
    public boolean delete(Integer id) {
        List<Group> groups=new ArrayList<>();
        List<Group> groupList=subjectsRepository.findById(id).get().getGroup();
        for (Group group:groupList) {
            groups.remove(group);
        }
        subjectsRepository.deleteById(id);
        return subjectsRepository.findById(id)
                .map(entity -> {
                    subjectsRepository.delete(entity);
                    subjectsRepository.flush();
                    return true;
                })
                .orElse(false);
    }

}
