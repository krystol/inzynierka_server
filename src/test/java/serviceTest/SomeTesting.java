
package serviceTest;

import krystian.adamczyk.service.InzynierkaService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.DriverManager;

public class SomeTesting {

  @Autowired
  private InzynierkaService service;

  @Test
  public void connectToDB() {
    try {
      Class.forName("com.mysql.jdbc.Driver");
    } catch(ClassNotFoundException ex) {
      // use the exception here
    }
  }
}