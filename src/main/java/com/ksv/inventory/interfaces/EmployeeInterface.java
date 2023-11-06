package com.ksv.inventory.interfaces;

import com.ksv.inventory.entity.Employee;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public interface EmployeeInterface {
    
    public ObservableList<Employee> EMPLOYEELIST = FXCollections.observableArrayList();
}
