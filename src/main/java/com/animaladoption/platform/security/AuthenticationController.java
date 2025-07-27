package com.animaladoption.platform.security;

import com.animaladoption.platform.domain.ong.Ong;
import com.animaladoption.platform.domain.ong.OngLoginDTO;
import com.animaladoption.platform.domain.person.Person;
import com.animaladoption.platform.domain.person.PersonLoginDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class AuthenticationController {

    private final TokenService tokenService;
    private final AuthenticationManager manager;

    public AuthenticationController(TokenService tokenService, AuthenticationManager authenticationManager) {
        this.tokenService = tokenService;
        this.manager = authenticationManager;
    }

    @PostMapping("/person-login")
    public ResponseEntity personLogin(@RequestBody @Valid PersonLoginDTO personData) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(personData.login(), personData.password());
        var authentication = manager.authenticate(authenticationToken);
        var tokenJWT = tokenService.generateToken((Person) authentication.getPrincipal(), null);
        return ResponseEntity.ok(new DataTokenJWT(tokenJWT));
    }

    @PostMapping("/ong-login")
    public ResponseEntity ongLogin(@RequestBody @Valid OngLoginDTO ongData) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(ongData.login(), ongData.password());
        var authentication = manager.authenticate(authenticationToken);
        var tokenJWT = tokenService.generateToken(null, (Ong) authentication.getPrincipal());
        return ResponseEntity.ok(new DataTokenJWT(tokenJWT));
    }

}
