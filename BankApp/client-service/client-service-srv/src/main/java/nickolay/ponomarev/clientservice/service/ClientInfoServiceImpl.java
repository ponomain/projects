package nickolay.ponomarev.clientservice.service;

import lombok.RequiredArgsConstructor;
import nickolay.ponomarev.clientservice.dto.ClientInfoDto;
import nickolay.ponomarev.clientservice.model.ClientInfo;
import nickolay.ponomarev.clientservice.repository.ClientInfoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import nickolay.ponomarev.clientservice.mapper.ClientInfoMapper;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * 12.04.2022
 * client-service
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */

@RequiredArgsConstructor
@Service
public class ClientInfoServiceImpl implements ClientInfoService {

    private final ClientInfoRepository clientInfoRepository;

    private final ClientInfoMapper clientInfoMapper;


    @Override
    public ClientInfoDto getClientById(@NotNull UUID id) throws IllegalArgumentException {
        ClientInfo existingClient = clientInfoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                "Client with id -" + id + "didn't found"));
        return clientInfoMapper.toDto(existingClient);
    }

    @Override
    @Transactional
    public ClientInfoDto createClient(@NotNull ClientInfoDto clientDto) {
        ClientInfo newClient = new ClientInfo();
        clientInfoMapper.toEntity(clientDto, newClient);
        newClient = clientInfoRepository.save(newClient);
        return clientInfoMapper.toDto(newClient);
    }

    @Override
    public List<UUID> getAllClients(int page, int size) {
        PageRequest request = PageRequest.of(page, size, Sort.by("id"));
        Page<ClientInfo> result = clientInfoRepository.findAll(request);
        List<ClientInfo> existingClients = result.getContent();
        return existingClients.stream().map(ClientInfo::getId).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ClientInfoDto updateClient(@NotNull UUID id, @NotNull ClientInfoDto clientDto) throws IllegalArgumentException {
        ClientInfo existingClient = clientInfoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                "Client with id -" + id + "didn't found"));
        clientInfoMapper.toEntity(clientDto, existingClient);
        existingClient = clientInfoRepository.save(existingClient);
        return clientInfoMapper.toDto(existingClient);
    }

    @Override
    @Transactional
    public void deleteClient(@NotNull UUID id) {
        clientInfoRepository.deleteById(id);
    }

    @Override
    public String getEmailFromId(@NotNull UUID id) throws IllegalArgumentException {
        ClientInfo existingClient = clientInfoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                "Client with id -" + id + "didn't found"));
        String email = existingClient.getEmail();
        return email.substring(0, email.indexOf("@"));
    }

    @Override
    public String getPhoneNumberFromId(@NotNull UUID id) throws IllegalArgumentException {
        ClientInfo existingClient = clientInfoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                "Client with id -" + id + "didn't found"));
        String phoneNumber = existingClient.getPhoneNumber();
        return phoneNumber.substring(phoneNumber.length() - 5);
    }

    @Override
    public String getInnFromId(@NotNull UUID id) throws IllegalArgumentException {
        ClientInfo existingClient = clientInfoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                "Client with id -" + id + "didn't found"));
        String inn = existingClient.getInn();
        return inn.substring(inn.length() - 4);
    }

}
