/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
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
@Table (name = "orders")

public class Orders implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idOrder;

    @JoinColumn(name = "idMaterial")
    @ManyToOne
    private Materials idMaterial;

    @JoinColumn(name = "idEmployee")
    @ManyToOne
    private Employees employee;

    @JoinColumn(name = "idSupplier")
    @ManyToOne
    private Suppliers idSupplier;

    @Column(name = "Date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "Quantity")
    private int quantity;

    @Column(name = "TotalPrice")
    private int totalPrice;

    public int getId() {
        return idOrder;
    }

    public void setId(int id) {
        this.idOrder = id;
    }

    public Materials getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(Materials idMaterial) {
        this.idMaterial = idMaterial;
    }

    public Employees getIdEmployee() {
        return employee;
    }

    public void setIdEmployee(Employees emp) {
        this.employee = emp;
    }

    public Suppliers getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(Suppliers idSupplier) {
        this.idSupplier = idSupplier;
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

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + this.id;
        hash = 41 * hash + Objects.hashCode(this.idMaterial);
        hash = 41 * hash + Objects.hashCode(this.employee);
        hash = 41 * hash + Objects.hashCode(this.idSupplier);
        hash = 41 * hash + Objects.hashCode(this.date);
        hash = 41 * hash + this.quantity;
        hash = 41 * hash + this.totalPrice;
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
        final Orders other = (Orders) obj;
        if (this.idOrder != other.idOrder) {
            return false;
        }
        if (this.quantity != other.quantity) {
            return false;
        }
        if (this.totalPrice != other.totalPrice) {
            return false;
        }
        if (!Objects.equals(this.idMaterial, other.idMaterial)) {
            return false;
        }
        if (!Objects.equals(this.employee, other.employee)) {
            return false;
        }
        if (!Objects.equals(this.idSupplier, other.idSupplier)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }

    

    
}
