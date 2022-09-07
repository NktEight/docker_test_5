package com.example.demo.controllers;


import com.example.demo.entities.Dashboard;
import com.example.demo.entities.Settings;
import com.example.demo.services.DashboardService;
import com.example.demo.services.SettingsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(DashboardController.class);
    @Autowired
    private DashboardService dashboardService;
    @Autowired
    private SettingsService settingsService;

    @GetMapping()
    @RequestMapping("/one")
    public String testOne() {
        Settings settings = new Settings();
        Dashboard dashboard = new Dashboard();
        settings.setValue("one");
        settings.setDashboard(dashboard);
        dashboardService.addDashboard(dashboard);
        settingsService.addSettings(settings);

        return dashboard.toString();
        ///result OK dashboard+ settings+
    }

    //////////////
    @GetMapping()
    @RequestMapping("/two")
    public String testTwo() {
        Settings settings = new Settings();
        settings.setValue("two");
        Dashboard dashboard = new Dashboard();
        dashboard.setSettingsList(List.of(settings));
        dashboardService.addDashboard(dashboard);
//        settingsService.addSettings(settings);
        return dashboard.toString();
        ///result OK dashboard+ settings-
    }

    @GetMapping()
    @RequestMapping("/three")
    public String testThree() {
        logger.info("THREE ZERO");
        Settings settings = new Settings();
        Dashboard dashboard = new Dashboard();
        settings.setValue("THREE");
        settings.setDashboard(dashboard);
        dashboard = dashboardService.addDashboard(dashboard);
        settings = settingsService.addSettings(settings);

        logger.info("THREE FIRST: " + dashboard + "___" + settings);
        Dashboard dashboard1 = new Dashboard();
        dashboard1.setSettingsList(List.of(settings));
        dashboard1 = dashboardService.addDashboard(dashboard1);
        logger.info("THREE SECOND: " + dashboard1 + "___" + settings);
        return dashboard.toString();
        ///result OK dashboard+ settings+ dashboard without settings
    }
    @PostConstruct
    private void postConstruct() {
        dashboardService.deleteAll();
        settingsService.deleteAll();
    }
}
