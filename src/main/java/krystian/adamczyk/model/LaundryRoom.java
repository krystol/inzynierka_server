package krystian.adamczyk.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class LaundryRoom {
  @Id
  @GeneratedValue
  private int id;
  private String roomName;
  private Integer keyInRoomNumber;
  private Integer roomNumber;
}
