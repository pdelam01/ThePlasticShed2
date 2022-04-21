/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Local;
import modelo.Components;

/**
 *
 * @author De la Hera
 */
@Local
public interface ComponentsFacadeLocal {

    void create(Components components);

    void edit(Components components);

    void remove(Components components);

    Components find(Object id);

    List<Components> findAll();

    List<Components> findRange(int[] range);

    int count();
    
}
