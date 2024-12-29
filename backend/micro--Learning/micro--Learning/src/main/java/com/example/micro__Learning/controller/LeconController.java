package com.example.micro__Learning.controller;
import com.example.micro__Learning.model.Lecon;
import com.example.micro__Learning.service.LeconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lecons")
public class LeconController {
    @Autowired
    private LeconService leconService;

    @GetMapping
    public List<Lecon> getAllLecons() {
        return leconService.findAll();
    }

    @PostMapping
    public Lecon createLecon(@RequestBody Lecon lecon) {
        return leconService.save(lecon);
    }

    @DeleteMapping("/{id}")
    public void deleteLecon(@PathVariable Long id) {
        leconService.deleteById(id);
    }
}
