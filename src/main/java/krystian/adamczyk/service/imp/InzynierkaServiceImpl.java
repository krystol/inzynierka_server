package krystian.adamczyk.service.imp;

import krystian.adamczyk.model.ApplicationException;
import krystian.adamczyk.model.BoardMessage;
import krystian.adamczyk.model.LaundryDay;
import krystian.adamczyk.model.LaundryRoom;
import krystian.adamczyk.model.Room;
import krystian.adamczyk.model.User;
import krystian.adamczyk.repository.BoardMessageJpaRepository;
import krystian.adamczyk.repository.LaundryDayJpaRepository;
import krystian.adamczyk.repository.LaundryRoomJpaRepository;
import krystian.adamczyk.repository.RoomJpaRepository;
import krystian.adamczyk.repository.UserJpaRepository;
import krystian.adamczyk.service.InzynierkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class InzynierkaServiceImpl implements InzynierkaService {

  private LaundryRoomJpaRepository laundryRoomJpaRepository;
  private LaundryDayJpaRepository laundryDayJpaRepository;
  private UserJpaRepository userJpaRepository;
  private RoomJpaRepository roomJpaRepository;
  private BoardMessageJpaRepository boardMessageJpaRepository;
  private DatabaseFillerOnStartup dtabaseFillerOnStartup;

  @Autowired
  public InzynierkaServiceImpl(UserJpaRepository userJpaRepository,
                               RoomJpaRepository roomJpaRepository,
                               BoardMessageJpaRepository boardMessageJpaRepository,
                               DatabaseFillerOnStartup dtabaseFillerOnStartup,
                               LaundryRoomJpaRepository laundryRoomJpaRepository,
                               LaundryDayJpaRepository laundryDayJpaRepository){
    this.userJpaRepository=userJpaRepository;
    this.roomJpaRepository=roomJpaRepository;
    this.boardMessageJpaRepository=boardMessageJpaRepository;
    this.dtabaseFillerOnStartup=dtabaseFillerOnStartup;
    this.laundryRoomJpaRepository = laundryRoomJpaRepository;
    this.laundryDayJpaRepository = laundryDayJpaRepository;
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
  public List<Room> listRooms() {
      return roomJpaRepository.findAll();
  }

  @Override
  public List<LaundryRoom> listAllLaundry() {
    return laundryRoomJpaRepository.findAll();
  }

  @Override
  public LaundryDay findLaundryDayByRoomIdAndDate(int roomId, String date) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
    LocalDate localDate = LocalDate.parse(date,formatter);
    return laundryDayJpaRepository.findByLaundryIdAndDate(roomId,localDate).orElseThrow(ApplicationException::new);
  }

  @Override
  public LaundryDay saveLaundryDayByRoomIdAndDate(LaundryDay room) {
    return laundryDayJpaRepository.save(room);
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

  public User findFirstUserByFirstNameAndLastName(String firstName, String lastName){
    return userJpaRepository.findUserByFirstNameAndLastName(firstName,lastName);
  }
}