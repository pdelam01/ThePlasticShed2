/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Employees;

/**
 *
 * @author De la Hera
 */
@Named
@ViewScoped
public class PlantillaController implements Serializable {

    public void checkLoggedUser() throws IOException {
        List<String> pagsPawn = Arrays.asList("homePawn.xhtml", "profilePawn.xhtml", "almacen.xhtml", "production.xhtml");
        List<String> pagsSecretary = Arrays.asList("homeSecretary.xhtml", "profileSecretary.xhtml", "almacen.xhtml", "production.xhtml", "sales.xhtml", "orders.xhtml");
        List<String> pagsAdmin = Arrays.asList("home.xhtml", "profile.xhtml", "almacen.xhtml", "production.xhtml", "sales.xhtml", "orders.xhtml", "usermng.xhtml");

        Employees empleado = (Employees) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("empleadoLogged");
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("empleadoLogged") != null) {
            String direccionContexto = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
            String requestPath = FacesContext.getCurrentInstance().getExternalContext().getRequestPathInfo();
            requestPath = requestPath.replace("/private/", "");
            System.out.println(pagsAdmin.contains(requestPath));
            switch (empleado.getRole()) {
                case "Administrador":
                    if (pagsAdmin.contains(requestPath)) {
                        //No se hace nada, se deja pasar la request
                    } else {
                        FacesContext.getCurrentInstance().getExternalContext().redirect(direccionContexto + "/public/error404.xhtml");
                    }
                    break;
                case "Secretario":
                    if (pagsSecretary.contains(requestPath)) {
                        //No se hace nada, se deja pasar la request
                    } else {
                        FacesContext.getCurrentInstance().getExternalContext().redirect(direccionContexto + "/public/error404.xhtml");
                    }
                    break;
                default:
                    if (pagsPawn.contains(requestPath)) {
                        //No se hace nada, se deja pasar la request
                    } else {
                        FacesContext.getCurrentInstance().getExternalContext().redirect(direccionContexto + "/public/error404.xhtml");
                    }
                    break;
            }
        } else {
            System.out.println("yo en el else");
            String direccionContexto = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
            FacesContext.getCurrentInstance().getExternalContext().redirect(direccionContexto + "/public/error404.xhtml");
        }
    }
}
