/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name = "components")

public class Components implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idComponent;

    @Column(name = "Name")
    private String name;

    @Column(name = "UPC")
    private String upc;

    @JoinColumn(name = "UPCAditionalComponent")
    @ManyToOne
    private Components aditionalComponent;

    @Column(name = "Quantity")
    private int quantity;
    
    @Column(name = "Price")
    private double price;

    public int getId() {
        return idComponent;
    }

    public void setId(int id) {
        this.idComponent = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public Components getUpcAditionalComponents() {
        return aditionalComponent;
    }

    public void setUpcAditionalComponents(Components upcAditionalComponents) {
        this.aditionalComponent = upcAditionalComponents;
    }
    
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + this.idComponent;
        hash = 61 * hash + Objects.hashCode(this.name);
        hash = 61 * hash + Objects.hashCode(this.upc);
        hash = 61 * hash + Objects.hashCode(this.aditionalComponent);
        hash = 61 * hash + this.quantity;
        hash = 61 * hash + (int) (Double.doubleToLongBits(this.price) ^ (Double.doubleToLongBits(this.price) >>> 32));
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
        final Components other = (Components) obj;
        if (this.idComponent != other.idComponent) {
            return false;
        }
        if (this.quantity != other.quantity) {
            return false;
        }
        if (Double.doubleToLongBits(this.price) != Double.doubleToLongBits(other.price)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.upc, other.upc)) {
            return false;
        }
        if (!Objects.equals(this.aditionalComponent, other.aditionalComponent)) {
            return false;
        }
        return true;
    }

    
    
    
    
}