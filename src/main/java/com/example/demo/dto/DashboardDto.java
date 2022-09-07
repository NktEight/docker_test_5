package com.example.demo.dto;

import com.example.demo.controllers.DashboardController;
import com.example.demo.entities.Dashboard;
import com.example.demo.entities.Settings;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
public class DashboardDto {
    private Long id;
    private List<SettingsDto> settingsDtoList;

    public static DashboardDto from(Dashboard dashboard) {
        DashboardDto dashboardDto = new DashboardDto();
        dashboardDto.setId(dashboard.getId());
        if (Objects.nonNull(dashboard.getSettingsList())) {
            dashboardDto.setSettingsDtoList(dashboard.getSettingsList().stream().map(SettingsDto::from).collect(Collectors.toList()));
        }
        return dashboardDto;
    }
}
