package com.example.micro__Learning.service;
import com.example.micro__Learning.model.Lecon;
import com.example.micro__Learning.repository.LeconRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeconService {
    @Autowired
    private LeconRepository leconRepository;

    public List<Lecon> findAll() {
        return leconRepository.findAll();
    }

    public Lecon save(Lecon lecon) {
        return leconRepository.save(lecon);
    }

    public void deleteById(Long id) {
        leconRepository.deleteById(id);
    }

    public List<Lecon> getLessonsByCourseId(Long courseId) {
        return leconRepository.findByCoursId(courseId);
    }


}
