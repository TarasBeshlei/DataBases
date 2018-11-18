package com.taras.DTO;

import com.taras.controller.DisciplineController;
import com.taras.domain.Student;
import com.taras.exceptions.NoSuchDisciplineException;
import com.taras.exceptions.NoSuchStudentException;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class StudentDTO extends ResourceSupport {
    Student student;
    public StudentDTO(Student student, Link selLink) throws NoSuchStudentException, NoSuchDisciplineException{
        this.student = student;
        add(selLink);
        add(linkTo(methodOn(DisciplineController.class).getDisciplineByStudentID(student.getId())).withRel("disciplines"));
    }

    public Long getStudentId(){
        return student.getId();
    }

    public String getSurname(){
        return student.getSurname();
    }

    public String getName(){
        return student.getName();
    }
    public String getEmail() {
        return student.getEmail();
    }

    public String getCity() {
        return student.getCity();
    }

    public String getGroups() {
        if(student.getGroups()== null) return "";
        return student.getGroups().getGroup_name();
    }

}
