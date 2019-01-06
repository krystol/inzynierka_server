package krystian.adamczyk.service;

import krystian.adamczyk.model.BoardMessage;
import krystian.adamczyk.model.Room;
import krystian.adamczyk.model.User;

import java.util.List;

public interface InzynierkaService {

  User findUser(int id) throws Exception;

  List<BoardMessage> listBoardMessages();

  void zapiszOgloszenie(BoardMessage boardMessage);

  void usunOgloszenie(BoardMessage boardMessage);

  void edytujOgloszenie(BoardMessage boardMessage);

  void bookRoom(Room room, User user);

  void freeRoom(Room room);

  void prolongBookingOfRoom(Room room);

  boolean isRoomOccupied(Room room);

  List<Room> listRooms();

  List<Room> listAllLaundry();

  List<Room> listAllExceptLaundry();
}