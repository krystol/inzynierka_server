package krystian.adamczyk.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class User {
  @Id
  @GeneratedValue
  @NotNull
  private int id;
  private Integer livingInRoomNumber;
  private String firstName;
  private String lastName;
}