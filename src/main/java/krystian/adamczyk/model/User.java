package krystian.adamczyk.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Data
@Entity
public class User {
  @Id
  @GeneratedValue
  private int id;
  private Integer livingInRoomNumber;
  private String firstName;
  private String lastName;
  @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
  @JsonManagedReference
  private Set<BoardMessage> boardMessages;
  @OneToMany(mappedBy = "occupiedByUser", cascade = CascadeType.PERSIST)
  @JsonManagedReference
  private Set<Room> occupiedRooms;
}