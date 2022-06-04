/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.ComponentsFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Components;

/**
 *
 * @author De la Hera
 */

@Named
@ViewScoped
public class ComponentsController implements Serializable{
    
    private List<Components> componentsList;
    
    @EJB
    private ComponentsFacadeLocal componentsEJB;
    
    @PostConstruct
    public void init(){
        componentsList = loadComponentList();
    }
    
    public List<Components> loadComponentList(){
        try {
            return componentsEJB.findComponentsList();
        } catch (Exception e) {
            System.out.println("Oh no! Algo ha ido mal: " + e.getMessage());
            return null;
        }
    }
    
    public List<Components> getComponentsList(){
        return componentsList;
    }
    
    public void setComponentsList(List<Components> listComp){
        componentsList = listComp;
    }
}
