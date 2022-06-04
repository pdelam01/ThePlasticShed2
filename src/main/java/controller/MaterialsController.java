/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.MaterialsFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Materials;

/**
 *
 * @author De la Hera
 */

@Named
@ViewScoped
public class MaterialsController implements Serializable{
    
    private List<Materials> materialsList;
    
    @EJB
    private MaterialsFacadeLocal materialsEJB;
    
    @PostConstruct
    public void init(){
        materialsList = loadMaterialsList();
    }
    
    public List<Materials> loadMaterialsList(){
        try {
            return materialsEJB.findMaterialsList();
        } catch (Exception e) {
            System.out.println("Oh no! Algo ha ido mal");
            return null;
        }
    }
    
    public List<Materials> getMaterialsList(){
        return materialsList;
    }
    
    public void setMaterialsList(List<Materials> listMat){
        materialsList = listMat;
    }
    
}
