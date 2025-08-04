package com.animaladoption.platform.domain.account;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/account")
@CrossOrigin(
        origins = "http://localhost:4200",
        allowCredentials = "true",
        allowedHeaders = "*",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}
)
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/me")
    public ResponseEntity<AccountGetDTO> me() {
        return ResponseEntity.ok(accountService.me());
    }

    @PutMapping("/password/{id}")
    public ResponseEntity<AccountPutDTO> updatePassword(@PathVariable UUID id, @RequestBody @Valid AccountPutDTO dto) {
        return ResponseEntity.ok(accountService.updatePassword(id, dto));
    }
}
