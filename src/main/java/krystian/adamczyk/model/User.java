package krystian.adamczyk.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private int id;
  private Integer livingInRoomNumber;
  private String firstName;
  private String lastName;
  @Column(unique = true)
  private String username;
}