package krystian.adamczyk.service;

import krystian.adamczyk.model.ApplicationException;
import krystian.adamczyk.model.BoardMessage;
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

  List<Room> listRoomsExceptLaundry();

  List<Room> listAllLaundry();

  User saveUser(User user);

  User updateUser(User user);

  void removeUser(User user);
  User findFirstUserByFirstNameAndLastName(String firstName, String lastName);
}