package com.animaladoption.platform.domain.ong;

import java.util.List;
import java.util.UUID;

public interface OngService {
    public List<OngGetDTO> getAllOngs();

    public OngGetDTO getById(UUID id);

    public OngPostDTO createNewOng(OngPostDTO dto);

    public OngPutDTO updateOng(UUID id, OngPutDTO dto);

    public void deleteOng(UUID id);
}
