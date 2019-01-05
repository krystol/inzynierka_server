package krystian.adamczyk.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Data
@Entity
public class BoardMessage {
  @Id
  @GeneratedValue
  @NotNull
  private int id;
  @ManyToOne
  @JoinColumn(name="user_id", nullable=false)
  private User author;
  private String title;
  private String message;
  private Date date;

  public BoardMessage(){
    date = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
  }
}