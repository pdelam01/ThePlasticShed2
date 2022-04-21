/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Local;
import modelo.AdminEmployees;

/**
 *
 * @author De la Hera
 */
@Local
public interface AdminEmployeesFacadeLocal {

    void create(AdminEmployees adminEmployees);

    void edit(AdminEmployees adminEmployees);

    void remove(AdminEmployees adminEmployees);

    AdminEmployees find(Object id);

    List<AdminEmployees> findAll();

    List<AdminEmployees> findRange(int[] range);

    int count();
    
}
