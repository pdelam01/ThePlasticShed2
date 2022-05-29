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
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Clients;
import modelo.Components;
import modelo.Sales;

/**
 *
 * @author De la Hera
 */

@Named
@ViewScoped
public class ClientsController implements Serializable {
    
    private List<Clients> clientsList;
    private List<SelectItem> clientsItemsList;
    private String cliente;
    private int index;
    private List<Components> componentsList;
    private Components component;
    private int quantity;
    private double totalPrice;
    private Sales sale;
    
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
        cliente = "";
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
            List<Clients> lista = clientsEJB.findClientsList();
            System.out.println("Clientes size: "+lista.size());
            return clientsEJB.findClientsList();
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
    
    public void doSale(){
        //Crear obj Sale, ejbsale añadir o crear y pasar obj sale creado. Chekar cantidad
        if(quantity > 0 || quantity < salesEJB.find(index).getQuantity()){
            /*sale.setQuantity(quantity);
            sale.setDate();
            sale.setId(index);
            sale.setIdClient(clientsList.get(index).getIdClient());
            sale.setIdComponent(component.getId());
            sale.setIdSecretary();
            sale.setTotalPrice(totalPrice);*/
        }else{
            //Venta no se realiza, cantidad < 0 || cantidad > cantidad db
        }
        
        salesEJB.edit(sale);
    }
    
    public List<Components> loadComponentList(){
        try {
            List<Components> lista = componentsEJB.findComponentsList();
            System.out.println("Componentes size: "+lista.size());
            return componentsEJB.findComponentsList();
        } catch (Exception e) {
            System.out.println("Oh no! Algo ha ido mal: " + e.getMessage());
            return null;
        }
    }
    
    public void calculatePrice(){
        totalPrice = quantity * component.getPrice();
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

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
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
