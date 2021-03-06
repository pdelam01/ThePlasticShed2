/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.Date;
import java.util.List;
import javax.ejb.Local;
import modelo.Employees;

/**
 *
 * @author De la Hera
 */
@Local
public interface EmployeesFacadeLocal {

    void create(Employees employees);

    void edit(Employees employees);
    
    void remove(Employees employees);

    Employees find(Object id);

    List<Employees> findAll();

    List<Employees> findRange(int[] range);

    int count();
    
    List loginCredentials(String username);

    List showEmployeeInfo(int id);
    
    void updateEmployee(String user, String name, String dni, Date date);
    
    boolean updateEmployeeSensibiliti(String ssn, String phone, String dni, int id); 
    
    List<Employees> findEmployeesList();
}
