package com.example.micro__Learning.repository;
import com.example.micro__Learning.model.Cours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CoursRepository extends JpaRepository<Cours, Long> {
    // Correcte méthode pour trouver les cours par l'ID de la catégorie
    List<Cours> findByCategorie_Id(Long categoryId);
}
