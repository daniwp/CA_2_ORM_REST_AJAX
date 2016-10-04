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
import java.util.ArrayList;

/**
 *
 * @author HazemSaeid
 */
public class PersonFacade implements IPersonFacade {

    EntityManagerFactory emf;
    
    private static PersonFacade instance = null;
    public static PersonFacade getInstance() {
        if (instance == null) {
            instance = new PersonFacade(Persistence.createEntityManagerFactory("CA2REST_PU"));
        }
        return instance;
    }

    public PersonFacade(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public Person getPerson(long id) {
        Person p = null;
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();
            p =  em.find(Person.class, id);

        } catch (Exception e) {
        } finally {
            em.close();
        }
        return p;
    }

    @Override
    public List<Person> getPersons() {
        List<Person> persons = new ArrayList();
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();
            persons = em.createQuery("SELECT p FROM Person p", Person.class).getResultList();

        } catch (Exception e) {
        } finally {
            em.close();

        }
        return persons;
    }

    @Override
    public Company getCompany(String cvr) {
        Company c = null;
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();
            c = em.createQuery("SELECT c FROM Company c WHERE c.cvr = :cvr", Company.class).setParameter("cvr", cvr).getSingleResult();

        } catch (Exception e) {
        } finally {
            em.close();

        }

        return c;
    }

    @Override
    public List<Person> getPersonsFromZipcode(int zipCode) {
        List<Person> persons = new ArrayList();
        EntityManager em = getEntityManager();

        try {
            CityInfo city = getCityInfo(zipCode);

            persons = em.createQuery("SELECT p FROM Person p WHERE p.address.city = :city", Person.class).setParameter("city", city).getResultList();

        } catch (Exception e) {
        } finally {
            em.close();
        }

        return persons;
    }

    public CityInfo getCityInfo(int zipCode) {
        CityInfo cityInfo = null;
        EntityManager em = getEntityManager();

        try {
            cityInfo = em.createQuery("SELECT c FROM CityInfo c WHERE c.zipCode = :zip", CityInfo.class).setParameter("zip", zipCode).getSingleResult();

        } catch (Exception e) {
        }

        return cityInfo;
    }

}
