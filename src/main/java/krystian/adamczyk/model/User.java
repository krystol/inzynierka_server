package krystian.adamczyk.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@Entity
public class User {
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private int id;
  private Integer livingInRoomNumber;
  private String firstName;
  private String lastName;
}