package com.example.Lubrigo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/oil")
public class RecommendsController {


    @Autowired
    private RecommendRepository recommendRepository;



    @GetMapping("/all")
    public List<Recommends> getAllRecommends(){
        return recommendRepository.findAll();
    }
}
