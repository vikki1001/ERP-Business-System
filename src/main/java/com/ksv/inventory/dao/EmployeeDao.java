package com.ksv.inventory.dao;

import com.ksv.inventory.entity.Employee;

import javafx.collections.ObservableList;

public interface EmployeeDao {
    
    public ObservableList<Employee> getEmployees();
    public Employee getEmployee(long id);
    public String getEmployeeType(String username);
    public String getEmployeeName(long id);
    public String getEmployeePhone(long id);
    public String getEmployeeEmail(long id);
    public void saveEmployee(Employee employee);
    public void updateEmployee(Employee employee);
    public void deleteEmployee(Employee employee);
    public boolean checkPassword(String username,String password);
    public boolean checkUser(String username);
}
