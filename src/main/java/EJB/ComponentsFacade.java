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
import modelo.Components;

/**
 *
 * @author De la Hera
 */
@Stateless
public class ComponentsFacade extends AbstractFacade<Components> implements ComponentsFacadeLocal {

    @PersistenceContext(unitName = "ThePlasticShed2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ComponentsFacade() {
        super(Components.class);
    }
    
    public  List<Components> findComponentsList(){
        try {
            return em.createQuery(
                    "FROM Components c")
                    .getResultList();
        } catch (Exception e) {
             System.out.println("Oh no! Algo ha ido mal: " + e.getMessage());
             return null;
        }
    }
    
}
