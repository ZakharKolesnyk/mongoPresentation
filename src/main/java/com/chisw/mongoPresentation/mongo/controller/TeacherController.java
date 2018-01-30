package com.chisw.mongoPresentation.mongo.controller;

import com.chisw.mongoPresentation.mongo.persistense.dto.TeacherDto;
import com.chisw.mongoPresentation.mongo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public List<TeacherDto> getTeachers() {
        return teacherService.listDto();
    }

    @GetMapping("/{id}")
    public TeacherDto getTeacher(@PathVariable String id) {
        return teacherService.byIdDto(id);
    }

    @PostMapping
    public TeacherDto createTeacher(@RequestBody TeacherDto teacher) {
        List<Object> list = new ArrayList<>().stream().filter(Objects::nonNull).collect(Collectors.toList());
        return teacherService.createAndReturnDto(teacher);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/{id}")
    public void deleteTeacher(@PathVariable String id) {
        teacherService.delete(id);
    }
}
