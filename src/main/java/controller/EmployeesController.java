/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.EmployeesFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
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
    private Date date;
    private Date dateEdit;
            
    @EJB
    private EmployeesFacadeLocal employeesEJB;
    
    @PostConstruct
    public void init(){
        employeesList = loadEmployeesList();
        employee = new Employees();
        roleList = new ArrayList<SelectItem>();
        employeeEdit = new Employees();
        addItems();
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
    
    public void removeEmployees(int id){
        try {
            Employees aux = getEmployee(id);
            employeesEJB.remove(aux);
            FacesContext.getCurrentInstance().getExternalContext().redirect("usermng.xhtml"); 
        } catch (Exception e) {
             System.out.println("Oh no! Algo ha ido mal: " + e.getMessage());
        }
    }
    
    private Employees getEmployee(int id) {
        for(int i=0; i<employeesList.size(); i++) {
            if(employeesList.get(i).getIdEmployee()==id) {
                return employeesList.get(i);
            }
        }
        return null;
    }
    
    private void addItems() {
        roleList.add(new SelectItem("Peón", "Peón"));
        roleList.add(new SelectItem("Secretario", "Secretario"));
        roleList.add(new SelectItem("Administrador", "Administrador"));
    }
    
    public void addEmployees() {
        employee.setBirthday(date);
        employee.setRole(rol);
        employee.setPass("admin");
        System.out.println("IDe: "+employee.getIdEmployee());
        employeesEJB.create(employee);

    }
    
    public void redirectAdd() {
        try{
            FacesContext.getCurrentInstance().getExternalContext().redirect("usermng.xhtml");
        } catch (Exception e) {
            System.out.println("Error al redireccionar");
        }
    }
    public void searchEmployees() {
        for(int i=0; i<employeesList.size(); i++) {
            if(this.dni.equals(employeesList.get(i).getDni())) {
                employeeEdit = employeesList.get(i);
            }
        }
        dni = "";
        rol = employeeEdit.getRole();
        dateEdit = employeeEdit.getBirthdayDate();
    }
    
    public void updateEmployee() {
        employeeEdit.setBirthday(dateEdit);
        employeeEdit.setRole(rol);
        employeesEJB.edit(employeeEdit);
        
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
    
    public Date getDate2() {
        return date;
    }

    public void setDate2(Date date2) {
        this.date = date2;
    }

    public Date getDateEdit() {
        return dateEdit;
    }

    public void setDateEdit(Date dateEdit) {
        this.dateEdit = dateEdit;
    }
    
    
    
}
