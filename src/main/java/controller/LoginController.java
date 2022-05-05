/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.EmployeesFacadeLocal;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Employees;

/**
 *
 * @author De la Hera
 */

@Named //Ambitos de clase: Vista, aplicacion, sesion, peticion 
@ViewScoped
public class LoginController implements Serializable{
    
    private Employees employees;
    
    @EJB
    private EmployeesFacadeLocal employeesEJB;
    
    @PostConstruct
    public void init(){
        employees = new Employees();
    }
    
    public void checkCredentials(){
        try {
            //employeesEJB.loginCredentials(username, password);
            System.out.println("Login controller "+employees.getUsername()+" "+employees.getPass());
            System.out.println("Login controller "+employeesEJB.loginCredentials(employees.getUsername(), employees.getPass()));
        } catch (Exception e) {
            System.out.println("controller.LoginController.checkCredentials" + e.getMessage());
        }
    }
    

    public Employees getEmployees() {
        return employees;
    }

    public void setEmployees(Employees employees) {
        this.employees = employees;
    }
    
    
}
