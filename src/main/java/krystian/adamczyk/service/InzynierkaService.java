package krystian.adamczyk.service;

import krystian.adamczyk.model.ApplicationException;
import krystian.adamczyk.model.AuthenticationRequest;
import krystian.adamczyk.model.Authorities;
import krystian.adamczyk.model.BoardMessage;
import krystian.adamczyk.model.LaundryDay;
import krystian.adamczyk.model.LaundryRoom;
import krystian.adamczyk.model.Room;
import krystian.adamczyk.model.User;

import java.util.List;

public interface InzynierkaService {

  User findUser(int id);

  List<BoardMessage> listBoardMessages();

  void zapiszOgloszenie(BoardMessage boardMessage);

  void usunOgloszenie(BoardMessage boardMessage);

  void edytujOgloszenie(BoardMessage boardMessage);

  void saveRoom(Room room);

  List<Room> listRooms();

  List<LaundryRoom> listAllLaundry();

  LaundryDay findLaundryDayByRoomIdAndDate(int roomId, String date);

  LaundryDay saveLaundryDayByRoomIdAndDate(LaundryDay room);

  User saveUser(User user);

  AuthenticationRequest saveCreds(AuthenticationRequest request);

  User updateUser(User user);

  void removeUser(User user);

  User findFirstUserByFirstNameAndLastName(String firstName, String lastName);

  Authorities saveAuthority(Authorities authorities);
}