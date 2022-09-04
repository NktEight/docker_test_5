package com.example.demo.crudwithvaadin;

import com.example.demo.entities.Dashboard;
import org.springframework.data.repository.CrudRepository;


public interface DashboardRepository extends CrudRepository<Dashboard, Long>{

}
