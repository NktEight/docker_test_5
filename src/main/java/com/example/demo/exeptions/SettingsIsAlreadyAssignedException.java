package com.example.demo.exeptions;

import java.text.MessageFormat;
import java.util.UUID;

public class SettingsIsAlreadyAssignedException extends RuntimeException{
    public SettingsIsAlreadyAssignedException(final UUID settingsId, final Long dashboardId) {
        super(MessageFormat.format("Settings {0} is already assigned to dashboard {1}",settingsId, dashboardId ));
    }
}
