package oksana.internship.lesson3.controllers;

import oksana.internship.lesson3.model.GroupName;
import oksana.internship.lesson3.service.GroupService;
import oksana.internship.lesson3.service.dto.GroupDto;
import oksana.internship.lesson3.service.dto.StudentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {
    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<GroupDto> getGroups() {
        return groupService.findAll();
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> create(@RequestBody GroupDto groupDto) {
        groupService.create(groupDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{groupName}")
    public List<StudentDto> findStudentsByGroupName(@PathVariable("groupName") String groupName) {
        return groupService.findAllStudentByGroupName(groupName);
    }
}
