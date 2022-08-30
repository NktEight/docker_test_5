package com.example.demo.controllers;

import com.example.demo.crudwithvaadin.SettingsRepository;
import com.example.demo.entities.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@PropertySource("classpath:application.properties")
public class SettingsController {
    @Autowired
   private SettingsRepository settingsRepository;

    @GetMapping("/getAllSettings")
    public String getAllSettings(){
        return this.settingsRepository.findAll().toString();
    }

    @GetMapping("/getSettingsById/{uuid}")
    public String getSettingsById(@PathVariable UUID uuid){
        return this.settingsRepository.findById(uuid).toString();
    }

    @GetMapping("/getHello")
    public String getTestHelloMessage(){
        return "Hello";
    }

    @PostMapping("/createSettings")
    public void createSettings(@RequestBody Settings settings){
        this.settingsRepository.save(settings);
    }
}
