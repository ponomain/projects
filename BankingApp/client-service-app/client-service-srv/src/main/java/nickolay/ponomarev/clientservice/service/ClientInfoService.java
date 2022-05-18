package nickolay.ponomarev.clientservice.service;

import nickolay.ponomarev.clientservice.dto.ClientInfoDto;

import java.util.List;
import java.util.UUID;

/**
 * 12.04.2022
 * client-service
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
public interface ClientInfoService {

    ClientInfoDto getClientById(UUID id);

    ClientInfoDto createClient(ClientInfoDto clientDto);

    List<UUID> getAllClients(int page, int size);

    ClientInfoDto updateClient(UUID id, ClientInfoDto clientDto);

    void deleteClient(UUID id);

    String getEmailFromId(UUID id);

    String getPhoneNumberFromId(UUID id);

    String getInnFromId(UUID id);

}
