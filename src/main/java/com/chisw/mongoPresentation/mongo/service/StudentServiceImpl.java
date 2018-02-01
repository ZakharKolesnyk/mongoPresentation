package com.chisw.mongoPresentation.mongo.service;

import com.chisw.mongoPresentation.mongo.persistense.dto.StudentDto;
import com.chisw.mongoPresentation.mongo.persistense.entity.Group;
import com.chisw.mongoPresentation.mongo.persistense.entity.Student;
import com.chisw.mongoPresentation.mongo.persistense.repository.GroupMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private GroupMongoRepository groupRepository;

    @Override
    public List<StudentDto> listDto() {
        List<Group> groups = groupRepository.findAll();
        if (Objects.isNull(groups)) {
            return Collections.emptyList();
        }
        return groups.stream()
                .filter(g -> Objects.nonNull(g.getStudents()))
                .flatMap(g -> g.getStudents().stream().map(Student::dto))
                .collect(Collectors.toList());
    }

    @Override
    public StudentDto byIdDto(String id) {
        List<Student> students = groupRepository.findStudentById(id).getStudents();
        return Objects.nonNull(students) ? students.stream().filter(s -> s.getId().equals(id)).findFirst().get().dto() : null;
    }

    @Override
    public void delete(String id) {
        Group group = groupRepository.findStudentById(id);
        List<Student> studentsWithoutSpecified = group.getStudents().stream().filter(s -> !s.getId().equals(id)).collect(Collectors.toList());
        group.setStudents(studentsWithoutSpecified);
        groupRepository.save(group);
    }

    @Override
    public List<StudentDto> listDtoByTeacherIdAndGroupId(String teacherId, String groupId) {
        return groupRepository.findOne(groupId).getStudents().stream().map(Student::dto).collect(Collectors.toList());
    }

    @Override
    public StudentDto createAndReturnDtoByTeacherIdAndGroupId(String teacherId, String groupId, StudentDto studentDto) {
        Group group = groupRepository.findOne(groupId);
        Student student = Student.builder().name(studentDto.getName()).build();
        if (Objects.isNull(group.getStudents())) {
            group.setStudents(new ArrayList<>());
        }
        group.getStudents().add(student);
        groupRepository.save(group);
        return student.dto();
    }
}
