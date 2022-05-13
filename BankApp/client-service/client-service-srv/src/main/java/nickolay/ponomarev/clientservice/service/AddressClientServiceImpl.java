package nickolay.ponomarev.clientservice.service;

import lombok.RequiredArgsConstructor;
import nickolay.ponomarev.clientservice.dto.AddressClientDto;
import nickolay.ponomarev.clientservice.model.AddressClient;
import nickolay.ponomarev.clientservice.model.ClientInfo;
import nickolay.ponomarev.clientservice.repository.AddressClientRepository;
import nickolay.ponomarev.clientservice.repository.ClientInfoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import nickolay.ponomarev.clientservice.mapper.AddressClientMapper;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * 21.04.2022
 * client-service
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class AddressClientServiceImpl implements AddressClientService {

    private final AddressClientRepository addressRepository;
    private final AddressClientMapper addressBranchMapper;
    private final ClientInfoRepository clientInfoRepository;

    @Override
    public AddressClientDto getAddressByIdFromClient(@NotNull UUID id) throws IllegalArgumentException {
        ClientInfo existingClient = clientInfoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                "Client with id -" + id + "didn't found"));
        AddressClient existingAddress = existingClient.getAddressClient();
        return addressBranchMapper.toDto(existingAddress);
    }

    @Override
    @Transactional
    public AddressClientDto createAddress(@NotNull AddressClientDto addressClientDto) {
        AddressClient newAddressClient = new AddressClient();
        addressBranchMapper.toSource(addressClientDto, newAddressClient);
        newAddressClient = addressRepository.save(newAddressClient);
        return addressBranchMapper.toDto(newAddressClient);
    }

    @Override
    public void deleteAddress(@NotNull UUID id) {
        addressRepository.deleteById(id);
    }
}
