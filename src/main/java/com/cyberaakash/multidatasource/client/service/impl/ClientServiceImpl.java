package com.cyberaakash.multidatasource.client.service.impl;

import com.cyberaakash.multidatasource.client.dto.ClientDto;
import com.cyberaakash.multidatasource.client.entity.Client;
import com.cyberaakash.multidatasource.client.mapper.ClientMapper;
import com.cyberaakash.multidatasource.client.repository.ClientRepository;
import com.cyberaakash.multidatasource.client.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientDto createClient(ClientDto clientDto) {
        Client client = ClientMapper.mapToClient(clientDto);
        Client savedClient = clientRepository.save(client);
        return ClientMapper.mapToClientDto(savedClient);
    }
}
