package oksana.internship.lesson3.controllers;

import oksana.internship.lesson3.model.Subject;
import oksana.internship.lesson3.service.SubjectService;
import oksana.internship.lesson3.service.dto.SubjectDto;
import oksana.internship.lesson3.service.mapper.SubjectMapper;
import oksana.internship.lesson3.validator.Errors;
import oksana.internship.lesson3.validator.Exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Set;

import static oksana.internship.lesson3.validator.ErrorsUtil.returnErrorsToClient;

@RestController
@RequestMapping("/subject")
public class SubjectsController {

    private final SubjectService subjectService;

    public SubjectsController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Set<SubjectDto> getSubjects() {
        return subjectService.findAll();
    }

    @GetMapping("/{id}")
    public SubjectDto findById(@PathVariable("id") int id) {
        return subjectService.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid Subject subject, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            returnErrorsToClient(bindingResult);

        subjectService.save(subject);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus>  update(@PathVariable("id") Integer id,
                                              @RequestBody @Valid SubjectDto subjectDto,
                                              BindingResult bindingResult) {
        Subject subjectToUpdate = SubjectMapper.INSTANCE.toEntity(subjectDto);
     //   subjectValidator.validate(subjectToUpdate, bindingResult);
        if (bindingResult.hasErrors())
            returnErrorsToClient(bindingResult);

        subjectService.update(id, subjectToUpdate);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String delete(@PathVariable("id") Integer id) {
        if (!subjectService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "предмет c id = "+id+" удален";
    }
      @ExceptionHandler
    private ResponseEntity<Errors> handleException(Exceptions e) {
        Errors response = new Errors(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}