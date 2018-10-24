package br.com.devdojo.examgenerator.persistence.dao;

import br.com.devdojo.examgenerator.annotation.ExceptionHandler;
import br.com.devdojo.examgenerator.custom.CustomRestTemplate;
import br.com.devdojo.examgenerator.custom.CustomTypeReference;
import br.com.devdojo.examgenerator.persistence.model.Course;
import br.com.devdojo.examgenerator.util.ApiUtil;
import br.com.devdojo.examgenerator.util.JsonUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

import static org.springframework.http.HttpMethod.GET;

public class CourseDAO implements Serializable {
    private final String LIST_URL = ApiUtil.BASE_URL + "/professor/course/list";
    private final CustomRestTemplate restRemplate;
    private final JsonUtil jsonUtil;
    private final CustomTypeReference<List<Course>> listcourse;

    @Inject
    public CourseDAO(CustomRestTemplate restTRemplate, CustomRestTemplate restRemplate, JsonUtil jsonUtil, CustomTypeReference<List<Course>> listCourse) {
        this.restRemplate = restRemplate;
        this.jsonUtil = jsonUtil;
        this.listcourse = listCourse;
    }

    @ExceptionHandler
    public List<Course> list(String name){
        UriComponents url = UriComponentsBuilder.fromUriString(LIST_URL).queryParam("name", name).build();
        ResponseEntity<List<Course>> exchange = restRemplate.exchange(url.toString(), GET, jsonUtil.tokenizedHttpEntityHeader(), listcourse.typeReference());
        return exchange.getBody();
    }
}
