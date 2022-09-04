package com.example.demo.dto;

import com.example.demo.entities.Settings;
import lombok.Data;

import java.util.Objects;
import java.util.UUID;

@Data
public class SettingsDto {
    private UUID uuid;
    private String value;
    private PlainDashboardDto plainDashboardDto;

    public static SettingsDto from(Settings settings){
        SettingsDto settingsDto = new SettingsDto();
        settingsDto.setUuid(settings.uuid);
        settingsDto.setValue(settings.getValue());
        if(Objects.nonNull(settings.getDashboard())){
            settingsDto.setPlainDashboardDto(PlainDashboardDto.from(settings.getDashboard()));
        }
        return settingsDto;
    }
}
