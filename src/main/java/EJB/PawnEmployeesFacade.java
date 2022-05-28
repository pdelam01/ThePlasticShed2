/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.PawnEmployees;

/**
 *
 * @author diego
 */
@Stateless
public class PawnEmployeesFacade extends AbstractFacade<PawnEmployees> implements PawnEmployeesFacadeLocal {

    @PersistenceContext(unitName = "ThePlasticShed2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PawnEmployeesFacade() {
        super(PawnEmployees.class);
    }
    
}
