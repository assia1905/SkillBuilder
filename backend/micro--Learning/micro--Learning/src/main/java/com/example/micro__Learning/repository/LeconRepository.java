package com.example.micro__Learning.repository;

import com.example.micro__Learning.model.Lecon;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LeconRepository extends JpaRepository<Lecon, Long> {
    // Méthode pour récupérer les leçons associées à un cours
    List<Lecon> findByCoursId(Long coursId);
}
