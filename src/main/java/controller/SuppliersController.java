/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.SuppliersFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Suppliers;

/**
 *
 * @author De la Hera
 */

@Named
@ViewScoped
public class SuppliersController implements Serializable {
    
    private List<Suppliers> suppliersList;
    
    @EJB
    private SuppliersFacadeLocal suppliersEJB;
    
    @PostConstruct
    public void init(){
        suppliersList = loadSuppliersList();
    }
    
    public List<Suppliers> loadSuppliersList(){
        try {
            List<Suppliers> lista = suppliersEJB.findSuppliersList();
            System.out.println("Proveedores size: "+lista.size());
            return suppliersEJB.findSuppliersList();
        } catch (Exception e) {
            System.out.println("Oh no! Algo ha ido mal: " + e.getMessage());
            return null;
        }
    }
    
    public List<Suppliers> getSuppliersList(){
        return suppliersList;
    }
    
    public void setSuppliersList(List<Suppliers> listSupp){
        suppliersList = listSupp;
    }
}
