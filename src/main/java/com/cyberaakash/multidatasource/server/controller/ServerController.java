package com.cyberaakash.multidatasource.server.controller;

import com.cyberaakash.multidatasource.server.dto.ServerDto;
import com.cyberaakash.multidatasource.server.service.ServerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/server")
public class ServerController {
    private ServerService serverService;

    public ServerController(ServerService serverService) {
        this.serverService = serverService;
    }
    @PostMapping("/set-server")
    public ResponseEntity<String> setClient(@RequestBody ServerDto serverDto) {
        if(!ObjectUtils.isEmpty(serverService.createServer(serverDto))) {
            return new ResponseEntity<>("Success! Server Created.", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Server Creation Failed", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
