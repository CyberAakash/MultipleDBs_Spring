package com.cyberaakash.multidatasource.server.mapper;

import com.cyberaakash.multidatasource.server.dto.ServerDto;
import com.cyberaakash.multidatasource.server.entity.Server;

public class ServerMapper {
    public static Server mapToServer(ServerDto ServerDto) {
        Server Server = new Server(
                ServerDto.getId(),
                ServerDto.getName()
        );
        return Server;
    }

    public static ServerDto mapToServerDto(Server Server) {
        ServerDto ServerDto = new ServerDto(
                Server.getId(),
                Server.getName()
        );
        return ServerDto;
    }
}
