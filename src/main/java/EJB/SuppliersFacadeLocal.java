/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Local;
import modelo.Suppliers;

/**
 *
 * @author De la Hera
 */
@Local
public interface SuppliersFacadeLocal {

    void create(Suppliers suppliers);

    void edit(Suppliers suppliers);

    void remove(Suppliers suppliers);

    Suppliers find(Object id);

    List<Suppliers> findAll();

    List<Suppliers> findRange(int[] range);
    
    List<Suppliers> findSuppliersList();

    int count();
    
}
