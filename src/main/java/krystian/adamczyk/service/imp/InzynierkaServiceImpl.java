package krystian.adamczyk.service.imp;

import krystian.adamczyk.model.BoardMessage;
import krystian.adamczyk.model.Room;
import krystian.adamczyk.model.User;
import krystian.adamczyk.repository.BoardMessageJpaRepository;
import krystian.adamczyk.repository.RoomJpaRepository;
import krystian.adamczyk.repository.UserJpaRepository;
import krystian.adamczyk.service.InzynierkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InzynierkaServiceImpl implements InzynierkaService {

  private UserJpaRepository userJpaRepository;
  private RoomJpaRepository roomJpaRepository;
  private BoardMessageJpaRepository boardMessageJpaRepository;
  private DatabaseFillerOnStartup dtabaseFillerOnStartup;

  @Autowired
  public InzynierkaServiceImpl(UserJpaRepository userJpaRepository, RoomJpaRepository roomJpaRepository, BoardMessageJpaRepository boardMessageJpaRepository, DatabaseFillerOnStartup dtabaseFillerOnStartup){
    this.userJpaRepository=userJpaRepository;
    this.roomJpaRepository=roomJpaRepository;
    this.boardMessageJpaRepository=boardMessageJpaRepository;
    this.dtabaseFillerOnStartup=dtabaseFillerOnStartup;
  }

  @Override
  public User findUser(int id) throws Exception {
    Optional<User> student = userJpaRepository.findById(id);

    if (!student.isPresent())
      throw new Exception();

    return student.get();
  }

  @Override
  public List<BoardMessage> listBoardMessages() {
    List<BoardMessage> l = boardMessageJpaRepository.findAll();
    return l;
  }

  @Override
  public void zapiszOgloszenie(BoardMessage boardMessage) {
    boardMessageJpaRepository.save(boardMessage);
  }

  @Override
  public void usunOgloszenie(BoardMessage boardMessage) {
    boardMessageJpaRepository.delete(boardMessage);
  }

  @Override
  public void edytujOgloszenie(BoardMessage boardMessage) {
    zapiszOgloszenie(boardMessage);
  }

  @Override
  public void bookRoom(Room room, User user) {
    room.setOccupied(true);
    room.setOccupiedByUser(user);
    roomJpaRepository.save(room);
  }

  @Override
  public void freeRoom(Room room) {
    room.setOccupied(false);
    room.setOccupiedByUser(null);
    roomJpaRepository.save(room);
  }

  @Override
  public void prolongBookingOfRoom(Room room) {

  }

  @Override
  public boolean isRoomOccupied(Room room) {
    return roomJpaRepository.findById(room.getId()).get().isOccupied();
  }

  @Override
  public List<Room> listRooms() {
    List<Room> tmp = getRooms();
    List<Room> returnList = new ArrayList<>();
    for(Room r : tmp){
      if(!r.getRoomName().contains("Pralnia")){
        returnList.add(r);
      }
    }
    return returnList;
  }

  @Override
  public List<Room> listAllLaundry() {
    List<Room> tmp = getRooms();
    List<Room> returnList = new ArrayList<>();
    for(Room r : tmp){
      if(r.getRoomName().contains("Pralnia")){
        returnList.add(r);
      }
    }
    return returnList;
  }

  public List<Room> getRooms() {
    return roomJpaRepository.findAll();
  }
}