package com.example.demo.exeptions;

import java.text.MessageFormat;
import java.util.UUID;

public class DashboardNotFoundException extends RuntimeException{
    public DashboardNotFoundException(Long id){
        super(MessageFormat.format("Could not find object with id: {0}", id.toString()));
    }
}
