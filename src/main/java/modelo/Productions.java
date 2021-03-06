/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table (name = "productions")

public class Productions implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProduction;

    @JoinColumn(name = "idComponent")
    @ManyToOne
    private Components component;

    @JoinColumn(name = "idEmployee")
    @ManyToOne
    private Employees employee;

    @Column(name = "Date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "QuantityComponent")
    private int quantity;

    public int getId() {
        return idProduction;
    }

    public void setId(int id) {
        this.idProduction = id;
    }

    public Components getIdComponent() {
        return component;
    }

    public void setIdComponent(Components idComponent) {
        this.component = idComponent;
    }

    public Employees getIdPawn() {
        return employee;
    }

    public void setIdPawn(Employees idPawn) {
        this.employee = idPawn;
    }
    
    public String getDateFormat() {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");  
        return format.format(date);  
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.idProduction;
        hash = 29 * hash + Objects.hashCode(this.component);
        hash = 29 * hash + Objects.hashCode(this.employee);
        hash = 29 * hash + Objects.hashCode(this.date);
        hash = 29 * hash + this.quantity;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Productions other = (Productions) obj;
        if (this.idProduction != other.idProduction) {
            return false;
        }
        if (this.quantity != other.quantity) {
            return false;
        }
        if (!Objects.equals(this.component, other.component)) {
            return false;
        }
        if (!Objects.equals(this.employee, other.employee)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }

    
    
}