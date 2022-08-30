package com.example.demo.entities;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "settings")
public class Settings {

    @Column(name = "value")
    public String value;
    @Id
    @GeneratedValue
    public UUID uuid;


    @Override
    public String toString() {
        return "Settings{" +
                "value='" + value + '\'' +
                ", uuid=" + uuid +
                '}';
    }
}
