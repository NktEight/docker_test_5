package com.example.demo.services;

import com.example.demo.controllers.SettingsController;
import com.example.demo.crudwithvaadin.DashboardRepository;
import com.example.demo.crudwithvaadin.SettingsRepository;
import com.example.demo.entities.Dashboard;
import com.example.demo.entities.Settings;
import com.example.demo.exeptions.DashboardNotFoundException;
import com.example.demo.exeptions.SettingsIsAlreadyAssignedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DashboardService {
    @Autowired
    private DashboardRepository dashboardRepository;
    @Autowired
    private SettingsService settingsService;
    private static final Logger logger = LoggerFactory.getLogger(SettingsController.class);
    public Dashboard addDashboard(Dashboard dashboard){
        logger.info("FINAL DASHBOARD CREATING: " + dashboard);
        return dashboardRepository.save(dashboard);
    }

    public List<Dashboard> getAllDashboards() {
        return StreamSupport.stream(dashboardRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Dashboard getDashboard(Long id){
        return dashboardRepository.findById(id).orElseThrow(() -> new DashboardNotFoundException(id));
    }

    public Dashboard deleteDashboardById(Long id){
        Dashboard dashboard = getDashboard(id);
        dashboard.getSettingsList().forEach(e -> settingsService.deleteSettings(e.uuid));
        dashboardRepository.delete(dashboard);
        return dashboard;
    }

    public void deleteAll(){
       dashboardRepository.deleteAll();;
    }
    @Transactional
    public Dashboard editDashboard(Long id, Dashboard dashboard){
        Dashboard dashboardToEdit = getDashboard(id);
//        dashboardRepository.delete(dashboard);
        return dashboardToEdit;
    }
    @Transactional
    public Dashboard addSettingsToDashboard(Long dashboardId, UUID settingsUUID){
            Dashboard dashboard = getDashboard(dashboardId);
            Settings settings = settingsService.getSettingsById(settingsUUID);
            if(Objects.nonNull(settings.getDashboard())){
                throw new SettingsIsAlreadyAssignedException(settings.getUuid(), settings.getDashboard().getId());
            }
            dashboard.addSettings(settings);
            settings.setDashboard(dashboard);
            return dashboard;
    }
    @Transactional
    public Dashboard removeSettingsFromDashboard(Long dashboardId, UUID settingsUUID){
        Dashboard dashboard = getDashboard(dashboardId);
        Settings settings = settingsService.getSettingsById(settingsUUID);
        dashboard.removeSettings(settings);
        return dashboard;
    }
}
