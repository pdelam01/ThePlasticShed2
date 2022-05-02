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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author De la Hera
 */

@Entity
@Table (name="employees")

public class Employees implements Serializable{
    @Id
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    private int id;
    
    @Column (name = "NameEmp")
    private String name;
    
    @Column (name = "DNI")
    private String dni;
    
    @Column (name = "SSN")
    private String ssn;
    
    @Column (name = "Role")
    private String role;
    
    @Column (name = "PhoneNumber")
    private int phoneNum;
    
    @Column (name = "Birthday")
    @Temporal(TemporalType.DATE)
    private Date birthday;
    
    @Column (name = "Username")
    private String username;
    
    @Column (name = "Password")
    private String pass;

    public int getIdEmployee() {
        return id;
    }

    public void setIdEmployee(int id) {
        this.id = id;
    }

    public String getNameEmp() {
        return name;
    }

    public void setNameEmp(String name) {
        this.name = name;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getRange() {
        return role;
    }

    public void setRange(String role) {
        this.role = role;
    }

    public int getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(int phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + this.id;
        hash = 73 * hash + Objects.hashCode(this.name);
        hash = 73 * hash + Objects.hashCode(this.dni);
        hash = 73 * hash + Objects.hashCode(this.ssn);
        hash = 73 * hash + Objects.hashCode(this.role);
        hash = 73 * hash + this.phoneNum;
        hash = 73 * hash + Objects.hashCode(this.birthday);
        hash = 73 * hash + Objects.hashCode(this.username);
        hash = 73 * hash + Objects.hashCode(this.pass);
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
        final Employees other = (Employees) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.phoneNum != other.phoneNum) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.dni, other.dni)) {
            return false;
        }
        if (!Objects.equals(this.ssn, other.ssn)) {
            return false;
        }
        if (!Objects.equals(this.role, other.role)) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.pass, other.pass)) {
            return false;
        }
        if (!Objects.equals(this.birthday, other.birthday)) {
            return false;
        }
        return true;
    }
    
    
}