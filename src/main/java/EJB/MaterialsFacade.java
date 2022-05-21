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
import modelo.Materials;

/**
 *
 * @author De la Hera
 */
@Stateless
public class MaterialsFacade extends AbstractFacade<Materials> implements MaterialsFacadeLocal {

    @PersistenceContext(unitName = "ThePlasticShed2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MaterialsFacade() {
        super(Materials.class);
    }
    
     public  List<Materials> findMaterialsList(){
        try {
            return em.createQuery(
                    "FROM Materials m")
                    .getResultList();
        } catch (Exception e) {
             System.out.println("Oh no! Algo ha ido mal: " + e.getMessage());
             return null;
        }
    }
    
}
