/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

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


    public List loginCredentials(String username, String password) {
        try {
            System.out.println(username+" "+password);
            return em.createQuery(
                    "FROM Employees e WHERE e.username=:user AND e.pass=:pass")
                    .setParameter("user", username)
                    .setParameter("pass", password)
                    .getResultList();
        } catch (Exception e) {
             System.out.println("Oh no! Algo ha ido mal: " + e.getMessage());
             return null;
        }
    }
    
    public List showEmployeeInfo(String username) {
        try {
            System.out.println(username+" ");
            return em.createQuery(
                    "FROM Employees e WHERE e.username=:user")
                    .setParameter("user", username)
                    .getResultList();
        } catch (Exception e) {
             System.out.println("Oh no! Algo ha ido mal: " + e.getMessage());
             return null;
        }
    }
    
    public void updateEmployee(String user, String name) {
        try {
            
        } catch (Exception e) {
            System.out.println("Oh no! Algo ha ido mal: " + e.getMessage());
        }
    }
    
}
