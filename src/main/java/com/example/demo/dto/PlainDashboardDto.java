package com.example.demo.dto;

import com.example.demo.entities.Dashboard;
import lombok.Data;

@Data
public class PlainDashboardDto {
    private Long id;
    public static PlainDashboardDto from(Dashboard dashboard){
        PlainDashboardDto plainDashboardDto = new PlainDashboardDto();
        plainDashboardDto.setId(dashboard.getId());
        return plainDashboardDto;
    }

    public PlainDashboardDto(Long id) {
        this.id = id;
    }

    public PlainDashboardDto(int int_id){
        this.id = (long) int_id;
    }

    public PlainDashboardDto() {
    }
}
