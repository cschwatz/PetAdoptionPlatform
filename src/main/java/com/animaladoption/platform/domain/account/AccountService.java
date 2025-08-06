package com.animaladoption.platform.domain.account;

import com.animaladoption.platform.domain.ong.Ong;

import java.util.UUID;

public interface AccountService {
    AccountGetDTO me();
    String getUsernameByToken();
    Ong getAuthenticatedOng();
    AccountPutDTO updatePassword(UUID id, AccountPutDTO dto);
}
