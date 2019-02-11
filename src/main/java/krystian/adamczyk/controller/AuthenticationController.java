/*
package krystian.adamczyk.controller;

import krystian.adamczyk.model.AuthenticationRequest;
import krystian.adamczyk.model.AuthenticationResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.stream.Collectors;


@RequestMapping(value = "/auth")
@RestController
@Slf4j
public class AuthenticationController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @PostMapping(value = "/signin")
  public AuthenticationResult signin(
      @RequestBody @Valid AuthenticationRequest authenticationRequest,
      HttpServletRequest request) {

    if (log.isDebugEnabled()) {
      log.debug("signin form  data@" + authenticationRequest);
    }

    return this.handleAuthentication(
        authenticationRequest.getUsername(),
        authenticationRequest.getPassword(),
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
}*/
