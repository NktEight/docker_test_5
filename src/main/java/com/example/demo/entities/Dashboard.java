package com.example.demo.entities;

import com.example.demo.dto.DashboardDto;
import com.example.demo.dto.PlainDashboardDto;
import com.example.demo.dto.SettingsDto;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "dashboard")
public class Dashboard {
    @Id
    @GeneratedValue
    private Long id;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "dashboard_id")
    @OneToMany(mappedBy="dashboard")
    public List<Settings> settingsList;

    public void addSettings(Settings settings){
        settingsList.add(settings);
    }

    public void removeSettings(Settings settings){
        settingsList.remove(settings);
    }
    public static Dashboard from(DashboardDto dashboardDto){
        Dashboard dashboard = new Dashboard();
        if(Objects.nonNull(dashboardDto.getSettingsDtoList())){
            List<Settings> settingsList1 = dashboardDto.getSettingsDtoList().stream().map(Settings::from).collect(Collectors.toList());
            dashboard.setSettingsList(settingsList1);
        }
        return dashboard;
    }

    public static Dashboard from(PlainDashboardDto plainDashboardDto){
        Dashboard dashboard = new Dashboard();
        if(Objects.nonNull(plainDashboardDto.getId())){
            dashboard.setId(plainDashboardDto.getId());
        }
        return dashboard;
    }
}
