package com.taras.service;

import com.taras.Repository.DisciplineRepository;
import com.taras.Repository.StudentRepository;
import com.taras.domain.Discipline;
import com.taras.domain.Student;
import com.taras.exceptions.ExistsStudentForDisciplinrException;
import com.taras.exceptions.NoSuchDisciplineException;
import com.taras.exceptions.NoSuchStudentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class DisciplineService {
    @Autowired
    DisciplineRepository disciplineRepository;

    @Autowired
    StudentRepository studentRepository;

    public Set<Discipline> getDisciplineByStudentId(Long person_id) throws NoSuchStudentException {
//        Person person = personRepository.findOne(person_id);//1.5.9
        Student student= studentRepository.findById(person_id).get();//2.0.0.M7
        if (student == null) throw new NoSuchStudentException();
        return student.getDisciplines();
    }

    public Discipline getDiscipline(Long discipline_id) throws NoSuchDisciplineException {
//        Book book = bookRepository.findOne(book_id);//1.5.9
        Discipline discipline = disciplineRepository.findById(discipline_id).get();//2.0.0.M7
        if (discipline == null) throw new NoSuchDisciplineException();
        return discipline;
    }

    public List<Discipline> getAllDisciplines() {
        return disciplineRepository.findAll();
    }

    @Transactional
    public void createDiscipline(Discipline book) {
        disciplineRepository.save(book);
    }

    @Transactional
    public void updateDiscipline(Discipline uDiscipline, Long discipline_id) throws NoSuchDisciplineException {
//        Book book = bookRepository.findOne(book_id);//1.5.9
        Discipline discipline = disciplineRepository.findById(discipline_id).get();//2.0.0.M7
        if (discipline == null) throw new NoSuchDisciplineException();
        //update
        discipline.setDisciplineName(uDiscipline.getDisciplineName());

    }

    @Transactional
    public void deleteDiscipline(Long discipline_id) throws NoSuchDisciplineException, ExistsStudentForDisciplinrException{
//        Book book = bookRepository.findOne(book_id);//1.5.9
        Discipline discipline = disciplineRepository.findById(discipline_id).get();//2.0.0.M7

        if (discipline == null) throw new NoSuchDisciplineException();
        if (discipline.getStudents().size() != 0) throw new ExistsStudentForDisciplinrException();
        disciplineRepository.delete(discipline);
    }
}
