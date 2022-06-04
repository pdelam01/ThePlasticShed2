/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.MaterialsFacadeLocal;
import EJB.OrdersFacadeLocal;
import EJB.SuppliersFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Employees;
import modelo.Materials;
import modelo.Orders;
import modelo.Suppliers;

/**
 *
 * @author De la Hera
 */

@Named
@ViewScoped
public class OrdersController implements Serializable {
    
    private List<Suppliers> suppliersList;
    private List<SelectItem> suppliersItemsList;
    private List<Materials> materialsList;
    private Materials material;
    private Employees employeeSession;
    private Orders order;
    private int supplierId;
    private int index;
    private int quantity;
    private double totalPrice;
    
    @EJB
    private SuppliersFacadeLocal suppliersEJB;
    
    @EJB
    private MaterialsFacadeLocal materialsEJB;
    
    @EJB 
    private OrdersFacadeLocal ordersEJB;
    
    @PostConstruct
    public void init(){
        suppliersList = loadSuppliersList();
        suppliersItemsList = new ArrayList<SelectItem>();
        supplierId = 0;
        index = 1;
        materialsList = loadMaterialsList();
        material = materialsList.get(index-1);
        quantity = 1;
        order = new Orders();
        calculatePrice();
        addItems();
    }
    
    public List<Suppliers> loadSuppliersList(){
        try {
            return suppliersEJB.findSuppliersList();
        } catch (Exception e) {
            System.out.println("Oh no! Algo ha ido mal: " + e.getMessage());
            return null;
        }
    }
    
    public List<Materials> loadMaterialsList(){
        try {
            return materialsEJB.findMaterialsList();
        } catch (Exception e) {
            System.out.println("Oh no! Algo ha ido mal: " + e.getMessage());
            return null;
        }
    }
    
    public List<Orders> loadOrdersList(){
        try {
            return ordersEJB.findOrdersList();
        } catch (Exception e) {
            System.out.println("Oh no! Algo ha ido mal: " + e.getMessage());
            return null;
        }
    }
    
    
    public void addItems(){
        for(int i=0; i<suppliersList.size(); i++){
            suppliersItemsList.add(new SelectItem(suppliersList.get(i).getId(), suppliersList.get(i).getName()+" a cargo "+suppliersList.get(i).getContactPerson()));
        }
    }
    
     public Date getLocalDate(){
        Date now = new Date();
        return now;
    }
    
    public void doOrder(){
        employeeSession = (Employees) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("empleadoLogged");
        
        if (quantity > 0) {
            order.setMaterial(material);
            order.setIdEmployee(employeeSession);
            order.setSupplier(suppliersEJB.find(supplierId));
            order.setDate(getLocalDate());
            order.setQuantity(quantity);
            order.setTotalPrice(totalPrice);
            material.setQuantity(materialsEJB.find(index).getQuantity() + quantity);

            ordersEJB.create(order);
            materialsEJB.edit(material);
            FacesContext.getCurrentInstance().addMessage("MessageId", new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Pedido realizado con éxito"));
        }else{
            FacesContext.getCurrentInstance().addMessage("MessageId", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Cantidad introducida inválida"));
        }
    }
    
    public double roundTwoDecimals(){
        String totalPriceString = String.format(Locale.US,"%.2f", quantity * material.getPrice());
        return Double.parseDouble(totalPriceString);
    }
    
    public void calculatePrice(){
        totalPrice = roundTwoDecimals();
    }
    
    public void moveLeft(){
        if(index!=1){
            index--;
        }
        material = materialsList.get(index-1);
        calculatePrice();
    }
    
    public void moveRight(){
        if(index!=materialsList.size()){
            index++;
        }
        material = materialsList.get(index-1);
        calculatePrice();
    }
    
    public List<Suppliers> getSuppliersList(){
        return suppliersList;
    }
    
    public void setSuppliersList(List<Suppliers> listSupp){
        suppliersList = listSupp;
    }

    public List<SelectItem> getSuppliersItemsList() {
        return suppliersItemsList;
    }

    public void setSuppliersItemsList(List<SelectItem> suppliersItemsList) {
        this.suppliersItemsList = suppliersItemsList;
    }

    public Materials getMaterial() {
        return material;
    }

    public void setMaterial(Materials material) {
        this.material = material;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public SuppliersFacadeLocal getSuppliersEJB() {
        return suppliersEJB;
    }

    public void setSuppliersEJB(SuppliersFacadeLocal suppliersEJB) {
        this.suppliersEJB = suppliersEJB;
    }
    
    
}
