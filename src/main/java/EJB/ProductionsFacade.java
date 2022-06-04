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
import modelo.Productions;

/**
 *
 * @author De la Hera
 */
@Stateless
public class ProductionsFacade extends AbstractFacade<Productions> implements ProductionsFacadeLocal {

    @PersistenceContext(unitName = "ThePlasticShed2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductionsFacade() {
        super(Productions.class);
    }

    @Override
    public List<Productions> findProductionsList() {
        try {
            return em.createQuery(
                    "FROM Productions p")
                    .getResultList();
        } catch (Exception e) {
             System.out.println("Oh no! Algo ha ido mal");
             return null;
        }
    }
    
}
