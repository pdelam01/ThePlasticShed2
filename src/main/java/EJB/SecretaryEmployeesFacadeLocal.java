/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Local;
import modelo.SecretaryEmployees;

/**
 *
 * @author De la Hera
 */
@Local
public interface SecretaryEmployeesFacadeLocal {

    void create(SecretaryEmployees secretaryEmployees);

    void edit(SecretaryEmployees secretaryEmployees);

    void remove(SecretaryEmployees secretaryEmployees);

    SecretaryEmployees find(Object id);

    List<SecretaryEmployees> findAll();

    List<SecretaryEmployees> findRange(int[] range);

    int count();
    
}
