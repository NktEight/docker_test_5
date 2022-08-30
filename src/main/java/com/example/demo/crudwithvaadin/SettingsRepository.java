package com.example.demo.crudwithvaadin;

import com.example.demo.entities.Settings;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface SettingsRepository extends CrudRepository<Settings, UUID> { }