/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author HazemSaeid
 */
public class SchemaBuiler {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CA2REST_PU");
         EntityManager em = emf.createEntityManager();
         Persistence.generateSchema("CA2REST_PU", null);
    }
}
