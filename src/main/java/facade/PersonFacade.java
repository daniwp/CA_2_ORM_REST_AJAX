/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entities.Address;
import entities.CityInfo;
import entities.Company;
import entities.Hobby;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import entities.Person;
import entities.Phone;
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
            p = em.find(Person.class, id);

        } catch (Exception e) {
            e.printStackTrace();
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
            e.printStackTrace();
        } finally {
            em.close();

        }
        return persons;
    }

    @Override
    public List<Person> getPersonsFromZipcode(int zipCode) {
        List<Person> persons = new ArrayList();
        EntityManager em = getEntityManager();

        try {
            CityInfo city = getCityInfo(zipCode);

            persons = em.createQuery("SELECT p FROM Person p WHERE p.address.city = :city", Person.class).setParameter("city", city).getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return persons;
    }

    private CityInfo getCityInfo(int zipCode) {
        CityInfo cityInfo = null;
        EntityManager em = getEntityManager();

        try {
            cityInfo = em.createQuery("SELECT c FROM CityInfo c WHERE c.zip = :zip", CityInfo.class).setParameter("zip", zipCode).getResultList().get(0);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cityInfo;
    }

    @Override
    public Person addPerson(Person person) {
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();

            if (person.getAddress() != null) {

                CityInfo city = getCityInfo(person.getAddress().getCity().getZipCode());

                if (city != null) {
                    person.getAddress().setCity(city);
                    em.merge(person);
                    em.getTransaction().commit();
                    return person;
                }
            }
            
            em.persist(person);

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return person;
    }

    @Override
    public Person addHobbyToPerson(Hobby hobby, long id) {
        EntityManager em = getEntityManager();
        Person p = null;

        try {
            em.getTransaction().begin();

            p = em.find(Person.class, id);

            hobby = addHobby(hobby);
            p.addHobby(hobby);

            em.merge(p);

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return p;
    }

    @Override
    public Person addPhoneToPerson(Phone phone, long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Person addAddressToPerson(Address address, long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Person deletePerson(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Hobby addHobby(Hobby hobby) {
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();

            Hobby h = em.find(Hobby.class, hobby);

            if (h != null) {
                return h;
            }

            em.persist(hobby);

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return hobby;
    }
}
