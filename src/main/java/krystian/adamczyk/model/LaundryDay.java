package krystian.adamczyk.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
@Data
public class LaundryDay {
  @Id
  @GeneratedValue
  private long dayId;
  private LocalDate date;
  private String hour8;
  private String hour9;
  private String hour10;
  private String hour11;
  private String hour12;
  private String hour13;
  private String hour14;
  private String hour15;
  private String hour16;
  private String hour17;
  private String hour18;
  private String hour19;
  private String hour20;
  private String hour21;
  @ManyToOne
  @JoinColumn(name="room_id")
  private LaundryRoom laundry;
}
