/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.ClientsFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Clients;

/**
 *
 * @author De la Hera
 */

@Named
@ViewScoped
public class ClientsController implements Serializable {
    
    private List<Clients> clientsList;
    
    @EJB
    private ClientsFacadeLocal clientsEJB;
    
    @PostConstruct
    public void init(){
        clientsList = loadClientsList();
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
    
    public List<Clients> getClientsList(){
        return clientsList;
    }
    
    public void setClientsList(List<Clients> listCli){
        clientsList = listCli;
    }
}
