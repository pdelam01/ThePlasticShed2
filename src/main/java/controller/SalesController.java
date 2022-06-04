/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.ClientsFacadeLocal;
import EJB.ComponentsFacadeLocal;
import EJB.SalesFacadeLocal;
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
import modelo.Clients;
import modelo.Components;
import modelo.Employees;
import modelo.Sales;

/**
 *
 * @author De la Hera
 */

@Named
@ViewScoped
public class SalesController implements Serializable {
    
    private List<Clients> clientsList;
    private List<SelectItem> clientsItemsList;
    private List<Components> componentsList;
    private Components component;
    private Sales sale;
    private Employees employeeSession;
    private int clienteId;
    private int index;
    private int quantity;
    private double totalPrice;   
    
    @EJB
    private ClientsFacadeLocal clientsEJB;
    
    @EJB
    private ComponentsFacadeLocal componentsEJB;
    
    @EJB 
    private SalesFacadeLocal salesEJB;
    
    @PostConstruct
    public void init(){
        clientsList = loadClientsList();
        clientsItemsList = new ArrayList<SelectItem>();
        clienteId = 0;
        index = 1;
        componentsList = loadComponentList();
        component = componentsList.get(index-1);
        quantity = 1;
        sale = new Sales();
        calculatePrice();
        addItems();
    }
    
    public List<Clients> loadClientsList(){
        try {
            return clientsEJB.findClientsList();
        } catch (Exception e) {
            System.out.println("Oh no! Algo ha ido mal: " + e.getMessage());
            return null;
        }
    }
    
    public List<Sales> loadSalesList(){
        try {
            return salesEJB.findSalesList();
        } catch (Exception e) {
            System.out.println("Oh no! Algo ha ido mal: " + e.getMessage());
            return null;
        }
    }
    
    public void addItems(){
        for(int i=0; i<clientsList.size(); i++){
            clientsItemsList.add(new SelectItem(clientsList.get(i).getIdClient(), clientsList.get(i).getName()+" "+clientsList.get(i).getSurname()));
        }
    }
    
    public Date getLocalDate(){
        Date now = new Date();
        return now;
    }
    
    public void doSale(){
        employeeSession = (Employees) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("empleadoLogged");
        
        if(utils.Utils.validQuantity(quantity, componentsEJB, index)){
            sale.setComponent(component);
            sale.setEmployee(employeeSession);
            sale.setClient(clientsEJB.find(clienteId));
            sale.setDate(getLocalDate());
            sale.setQuantity(quantity);
            sale.setTotalPrice(totalPrice);
            component.setQuantity(componentsEJB.find(index).getQuantity()-quantity);
            
            salesEJB.create(sale);
            componentsEJB.edit(component);
            FacesContext.getCurrentInstance().addMessage("MessageId", new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Venta realizada con éxito"));
        }else{
            FacesContext.getCurrentInstance().addMessage("MessageId", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Cantidad introducida superior a la disponible o inválida"));
        }
    }
    
    public List<Components> loadComponentList(){
        try {
            return componentsEJB.findComponentsList();
        } catch (Exception e) {
            System.out.println("Oh no! Algo ha ido mal: " + e.getMessage());
            return null;
        }
    }
    
    public double roundTwoDecimals(){
        String totalPriceString = String.format(Locale.US,"%.2f", quantity * component.getPrice());
        return Double.parseDouble(totalPriceString);
    }
    
    public void calculatePrice(){
        totalPrice = roundTwoDecimals();
    }
    
    public void moveLeft(){
        if(index!=1){
            index--;
        }
        component = componentsList.get(index-1);
        calculatePrice();
    }
    
    public void moveRight(){
        if(index!=componentsList.size()){
            index++;
        }
        component = componentsList.get(index-1);
        calculatePrice();
    }
    
    public List<Clients> getClientsList(){
        return clientsList;
    }
    
    public void setClientsList(List<Clients> listCli){
        clientsList = listCli;
    }

    public List<SelectItem> getClientsItemsList() {
        return clientsItemsList;
    }

    public void setClientsItemsList(List<SelectItem> clientsItemsList) {
        this.clientsItemsList = clientsItemsList;
    }

    public ClientsFacadeLocal getClientsEJB() {
        return clientsEJB;
    }

    public void setClientsEJB(ClientsFacadeLocal clientsEJB) {
        this.clientsEJB = clientsEJB;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int cliente) {
        this.clienteId = cliente;
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
    
    
}
