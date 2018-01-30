package com.chisw.mongoPresentation.mongo.service;

import com.chisw.mongoPresentation.mongo.persistense.dto.GroupDto;
import com.chisw.mongoPresentation.mongo.persistense.entity.Group;
import com.chisw.mongoPresentation.mongo.persistense.entity.Teacher;
import com.chisw.mongoPresentation.mongo.persistense.repository.GroupMongoRepository;
import com.chisw.mongoPresentation.mongo.persistense.repository.TeacherMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupMongoRepository repository;

    @Autowired
    private TeacherMongoRepository teacherRepository;

    @Override
    public List<GroupDto> listDto() {
        return repository.findAll().stream().map(Group::dto).collect(Collectors.toList());
    }

    @Override
    public GroupDto byIdDto(String id) {
        return repository.findOne(id).dto();
    }

    @Override
    public GroupDto createAndReturnDto(GroupDto groupDto) {
        return repository.save(Group.builder().id(groupDto.getId()).name(groupDto.getName()).build()).dto();
    }

    @Override
    public List<GroupDto> createAndReturnDto(List<GroupDto> group) {
        return repository.save(
                group.stream().map(g -> Group.builder().id(g.getId()).name(g.getName()).build()).collect(Collectors.toList())
        ).stream().map(Group::dto).collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        repository.delete(id);
    }

    @Override
    public List<GroupDto> listDtoByTeacherId(String teacherId) {
        return repository.listByTeacherIdDto(teacherId)
                .stream().map(Group::dto).collect(Collectors.toList());
    }

    @Override
    public GroupDto createAndReturnByTeacherIdDto(String teacherId, GroupDto groupDto) {
        Teacher teacher = teacherRepository.findOne(teacherId);
        Group group = repository.save(Group.builder().name(groupDto.getName()).teachers(Collections.singletonList(teacher)).build());
        if (Objects.isNull(teacher.getGroups())) {
            teacher.setGroups(new ArrayList<>());
        }
        teacher.getGroups().add(group);
        teacherRepository.save(teacher);
        return group.dto();
    }
}
