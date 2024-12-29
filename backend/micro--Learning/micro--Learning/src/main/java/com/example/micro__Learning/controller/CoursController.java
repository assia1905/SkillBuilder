package com.example.micro__Learning.controller;

import com.example.micro__Learning.model.Cours;
import com.example.micro__Learning.model.Lecon;
import com.example.micro__Learning.service.CoursService;
import com.example.micro__Learning.service.LeconService; // Ajouter le service pour les leçons
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cours")
public class CoursController {
    @Autowired
    private CoursService coursService;

    @Autowired
    private LeconService leconService; // Assurez-vous que le service Lecon existe

    @GetMapping
    public List<Cours> getAllCours() {
        return coursService.findAll();
    }

    @PostMapping
    public Cours createCours(@RequestBody Cours cours) {
        return coursService.save(cours);
    }

    @DeleteMapping("/{id}")
    public void deleteCours(@PathVariable Long id) {
        coursService.deleteById(id);
    }
    @GetMapping("/{courseId}/lecons")
    public ResponseEntity<List<Lecon>> getLessonsByCourseId(@PathVariable Long courseId) {
        List<Lecon> lecons = coursService.getLessonsByCourseId(courseId);
        return ResponseEntity.ok(lecons);
    }


}
