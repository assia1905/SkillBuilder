package com.example.micro__Learning.service;
import com.example.micro__Learning.model.Cours;
import com.example.micro__Learning.model.Lecon;
import com.example.micro__Learning.repository.CoursRepository;
import com.example.micro__Learning.repository.LeconRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoursService {
    @Autowired
    private CoursRepository coursRepository;

    @Autowired
    private LeconRepository leconRepository;

    public List<Cours> findAll() {
        return coursRepository.findAll();
    }

    public Cours save(Cours cours) {
        return coursRepository.save(cours);
    }

    public void deleteById(Long id) {
        coursRepository.deleteById(id);
    }


    public List<Lecon> getLessonsByCourseId(Long courseId) {
        return leconRepository.findByCoursId(courseId);
    }
}
