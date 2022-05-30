/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Local;
import modelo.Sales;

/**
 *
 * @author De la Hera
 */
@Local
public interface SalesFacadeLocal {

    void create(Sales sales);

    void edit(Sales sales);

    void remove(Sales sales);

    Sales find(Object id);

    List<Sales> findAll();

    List<Sales> findRange(int[] range);
    
    List<Sales> findSalesList();

    int count();
    
}
