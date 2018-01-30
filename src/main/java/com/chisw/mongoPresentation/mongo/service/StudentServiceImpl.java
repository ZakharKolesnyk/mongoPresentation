package com.chisw.mongoPresentation.mongo.service;

import com.chisw.mongoPresentation.mongo.persistense.dto.StudentDto;
import com.chisw.mongoPresentation.mongo.persistense.entity.Group;
import com.chisw.mongoPresentation.mongo.persistense.entity.Student;
import com.chisw.mongoPresentation.mongo.persistense.repository.GroupMongoRepository;
import com.chisw.mongoPresentation.mongo.persistense.repository.StudentMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMongoRepository repository;

    @Autowired
    private GroupMongoRepository groupRepository;

    @Override
    public List<StudentDto> listDto() {
        return repository.findAll().stream().map(Student::dto).collect(Collectors.toList());
    }

    @Override
    public StudentDto byIdDto(String id) {
        return repository.findOne(id).dto();
    }

    @Override
    public StudentDto createAndReturnDto(StudentDto student) {
        return repository.save(Student.builder().id(student.getId()).name(student.getName()).build()).dto();
    }

    @Override
    public List<StudentDto> createAndReturnDto(List<StudentDto> student) {
        return repository.save(
                student.stream().map(s -> Student.builder().id(s.getId()).name(s.getName()).build()).collect(Collectors.toList())
        ).stream().map(Student::dto).collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        repository.delete(id);
    }

    @Override
    public List<StudentDto> listDtoByTeacherIdAndGroupId(String teacherId, String groupId) {
        return repository.listByGroupIdDto(groupId)
                .stream().map(Student::dto).collect(Collectors.toList());
    }

    @Override
    public StudentDto createAndReturnDtoByTeacherIdAndGroupId(String teacherId, String groupId, StudentDto studentDto) {
        Group group = groupRepository.findOne(groupId);
        Student student = Student.builder().name(studentDto.getName()).group(group).build();
        repository.save(student);
        if (Objects.isNull(group.getStudents())){
            group.setStudents(new ArrayList<>());
        }
        group.getStudents().add(student);
        groupRepository.save(group);
        return student.dto();
    }
}
