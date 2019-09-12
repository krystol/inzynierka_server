package krystian.adamczyk.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class AuthenticationRequest implements Serializable {
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private long id;
  @Column(unique = true)
  private String username;
  private String password;
  private int enabled;

}
