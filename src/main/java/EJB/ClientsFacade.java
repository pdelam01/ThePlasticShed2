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
import modelo.Clients;

/**
 *
 * @author De la Hera
 */
@Stateless
public class ClientsFacade extends AbstractFacade<Clients> implements ClientsFacadeLocal {

    @PersistenceContext(unitName = "ThePlasticShed2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClientsFacade() {
        super(Clients.class);
    }
    
    public  List<Clients> findClientsList(){
        try {
            return em.createQuery(
                    "FROM Clients c")
                    .getResultList();
        } catch (Exception e) {
             System.out.println("Oh no! Algo ha ido mal");
             return null;
        }
    }
    
}
