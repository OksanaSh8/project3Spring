package oksana.internship.lesson3.service.mapper;

import oksana.internship.lesson3.model.Group;
import oksana.internship.lesson3.model.Subject;
import oksana.internship.lesson3.service.dto.GroupDto;
import oksana.internship.lesson3.service.dto.SubjectDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface GroupMapper {

    GroupMapper INSTANCE = Mappers.getMapper(GroupMapper.class);

    @Mapping(ignore = true, target = "subjectsList")
    @Named("groupMappingToDto")
    GroupDto toDto(Group group);

    //   @InheritInverseConfiguration
    Group toEntity(GroupDto groupDto);


    List<GroupDto> toDTOList(List<Group> groups);
}

