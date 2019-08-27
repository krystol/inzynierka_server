package krystian.adamczyk.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class RegisterRequest implements Serializable {
  private String username;
  private String password;
  private String firstName;
  private String lastName;
  private String roomNumber;
}
