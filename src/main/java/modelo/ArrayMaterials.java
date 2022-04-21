/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;


@Entity
@Table (name = "arraymaterials")
/**
 *
 * @author De la Hera
 */
public class ArrayMaterials implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "idMaterial")
    private Materials idMaterial;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "idComponente")
    private Components idComponent;

    public Materials getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(Materials idMaterial) {
        this.idMaterial = idMaterial;
    }

    public Components getIdComponent() {
        return idComponent;
    }

    public void setIdComponent(Components idComponent) {
        this.idComponent = idComponent;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.idMaterial);
        hash = 29 * hash + Objects.hashCode(this.idComponent);
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
        final ArrayMaterials other = (ArrayMaterials) obj;
        if (!Objects.equals(this.idMaterial, other.idMaterial)) {
            return false;
        }
        if (!Objects.equals(this.idComponent, other.idComponent)) {
            return false;
        }
        return true;
    }

   
    
}
