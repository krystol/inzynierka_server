package krystian.adamczyk.service.imp;

import krystian.adamczyk.model.ApplicationException;
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
  public User findUser(int id) throws ApplicationException {
    return userJpaRepository.findById(id).orElseThrow(ApplicationException::new);
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
  public void saveRoom(Room room) {
    roomJpaRepository.save(room);
  }

  @Override
  public List<Room> listRoomsExceptLaundry() {
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

  @Override
  public User saveUser(User user) {
    return userJpaRepository.save(user);
  }

  @Override
  public User updateUser(User user) throws ApplicationException{
    User toBeUpdated = findUser(user.getId());
    toBeUpdated.setFirstName(user.getFirstName());
    toBeUpdated.setLastName(user.getLastName());
    toBeUpdated.setLivingInRoomNumber(user.getLivingInRoomNumber());
    return saveUser(toBeUpdated);
  }

  @Override
  public void removeUser(User user) throws ApplicationException {
    User updated = findUser(user.getId());
    userJpaRepository.delete(updated);
  }

  private List<Room> getRooms() {
    return roomJpaRepository.findAll();
  }

  public User findFirstUserByFirstNameAndLastName(String firstName, String lastName){
    return userJpaRepository.findUserByFirstNameAndLastName(firstName,lastName);
  }
}