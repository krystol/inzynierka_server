package krystian.adamczyk.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Room {
  @Id
  @GeneratedValue
  private int id;
  private String roomName;
  private boolean isOccupied;
  private Integer keyInRoomNumber;
  private Integer roomNumber;
  @ManyToOne
  @JoinColumn(name="user_id")
  private User occupiedByUser;
}