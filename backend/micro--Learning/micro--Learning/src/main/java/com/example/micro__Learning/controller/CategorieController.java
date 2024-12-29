package com.example.micro__Learning.controller;
import com.example.micro__Learning.model.Categorie;
import com.example.micro__Learning.model.Cours;
import com.example.micro__Learning.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategorieController {
    @Autowired
    private CategorieService categorieService;

    @GetMapping
    public List<Categorie> getAllCategories() {
        return categorieService.findAll();
    }
    @GetMapping("/{id}/cours")
    public List<Cours> getCoursesByCategoryId(@PathVariable Long id) {
        return categorieService.findCoursesByCategoryId(id);
    }
    @PostMapping
    public Categorie createCategorie(@RequestBody Categorie categorie) {
        return categorieService.save(categorie);
    }

    @DeleteMapping("/{id}")
    public void deleteCategorie(@PathVariable Long id) {
        categorieService.deleteById(id);
    }

}
