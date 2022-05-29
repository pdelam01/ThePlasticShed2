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
    private float price;

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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.idComponent;
        hash = 41 * hash + Objects.hashCode(this.name);
        hash = 41 * hash + Objects.hashCode(this.upc);
        hash = 41 * hash + Objects.hashCode(this.aditionalComponent);
        hash = 41 * hash + this.quantity;
        hash = 41 * hash + Float.floatToIntBits(this.price);
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
        if (Float.floatToIntBits(this.price) != Float.floatToIntBits(other.price)) {
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