package schemagenerator;

import entities.Address;
import entities.CityInfo;
import entities.Hobby;
import entities.Person;
import entities.Phone;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class SchemaGenerator {

    public static void main(String[] args) {
        Persistence.generateSchema("CA2REST_PU", null);

        EntityManager em = Persistence.createEntityManagerFactory("CA2REST_PU").createEntityManager();

        em.getTransaction().begin();

        Person p = new Person("Daniel", "Winkel", "dwp@dwp.com");
        Person p1 = new Person("Mohammed", "Winkel", "dwp@dwp.com");
        Person p2 = new Person("David", "Winkel", "dwp@dwp.com");

        Address address = new Address("Nygardsvej 123", new CityInfo(2980, "Kokkedal"));
        Phone ph = new Phone("93949192", "mobil");
        Hobby hb = new Hobby("Football", "kick ball");
        Hobby hb1 = new Hobby("Tennis", "hit ball");
        Hobby hb2 = new Hobby("Swimming", "swim in water");
        Hobby hb3 = new Hobby("Running", "run Forest run!");

        p.addHobby(hb);
        p.addHobby(hb1);
        p.addHobby(hb2);
        p1.addHobby(hb3);
        p.addPhone(ph);
        p.setAddress(address);
        p1.setAddress(address);

        em.persist(p);
        em.persist(p1);
        em.persist(p2);
        em.getTransaction().commit();

//        List<Person> ps = new ArrayList();
//        ps.add(em.find(Person.class, p.getId()));
//        ps.add(em.find(Person.class, p1.getId()));
//        ps.add(em.find(Person.class, p2.getId()));
//
//        for (Person pp : ps) {
//            if (!pp.getHobbies().isEmpty()) {
//                System.out.println(pp.getHobbies().get(0).getName());
//            }
//            System.out.println(pp.getFirstName());
//            if (pp.getAddress() != null) {
//                System.out.println(pp.getAddress().getStreet());
//            }
//        }
        em.close();

    }

}
