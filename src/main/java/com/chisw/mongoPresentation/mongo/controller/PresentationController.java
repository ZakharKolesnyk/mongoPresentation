package com.chisw.mongoPresentation.mongo.controller;

import com.chisw.mongoPresentation.mongo.persistense.dto.GroupDto;
import com.chisw.mongoPresentation.mongo.persistense.dto.StudentDto;
import com.chisw.mongoPresentation.mongo.persistense.entity.Group;
import com.chisw.mongoPresentation.mongo.persistense.entity.Student;
import com.chisw.mongoPresentation.mongo.persistense.entity.Teacher;
import com.chisw.mongoPresentation.mongo.persistense.repository.GroupMongoRepository;
import com.chisw.mongoPresentation.mongo.persistense.repository.StudentMongoRepository;
import com.chisw.mongoPresentation.mongo.persistense.repository.TeacherMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/present")
public class PresentationController {

    @Autowired
    private TeacherMongoRepository teacherMongoRepository;

    @Autowired
    private GroupMongoRepository groupMongoRepository;

    @Autowired
    private StudentMongoRepository studentMongoRepository;

    @GetMapping("/students_by_teacher")
    private List<StudentDto> studentsByTeacherId(@RequestParam String teacherId) {
        List<Group> groups = teacherMongoRepository.groupDto(teacherId).getGroups();
        return studentMongoRepository.findAllByGroupIn(groups).stream().map(Student::dto).collect(Collectors.toList());
    }

    @GetMapping("/groups_by_teacher")
    private List<GroupDto> groupsByTeacherId(@RequestParam String teacherId) {
        return groupMongoRepository.listByTeacherIdDto(teacherId).stream().map(Group::dto).collect(Collectors.toList());
    }

    @GetMapping("/full_teacher")
    private Teacher teacher(@RequestParam String teacherId) {
        return teacherMongoRepository.findOne(teacherId);
    }
}
