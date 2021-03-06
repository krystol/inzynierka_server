package krystian.adamczyk.service.imp;

import java.time.LocalDate;
import javax.transaction.Transactional;
import javax.validation.Valid;
import krystian.adamczyk.model.ApplicationException;
import krystian.adamczyk.model.AuthenticationRequest;
import krystian.adamczyk.model.Authorities;
import krystian.adamczyk.model.BoardMessage;
import krystian.adamczyk.model.LaundryDay;
import krystian.adamczyk.model.LaundryRoom;
import krystian.adamczyk.model.RegisterRequest;
import krystian.adamczyk.model.Room;
import krystian.adamczyk.model.User;
import krystian.adamczyk.repository.AuthenticationJpaRepository;
import krystian.adamczyk.repository.AuthoritiesJpaRepository;
import krystian.adamczyk.repository.BoardMessageJpaRepository;
import krystian.adamczyk.repository.LaundryDayJpaRepository;
import krystian.adamczyk.repository.LaundryRoomJpaRepository;
import krystian.adamczyk.repository.RoomJpaRepository;
import krystian.adamczyk.repository.UserJpaRepository;
import krystian.adamczyk.service.InzynierkaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@RequiredArgsConstructor
@Slf4j
public class DatabaseFillerOnStartup implements ApplicationListener<ContextRefreshedEvent> {


    private final UserJpaRepository userJpaRepository;
    private final RoomJpaRepository roomJpaRepository;
    private final LaundryRoomJpaRepository laundryRoomJpaRepository;
    private final LaundryDayJpaRepository laundryDayJpaRepository;
    private final BoardMessageJpaRepository boardMessageJpaRepository;
    private final AuthenticationJpaRepository authenticationJpaRepository;
    private final AuthoritiesJpaRepository authoritiesJpaRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
//        AuthenticationRequest ar = new AuthenticationRequest();
//        ar.setUsername("test");
//        ar.setPassword(encoder.encode("test"));
//        ar.setEnabled(1);
//        authenticationJpaRepository.save(ar);
//        Authorities auth = new Authorities();
//        auth.setUsername("test");
//        auth.setAuthority("ROLE_ADMIN");
//        authoritiesJpaRepository.save(auth);

        RegisterRequest request = new RegisterRequest();
        request.setFirstName("testName");
        request.setLastName("testSurname");
        request.setUsername("test@test");
        request.setPassword("test");
        request.setRoomNumber("1");
        registerEntities(request);
        User ux = new User();
        ux.setFirstName("Krystian");
        ux.setLastName("Adamczyk");
        ux.setUsername("test@inzynierka.pl");
        ux.setLivingInRoomNumber(214);
        userJpaRepository.save(ux);
        User u = new User();
        u.setFirstName("Tomasz");
        u.setLastName("Iksinski");
        u.setLivingInRoomNumber(215);
        userJpaRepository.save(u);
        User u2 = new User();
        u2.setFirstName("Jakub");
        u2.setLastName("Nowak");
        u2.setLivingInRoomNumber(105);
        userJpaRepository.save(u2);
        BoardMessage wiadomosc = new BoardMessage();
        wiadomosc.setAuthor(u);
        wiadomosc.setTitle("Sprzedam Opla");
        wiadomosc.setMessage("2005 rok, stoi za akademikiem");
        boardMessageJpaRepository.save(wiadomosc);
        BoardMessage wiadomosc2 = new BoardMessage();
        wiadomosc2.setAuthor(u);
        wiadomosc2.setTitle("Sprzedam lodowke");
        wiadomosc2.setMessage("Sprzedam lodowke za czteropak");
        boardMessageJpaRepository.save(wiadomosc2);
        Room r1 = new Room();
        r1.setRoomName("Bilard");
        r1.setRoomNumber(605);
        r1.setOccupied(true);
        r1.setOccupiedByUser(u);
        r1.setKeyInRoomNumber(100);
        Room r2 = new Room();
        r2.setRoomName("Silownia");
        r2.setRoomNumber(211);
        r2.setKeyInRoomNumber(200);
        Room r3 = new Room();
        r3.setRoomName("Pokoj spokojnej nauki");
        r3.setRoomNumber(501);
        r3.setKeyInRoomNumber(400);
        Room r4 = new Room();
        r4.setRoomName("Joker");
        r4.setRoomNumber(610);
        r4.setKeyInRoomNumber(500);
        Room r5 = new Room();
        r5.setRoomName("Ping-Pong");
        r5.setRoomNumber(611);
        r5.setKeyInRoomNumber(600);
        roomJpaRepository.save(r1);
        roomJpaRepository.save(r2);
        roomJpaRepository.save(r3);
        roomJpaRepository.save(r4);
        roomJpaRepository.save(r5);

