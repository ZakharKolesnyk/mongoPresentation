package com.chisw.mongoPresentation.mongo.persistense.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDto implements Serializable {

    private String id;

    private String name;

    private String text;
}
