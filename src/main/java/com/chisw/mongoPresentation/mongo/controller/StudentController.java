package com.chisw.mongoPresentation.mongo.controller;

import com.chisw.mongoPresentation.mongo.persistense.dto.StudentDto;
import com.chisw.mongoPresentation.mongo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers/{teacherId}/groups/{groupId}/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<StudentDto> getStudents(@PathVariable String teacherId, @PathVariable String groupId) {
        return studentService.listDtoByTeacherIdAndGroupId(teacherId, groupId);
    }

    @GetMapping("/{id}")
    public StudentDto getStudent(@PathVariable String id) {
        return studentService.byIdDto(id);
    }

    @PostMapping
    public StudentDto createStudent(@PathVariable String teacherId,
                                    @PathVariable String groupId,
                                    @RequestBody StudentDto student) {
        return studentService.createAndReturnDtoByTeacherIdAndGroupId(teacherId, groupId, student);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable String id) {
        studentService.delete(id);
    }
}
