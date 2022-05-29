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
@Table (name = "materials")

public class Materials implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMaterial;

    @Column(name = "Name")
    private String name;

    @Column(name = "UPC")
    private String upc;

    @Column(name = "Quantity")
    private int quantity;

    @Column(name = "Price")
    private float price;

    @JoinColumn(name = "idSupplier")
    @ManyToOne
    private Suppliers supplier;

    public int getId() {
        return idMaterial;
    }

    public void setId(int id) {
        this.idMaterial = id;
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

    public Suppliers getIdSupplier() {
        return supplier;
    }

    public void setIdSupplier(Suppliers idSupplier) {
        this.supplier = idSupplier;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + this.idMaterial;
        hash = 23 * hash + Objects.hashCode(this.name);
        hash = 23 * hash + Objects.hashCode(this.upc);
        hash = 23 * hash + this.quantity;
        hash = 23 * hash + Float.floatToIntBits(this.price);
        hash = 23 * hash + Objects.hashCode(this.supplier);
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
        final Materials other = (Materials) obj;
        if (this.idMaterial != other.idMaterial) {
            return false;
        }
        if (this.quantity != other.quantity) {
            return false;
        }
        if (this.price != other.price) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.upc, other.upc)) {
            return false;
        }
        if (!Objects.equals(this.supplier, other.supplier)) {
            return false;
        }
        return true;
    }

    
    
    
}