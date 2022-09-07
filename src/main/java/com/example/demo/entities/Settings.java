package com.example.demo.entities;

import com.example.demo.dto.SettingsDto;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;
@Data
@Entity
@Table(name = "settings")
public class Settings {

    @Column(name = "value")
    public String value;
    @Id
    @GeneratedValue
    public UUID uuid;
    @ManyToOne
    @JoinColumn(name="dashboard_id", nullable=false)
    public Dashboard dashboard;

    public static Settings from(SettingsDto settingsDto){
        Settings settings = new Settings();
        settings.setValue(settingsDto.getValue());
        if(Objects.nonNull(settingsDto.getUuid())){
            settings.setUuid(settingsDto.getUuid());
        }
        if(Objects.nonNull(settingsDto.getPlainDashboardDto())){
            settings.setDashboard(Dashboard.from(settingsDto.getPlainDashboardDto()));
        }
        return settings;
    }

}
