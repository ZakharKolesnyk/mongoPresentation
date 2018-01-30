package com.chisw.mongoPresentation.jpa.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value="name",description = "for test requests")
public class GroupController {

    @GetMapping("/test")
    @ApiOperation("give me params")
    public String test(){
        return "sadas";
    }
}
