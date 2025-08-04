package com.animaladoption.platform.domain.account;

import java.util.UUID;

public interface AccountService {
    AccountGetDTO me();
    String getUsernameByToken();

    AccountPutDTO updatePassword(UUID id, AccountPutDTO dto);
}
