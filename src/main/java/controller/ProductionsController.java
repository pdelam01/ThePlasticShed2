/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import EJB.ComponentsFacadeLocal;
import EJB.MaterialsFacadeLocal;
import EJB.ProductionsFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Components;
import modelo.Materials;

/**
 *
 * @author diego
 */
@Named //Ambitos de clase: Vista, aplicacion, sesion, peticion 
@ViewScoped
public class ProductionsController implements Serializable{

    private List<Components> componentList;
    private List<Materials> materialList;
    private int index;
    private Components component;
    
    @EJB
    private ProductionsFacadeLocal productionEJB;
    
    @EJB
    private ComponentsFacadeLocal componentsEJB;
    
    @EJB
    private MaterialsFacadeLocal materialsEJB;
    
    @PostConstruct
    public void init(){
        componentList = loadComponentList();
        materialList = loadMaterialList();
        index = 1;
        component = componentList.get(index-1);
    }
    
    private List<Components> loadComponentList() {
        try {
            return componentsEJB.findComponentsList();
        } catch (Exception e) {
            System.out.println("Oh no! Algo ha ido mal: " + e.getMessage());
            return null;
        }
    }
    
    private List<Materials> loadMaterialList() {
        try {
            return materialsEJB.findMaterialsList();
        } catch (Exception e) {
            System.out.println("Oh no! Algo ha ido mal: " + e.getMessage());
            return null;
        }
    }
    
    public void moveLeft() {
        if(index!=1){
            index--;
        }
        component = componentList.get(index-1);
    }
    
    public void moveRight() {
        if(index!=componentList.size()){
            index++;
        }
        component = componentList.get(index-1);
    }

    public List<Components> getComponentList() {
        return componentList;
    }

    public void setComponentList(List<Components> componentList) {
        this.componentList = componentList;
    }

    public List<Materials> getMaterialList() {
        return materialList;
    }

    public void setMaterialList(List<Materials> materialList) {
        this.materialList = materialList;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Components getComponent() {
        return component;
    }

    public void setComponent(Components component) {
        this.component = component;
    }
    
    
    
}
