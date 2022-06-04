/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import EJB.ArrayMaterialsFacadeLocal;
import EJB.ComponentsFacadeLocal;
import EJB.MaterialsFacadeLocal;
import EJB.ProductionsFacadeLocal;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.ArrayMaterials;
import modelo.Components;
import modelo.Employees;
import modelo.Materials;
import modelo.Productions;

/**
 *
 * @author diego
 */
@Named
@ViewScoped
public class ProductionsController implements Serializable{

    private List<Components> componentList;
    private List<Materials> materialList;
    private List<ArrayMaterials> arrayMaterils;
    private int index;
    private int quantity;
    private Materials materialOne;
    private Materials materialTwo;
    private Components component;
    
    @EJB
    private ProductionsFacadeLocal productionEJB;
    
    @EJB
    private ComponentsFacadeLocal componentsEJB;
    
    @EJB
    private MaterialsFacadeLocal materialsEJB;
    
    @EJB
    private ArrayMaterialsFacadeLocal arrayMaterialsEJB;
    
    @PostConstruct
    public void init(){
        componentList = loadComponentList();
        materialList = loadMaterialList();
        index = 1;
        component = componentList.get(index-1);
        arrayMaterils = loadArrayMaterialList();
        quantity = 1;
        materialOne = new Materials();
        materialTwo = new Materials();
    }
    
    private List<Components> loadComponentList() {
        try {
            return componentsEJB.findComponentsList();
        } catch (Exception e) {
            System.out.println("Oh no! Algo ha ido mal");
            return null;
        }
    }
    
    private List<Materials> loadMaterialList() {
        try {
            return materialsEJB.findMaterialsList();
        } catch (Exception e) {
            System.out.println("Oh no! Algo ha ido mal");
            return null;
        }
    }
    
    public List<Productions> loadProductionsList() {
        try {
            return productionEJB.findProductionsList();
        } catch (Exception e) {
            System.out.println("Oh no! Algo ha ido mal");
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
    
    public String aditionalComponent() {
        if(component.getUpcAditionalComponents()!=null) {
            return component.getUpcAditionalComponents().getName();
        }else{
            return "-";
        }
    }
    
    private List<ArrayMaterials> loadArrayMaterialList() {
        return arrayMaterialsEJB.findAll();
    }
    
    public String obtainFirstMaterial() {
        for (int i=0; i<arrayMaterils.size();i++) {
            ArrayMaterials aux = arrayMaterils.get(i);
            if(aux.getIdComponent().getId()==component.getId()) {
                materialOne = aux.getIdMaterial();
                return materialOne.getName();
            }
        }
        return "-";
    }
    
    public String obtainSecondMaterial() {
        boolean second = false;
        for (int i=0; i<arrayMaterils.size();i++) {
            ArrayMaterials aux = arrayMaterils.get(i);
            if(aux.getIdComponent().getId()==component.getId()) {
                if (second) {
                    materialTwo = aux.getIdMaterial();
                    return materialTwo.getName();
                }else {
                    second = true;
                }
            }
        }
        materialTwo = null;
        return "-";
    }
    
    public Date getLocalDate(){
        Date now = new Date();
        return now;
    }
    
    public void doProduction() {
        Productions production = obtainProduction();
        if(utils.Utils.isANum(String.valueOf(quantity)) && controlMaterials() && controlAditionalComponent() && quantity>0) {
            editComponents();
            editAditionalComponents();
            editMaterials();
            productionEJB.create(production);
            FacesContext.getCurrentInstance().addMessage("MessageId", new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Producción realizada con éxito"));
            componentList = loadComponentList();
            materialList = loadMaterialList();
        }else{
            FacesContext.getCurrentInstance().addMessage("MessageId", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Cantidad introducida inválida"));
        }
    }
    
    private Productions obtainProduction() {
        Productions production = new Productions();
        production.setDate(getLocalDate());
        production.setIdPawn((Employees) FacesContext.getCurrentInstance().
                getExternalContext().getSessionMap().get("empleadoLogged"));
        production.setIdComponent(component);
        production.setQuantity(quantity);
        return production;
    }
    
    private boolean controlMaterials() {
        if(quantity<=materialOne.getQuantity()) {
            if(materialTwo!=null) {
                if(quantity<=materialTwo.getQuantity()) {
                    return true;
                }
            }else{
                return true;    
            }
        }
        return false;
    }
    
    private boolean controlAditionalComponent() {
        if(component.getUpcAditionalComponents()!=null){
            if(quantity<component.getUpcAditionalComponents().getQuantity()) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }
    
    private void editComponents() {
        int aux = component.getQuantity();
        component.setQuantity(aux+quantity);
        componentsEJB.edit(component);
    }
    
    private void editAditionalComponents() {
        Components aditionalComponent = component.getUpcAditionalComponents();
        if(aditionalComponent!=null) {
            int aux = aditionalComponent.getQuantity();
            aditionalComponent.setQuantity(aux-quantity);
            componentsEJB.edit(aditionalComponent);
        }
    }
    
    private void editMaterials() {
        int aux = materialOne.getQuantity();
        System.out.println(aux);
        materialOne.setQuantity(aux-quantity);
        materialsEJB.edit(materialOne);
        if(materialTwo!=null) {
            int number = materialTwo.getQuantity();
            materialTwo.setQuantity(number-quantity);
            materialsEJB.edit(materialTwo);
        }
    }
    
    public void redirect() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("production.xhtml");
        } catch (Exception e) {
            System.out.println("Error al redireccionar"+e.getMessage());
        }
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

    public List<ArrayMaterials> getArrayMaterilas() {
        return arrayMaterils;
    }

    public void setArrayMaterilas(List<ArrayMaterials> arrayMaterils) {
        this.arrayMaterils = arrayMaterils;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Materials getMaterialOne() {
        return materialOne;
    }

    public void setMaterialOne(Materials materialOne) {
        this.materialOne = materialOne;
    }

    public Materials getMaterialTwo() {
        return materialTwo;
    }

    public void setMaterialTwo(Materials materialTwo) {
        this.materialTwo = materialTwo;
    }
    
    
    
}
