package JsonConverterTests;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
   public static void main(String[] args) {
      Result result = JUnitCore.runClasses(JsonConverterTest.class);
		
      for (Failure failure : result.getFailures()) {
         System.out.println(failure.toString());
      }
      String res;
      if (result.wasSuccessful()) {
          res = "Passed";
      } else {
          res = "Failed";
      }
		
      System.out.println("Results for JsonConverterTest are: " + res);
   }
} 