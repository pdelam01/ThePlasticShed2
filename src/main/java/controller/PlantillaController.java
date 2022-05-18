/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.Serializable;
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
public class PlantillaController implements Serializable{
    
    public void checkLoggedUser() throws IOException{
        System.out.println("checklogged user ");
        Employees empleado = (Employees) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("empleadoLogged");
        if(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("empleadoLogged")!=null){
            System.out.println("estoy en el if()");
           // FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath()+"/private/home2.xhtml");    
        }else{
            System.out.println("yo en el else");
            String direccionContexto = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
           // FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml?faces-redirect=true");
           FacesContext.getCurrentInstance().getExternalContext().redirect(direccionContexto + "/public/permisosinsuficientes.xhtml");
        }
    }
}