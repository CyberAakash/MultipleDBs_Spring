package com.cyberaakash.multidatasource.client.controller;


import com.cyberaakash.multidatasource.client.dto.ClientDto;
import com.cyberaakash.multidatasource.client.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/set-client")
    public ResponseEntity<String> setClient(@RequestBody ClientDto clientDto) {
        if(!ObjectUtils.isEmpty(clientService.createClient(clientDto))) {
            return new ResponseEntity<>("Success! Client Created.", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Client Creation Failed", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
