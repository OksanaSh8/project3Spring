package oksana.internship.lesson3.controllers;

import oksana.internship.lesson3.model.Student;
import oksana.internship.lesson3.service.StudentService;
import oksana.internship.lesson3.service.dto.StudentDto;
import oksana.internship.lesson3.service.mapper.StudentMapper;
import oksana.internship.lesson3.validator.Errors;
import oksana.internship.lesson3.validator.Exceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

import static oksana.internship.lesson3.validator.ErrorsUtil.returnErrorsToClient;


@RestController
@RequestMapping("/student")
public class StudentsController {

    private final StudentService studentsService;
    @Autowired
    public StudentsController(StudentService studentsService) {
        this.studentsService = studentsService;
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<StudentDto> getStudents() {
        return studentsService.findAll();
    }

    @GetMapping("/{id}")
    public StudentDto findById(@PathVariable("id") int id) {
        return studentsService.findById(id);
    }


    @GetMapping("/all/{id}")
    public StudentDto findSubjectByIdStudent(@PathVariable("id") int id) {

        return studentsService.findSubjectsByIdStudent(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid StudentDto studentDto,
                                             BindingResult bindingResult) {
        Student studentToAdd = StudentMapper.INSTANCE.toEntity(studentDto);
        if (bindingResult.hasErrors())
            returnErrorsToClient(bindingResult);

        studentsService.save(studentToAdd);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable("id") Integer id,
                                             @RequestBody @Valid StudentDto studentDto,
                                             BindingResult bindingResult) {
        Student studentToUpdate = StudentMapper.INSTANCE.toEntity(studentDto);
     //   studentValidator.validate(studentToUpdate, bindingResult);
        if (bindingResult.hasErrors())
            returnErrorsToClient(bindingResult);

        studentsService.update(id, studentToUpdate);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String delete(@PathVariable("id") Integer id) {
        if (!studentsService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "студент c id = "+id+" удален";
    }
  @ExceptionHandler
    private ResponseEntity<Errors> handleException(Exceptions e) {
        Errors response = new Errors(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


}