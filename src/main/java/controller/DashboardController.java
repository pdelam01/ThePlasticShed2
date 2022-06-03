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

/**
 *
 * @author Pablo Javier Barrio Navarro
 */
@Named //Ambitos de clase: Vista, aplicacion, sesion, peticion 
@ViewScoped
public class DashboardController implements Serializable{
    
    @PostConstruct
    public void init() {
        //Para futura Funcionalidad
    }
    
    public void redirectProfile() {
        try {
            System.out.println("redirecciono a perfil");
            FacesContext.getCurrentInstance().getExternalContext().redirect("profile.xhtml");
        } catch (Exception e) {
            System.out.println("[ERR] controller dashboard redirect perfil error" + e.getMessage());
        }

    }
    
    public void redirectAlmacen() {
        try {
            System.out.println("redirecciono a almacen");
            FacesContext.getCurrentInstance().getExternalContext().redirect("almacen.xhtml");
        } catch (Exception e) {
            System.out.println("[ERR] controller dashboard redirect almacen error" + e.getMessage());
        }

    }
    
    public void redirectDashboard() {
        try {
            System.out.println("redirecciono a dashboard");
            FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml");
        } catch (Exception e) {
            System.out.println("[ERR] controller dashboard redirect almacen error" + e.getMessage());
        }

    }
   
}
