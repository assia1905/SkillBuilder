package com.example.micro__Learning.repository;

import com.example.micro__Learning.model.Categorie;
import com.example.micro__Learning.model.Cours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Long> {
}
