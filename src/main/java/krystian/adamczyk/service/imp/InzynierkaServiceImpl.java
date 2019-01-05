package krystian.adamczyk.service.imp;

import krystian.adamczyk.model.BoardMessage;
import krystian.adamczyk.model.Room;
import krystian.adamczyk.model.User;
import krystian.adamczyk.repository.BoardMessageJpaRepository;
import krystian.adamczyk.repository.RoomJpaRepository;
import krystian.adamczyk.repository.RoomTypeJpaRepository;
import krystian.adamczyk.repository.UserJpaRepository;
import krystian.adamczyk.service.InzynierkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InzynierkaServiceImpl implements InzynierkaService {

  private UserJpaRepository userJpaRepository;
  private RoomJpaRepository roomJpaRepository;
  private RoomTypeJpaRepository roomTypeJpaRepository;
  private BoardMessageJpaRepository boardMessageJpaRepository;

  @Autowired
  public InzynierkaServiceImpl(UserJpaRepository userJpaRepository, RoomJpaRepository roomJpaRepository, RoomTypeJpaRepository roomTypeJpaRepository, BoardMessageJpaRepository boardMessageJpaRepository){
    this.userJpaRepository=userJpaRepository;
    this.roomJpaRepository=roomJpaRepository;
    this.roomTypeJpaRepository=roomTypeJpaRepository;
    this.boardMessageJpaRepository=boardMessageJpaRepository;
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
    return boardMessageJpaRepository.findAll();
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
    return roomJpaRepository.findAll();
  }

  @Override
  public List<Room> listAllLaundry() {
    List<Room> allRooms = listRooms();
    for(Room r : allRooms){
      if(!r.getRoomType().getRoomName().equals("pralnia")){
        allRooms.remove(r);
      }
    }
    return allRooms;
  }
}