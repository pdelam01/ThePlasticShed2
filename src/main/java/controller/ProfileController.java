/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.EmployeesFacadeLocal;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Employees;

/**
 *
 * @author diego
 */
@Named //Ambitos de clase: Vista, aplicacion, sesion, peticion 
@ViewScoped
public class ProfileController implements Serializable{
    
    private Employees employees;
    
    private Employees employeeToUpdate;
    
    private Date date;
    
    @EJB
    private EmployeesFacadeLocal employeesEJB;
    
    @PostConstruct
    public void init(){
        employees = showInfo();
        date = employees.getBirthdayDate();
        employeeToUpdate = new Employees();
    }
    
    public Employees showInfo() {
        System.out.println("VAmos a BBSS");
        try {
            Employees pr = (Employees) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("empleadoLogged");
            System.out.println(pr.getIdEmployee());
            List result = employeesEJB.showEmployeeInfo(pr.getIdEmployee());
            if(!result.isEmpty()) {
                return (Employees) result.get(0);
            } else {
                FacesContext.getCurrentInstance().getExternalContext().redirect("public/error404.xhtml");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Oh no! Algo ha ido mal: " + e.getMessage());
            return null;
        }
    }
    
    public void updateInfoEmployee() {
        System.out.println("Probar");
        try {
            String user = "", name = "";
            String returnUsername = employeeToUpdate.getUsername().trim();
            if(returnUsername.length()!=0) {
                user = employeeToUpdate.getUsername();
            }else{
                user = employees.getUsername();
            }
            String returnName = employeeToUpdate.getNameEmp().trim();
            if(returnName.length()!=0) {
                System.out.println("El nombre es:"+employeeToUpdate.getNameEmp());
                name = employeeToUpdate.getNameEmp();
            }else{
                name = employees.getNameEmp();
            }
            System.out.println("User: "+user+", name: "+name);
            employeesEJB.updateEmployee(user, name, employees.getDni(), date);
            FacesContext.getCurrentInstance().getExternalContext().redirect("profile.xhtml");
        } catch (Exception e) {
            System.out.println("Oh no! Algo ha ido mal: " + e.getMessage());
        }
    }
    
    public void updateInfoSensibilityEmployee() {
        try {
            String ssn = "", phoneNumber = "", dni = "";
            String returnSSN = employeeToUpdate.getSsn().trim();
            if(returnSSN.length()==10) {
                ssn = employeeToUpdate.getSsn().trim();
            }else{
                ssn = employees.getSsn();
            }
            String returnPhoneNumber = employeeToUpdate.getPhoneNum().trim();
            if(returnPhoneNumber.length()==9 && checkNumbers(returnPhoneNumber)) {
                phoneNumber = employeeToUpdate.getPhoneNum().trim();
            }else{
                phoneNumber = employees.getPhoneNum();
            }
            String returnDNI = employeeToUpdate.getDni().trim();
            if(checkDni(returnDNI)) {
                dni = employeeToUpdate.getDni().trim();
            }else{
                dni = employees.getDni();
            }
            employeesEJB.updateEmployeeSensibiliti(ssn, phoneNumber, dni, employees.getIdEmployee());
            FacesContext.getCurrentInstance().getExternalContext().redirect("profile.xhtml");
        } catch (Exception e) {
            System.out.println("Oh no! Algo ha ido mal: " + e.getMessage());
        }
    }
    
    private boolean checkNumbers(String phone) {
        for(int i=0; i<phone.length(); i++) {
            if(!Character.isDigit(phone.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    
    private boolean checkDni(String dni) {
        if(dni.length()!=9) {
            return false;
        }
        for(int i=0; i<7; i++) {
            if(!Character.isDigit(dni.charAt(i))) {
                return false;
            }
        }
        if(!Character.isUpperCase(dni.charAt(8))) {
            return false;
        }
        return true;
    }
    
    public Employees getEmployees() {
        return employees;
    }

    public void setEmployees(Employees employees) {
        this.employees = employees;
    }
    
    public Employees getEmployeesToUpdate() {
        return employeeToUpdate;
    }

    public void setEmployeesToUpdate(Employees employees) {
        this.employeeToUpdate = employees;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
}
