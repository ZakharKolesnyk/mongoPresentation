package com.chisw.mongoPresentation.mongo.persistense.repository;

import com.chisw.mongoPresentation.mongo.persistense.entity.Group;
import com.chisw.mongoPresentation.mongo.persistense.entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface StudentMongoRepository extends MongoRepository<Student, String> {

    @Query(value = "{'group':?0}", fields = "{'id':1, 'name':1}")
    List<Student> listByGroupIdDto(String groupId);

    List<Student> findAllByGroupIn(List<Group> groups);
}
