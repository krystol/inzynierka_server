package krystian.adamczyk.controller;

import krystian.adamczyk.model.BoardMessage;
import krystian.adamczyk.model.User;
import krystian.adamczyk.service.InzynierkaService;
import org.springframework.beans.factory.annotation.Autowired;
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
  public ResponseEntity retrieveUser(@PathVariable int id) throws Exception {
    return new ResponseEntity<>(service.findUser(id),HttpStatus.OK);
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
      return new ResponseEntity<>(service.listRooms(),HttpStatus.OK);
    } catch (Exception e){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping(value = "/saveBoardMessage")
  public ResponseEntity saveBoardMessage(@RequestBody BoardMessage message){
    //service.zapiszOgloszenie(message);
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
    return new ResponseEntity<>(HttpStatus.OK);
  }
}