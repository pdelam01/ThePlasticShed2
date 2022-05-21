/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Local;
import modelo.Materials;

/**
 *
 * @author De la Hera
 */
@Local
public interface MaterialsFacadeLocal {

    void create(Materials materials);

    void edit(Materials materials);

    void remove(Materials materials);

    Materials find(Object id);

    List<Materials> findAll();

    List<Materials> findRange(int[] range);
    
    List<Materials> findMaterialsList();
    
    int count();
    
}
