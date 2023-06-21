package com.college.Students.query.api.controller;



import com.college.Students.query.api.queries.XDomainQuery;
import io.opentelemetry.instrumentation.annotations.SpanAttribute;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.college.Students.command.api.data.Student;
import com.college.Students.core.api.model.StudentRestModel;
import com.college.Students.query.api.queries.GetStudentByIdQuery;
import com.college.Students.query.api.queries.GetStudentsQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentQueryController {

    private QueryGateway queryGateway;
    private static final Logger logger = LoggerFactory.getLogger(StudentQueryController.class);

    @Autowired
    public StudentQueryController(QueryGateway queryGateway, RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.queryGateway = queryGateway;
    }

    private final RestTemplate restTemplate;

    @GetMapping("/")
    public List<StudentRestModel> getStudents() {
        GetStudentsQuery query = new GetStudentsQuery();
        List<StudentRestModel> students = queryGateway.query(query, ResponseTypes.multipleInstancesOf(StudentRestModel.class)).join();
        logger.info("You are fetching students: {}", students);
        System.out.println("Queried");
        return students;
    }
    @WithSpan
    @GetMapping("/{sid}")
    public Student getStudentById(@PathVariable @SpanAttribute("sid") String sid) {
        System.out.println("Queried");
        GetStudentByIdQuery getStudentByIdQuery  = new GetStudentByIdQuery(sid);
        Student student = queryGateway.query(getStudentByIdQuery, ResponseTypes.instanceOf(Student.class)).join();
        System.out.println("Query Ended");
        return student;
    }

    @GetMapping("/endpoint")
    public String crossDomain() {
        System.out.println("inside ms1");
        XDomainQuery xDomainQuery = new XDomainQuery();
        String s = queryGateway.query(xDomainQuery, ResponseTypes.instanceOf(String.class)).join();
        System.out.println("Query Complete");
        return s;
    }

}
