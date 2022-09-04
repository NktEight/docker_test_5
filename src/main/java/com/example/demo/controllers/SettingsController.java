package com.example.demo.controllers;

//import ch.qos.logback.classic.Logger;

import ch.qos.logback.classic.LoggerContext;
import com.example.demo.crudwithvaadin.SettingsRepository;
import com.example.demo.dto.SettingsDto;
import com.example.demo.entities.Settings;
import com.example.demo.services.SettingsService;
import org.apache.logging.log4j.spi.LoggerContextFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/settings")
@PropertySource("classpath:application.properties")
public class SettingsController {
    @Autowired
    private SettingsRepository settingsRepository;
    @Autowired
    private SettingsService settingsService;
    private static final Logger logger = LoggerFactory.getLogger(SettingsController.class);

    @PostMapping
    public ResponseEntity<SettingsDto> addSettings(@RequestBody SettingsDto settingsDto) {
        Settings settings = settingsService.addSettings(Settings.from(settingsDto));
        return new ResponseEntity<>(SettingsDto.from(settings) , HttpStatus.OK);

    }
    @GetMapping
    public ResponseEntity<List<SettingsDto>> getListSettings(){
        List<Settings> settingsList = settingsService.getAllSettings();
        List<SettingsDto> settingsDtoList = settingsList.stream().map(SettingsDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(settingsDtoList, HttpStatus.OK);
    }

    @GetMapping(value = "{uuid}")
    public ResponseEntity<SettingsDto> getSettings(@PathVariable final UUID uuid){
        Settings settings = settingsService.getSettingsById(uuid);
        return new ResponseEntity<>(SettingsDto.from(settings), HttpStatus.OK);
    }

    @DeleteMapping(value = "{uuid}")
    public ResponseEntity<SettingsDto> deleteSettings(@PathVariable final UUID uuid){
        Settings settings = settingsService.deleteSettings(uuid);
        return new ResponseEntity<>(SettingsDto.from(settings), HttpStatus.OK);
    }

    @PutMapping(value = "{uuid}")
    public ResponseEntity<SettingsDto> editSettings(@PathVariable final UUID uuid, @RequestBody final SettingsDto settingsDto){
        Settings settings = settingsService.editSettings(uuid, Settings.from(settingsDto));
        return new ResponseEntity<>(SettingsDto.from(settings), HttpStatus.OK);
    }
//
//
//    @GetMapping("/getAllSettings")
//    public String getAllSettings() {
//        logger.info("getAllSettings used");
//        return this.settingsRepository.findAll().toString();
//    }
//
//    @GetMapping("/getSettingsById/{uuid}")
//    public String getSettingsById(@PathVariable UUID uuid) {
//        return this.settingsRepository.findById(uuid).toString();
//    }
//
//    @GetMapping("/getHello")
//    public String getTestHelloMessage() {
//        return "Hello";
//    }
//
//    @DeleteMapping("/delete/{uuid}")
//    public void deleteById(@PathVariable UUID uuid) {
//        this.settingsRepository.deleteById(uuid);
//        logger.info("Settings with uuid: \"" + uuid.toString() + "\" has been deleted");
//    }
//
//    @PutMapping("/update")
//    public void updateById(@RequestBody Settings settings) {
//        this.settingsRepository.save(settings);
//        logger.info("Settings: " + settings.toString() + " has been updated");
//    }


}
