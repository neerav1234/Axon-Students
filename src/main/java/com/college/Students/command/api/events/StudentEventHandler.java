package com.college.Students.command.api.events;

import com.college.Students.command.api.data.Student;
import com.college.Students.command.api.data.StudentRepository;
import com.college.Students.query.api.events.FetchedStudentsEvent;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("student")
public class StudentEventHandler {

//    @Autowired
    private StudentRepository repo;

    public StudentEventHandler(StudentRepository repo) {
        this.repo = repo;
    }

    @EventHandler
    public void on(StudentAddedEvent event) {
        System.out.println("Entered Add Event Handler");
        Student student = new Student();
        BeanUtils.copyProperties(event, student);
        repo.save(student);
        System.out.println("Saved in Repo");

    }

    @EventHandler
    public void on(StudentDeletedEvent event) {
        System.out.print("Entered Delete Event Handler with id ");
        System.out.println(event.getSid());
        Student student = repo.getOne(event.getSid().toString());
        repo.delete(student);
        System.out.println("Deleted from Repo");

    }

    @EventHandler
    public void on(FetchedStudentsEvent event) {
        System.out.println("Entered Query Event Handler");

    }
}
