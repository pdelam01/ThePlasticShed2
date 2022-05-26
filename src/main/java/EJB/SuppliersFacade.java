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
import modelo.Suppliers;

/**
 *
 * @author De la Hera
 */
@Stateless
public class SuppliersFacade extends AbstractFacade<Suppliers> implements SuppliersFacadeLocal {

    @PersistenceContext(unitName = "ThePlasticShed2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SuppliersFacade() {
        super(Suppliers.class);
    }
    
    public List<Suppliers> findSuppliersList(){
        try {
            return em.createQuery(
                    "FROM Suppliers s")
                    .getResultList();
        } catch (Exception e) {
             System.out.println("Oh no! Algo ha ido mal: " + e.getMessage());
             return null;
        }
    }
    
}
