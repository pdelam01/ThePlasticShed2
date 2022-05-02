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
    
    public void checkCredentials(String user, String pass){
        try {
           
        } catch (Exception e) {
            System.out.println("controller.LoginController.checkCredentials" + e.getMessage());
        }
        
        //return false;
    }
    

    public Employees getEmployees() {
        return employees;
    }

    public void setEmployees(Employees employees) {
        this.employees = employees;
    }
    
    
}
