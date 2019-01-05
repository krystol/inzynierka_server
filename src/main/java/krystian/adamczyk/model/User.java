package krystian.adamczyk.model;


import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Entity
public class User {
  @Id
  @GeneratedValue
  @NotNull
  private int id;
  private int livingInRoomNumber;
  private String firstName;
  private String lastName;
  @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
  private Set<BoardMessage> boardMessages;
  @OneToMany(mappedBy = "occupiedByUser", cascade = CascadeType.PERSIST)
  private Set<Room> occupiedRooms;
}