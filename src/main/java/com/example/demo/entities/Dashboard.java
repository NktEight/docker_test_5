package com.example.demo.entities;

import com.example.demo.dto.DashboardDto;
import com.example.demo.dto.SettingsDto;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "dashboard")
public class Dashboard {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "dashboard_id")
    public List<Settings> settingsList;

    public void addSettings(Settings settings){
        settingsList.add(settings);
    }

    public void removeSettings(Settings settings){
        settingsList.remove(settings);
    }
    public static Dashboard from(DashboardDto dashboardDto){
        Dashboard dashboard = new Dashboard();
//        dashboard.setSettingsList(dashboardDto.getSettingsList());
        return dashboard;
    }
}
