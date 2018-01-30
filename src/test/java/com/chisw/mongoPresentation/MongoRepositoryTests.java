package com.chisw.mongoPresentation;

import com.chisw.mongoPresentation.mongo.persistense.dto.TeacherDto;
import com.chisw.mongoPresentation.mongo.persistense.repository.TeacherMongoRepository;
import com.chisw.mongoPresentation.mongo.service.TeacherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoRepositoryTests {

	@Autowired
	private TeacherService teacherService;

	@Autowired
	private TeacherMongoRepository teacherMongoRepository;

	@Test
	public void contextLoads() {
		teacherService.createAndReturnDto(TeacherDto.builder().id("asdasd").name("asdasdasdasdasd").build());
		System.out.println(teacherService.byIdDto("asdasd"));
		System.out.println(teacherMongoRepository.count());
	}

}
