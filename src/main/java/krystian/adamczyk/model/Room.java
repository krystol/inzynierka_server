package krystian.adamczyk.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity
public class Room {
  @Id
  @GeneratedValue
  private int id;
  @ManyToOne
  @JoinColumn(name="room_type", nullable=false)
  private RoomType roomType;
  private boolean isOccupied;
  private int keyInRoomNumber;
  @ManyToOne
  @JoinColumn(name="user_id", nullable=false)
  private User occupiedByUser;
}