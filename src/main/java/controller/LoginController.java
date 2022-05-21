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
            List result = employeesEJB.loginCredentials(employees.getUsername(), employees.getPass());
            if (!result.isEmpty()) {
                System.out.println(result.get(0).toString());
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("empleadoLogged", result.get(0));
                FacesContext.getCurrentInstance().getExternalContext().redirect("faces/private/home.xhtml");
            } else {
                System.out.println("USUARIO O CONTRASEÑA INCORRECTA");
                FacesContext.getCurrentInstance().getExternalContext().redirect("public/error404.xhtml");
            }
        } catch (Exception e) {
            System.out.println("controller.LoginController.checkCredentials" + e.getMessage());
        }

    }

    public void closeSession() {
        try {
            System.out.println("cierro la session");
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            FacesContext.getCurrentInstance().getExternalContext().redirect("../index.xhtml");
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
