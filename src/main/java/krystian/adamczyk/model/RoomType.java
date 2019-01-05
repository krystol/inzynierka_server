package krystian.adamczyk.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Data
@Entity
public class RoomType {
  @Id
  @GeneratedValue
  private int id;
  private String roomName;
  @OneToMany(mappedBy="roomType", cascade = CascadeType.PERSIST)
  private Set<Room> rooms;
}