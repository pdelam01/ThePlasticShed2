/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.EmployeesFacadeLocal;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.component.behavior.FacesBehavior;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Employees;

/**
 *
 * @author De la Hera
 */

@Named
@ViewScoped
public class EmployeesController implements Serializable{
    
    private List<Employees> employeesList;
    private Employees employee;
    private String rol;
    private Employees employeeEdit;
    private String dni;
    
    private List<SelectItem> roleList;
            
    @EJB
    private EmployeesFacadeLocal employeesEJB;
    
    @PostConstruct
    public void init(){
        employeesList = loadEmployeesList();
        employee = new Employees();
        roleList = new ArrayList<SelectItem>();
        addItems();
        employee.setBirthday(new Date());
    }

    public List<Employees> loadEmployeesList() {
        try {
            List<Employees> lista = employeesEJB.findEmployeesList();
            System.out.println("Employees size: "+lista.size());
            return employeesEJB.findEmployeesList();
        } catch (Exception e) {
            System.out.println("Oh no! Algo ha ido mal: " + e.getMessage());
            return null;
        }
    }
    
    public void removeEmployees(int index){
        try {
            System.out.println("IDENTIFICADOR: "+(index-1));
            System.out.println(" NOMBRE USER "+employeesList.get(index-1).getNameEmp());
            System.out.println(" IDENT USER "+employeesList.get(index-1).getIdEmployee());
            employeesEJB.remove(employeesList.get(index-1));
            FacesContext.getCurrentInstance().getExternalContext().redirect("usermng.xhtml");
        } catch (Exception e) {
             System.out.println("Oh no! Algo ha ido mal: " + e.getMessage());
        }
    }
    
    private void addItems() {
        roleList.add(new SelectItem("Peón", "Peón"));
        roleList.add(new SelectItem("Secretario", "Secretario"));
        roleList.add(new SelectItem("Administrador", "Administrador"));
    }
    
    public void addEmployees() {
        System.out.println("U: "+ employee.getUsername()+employee.getNameEmp()+employee.getDni()+employee.getSsn()+employee.getPhoneNum()+rol);
        employee.setRole(rol);
        //employeesEJB.create(employee);
    }
    
    public void searchEmployees() {
        for(int i=0; i<employeesList.size(); i++) {
            if(this.dni.equals(employeesList.get(i).getDni())) {
                employeeEdit = employeesList.get(i);
            }
        }
        System.out.println("Nombre: "+employeeEdit.getNameEmp());
    }
    
    public List<Employees> getEmployeesList(){
        return employeesList;
    }
    
    public void setEmployeesList(List<Employees> listEmp){
        employeesList = listEmp;
    }
    
    public Employees getEmployee() {
        return employee;
    }

    public void setEmployee(Employees employee) {
        this.employee = employee;
    }
    
    public Employees getEmployeeEdit() {
        return employeeEdit;
    }

    public void setEmployeeEdit(Employees employee) {
        this.employeeEdit = employee;
    }
    
    public List<SelectItem> getListRoles() {
        return roleList;
    }
 
    public void setListaColores(List<SelectItem> roleList) {
        this.roleList = roleList;
    }
    
    public String getRol() {
        return this.rol;
    }
    
    public void setRol(String role) {
        this.rol = role;
    }
    
    public String getDni() {
        return this.dni;
    }
    
    public void setDni(String dni) {
        this.dni = dni;
    }
    
}
