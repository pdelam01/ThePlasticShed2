/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import EJB.ComponentsFacadeLocal;

/**
 *
 * @author De la Hera
 */
public class Utils {
    
    public static boolean validDNI(String dni){
        if(dni.length()!=9) {
            return false;
        }
        
        for(int i=0; i<7; i++) {
            if(!Character.isDigit(dni.charAt(i))) {
                return false;
            }
        }
        
        if(!Character.isUpperCase(dni.charAt(8))) {
            return false;
        }
        
        return true;
    }
    
    public static boolean validPhoneNumber(String phone){
        if(phone.length()==9){
            for(int i=0; i < phone.length(); i++) {
                if(!Character.isDigit(phone.charAt(i))) {
                    return false;
                }
            }
        }else{
            return false;
        }
        
        return true;
    }
    
    public static boolean validSSN(String ssn){
        if(ssn.length()==10){
            for(int i=0; i < ssn.length(); i++) {
                if(!Character.isDigit(ssn.charAt(i))) {
                    return false;
                }
            }
        }else{
            return false;
        }
        
        return true;
    }
    
    public static boolean validUsername(String username){
        if (username.length() == 8) {
            String letras = username.substring(0, 6);
            String digitos = username.substring(6);

            for (int i = 0; i < letras.length(); i++) {
                if (!Character.isAlphabetic(letras.charAt(i))) {
                    return false;
                }
            }
            
            for(int i=0; i < digitos.length(); i++) {
                if(!Character.isDigit(digitos.charAt(i))) {
                    return false;
                }
            }
        }else{
            return false;
        }
        
        return true;
    }
    
    public static boolean validName(String name){
        if(name.length() != 0){
            for (int i = 0; i < name.length(); i++) {
                if (!Character.isAlphabetic(name.charAt(i))) {
                    return false;
                }
            }
        }else{
            return false;
        }
        
        return true;
    }
    
    public static boolean validQuantity(int quantity, ComponentsFacadeLocal componentsEJB, int index){
        if(quantity > 0 && quantity < componentsEJB.find(index).getQuantity()){
            return true;
        }else{
            return false;
        }
    }
    
    public static boolean isANum(String num) {
        for (Character character : num.toCharArray()) {
            if (!Character.isDigit(character)) {
                return false;
            }
        }
        return true;
    }


}
