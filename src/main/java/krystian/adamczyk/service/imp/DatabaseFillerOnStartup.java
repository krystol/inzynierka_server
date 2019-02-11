package krystian.adamczyk.service.imp;

import krystian.adamczyk.model.BoardMessage;
import krystian.adamczyk.model.Room;
import krystian.adamczyk.model.User;
import krystian.adamczyk.repository.BoardMessageJpaRepository;
import krystian.adamczyk.repository.RoomJpaRepository;
import krystian.adamczyk.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DatabaseFillerOnStartup implements ApplicationListener<ContextRefreshedEvent> {


  private UserJpaRepository userJpaRepository;
  private RoomJpaRepository roomJpaRepository;
  private BoardMessageJpaRepository boardMessageJpaRepository;

  @Autowired
  public DatabaseFillerOnStartup(UserJpaRepository userJpaRepository, RoomJpaRepository roomJpaRepository, BoardMessageJpaRepository boardMessageJpaRepository){
    this.userJpaRepository=userJpaRepository;
    this.roomJpaRepository=roomJpaRepository;
    this.boardMessageJpaRepository=boardMessageJpaRepository;
  }

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {
    User ux = new User();
    ux.setFirstName("Krystian");
    ux.setLastName("Adamczyk");
    ux.setLivingInRoomNumber(214);
    userJpaRepository.save(ux);
    User u = new User();
    u.setFirstName("Tomasz");
    u.setLastName("Iksinski");
    u.setLivingInRoomNumber(215);
    userJpaRepository.save(u);
    User u2 = new User();
    u2.setFirstName("Jakub");
    u2.setLastName("Nowak");
    u2.setLivingInRoomNumber(105);
    userJpaRepository.save(u2);
    BoardMessage wiadomosc = new BoardMessage();
    wiadomosc.setAuthor(u);
    wiadomosc.setTitle("Sprzedam Opla");
    wiadomosc.setMessage("2005 rok, stoi za akademikiem");
    boardMessageJpaRepository.save(wiadomosc);
    BoardMessage wiadomosc2 = new BoardMessage();
    wiadomosc2.setAuthor(u);
    wiadomosc2.setTitle("Sprzedam lodowke");
    wiadomosc2.setMessage("Sprzedam lodowke za czteropak");
    boardMessageJpaRepository.save(wiadomosc2);
    Room r1 = new Room();
    r1.setRoomName("Bilard");
    r1.setRoomNumber(605);
    r1.setOccupied(true);
    r1.setOccupiedByUser(u);
    r1.setKeyInRoomNumber(100);
    Room r2 = new Room();
    r2.setRoomName("Silownia");
    r2.setRoomNumber(211);
    r2.setKeyInRoomNumber(200);
    Room r3 = new Room();
    r3.setRoomName("Pokoj spokojnej nauki");
    r3.setRoomNumber(501);
    r3.setKeyInRoomNumber(400);
    Room r4 = new Room();
    r4.setRoomName("Joker");
    r4.setRoomNumber(610);
    r4.setKeyInRoomNumber(500);
    Room r5 = new Room();
    r5.setRoomName("Ping-Pong");
    r5.setRoomNumber(611);
    r5.setKeyInRoomNumber(600);
    roomJpaRepository.save(r1);
    roomJpaRepository.save(r2);
    roomJpaRepository.save(r3);
    roomJpaRepository.save(r4);
    roomJpaRepository.save(r5);

    Room rp1 = new Room();
    rp1.setRoomName("Pralnia p. 1");
    rp1.setRoomNumber(125);
    rp1.setKeyInRoomNumber(102);
    roomJpaRepository.save(rp1);
    Room rp2 = new Room();
    rp2.setRoomName("Pralnia p. 2");
    rp2.setRoomNumber(225);
    rp2.setKeyInRoomNumber(202);
    roomJpaRepository.save(rp2);
    Room rp3 = new Room();
    rp3.setRoomName("Pralnia p. 3");
    rp3.setRoomNumber(325);
    rp3.setKeyInRoomNumber(302);
    roomJpaRepository.save(rp3);
    Room rp4 = new Room();
    rp4.setRoomName("Pralnia p. 4");
    rp4.setRoomNumber(425);
    rp4.setKeyInRoomNumber(402);
    roomJpaRepository.save(rp4);
    Room rp5 = new Room();
    rp5.setRoomName("Pralnia p. 5");
    rp5.setRoomNumber(525);
    rp5.setKeyInRoomNumber(502);
    roomJpaRepository.save(rp5);
    Room rp6 = new Room();
    rp6.setRoomName("Pralnia p. 6");
    rp6.setRoomNumber(625);
    rp6.setKeyInRoomNumber(602);
    roomJpaRepository.save(rp6);
    Room rp7 = new Room();
    rp7.setRoomName("Pralnia p. 7");
    rp7.setRoomNumber(725);
    rp7.setKeyInRoomNumber(702);
    roomJpaRepository.save(rp7);
    Room rp8 = new Room();
    rp8.setRoomName("Pralnia p. 8");
    rp8.setRoomNumber(825);
    rp8.setKeyInRoomNumber(802);
    roomJpaRepository.save(rp8);
    Room rp9 = new Room();
    rp9.setRoomName("Pralnia p. 9");
    rp9.setRoomNumber(925);
    rp9.setKeyInRoomNumber(902);
    roomJpaRepository.save(rp9);
    Room rp10 = new Room();
    rp10.setRoomName("Pralnia p. 10");
    rp10.setRoomNumber(1025);
    rp10.setKeyInRoomNumber(1002);
    roomJpaRepository.save(rp10);
  }
}
