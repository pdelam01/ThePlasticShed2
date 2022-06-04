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
    private int idEmployee;
    
    @Column (name = "NameEmp")
    private String name;
    
    @Column (name = "DNI")
    private String dni;
    
    @Column (name = "SSN")
    private String ssn;
    
    @Column (name = "Role")
    private String role;
    
    @Column (name = "PhoneNumber")
    private String phoneNum;
    
    @Column (name = "Birthday")
    @Temporal(TemporalType.DATE)
    private Date birthday;
    
    @Column (name = "Username")
    private String username;
    
    @Column (name = "Password")
    private String pass;
    
    @Column (name = "Salt")
    private String salt;
    
    @Column (name = "Active")
    private int active;

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int id) {
        this.idEmployee = id;
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

    public String getRole() {
        return role.charAt(0) + role.substring(1).toLowerCase();
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getBirthday() {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");  
        return format.format(birthday);  
    }
    
    public Date getBirthdayDate() {
        return this.birthday;
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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.idEmployee;
        hash = 53 * hash + Objects.hashCode(this.name);
        hash = 53 * hash + Objects.hashCode(this.dni);
        hash = 53 * hash + Objects.hashCode(this.ssn);
        hash = 53 * hash + Objects.hashCode(this.role);
        hash = 53 * hash + Objects.hashCode(this.phoneNum);
        hash = 53 * hash + Objects.hashCode(this.birthday);
        hash = 53 * hash + Objects.hashCode(this.username);
        hash = 53 * hash + Objects.hashCode(this.pass);
        hash = 53 * hash + Objects.hashCode(this.salt);
        hash = 53 * hash + this.active;
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
        if (this.idEmployee != other.idEmployee) {
            return false;
        }
        if (this.active != other.active) {
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
        if (!Objects.equals(this.phoneNum, other.phoneNum)) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.pass, other.pass)) {
            return false;
        }
        if (!Objects.equals(this.salt, other.salt)) {
            return false;
        }
        if (!Objects.equals(this.birthday, other.birthday)) {
            return false;
        }
        return true;
    }

    
    
    
  
}