package com.example.demo.services;

import com.example.demo.crudwithvaadin.DashboardRepository;
import com.example.demo.crudwithvaadin.SettingsRepository;
import com.example.demo.entities.Settings;
import com.example.demo.exeptions.SettingsNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SettingsService {
    @Autowired
    private SettingsRepository settingsRepository;
    @Autowired
    private DashboardRepository dashboardRepository;

    public Settings addSettings(Settings settings) {
//         dashboardRepository.findById(settings.dashboard.getId()).orElseThrow()

        return settingsRepository.save(settings);
    }

//    public Settings getSettings(UUID uuid) {
//        return settingsRepository.findById(uuid).orElseThrow(() ->
//                new RuntimeException("test"));
//    }

    public List<Settings> getAllSettings() {
        return StreamSupport.stream(settingsRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Settings getSettingsById(UUID uuid) {
        return settingsRepository.findById(uuid)
                .orElseThrow(() -> new SettingsNotFoundException(uuid));
    }

    public Settings deleteSettings(UUID uuid){
        Settings settings = getSettingsById(uuid);
        this.settingsRepository.delete(settings);
        return settings;
    }
    @Transactional
    public Settings editSettings(UUID uuid, Settings settings){
        Settings settingsToEdit = getSettingsById(uuid);
        settingsToEdit.setValue(settings.getValue());
        return settingsToEdit;
    }
}
