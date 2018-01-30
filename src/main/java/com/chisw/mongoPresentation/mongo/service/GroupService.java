package com.chisw.mongoPresentation.mongo.service;

import com.chisw.mongoPresentation.mongo.persistense.dto.GroupDto;

import java.util.List;

public interface GroupService {

    List<GroupDto> listDto();

    GroupDto byIdDto(String id);

    GroupDto createAndReturnDto(GroupDto group);

    List<GroupDto> createAndReturnDto(List<GroupDto> group);

    void delete(String id);

    List<GroupDto> listDtoByTeacherId(String teacherId);

    GroupDto createAndReturnByTeacherIdDto(String teacherId, GroupDto group);
}
