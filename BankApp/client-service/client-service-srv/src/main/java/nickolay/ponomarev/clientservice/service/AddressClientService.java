package nickolay.ponomarev.clientservice.service;

import nickolay.ponomarev.clientservice.dto.AddressClientDto;

import java.util.UUID;

/**
 * 21.04.2022
 * client-service
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
public interface AddressClientService {

    AddressClientDto getAddressByIdFromClient(UUID id);

    AddressClientDto createAddress(AddressClientDto addressClientDto);

    void deleteAddress(UUID id);
}
