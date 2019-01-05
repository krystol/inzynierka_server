package krystian.adamczyk;

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

import java.util.ArrayList;
import java.util.List;

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
      BoardMessage wiadomosc = new BoardMessage();
      User u = new User();
      u.setFirstName("Tomasz");
      u.setLastName("Iksinski");
      u.setLivingInRoomNumber(214);
      wiadomosc.setAuthor(u);
      wiadomosc.setTitle("Sprzedam Opla");
      wiadomosc.setMessage("2005 rok, stoi za akademikiem");
      BoardMessage wiadomosc2 = new BoardMessage();
      wiadomosc2.setAuthor(u);
      wiadomosc2.setTitle("Sprzedam lodowke");
      wiadomosc2.setMessage("Sprzedam lodowke za czteropak");
      List<BoardMessage> lista = new ArrayList<>();
      lista.add(wiadomosc);
      lista.add(wiadomosc2);
      //service.zapiszOgloszenie(wiadomosc);
      //service.zapiszOgloszenie(wiadomosc2);
      //return new ResponseEntity<>(service.listBoardMessages(),HttpStatus.OK);
      return new ResponseEntity<>(lista,HttpStatus.OK);
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

/*
  private List<AccountDto> map(List<Account> accounts) {
    ModelMapper modelMapper = new ModelMapper();
    Type listType = new TypeToken<List<AccountDto>>() {
    }.getType();
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
    return modelMapper.map(accounts, listType);
  }


  private Account map(AccountDto accountDto) {
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
    return modelMapper.map(accountDto, Account.class);
  }

  private AccountDto map(Account account) {
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
    return modelMapper.map(account, AccountDto.class);
  }

  private void updateAccountEntity(AccountDto accountDto, Account it) {
    if (!StringUtils.isBlank(accountDto.getFirstName())) {
      it.getOwnerDetails().setFirstName(accountDto.getFirstName());
    }
    if (!StringUtils.isBlank(accountDto.getLastName())) {
      it.getOwnerDetails().setLastName(accountDto.getLastName());
    }
    if (!StringUtils.isBlank(accountDto.getEmail())) {
      it.getContactInfo().setEmail(accountDto.getEmail());
    }
    if (!StringUtils.isBlank(accountDto.getUserName())) {
      it.setUserName(accountDto.getUserName());
    }
  }

  public void validator(Object toValidate) throws RuntimeException {
    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    Set<? extends ConstraintViolation<?>> constraintViolations = validator.validate(toValidate);

    if (constraintViolations.size() > 0) {
      Set<String> violationMessages = new HashSet<>();

      for (ConstraintViolation<?> constraintViolation : constraintViolations) {
        violationMessages.add(((PathImpl)(constraintViolation).getPropertyPath()).getLeafNode().asString() + ": " + constraintViolation.getMessage());
      }
      throw new RuntimeException("Fields are not valid: " + StringUtils.join(violationMessages, ", "));
    }
  }*/
}