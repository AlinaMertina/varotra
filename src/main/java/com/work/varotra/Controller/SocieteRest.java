package com.work.varotra.Controller;


import java.text.SimpleDateFormat;

import org.hibernate.annotations.Parameter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.work.varotra.Service.SocieteService;
import com.work.varotra.Service.FourniseurService;

import lombok.RequiredArgsConstructor;
import com.work.varotra.Entity.Stock;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path="/societe")
@RequiredArgsConstructor
public class SocieteRest {
    private final SocieteService societeService;

   
}
