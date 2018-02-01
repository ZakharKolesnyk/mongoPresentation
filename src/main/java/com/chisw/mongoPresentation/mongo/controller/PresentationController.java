package com.chisw.mongoPresentation.mongo.controller;

import com.chisw.mongoPresentation.mongo.persistense.dto.GroupDto;
import com.chisw.mongoPresentation.mongo.persistense.dto.StudentDto;
import com.chisw.mongoPresentation.mongo.persistense.entity.Group;
import com.chisw.mongoPresentation.mongo.persistense.entity.Student;
import com.chisw.mongoPresentation.mongo.persistense.entity.Teacher;
import com.chisw.mongoPresentation.mongo.persistense.repository.GroupMongoRepository;
import com.chisw.mongoPresentation.mongo.persistense.repository.TeacherMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/present")
public class PresentationController {

    @Autowired
    private TeacherMongoRepository teacherMongoRepository;

    @Autowired
    private GroupMongoRepository groupMongoRepository;

    @GetMapping("/students_by_teacher")
    public List<StudentDto> studentsByTeacherId(@RequestParam String teacherId) {
        List<Group> groups = teacherMongoRepository.groupDto(teacherId).getGroups();
        if (Objects.isNull(groups)) {
            return Collections.emptyList();
        }
        return groups.stream()
                .filter(g -> Objects.nonNull(g.getStudents()))
                .flatMap(g -> g.getStudents().stream().map(Student::dto))
                .collect(Collectors.toList());
    }

    @GetMapping("/groups_by_teacher")
    public List<GroupDto> groupsByTeacherId(@RequestParam String teacherId) {
        return groupMongoRepository.listByTeacherIdDto(teacherId).stream().map(Group::dto).collect(Collectors.toList());
    }

    @GetMapping("/full_teacher")
    public Teacher teacher(@RequestParam String teacherId) {
        return teacherMongoRepository.findOne(teacherId);
    }

    @PostMapping("/teacher_search")
    public List<Teacher> searchByIndex(@RequestParam String query) {
        return teacherMongoRepository.textSearch(query);
    }

}
