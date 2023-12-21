package oksana.internship.lesson3.service.mapper;

import oksana.internship.lesson3.model.Student;
import oksana.internship.lesson3.service.dto.StudentDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    StudentDto toDto(Student student);

    Student toEntity(StudentDto studentDto);

    List<StudentDto> toDTOList(List<Student> students);

}
