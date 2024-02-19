package com.cyberaakash.multidatasource.client.mapper;

import com.cyberaakash.multidatasource.client.dto.ClientDto;
import com.cyberaakash.multidatasource.client.entity.Client;

public class ClientMapper {
    public static Client mapToClient(ClientDto clientDto) {
        Client client = new Client(
                clientDto.getId(),
                clientDto.getName()
        );
        return client;
    }

    public static ClientDto mapToClientDto(Client client) {
        ClientDto clientDto = new ClientDto(
                client.getId(),
                client.getName()
        );
        return clientDto;
    }
}
