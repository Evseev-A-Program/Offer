package com.example.offer.clients;

import org.springframework.web.client.RestTemplate;

public class CustomerClients {

    public static Object getPaidType(Long id)
    {
        final String uri = "http://localhost:8080/paid-type/get?id=" + id;

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uri, Object.class);
    }
}
