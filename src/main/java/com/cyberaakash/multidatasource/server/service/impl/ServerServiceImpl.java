package com.cyberaakash.multidatasource.server.service.impl;

import com.cyberaakash.multidatasource.server.dto.ServerDto;
import com.cyberaakash.multidatasource.server.entity.Server;
import com.cyberaakash.multidatasource.server.mapper.ServerMapper;
import com.cyberaakash.multidatasource.server.repository.ServerRepository;
import com.cyberaakash.multidatasource.server.service.ServerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ServerServiceImpl implements ServerService {
    private ServerRepository serverRepository;

    public ServerServiceImpl(ServerRepository serverRepository) {
        this.serverRepository = serverRepository;
    }

    @Override
    public ServerDto createServer(ServerDto serverDto) {
        Server server = ServerMapper.mapToServer(serverDto);
        Server savedServer = serverRepository.save(server);
        return ServerMapper.mapToServerDto(savedServer);
    }
}
