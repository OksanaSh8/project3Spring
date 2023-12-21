package oksana.internship.lesson3.service.mapper;

import oksana.internship.lesson3.model.Group;
import oksana.internship.lesson3.model.Subject;
import oksana.internship.lesson3.service.dto.GroupDto;
import oksana.internship.lesson3.service.dto.SubjectDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;


@Mapper(uses = GroupMapper.class)
public interface SubjectMapper {

    SubjectMapper INSTANCE = Mappers.getMapper(SubjectMapper.class);

    @Mapping(source = "group", target = "groupList", qualifiedByName ="groupMappingToDto")
    SubjectDto toDto(Subject subject);

 //   @InheritInverseConfiguration
    Subject toEntity(SubjectDto subjectDto);


    @Mapping(source = "group", target = "groupList", qualifiedByName ="groupMappingToDto")
    Set<SubjectDto> toDTOList(List<Subject> subjects);
}
