/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.EmployeesFacadeLocal;
import java.io.Serializable;
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
    
    @EJB
    private EmployeesFacadeLocal employeesEJB;
    
    @PostConstruct
    public void init(){
        employees = showInfo();
        employeeToUpdate = new Employees();
    }
    
    public Employees showInfo() {
        System.out.println("VAmos a BBSS");
        try {
            //Employees pr = (Employees) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("empleadoLogged");
            //System.out.println(pr.getUsername());
            System.out.println("QUE lo que tu esta asiendo");
            //List result = employeesEJB.showEmployeeInfo(pr.getUsername());
            List result = employeesEJB.showEmployeeInfo("Admin"); 
            System.out.println("Alo");
            if(!result.isEmpty()) {
                System.out.println("QUE lo que tu esta asiendox2");
                System.out.println(result.get(0).toString());
                return (Employees) result.get(0);
            } else {
                System.out.println("USUARIO O CONTRASEÑA INCORRECTA");
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
            if(employeeToUpdate.getUsername()!=null) {
                user = employeeToUpdate.getUsername();
            }
            if(employeeToUpdate.getNameEmp()!=null) {
                name = employeeToUpdate.getNameEmp();
            }
            System.out.println("User: "+user+", name: "+name);
        } catch (Exception e) {
            System.out.println("Oh no! Algo ha ido mal: " + e.getMessage());
        }
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
}