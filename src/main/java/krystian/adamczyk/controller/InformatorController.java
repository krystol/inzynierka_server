package krystian.adamczyk.controller;

import krystian.adamczyk.model.ApplicationException;
import krystian.adamczyk.model.BoardMessage;
import krystian.adamczyk.model.Room;
import krystian.adamczyk.model.User;
import krystian.adamczyk.service.InzynierkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class InformatorController {

  private InzynierkaService service;

  @Autowired
  public InformatorController(InzynierkaService service){
    this.service = service;
  }

  @GetMapping(value = "/users/{id}", produces = "application/json")
  public ResponseEntity retrieveUser(@PathVariable int id) {
    try {
      User user = service.findUser(id);
      return new ResponseEntity<>(user, HttpStatus.OK);
    }catch(ApplicationException e){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping(value = "/user/")
  public ResponseEntity saveUser(@RequestBody User user) {
    User savedUser = service.saveUser(user);
    HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.set("Location", "/user/" + savedUser.getId());
    return new ResponseEntity<>(responseHeaders, HttpStatus.CREATED);
  }


  @GetMapping(value = "/boardMessages", produces = "application/json")
  public ResponseEntity listBoardMessages(){
    try{
      return new ResponseEntity<>(service.listBoardMessages(),HttpStatus.OK);
    } catch (Exception e){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping(value = "/getrooms", produces = "application/json")
  public ResponseEntity listRooms(){
    try{
      return new ResponseEntity<>(service.listRoomsExceptLaundry(),HttpStatus.OK);
    } catch (Exception e){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping(value = "/getlaundry", produces = "application/json")
  public ResponseEntity listLaundry(){
    try{
      return new ResponseEntity<>(service.listAllLaundry(),HttpStatus.OK);
    } catch (Exception e){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping(value = "/saveBoardMessage")
  public ResponseEntity saveBoardMessage(@RequestBody BoardMessage message){
    saveUser(message.getAuthor());
    service.zapiszOgloszenie(message);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @PostMapping(value = "/saveRoom")
  public ResponseEntity saveRoom(@RequestBody Room room){
    saveUser(room.getOccupiedByUser());
    service.saveRoom(room);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @DeleteMapping(value = "/deleteBoardMessage")
  public ResponseEntity deleteBoardMessage(@RequestBody BoardMessage message){
    service.usunOgloszenie(message);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PutMapping(value = "/saveBoardMessage")
  public ResponseEntity editBoardMessage(@RequestBody BoardMessage message){
    service.edytujOgloszenie(message);
    return new ResponseEntity<>(HttpStatus.ACCEPTED);
  }

  @PutMapping(value = "/user/")
  public ResponseEntity updateUser(@RequestBody User user) {
    try {
      return new ResponseEntity<>(service.updateUser(user), HttpStatus.ACCEPTED);
    }catch(ApplicationException e){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  @DeleteMapping(value = "/user/")
  public ResponseEntity removeUser(@RequestBody User user) {
    try {
      service.removeUser(user);
      return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }catch(ApplicationException e){
      return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
  }
}