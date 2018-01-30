package com.chisw.mongoPresentation.mongo.persistense.repository;

import com.chisw.mongoPresentation.mongo.persistense.entity.Group;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface GroupMongoRepository extends MongoRepository<Group, String> {

    @Query(value = "{'teachers':?0}", fields = "{'id':1, 'name':1}")
    List<Group> listByTeacherIdDto(String teacherId);
}
