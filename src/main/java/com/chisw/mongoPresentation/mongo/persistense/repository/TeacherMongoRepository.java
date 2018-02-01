package com.chisw.mongoPresentation.mongo.persistense.repository;

import com.chisw.mongoPresentation.mongo.persistense.entity.Teacher;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TeacherMongoRepository extends MongoRepository<Teacher, String> {

    @Query(value = "{'id':?0}", fields = "{'groups':1}")
    Teacher groupDto(String teacherId);

    @Query(value = "{$text:{$search:?0}}")
    List<Teacher> textSearch(String query);
}
