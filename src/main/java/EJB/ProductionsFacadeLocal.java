/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Local;
import modelo.Productions;

/**
 *
 * @author De la Hera
 */
@Local
public interface ProductionsFacadeLocal {

    void create(Productions productions);

    void edit(Productions productions);

    void remove(Productions productions);

    Productions find(Object id);

    List<Productions> findAll();

    List<Productions> findRange(int[] range);
    
    List<Productions> findProductionsList();

    int count();
    
}
