package br.com.devdojo.examgenerator.persistence.dao;

import br.com.devdojo.examgenerator.annotation.ExceptionHandler;
import br.com.devdojo.examgenerator.custom.CustomRestTemplate;
import br.com.devdojo.examgenerator.persistence.model.Question;
import br.com.devdojo.examgenerator.util.ApiUtil;
import br.com.devdojo.examgenerator.util.JsonUtil;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

import static org.springframework.http.HttpMethod.*;

public class QuestionDAO implements Serializable {
    private final String LIST_URL = ApiUtil.BASE_URL + "/professor/course/question/list/{courseId}/";
    private final String DELETE_OR_FIND_ONE_URL = ApiUtil.BASE_URL + "/professor/course/question/{id}";
    private final String CREATE_UPDATE_URL = ApiUtil.BASE_URL + "/professor/course/question/";
    private final CustomRestTemplate restRemplate;
    private final JsonUtil jsonUtil;
    private final ParameterizedTypeReference<List<Question>> questionListTypeReference = new ParameterizedTypeReference<List<Question>>() {
    };

    @Inject
    public QuestionDAO(CustomRestTemplate restRemplate, JsonUtil jsonUtil) {
        this.restRemplate = restRemplate;
        this.jsonUtil = jsonUtil;
    }


    public List<Question> list(long courseId, String title) {
        UriComponents url = UriComponentsBuilder.fromUriString(LIST_URL).queryParam("title", title).build();
        ResponseEntity<List<Question>> exchange = restRemplate.exchange(url.toString(), GET, jsonUtil.tokenizedHttpEntityHeader(), questionListTypeReference, courseId);
        return exchange.getBody();
    }

    public Question findBy(long id) {
        return restRemplate.exchange(DELETE_OR_FIND_ONE_URL, GET, jsonUtil.tokenizedHttpEntityHeader(), Question.class, id).getBody();
    }

    @ExceptionHandler
    public Question update(Question question) {
        return createOrUpdate(PUT, question);
    }

    public Question create(Question question) {
        return createOrUpdate(POST, question);
    }

    private Question createOrUpdate(HttpMethod httpMethod, Question question) {
        return restRemplate.exchange(CREATE_UPDATE_URL, httpMethod, jsonUtil.tokenizedHttpEntityHeader(question), Question.class).getBody();
    }

    public void delete(Question question) {
        restRemplate.exchange(DELETE_OR_FIND_ONE_URL, DELETE, jsonUtil.tokenizedHttpEntityHeader(question), Question.class, question.getId());
    }
}
