package com.example.demo.controllers;

import com.example.demo.crudwithvaadin.DashboardRepository;
import com.example.demo.crudwithvaadin.SettingsRepository;
import com.example.demo.dto.DashboardDto;
import com.example.demo.dto.SettingsDto;
import com.example.demo.entities.Dashboard;
import com.example.demo.entities.Settings;
import com.example.demo.services.DashboardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
//@PropertySource("classpath:application.properties")
@RequestMapping("/dashboard")
public class DashboardController {
    @Autowired
    private DashboardRepository dashboardRepository;
    @Autowired
    private DashboardService dashboardService;
    private static final Logger logger = LoggerFactory.getLogger(DashboardController.class);

    @PostMapping
    public ResponseEntity<DashboardDto> addDashboard(@RequestBody final DashboardDto dashboardDto){
        Dashboard dashboard = dashboardService.addDashboard(Dashboard.from(dashboardDto));
        logger.info("NKT: " + dashboard.toString());
        return new ResponseEntity<>(DashboardDto.from(dashboard), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<DashboardDto>> getDashboards(){
        List<Dashboard> dashboardList = dashboardService.getAllDashboards();
        List<DashboardDto> dashboardDtoList = dashboardList.stream().map(DashboardDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(dashboardDtoList, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<DashboardDto> getDashboard(@PathVariable final Long id){
        Dashboard dashboard = dashboardService.getDashboard(id);
        return new ResponseEntity<>(DashboardDto.from(dashboard), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<DashboardDto> deleteDashboard(@PathVariable final Long id){
        Dashboard dashboard = dashboardService.deleteDashboardById(id);
        return new ResponseEntity<>(DashboardDto.from(dashboard), HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<DashboardDto> deleteDashboard(@PathVariable final Long id, @RequestBody final DashboardDto dashboardDto){
        Dashboard dashboard = dashboardService.editDashboard(id, Dashboard.from(dashboardDto));

        return new ResponseEntity<>(DashboardDto.from(dashboard), HttpStatus.OK);
    }

    @PostMapping(value = "{dashboardId}/settings/{settingsUUID}/add")
    public ResponseEntity<DashboardDto> addSettingsToDashboard(
            @PathVariable final Long dashboardId,
            @PathVariable final UUID settingsUUID
            ){

        Dashboard dashboard = dashboardService.addSettingsToDashboard(dashboardId, settingsUUID);
        return new ResponseEntity<>(DashboardDto.from(dashboard), HttpStatus.OK);
    }

    @DeleteMapping(value = "{dashboardId}/settings/{settingsUUID}/remove")
    public ResponseEntity<DashboardDto> deleteSettingsFromDashboard(
            @PathVariable final Long dashboardId,
            @PathVariable final UUID settingsUUID
    ){

        Dashboard dashboard = dashboardService.removeSettingsFromDashboard(dashboardId, settingsUUID);
        return new ResponseEntity<>(DashboardDto.from(dashboard), HttpStatus.OK);
    }

//    @PostMapping("/createDashboard")
//    public void createDashboard(@RequestBody Dashboard dashboard) {
//        this.dashboardRepository.save(dashboard);
//    }
//
//    @GetMapping("/getAllDashboards")
//    public String getAllDashboards() {
//        return this.dashboardRepository.findAll().toString();
//    }


}
