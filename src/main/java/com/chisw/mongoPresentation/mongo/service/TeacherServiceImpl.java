package com.chisw.mongoPresentation.mongo.service;

import com.chisw.mongoPresentation.mongo.persistense.dto.TeacherDto;
import com.chisw.mongoPresentation.mongo.persistense.entity.Teacher;
import com.chisw.mongoPresentation.mongo.persistense.repository.TeacherMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMongoRepository repository;

    @Override
    public List<TeacherDto> listDto() {
        return repository.findAll().stream().map(Teacher::dto).collect(Collectors.toList());
    }

    @Override
    public TeacherDto byIdDto(String id) {
        return repository.findOne(id).dto();
    }

    @Override
    public TeacherDto createAndReturnDto(TeacherDto teacher) {
        return repository.save(Teacher.builder().id(teacher.getId()).name(teacher.getName()).build()).dto();
    }

    @Override
    public List<TeacherDto> createAndReturnDto(List<TeacherDto> teacher) {
        return repository.save(
                teacher.stream().map(t -> Teacher.builder().id(t.getId()).name(t.getName()).build()).collect(Collectors.toList())
        ).stream().map(Teacher::dto).collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        repository.delete(id);
    }
}
