/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entities.CityInfo;
import entities.Company;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import entities.Person;

/**
 *
 * @author HazemSaeid
 */
public class PersonFacade implements IPersonFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("CA2REST_PU");
    EntityManager em = emf.createEntityManager();

    Person p = new Person();
    Company c = new Company();
    CityInfo ci = new CityInfo();
    @Override
    public Person getPerson(int id) {
        Person p = new Person();

        try {
            em.getTransaction().begin();
            p = (Person) em.createNativeQuery("SELECT P.ID FROM Person p", Person.class).getSingleResult();

        } catch (Exception e) {
        } finally {
            em.close();
        }
        return p;
    }

    @Override
    public List<Person> getPersons() {
        try {
            em.getTransaction().begin();
            Person p = (Person) em.createQuery("SELECT p FROM Person p", Person.class).getResultList();

        } catch (Exception e) {

        } finally {
            em.close();

        }
        return (List<Person>) p;
    }

    @Override
    public Company getCompany(String cvr) {
        try {
            em.getTransaction().begin();
            Company c = em.createQuery("SELECT c.cvr FROM Company c", Company.class).getSingleResult();
        } catch (Exception e) {
        } finally {
            em.close();

        }
        return c;
    }

    @Override
    public List<Person> getPersonZip(int zipCode) {
        try {
            em.getTransaction().begin();
            p = (Person) em.createNativeQuery("SELECT CityInfo.ZipCode, Person.ID, Person.firstName FROM ZipCode INNER JOIN Person ON CityInfo.ID=Person.ID").getResultList();
        } catch (Exception e) {
        }   finally {
            em.close();
        }
        return (List<Person>) p;
    }

}
