package JsonConverterTests;

import entities.Person;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import jsonconverter.JSONConverter;
import static jsonconverter.JSONConverter.getClassInstance;

/**
 *
 * @author John
 */
public class StringCatcher {
    public static void main(String[] args) {
    JSONConverter con = getClassInstance(); 
    Person p1 = new Person("Hans", "Christian", "hans@christian.dk");
    Person p2 = new Person("Simon", "Andersen", "Simon@andersen.dk");
    List<Person> plist = new ArrayList<Person>();
    plist.add(p1);
    plist.add(p2);
        System.out.println(con.getJsonFromPersonAllDetails(p1));
        System.out.println(con.getJsonFromPersonsAllDetails(plist));
    }

}
