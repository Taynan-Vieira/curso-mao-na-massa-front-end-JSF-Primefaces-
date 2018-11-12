package br.com.devdojo.examgenerator.persistence.dao;

import br.com.devdojo.examgenerator.annotation.ExceptionHandler;
import br.com.devdojo.examgenerator.custom.CustomRestTemplate;
import br.com.devdojo.examgenerator.custom.CustomTypeReference;
import br.com.devdojo.examgenerator.persistence.model.Course;
import br.com.devdojo.examgenerator.util.ApiUtil;
import br.com.devdojo.examgenerator.util.JsonUtil;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.HttpMethod.*;

public class CourseDAO implements Serializable {
    private final String LIST_URL = ApiUtil.BASE_URL + "/professor/course/list";
    private final String DELETE_OR_FIND_ONE_URL = ApiUtil.BASE_URL + "/professor/course/{id}";
    private final String LIST_DATE_CREATION = ApiUtil.BASE_URL + "/professor/course/listDateCreationCourse/{id}";
    private final String LIST_DATE_EVENT = ApiUtil.BASE_URL + "/professor/course/listDateEventCourse/{id}";
    private final String CREATE_UPDATE_URL = ApiUtil.BASE_URL + "/professor/course/";
    private final CustomRestTemplate restRemplate;
    private final JsonUtil jsonUtil;
    private final CustomTypeReference<List<Course>> listcourse;
    private final CustomTypeReference<List<Course>> listcoursedatecreate;
    private final CustomTypeReference<List<Course>> listcoursedateevent;

    @Inject
    public CourseDAO(CustomRestTemplate restTRemplate, CustomRestTemplate restRemplate, JsonUtil jsonUtil, CustomTypeReference<List<Course>> listCourse, CustomTypeReference<List<Course>> listcoursedatecreate, CustomTypeReference<List<Course>> listcoursedateevent) {
        this.restRemplate = restRemplate;
        this.jsonUtil = jsonUtil;
        this.listcourse = listCourse;
        this.listcoursedatecreate = listcoursedatecreate;
        this.listcoursedateevent = listcoursedateevent;
    }

    @ExceptionHandler
    public List<Course> list(String name) {
        UriComponents url = UriComponentsBuilder.fromUriString(LIST_URL).queryParam("name", name).build();
        ResponseEntity<List<Course>> exchange = restRemplate.exchange(url.toString(), GET, jsonUtil.tokenizedHttpEntityHeader(), listcourse.typeReference());
        return exchange.getBody();
    }

    @ExceptionHandler
    public List<Course> listDateCreateCourse(LocalDate dateCreation) {
        UriComponents url = UriComponentsBuilder.fromUriString(LIST_DATE_CREATION).queryParam("dateCreation", dateCreation).build();
        ResponseEntity<List<Course>> exchange = restRemplate.exchange(url.toString(), GET, jsonUtil.tokenizedHttpEntityHeader(), listcoursedatecreate.typeReference());
        return exchange.getBody();
    }

    @ExceptionHandler
    public List<Course> listDateEventCourse(LocalDate dateEvent) {
        UriComponents url = UriComponentsBuilder.fromUriString(LIST_DATE_EVENT).queryParam("dateEvent", dateEvent).build();
        ResponseEntity<List<Course>> exchange = restRemplate.exchange(url.toString(), GET, jsonUtil.tokenizedHttpEntityHeader(), listcoursedateevent.typeReference());
        return exchange.getBody();
    }

    @ExceptionHandler
    public Course findBy(long id) {
        return restRemplate.exchange(DELETE_OR_FIND_ONE_URL, GET, jsonUtil.tokenizedHttpEntityHeader(), Course.class, id).getBody();
    }

    @ExceptionHandler
    public Course update(Course course) {
        return createOrUpdate(PUT, course);
    }

    public Course create(Course course) {
        return createOrUpdate(POST, course);
    }

    private Course createOrUpdate(HttpMethod httpMethod, Course course) {
        return restRemplate.exchange(CREATE_UPDATE_URL, httpMethod, jsonUtil.tokenizedHttpEntityHeader(course), Course.class).getBody();
    }

    public void delete(Course course) {
        restRemplate.exchange(DELETE_OR_FIND_ONE_URL, DELETE, jsonUtil.tokenizedHttpEntityHeader(course), Course.class, course.getId());
    }
}
