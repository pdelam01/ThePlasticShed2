/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Local;
import modelo.ArrayMaterials;

/**
 *
 * @author De la Hera
 */
@Local
public interface ArrayMaterialsFacadeLocal {

    void create(ArrayMaterials arrayMaterials);

    void edit(ArrayMaterials arrayMaterials);

    void remove(ArrayMaterials arrayMaterials);

    ArrayMaterials find(Object id);

    List<ArrayMaterials> findAll();

    List<ArrayMaterials> findRange(int[] range);

    int count();
    
}
