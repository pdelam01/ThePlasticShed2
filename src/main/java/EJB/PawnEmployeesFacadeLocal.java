/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Local;
import modelo.PawnEmployees;

/**
 *
 * @author diego
 */
@Local
public interface PawnEmployeesFacadeLocal {

    void create(PawnEmployees pawnEmployees);

    void edit(PawnEmployees pawnEmployees);

    void remove(PawnEmployees pawnEmployees);

    PawnEmployees find(Object id);

    List<PawnEmployees> findAll();

    List<PawnEmployees> findRange(int[] range);

    int count();
    
}
