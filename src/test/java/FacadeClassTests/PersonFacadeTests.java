package FacadeClassTests;

import entities.Person;
import facade.PersonFacade;
import java.util.List;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import org.junit.Test;

/**
 *
 * @author John
 */
public class PersonFacadeTests {

    protected PersonFacade facade = PersonFacade.getInstance();

    @Test
    public void testGetPerson() {
        long id = 4;

        Person p = facade.getPerson(id);
        long nId = p.getId();

        assertEquals(nId, id);
    }

    //As the id is first assigned to Person instance when persisted to db,
    //This method finds the current highest id in collection before the person is persisted
    //and expects the new persons id to be maxId + 1;  
    @Test
    public void testAddPerson() {
        List<Person> pList = facade.getPersons();
        long maxId = 0;
        for (Person pers : pList) {
            if (pers.getId() > maxId) {
                maxId = pers.getId();
            }
        }
        maxId++;

        Person p = new Person("Hans", "Chrsitian", "test@test.dk");
        facade.addPerson(p);

        long nId = facade.getPerson(maxId).getId();
        assertEquals(maxId, nId);

    }
    
    
    //Works, but deletes row from table. (Works only once)....
//    @Test
//    public void testDeletePerson() {
//        long pId = 5;
//        
//        facade.deletePerson(pId);
//        Person p = facade.getPerson(pId);
//        assertTrue(p == null);
//    }
    
//    @Test
//    public void testaddHobbyToPerson() {
//        Hobby hobby = new Hobby("TestHobby", "Test Description");
//        long pId = 1;
//        facade.addHobbyToPerson(hobby, pId);
//        Person p = facade.getPerson(pId);
//        assertTrue(p.getHobbies().contains(hobby));       
//    }
//    
//    @Test
//    public void addPhoneToPerson() {
//        String number = "12345678";
//        long pId = 1;
//        Phone phone = new Phone(number, "Test Phone");
//        
//        facade.addPhoneToPerson(phone, pId);
//        
//        Person p = facade.getPersonOnPhone(number);
//        long nId = p.getId();
//        assertEquals(pId, nId);
//        
//    }
    
    
}
