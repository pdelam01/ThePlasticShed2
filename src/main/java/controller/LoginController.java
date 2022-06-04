/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.EmployeesFacadeLocal;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.sound.midi.SysexMessage;
import modelo.Employees;
import utils.PasswordUtils;

/**
 *
 * @author De la Hera
 */
@Named //Ambitos de clase: Vista, aplicacion, sesion, peticion 
@ViewScoped
public class LoginController implements Serializable {

    private Employees employees;

    @EJB
    private EmployeesFacadeLocal employeesEJB;

    @PostConstruct
    public void init() {
        employees = new Employees();
    }
    
    public void checkCredentials() {
        try {
            List result = employeesEJB.loginCredentials(employees.getUsername());
            if (!result.isEmpty()) {
                if(verifyPassword((Employees) result.get(0))) {
                    System.out.println(result.get(0).toString());
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("empleadoLogged", result.get(0));
                    FacesContext.getCurrentInstance().getExternalContext().redirect("faces/private/home.xhtml");
                } else {
                    errorLogin();
                }
            } else {
                errorLogin();
            }
        } catch (Exception e) {
            errorLogin();
        }
    }
    
    private boolean verifyPassword(Employees emp) {
        return PasswordUtils.verifyUserPassword(employees.getPass(), emp.getPass(), emp.getSalt());
    }
    
    private void errorLogin() {
        cleanValues();
        FacesContext.getCurrentInstance().addMessage("MessageId2", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Usuario o"
            + " contraseña incorrecta"));
    }
    
    private void cleanValues() {
        employees = new Employees();
    }

    public void closeSession() {
        try {
            System.out.println("cierro la session");
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            FacesContext.getCurrentInstance().getExternalContext().redirect("/ThePlasticShed_v2");
        } catch (Exception e) {
            System.out.println("controller.LoginController.closeSession" + e.getMessage());
        }

    }

    public Employees getEmployees() {
        return employees;
    }

    public void setEmployees(Employees employees) {
        this.employees = employees;
    }
    
}
