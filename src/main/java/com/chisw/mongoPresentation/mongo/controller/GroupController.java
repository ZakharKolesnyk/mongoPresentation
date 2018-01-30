package com.chisw.mongoPresentation.mongo.controller;

import com.chisw.mongoPresentation.mongo.persistense.dto.GroupDto;
import com.chisw.mongoPresentation.mongo.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers/{teacherId}/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping
    public List<GroupDto> getGroups(@PathVariable String teacherId) {
        return groupService.listDtoByTeacherId(teacherId);
    }

    @GetMapping("/{id}")
    public GroupDto getGroup(@PathVariable String id) {
        return groupService.byIdDto(id);
    }

    @PostMapping
    public GroupDto createGroup(@PathVariable String teacherId, @RequestBody GroupDto group) {
        return groupService.createAndReturnByTeacherIdDto(teacherId, group);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/{id}")
    public void deleteGroup(@PathVariable String id) {
        groupService.delete(id);
    }
}
