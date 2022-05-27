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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table (name = "secretaryemployees")

/**
 *
 * @author De la Hera
 */
public class SecretaryEmployees implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSecretary;
    
    @JoinColumn(name = "idemployee")
    @OneToOne
    private Employees employee;

    public int getIdSecretary() {
        return idSecretary;
    }

    public void setIdSecretary(int idSecretary) {
        this.idSecretary = idSecretary;
    }

    public Employees getEmployee() {
        return employee;
    }

    public void setEmployee(Employees employee) {
        this.employee = employee;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + this.idSecretary;
        hash = 23 * hash + Objects.hashCode(this.employee);
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
        final SecretaryEmployees other = (SecretaryEmployees) obj;
        if (this.idSecretary != other.idSecretary) {
            return false;
        }
        if (!Objects.equals(this.employee, other.employee)) {
            return false;
        }
        return true;
    }
    
    
}
