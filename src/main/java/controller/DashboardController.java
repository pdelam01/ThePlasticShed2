/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.ComponentsFacadeLocal;
import EJB.EmployeesFacadeLocal;
import EJB.MaterialsFacadeLocal;
import EJB.OrdersFacadeLocal;
import EJB.ProductionsFacadeLocal;
import EJB.SalesFacadeLocal;
import java.io.Serializable;
import java.text.NumberFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Components;
import modelo.Employees;
import modelo.Materials;
import modelo.Orders;
import modelo.Productions;
import modelo.Sales;
import org.primefaces.component.linechart.LineChart;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.PieChartModel;

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
    private List<Sales> sales;
    private List<Double> salesMonth;
    
    @EJB
    private OrdersFacadeLocal ordersEJB;
    
    @EJB
    private SalesFacadeLocal salesEJB;
    
    @EJB
    private ProductionsFacadeLocal productionsEJB;
    
    @EJB
    private EmployeesFacadeLocal employeesEJB;
    
    @EJB
    private ComponentsFacadeLocal componentsEJB;
    
    @EJB
    private MaterialsFacadeLocal materialsEJB;
    
    @PostConstruct
    public void init() {
        defaultZoneId = ZoneId.systemDefault();
        format = NumberFormat.getCurrencyInstance(new Locale("es", "ES"));
        now = LocalDate.now();
        sales = salesEJB.findAll();
        salesMonth = salesPerMonth();
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
            if(localDate.getMonthValue() == now.getMonthValue() &&
                    localDate.getYear() == now.getYear()) {
                totalPrice += orders.get(i).getTotalPrice();
            }
        }
        return format.format(totalPrice);
    }
    
    public String saleProfit() {
        double totalPrice = 0;
        for (int i=0; i<sales.size(); i++) {
            Date date = sales.get(i).getDate();
            Instant ins = date.toInstant();
            LocalDate localDate = ins.atZone(defaultZoneId).toLocalDate();
            if(localDate.getMonthValue() == now.getMonthValue() &&
                    localDate.getYear() == now.getYear()) {
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
            if(localDate.getMonthValue() == now.getMonthValue() &&
                    localDate.getYear() == now.getYear()) {
                totalProductions ++;
            }
        }
        return totalProductions;
    }
    
    public String employeeOfTheMonth() {
        List<Employees> employees = employeesEJB.findAll();
        sales = filterSales(sales);
        Map<Employees, Double> map = createMap(employees);
        map = fillMap(map, sales);
        Employees betterEmployee = bestEmployee(map);
        return betterEmployee.getNameEmp();
    }
    
    private List<Sales> filterSales(List<Sales> sales) {
        List<Sales> finalSales = new ArrayList<>();
        for (int i=0; i<sales.size(); i++) {
            Date date = sales.get(i).getDate();
            Instant ins = date.toInstant();
            LocalDate localDate = ins.atZone(defaultZoneId).toLocalDate();
            if(localDate.getMonthValue()==now.getMonthValue() &&
                    localDate.getYear()==now.getYear()) {
                finalSales.add(sales.get(i));
            }
        }
        return finalSales;
    }
    
    private Map<Employees, Double> createMap(List<Employees> emp) {
        Map<Employees, Double> map = new HashMap<>();
        for (int i=0; i<emp.size(); i++) {
            map.put(emp.get(i), 0.0);
        }
        return map;
    }
    
    private Map<Employees, Double> fillMap(Map<Employees, Double> map,  List<Sales> sales) {
        for (int i=0; i<sales.size(); i++) {
            Sales sale = sales.get(i);
            map.put(sale.getEmployee(), map.get(sale.getEmployee())+sale.getTotalPrice());
        }
        return map;
    }
    
    private Employees bestEmployee(Map<Employees, Double> map) {
        Iterator iterator = map.entrySet().iterator();
        double best = 0.0;
        Employees bestEmployee = new Employees();
        while(iterator.hasNext()) {
            Entry entry = (Entry) iterator.next();
            if((double)entry.getValue()>=best) {
                best = (double) entry.getValue();
                bestEmployee = (Employees) entry.getKey();
            }
        }
        return bestEmployee;
    } 
    
    public LineChartModel getGraph() {
        LineChartModel salesGraph = new LineChartModel();
        salesGraph.addSeries(createSeries());
        salesGraph.setLegendPosition("Ventas");
        //salesGraph
        
        Axis y = salesGraph.getAxis(AxisType.Y);
        y.setMin(0);
        y.setMax(1000);
        y.setLabel("Euros");

        Axis x = salesGraph.getAxis(AxisType.X);
        x.setMin(1);
        x.setMax(12);
        x.setTickInterval("1");
        x.setLabel("Meses");
        return salesGraph;
    }
    
    private LineChartSeries createSeries() {
        LineChartSeries saleSerie = new LineChartSeries();
        saleSerie.setLabel("Ventas");
        for (int i=0; i<salesMonth.size(); i++) {
            saleSerie.set(i+1, salesMonth.get(i));
        }        
        return saleSerie;
    }
    
    private List<Double> salesPerMonth() {
        List<Double> salesMonth = createList();
        for (int i=1; i<13; i++) {
            for (int j=0; j<sales.size(); j++) {
                Sales sale = sales.get(j);
                Date date = sale.getDate();
                Instant ins = date.toInstant();
                LocalDate localDate = ins.atZone(defaultZoneId).toLocalDate();
                if(localDate.getMonthValue()==i) {
                    salesMonth.set(i-1, salesMonth.get(i-1)+sale.getTotalPrice());
                }
            }
        }
        return salesMonth;
    }
    
    private List<Double> createList() {
        List<Double> list = new ArrayList<>();
        for(int i=0; i<12; i++) {
            list.add(0.0);
        }
        return list;
    }
    
    public PieChartModel getCircularGraph() {
        PieChartModel pieModel = new PieChartModel(); 
        
        List<Materials> materials = materialsEJB.findAll();
        pieModel.set("Componentes", obtaintComponentQuantity());  
        pieModel.set("Materiales", obtaintMaterialQuantity());  
        //pieModel.set("Volvo", 400);  
        return pieModel;
    }
    
    private int obtaintComponentQuantity() {
        List<Components> components = componentsEJB.findAll();
        int quantity = 0;
        for (int i=0; i<components.size(); i++) {
            quantity += components.get(i).getQuantity();
        }
        return quantity;
    }
    
    private int obtaintMaterialQuantity() {
        List<Materials> materials = materialsEJB.findAll();
        int quantity = 0;
        for (int i=0; i<materials.size(); i++) {
            quantity += materials.get(i).getQuantity();
        }
        return quantity;
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

    public List<Sales> getSales() {
        return sales;
    }

    public void setSales(List<Sales> sales) {
        this.sales = sales;
    }

    public List<Double> getSalesMonth() {
        return salesMonth;
    }

    public void setSalesMonth(List<Double> salesMonth) {
        this.salesMonth = salesMonth;
    }
    
    
    
}
