package krystian.adamczyk.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class AuthenticationRequest implements Serializable {
  @Id
  private long id;
  @Column(unique = true)
  private String username;
  private String password;
}
