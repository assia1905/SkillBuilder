package com.example.micro__Learning.service;
import com.example.micro__Learning.model.Categorie;
import com.example.micro__Learning.model.Cours;
import com.example.micro__Learning.repository.CategorieRepository;
import com.example.micro__Learning.repository.CoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieService {
    @Autowired
    private CategorieRepository categorieRepository;

    @Autowired
    private CoursRepository coursRepository;

    public List<Categorie> findAll() {
        return categorieRepository.findAll();
    }

    public Categorie save(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    public void deleteById(Long id) {
        categorieRepository.deleteById(id);
    }

    public List<Cours> findCoursesByCategoryId(Long categoryId) {
        // Utilisez la méthode correcte du repository
        return coursRepository.findByCategorie_Id(categoryId);
    }

}
