/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.EmployeesFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.component.behavior.FacesBehavior;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Employees;

/**
 *
 * @author De la Hera
 */

@Named
@ViewScoped
public class EmployeesController implements Serializable{
    
    private List<Employees> employeesList;
    
    @EJB
    private EmployeesFacadeLocal employeesEJB;
    
    @PostConstruct
    public void init(){
        employeesList = loadEmployeesList();
    }

    public List<Employees> loadEmployeesList() {
        try {
            List<Employees> lista = employeesEJB.findEmployeesList();
            System.out.println("Employees size: "+lista.size());
            return employeesEJB.findEmployeesList();
        } catch (Exception e) {
            System.out.println("Oh no! Algo ha ido mal: " + e.getMessage());
            return null;
        }
    }
    
    public void removeEmployees(int index){
        try {
            System.out.println("IDENTIFICADOR: "+(index-1));
            System.out.println(" NOMBRE USER "+employeesList.get(index-1).getNameEmp());
            System.out.println(" IDENT USER "+employeesList.get(index-1).getIdEmployee());
            employeesEJB.remove(employeesList.get(index-1));
            FacesContext.getCurrentInstance().getExternalContext().redirect("usermng.xhtml");
        } catch (Exception e) {
             System.out.println("Oh no! Algo ha ido mal: " + e.getMessage());
        }
    }
    
    public List<Employees> getEmployeesList(){
        return employeesList;
    }
    
    public void setEmployeesList(List<Employees> listEmp){
        employeesList = listEmp;
    }
}
