/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.OrdersFacadeLocal;
import EJB.ProductionsFacadeLocal;
import EJB.SalesFacadeLocal;
import java.io.Serializable;
import java.text.NumberFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Orders;
import modelo.Productions;
import modelo.Sales;

/**
 *
 * @author Pablo Javier Barrio Navarro
 */
@Named //Ambitos de clase: Vista, aplicacion, sesion, peticion 
@ViewScoped
public class DashboardController implements Serializable{
    
    private ZoneId defaultZoneId;
    private NumberFormat format;
    private LocalDate now;
    
    @EJB
    private OrdersFacadeLocal ordersEJB;
    
    @EJB
    private SalesFacadeLocal salesEJB;
    
    @EJB
    private ProductionsFacadeLocal productionsEJB;
    
    @PostConstruct
    public void init() {
        defaultZoneId = ZoneId.systemDefault();
        format = NumberFormat.getCurrencyInstance(new Locale("es", "ES"));
        now = LocalDate.now();
    }
    
    public void redirectProfile() {
        try {
            System.out.println("redirecciono a perfil");
            FacesContext.getCurrentInstance().getExternalContext().redirect("profile.xhtml");
        } catch (Exception e) {
            System.out.println("[ERR] controller dashboard redirect perfil error" + e.getMessage());
        }

    }
    
    public void redirectAlmacen() {
        try {
            System.out.println("redirecciono a almacen");
            FacesContext.getCurrentInstance().getExternalContext().redirect("almacen.xhtml");
        } catch (Exception e) {
            System.out.println("[ERR] controller dashboard redirect almacen error" + e.getMessage());
        }

    }
    
    public void redirectDashboard() {
        try {
            System.out.println("redirecciono a dashboard");
            FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml");
        } catch (Exception e) {
            System.out.println("[ERR] controller dashboard redirect almacen error" + e.getMessage());
        }

    }
    
    public String materialWaste() {
        List<Orders> orders = ordersEJB.findAll();
        double totalPrice = 0;
        for (int i=0; i<orders.size(); i++) {
            Date date = orders.get(i).getDate();
            Instant ins = date.toInstant();
            LocalDate localDate = ins.atZone(defaultZoneId).toLocalDate();
            if(localDate.getMonthValue()==now.getDayOfMonth() &&
                    localDate.getYear()==now.getYear()) {
                totalPrice += orders.get(i).getTotalPrice();
            }
        }
        return format.format(totalPrice);
    }
    
    public String saleProfit() {
        List<Sales> sales = salesEJB.findAll();
        double totalPrice = 0;
        for (int i=0; i<sales.size(); i++) {
            Date date = sales.get(i).getDate();
            Instant ins = date.toInstant();
            LocalDate localDate = ins.atZone(defaultZoneId).toLocalDate();
            if(localDate.getMonthValue()==now.getDayOfMonth() &&
                    localDate.getYear()==now.getYear()) {
                totalPrice += sales.get(i).getTotalPrice();
            }
        }
        return format.format(totalPrice);
    }
    
    public int workInProgress() {
        List<Productions> productions = productionsEJB.findAll();
        int totalProductions = 0;
        for (int i=0; i<productions.size(); i++) {
            Date date = productions.get(i).getDate();
            Instant ins = date.toInstant();
            LocalDate localDate = ins.atZone(defaultZoneId).toLocalDate();
            if(localDate.getMonthValue()==now.getDayOfMonth() &&
                    localDate.getYear()==now.getYear()) {
                totalProductions ++;
            }
        }
        return totalProductions;
    }

    public ZoneId getDefaultZoneId() {
        return defaultZoneId;
    }

    public void setDefaultZoneId(ZoneId defaultZoneId) {
        this.defaultZoneId = defaultZoneId;
    }

    public NumberFormat getFormat() {
        return format;
    }

    public void setFormat(NumberFormat format) {
        this.format = format;
    }

    public LocalDate getNow() {
        return now;
    }

    public void setNow(LocalDate now) {
        this.now = now;
    }
    
    
    
}
