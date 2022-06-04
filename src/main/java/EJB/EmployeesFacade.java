/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Employees;

/**
 *
 * @author De la Hera
 */
@Stateless
public class EmployeesFacade extends AbstractFacade<Employees> implements EmployeesFacadeLocal {

    @PersistenceContext(unitName = "ThePlasticShed2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmployeesFacade() {
        super(Employees.class);
    }


    public List loginCredentials(String username) {
        try {
            System.out.println(username);
            return em.createQuery(
                    "FROM Employees e WHERE e.username=:user")
                    .setParameter("user", username)
                    .getResultList();
        } catch (Exception e) {
             System.out.println("Oh no! Algo ha ido mal");
             return null;
        }
    }
    
    public List showEmployeeInfo(int id) {
        try {
            return em.createQuery(
                    "FROM Employees e WHERE e.idEmployee=:id")
                    .setParameter("id", id)
                    .getResultList();
        } catch (Exception e) {
             System.out.println("Oh no! Algo ha ido mal");
             return null;
        }
    }
    
    public void updateEmployee(String user, String name, String dni, Date date) {
        try {
            em.createQuery(
                    "UPDATE Employees e SET e.username=:user, e.name=:name, e.birthday=:date"
                            + " WHERE e.dni=:dni")
                    .setParameter("user", user)
                    .setParameter("name", name)
                    .setParameter("date", date)
                    .setParameter("dni", dni)
                    .executeUpdate();
        } catch (Exception e) {
            System.out.println("Oh no! Algo ha ido mal");
        }
    }
    
    public void updateEmployeeSensibiliti(String ssn, String phone, String dni, int id) {
        try {
            em.createQuery(
                    "UPDATE Employees e SET e.ssn=:ssn, e.phoneNum=:phone, e.dni=:dni"
                            + " WHERE e.idEmployee=:id")
                    .setParameter("ssn", ssn)
                    .setParameter("phone", phone)
                    .setParameter("dni", dni)
                    .setParameter("id", id)
                    .executeUpdate();
        } catch (Exception e) {
            System.out.println("Oh no! Algo ha ido mal");
        }
    }
    
    public List<Employees> findEmployeesList() {
        try {
            return em.createQuery(
                    "FROM Employees e")
                    .getResultList();
        } catch (Exception e) {
            System.out.println("Oh no! Algo ha ido mal");
            return null;
        }
    }
    
}
