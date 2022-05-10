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

/**
 *
 * @author De la Hera
 */

@Named //Ambitos de clase: Vista, aplicacion, sesion, peticion 
@ViewScoped
public class PlantillaController implements Serializable{
    
    public void checkLoggedUser() throws IOException{
        System.out.println("polla checklogged user ");
        if(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("empleadoLogged")!=null){
            System.out.println(FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath());
            FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath()+"/private/home.xhtml");    
        }else{
            System.out.println("Lol yo en el else bro");
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml?faces-redirect=true");
        }
    }
}