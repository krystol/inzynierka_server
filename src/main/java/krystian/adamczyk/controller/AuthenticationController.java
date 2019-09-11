package krystian.adamczyk.controller;

import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import krystian.adamczyk.model.ApplicationException;
import krystian.adamczyk.model.AuthenticationRequest;
import krystian.adamczyk.model.AuthenticationResult;
import krystian.adamczyk.model.RegisterRequest;
import krystian.adamczyk.model.User;
import krystian.adamczyk.model.UserCredentials;
import krystian.adamczyk.service.InzynierkaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping(value = "/auth")
@RestController
@Slf4j
public class AuthenticationController {

  @Autowired
  private InzynierkaService service;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @PostMapping(value = "/signin")
  public AuthenticationResult signin(
      @RequestBody @Valid AuthenticationRequest authenticationRequest,
      HttpServletRequest request) {

    if (log.isDebugEnabled()) {
      log.debug("signin form  data@" + authenticationRequest);
    }

    return this.handleAuthentication(
        authenticationRequest.getUsername(),
            "{noop}"+authenticationRequest.getPassword(),
        request);
  }

  @PostMapping(value = "/register")
  public AuthenticationResult register(
      @RequestBody @Valid RegisterRequest registerRequest,
      HttpServletRequest request) {

    if (log.isDebugEnabled()) {
      log.debug("register form  data@" + registerRequest);
    }

    User u = new User();
    u.setFirstName(registerRequest.getFirstName());
    u.setLastName(registerRequest.getLastName());
    u.setUsername(registerRequest.getUsername());
    u.setLivingInRoomNumber(Integer.valueOf(registerRequest.getRoomNumber()));
    try {
      service.saveUser(u);
    } catch (ApplicationException e) {
      log.debug("Error during registering new user");
    }
    try{
      AuthenticationRequest ar = new AuthenticationRequest();
      ar.setUsername(registerRequest.getUsername());
      ar.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
      service.saveCreds(ar);
    } catch (ApplicationException e) {
      service.removeUser(u);
    log.debug("Error during saving credentials");
  }


    return this.handleAuthentication(
        registerRequest.getUsername(),
        registerRequest.getPassword(),
        request);
  }

  private AuthenticationResult handleAuthentication(String username, String password, HttpServletRequest request) {

    final UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
    final Authentication authentication = this.authenticationManager.authenticate(token);
    SecurityContextHolder.getContext().setAuthentication(authentication);
    final HttpSession session = request.getSession(true);
    session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());

    return AuthenticationResult.builder()
        .name(authentication.getName())
        .roles(authentication.getAuthorities().stream().map(r -> r.getAuthority()).collect(Collectors.toList()))
        .token(session.getId())
        .build();
  }
}