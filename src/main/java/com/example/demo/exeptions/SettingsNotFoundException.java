package com.example.demo.exeptions;

import java.text.MessageFormat;
import java.util.UUID;

public class SettingsNotFoundException extends RuntimeException{
    public SettingsNotFoundException(UUID uuid){
        super(MessageFormat.format("Could not find object with id: {0}", uuid.toString()));
    }
}
