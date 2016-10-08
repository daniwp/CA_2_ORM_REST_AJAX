package facade;

import entities.Address;
import entities.CityInfo;
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
        EntityManager em = getEntityManager();
        Phone p = null;
        Person person = null;

        try {
            em.getTransaction().begin();

            person = em.find(Person.class, id);
            p = em.find(Phone.class, phone.getId());

            if (p == null) {
                p = phone;

            }

            if (person != null) {
                person.addPhone(p);
                em.merge(person);

                em.getTransaction().commit();

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return person;

    }

    @Override
    public Person addAddressToPerson(Address address, long id) {
        EntityManager em = getEntityManager();
        Address a = null;
        Person p = null;

        try {
            em.getTransaction().begin();

            p = em.find(Person.class, id);
            a = em.find(Address.class, address.getId());

            if (a == null) {
                a = address;

            }

            if (p != null) {
                p.setAddress(a);
                em.merge(p);
                em.getTransaction().commit();

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return p;
    }

    @Override
    public Person deletePerson(long id) {
        EntityManager em = getEntityManager();
        Person p = null;

        try {
            em.getTransaction().begin();

            p = em.find(Person.class, id);

            em.remove(p);

            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return p;
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

    @Override
    public Person editPerson(Person person, long id) {
        EntityManager em = getEntityManager();
        Person p = null;

        try {
            em.getTransaction().begin();

            person.setId(id);
            em.merge(person);

            em.getTransaction().commit();

            p = em.find(Person.class, id);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return person;
    }

    @Override
    public Person getPersonOnPhone(String number) {
        EntityManager em = getEntityManager();
        Person person = null;

        long phoneID = -1;
        long personID = -1;

        try {
            phoneID = em.createQuery("SELECT p.id FROM Phone p WHERE p.number = :number", Long.class).setParameter("number", number).getResultList().get(0);
            personID = (long) em.createNativeQuery("SELECT person_phone.`Person_ID` FROM person_phone WHERE `phones_ID` = ?id").setParameter("id", phoneID).getResultList().get(0);

            person = em.find(Person.class, personID);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return person;
    }

    @Override
    public List<Person> getPersonsWithHobby(String name) {
        EntityManager em = getEntityManager();
        Hobby h = null;

        List<Long> personIDs = new ArrayList();
        List<Person> persons = new ArrayList();

        try {
            h = em.createQuery("SELECT h FROM Hobby h WHERE h.name = :name", Hobby.class).setParameter("name", name).getResultList().get(0);

            if (h == null) {
                return persons;
            }

            personIDs = (List<Long>) em.createNativeQuery("SELECT person_hobby.`persons_ID` FROM person_hobby WHERE `hobbies_ID` = ?id").setParameter("id", h.getId()).getResultList();

            if (personIDs.isEmpty()) {
                return persons;
            }

            for (Long id : personIDs) {
                persons.add(em.find(Person.class, id));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return persons;
    }

    @Override
    public List<CityInfo> getCities() {
        EntityManager em = getEntityManager();
        List<CityInfo> cities = new ArrayList();

        try {
            cities = em.createQuery("SELECT c FROM CityInfo c", CityInfo.class).getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return cities;
    }

    @Override
    public long getNumberOfPersonsWithHobby(String name) {
        EntityManager em = getEntityManager();
        Hobby h = null;

        List<Long> personIDs = new ArrayList();
        int count = -1;

        try {
            h = em.createQuery("SELECT h FROM Hobby h WHERE h.name = :name", Hobby.class).setParameter("name", name).getResultList().get(0);

            if (h == null) {
                return count;
            }

            personIDs = (List<Long>) em.createNativeQuery("SELECT person_hobby.`persons_ID` FROM person_hobby WHERE `hobbies_ID` = ?id").setParameter("id", h.getId()).getResultList();

            count = personIDs.size();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }
}
