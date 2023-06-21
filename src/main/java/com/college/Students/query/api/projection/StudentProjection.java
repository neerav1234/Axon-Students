package com.college.Students.query.api.projection;

import com.college.Students.command.api.data.Student;
import com.college.Students.command.api.data.StudentRepository;
import com.college.Students.core.api.model.StudentRestModel;
import com.college.Students.query.api.events.StudentFetchedByIdEvent;
import com.college.Students.query.api.events.FetchedStudentsEvent;
import com.college.Students.query.api.events.XDomainEvent;
import com.college.Students.query.api.queries.GetStudentByIdQuery;
import com.college.Students.query.api.queries.GetStudentsQuery;
import com.college.Students.query.api.queries.XDomainQuery;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class StudentProjection {
    private StudentRepository repo;

    public StudentProjection(StudentRepository repo) {
        this.repo = repo;
    }



    @QueryHandler
    public List<StudentRestModel> handle(GetStudentsQuery query) {
        //logix
        System.out.println("Handling Query...");
        FetchedStudentsEvent event = new FetchedStudentsEvent();
        //event release
        System.out.println("Event Released or Query Handled");
        List<Student> students =  repo.findAll();
        List<StudentRestModel> studentRestModels =
                students.stream()
                        .map(student -> StudentRestModel
                                .builder()
                                .sid(student.getSid())
                                .sname(student.getSname())
                                .course(student.getCourse())
                                .build())
                        .collect(Collectors.toList());
        System.out.println("Query Handled");
        return studentRestModels;
    }

    @QueryHandler
    public Optional<Student> handle(GetStudentByIdQuery getStudentByIdQuery) {
        System.out.println("Handeling Query");
        StudentFetchedByIdEvent getStudentByIdEvent = new StudentFetchedByIdEvent();
        BeanUtils.copyProperties(getStudentByIdQuery, getStudentByIdEvent);
        Optional student = repo.findById(getStudentByIdQuery.getSid().toString());
        System.out.println("Query Handled");
        return student;
    }
    @QueryHandler
    public String handle(XDomainQuery xDomainQuery) {
        System.out.println("Query Handeling");
        XDomainEvent xDomainEvent = new XDomainEvent();
        BeanUtils.copyProperties(xDomainQuery, xDomainEvent);
        String s = "Successfully Called";
        return s;
    }

    @EventSourcingHandler
    public void on(FetchedStudentsEvent event) {
        System.out.println("Query Event Sourced");
    }

    @EventSourcingHandler
    public void on(StudentFetchedByIdEvent event) {
        System.out.println("Query Event Sourced");
    }

    @EventSourcingHandler
    public void on(XDomainEvent xDomainEvent) {
        System.out.println("xDomain Query Sourced");
    }

}
