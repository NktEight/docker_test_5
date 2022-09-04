package com.example.demo.entities;

import com.example.demo.dto.SettingsDto;
import lombok.Data;

import javax.persistence.*;
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
    public Dashboard dashboard;

    public static Settings from(SettingsDto settingsDto){
        Settings settings = new Settings();
        settings.setValue(settingsDto.getValue());
        return settings;
    }

}
