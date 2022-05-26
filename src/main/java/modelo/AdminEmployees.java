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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table (name = "administratoremployees")

/**
 *
 * @author De la Hera
 */
public class AdminEmployees implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OneToOne
    private Employees id;

    public Employees getId() {
        return id;
    }

    public void setId(Employees id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.id);
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
        final AdminEmployees other = (AdminEmployees) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
}