        LaundryRoom rp1 = new LaundryRoom();
        rp1.setRoomName("Pralnia p. 1");
        rp1.setRoomNumber(125);
        rp1.setKeyInRoomNumber(102);
        laundryRoomJpaRepository.save(rp1);
        LaundryRoom rp2 = new LaundryRoom();
        rp2.setRoomName("Pralnia p. 2");
        rp2.setRoomNumber(225);
        rp2.setKeyInRoomNumber(202);
        laundryRoomJpaRepository.save(rp2);
        LaundryRoom rp3 = new LaundryRoom();
        rp3.setRoomName("Pralnia p. 3");
        rp3.setRoomNumber(325);
        rp3.setKeyInRoomNumber(302);
        laundryRoomJpaRepository.save(rp3);
        LaundryRoom rp4 = new LaundryRoom();
        rp4.setRoomName("Pralnia p. 4");
        rp4.setRoomNumber(425);
        rp4.setKeyInRoomNumber(402);
        laundryRoomJpaRepository.save(rp4);
        LaundryRoom rp5 = new LaundryRoom();
        rp5.setRoomName("Pralnia p. 5");
        rp5.setRoomNumber(525);
        rp5.setKeyInRoomNumber(502);
        laundryRoomJpaRepository.save(rp5);
        LaundryRoom rp6 = new LaundryRoom();
        rp6.setRoomName("Pralnia p. 6");
        rp6.setRoomNumber(625);
        rp6.setKeyInRoomNumber(602);
        laundryRoomJpaRepository.save(rp6);
        LaundryRoom rp7 = new LaundryRoom();
        rp7.setRoomName("Pralnia p. 7");
        rp7.setRoomNumber(725);
        rp7.setKeyInRoomNumber(702);
        laundryRoomJpaRepository.save(rp7);
        LaundryRoom rp8 = new LaundryRoom();
        rp8.setRoomName("Pralnia p. 8");
        rp8.setRoomNumber(825);
        rp8.setKeyInRoomNumber(802);
        laundryRoomJpaRepository.save(rp8);
        LaundryRoom rp9 = new LaundryRoom();
        rp9.setRoomName("Pralnia p. 9");
        rp9.setRoomNumber(925);
        rp9.setKeyInRoomNumber(902);
        laundryRoomJpaRepository.save(rp9);
        LaundryRoom rp10 = new LaundryRoom();
        rp10.setRoomName("Pralnia p. 10");
        rp10.setRoomNumber(1025);
        rp10.setKeyInRoomNumber(1002);
        laundryRoomJpaRepository.save(rp10);
        LaundryDay lDay = new LaundryDay();
        lDay.setDate(LocalDate.now());
        lDay.setLaundry(rp10);
        lDay.setHour8(ux.getFirstName() + " " + ux.getLastName());
        lDay.setHour9(ux.getFirstName() + " " + ux.getLastName());
        lDay.setHour14(u.getFirstName() + " " + u.getLastName());
        lDay.setHour15(u.getFirstName() + " " + u.getLastName());
        laundryDayJpaRepository.save(lDay);
    }


    @Transactional
    private void registerEntities(@Valid @RequestBody RegisterRequest registerRequest) {
        String username = registerRequest.getUsername();
        User u = new User();
        u.setFirstName(registerRequest.getFirstName());
        u.setLastName(registerRequest.getLastName());
        u.setUsername(username);
        u.setLivingInRoomNumber(Integer.valueOf(registerRequest.getRoomNumber()));
        try {
            userJpaRepository.save(u);
        } catch (ApplicationException e) {
            log.debug("Error during registering new user");
        }
        try {
            AuthenticationRequest ar = new AuthenticationRequest();
            ar.setUsername(username);
            ar.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
            ar.setEnabled(1);
            authenticationJpaRepository.save(ar);
            Authorities authority = new Authorities();
            authority.setUsername(username);
            authority.setAuthority("ROLE_ADMIN");
            authoritiesJpaRepository.save(authority);
        } catch (ApplicationException e) {
            log.debug("Error during saving credentials");
        }
    }
}
