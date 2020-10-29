package org.company.ucm.client;

import org.company.ucm.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Component
public class FarmersWelfareServicesClient {

    @Value("${service.farmersWelfareServiceUrl}")
    private String farmersWelfareServiceUrl;

    private RestTemplate restTemplate;

    public FarmersWelfareServicesClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<User> findUserByUsername(String userName) {
        URI getUserByEmailAddressUrl = UriComponentsBuilder
                                        .fromHttpUrl(this.farmersWelfareServiceUrl)
                                        .path("/user/emailAddress")
                                        .build()
                                        .toUri();
        ResponseEntity<User> responseEntity = null;

        try{
            HttpHeaders headers = new HttpHeaders();
            headers.add("emailAddress", userName);
            HttpEntity httpEntity = new HttpEntity<>(headers);
            responseEntity = restTemplate.exchange(getUserByEmailAddressUrl, HttpMethod.GET, httpEntity, User.class);

        }catch(Exception ex){
           return Optional.empty();
        }
        return Optional.ofNullable(responseEntity.getBody());
    }

    public User createUser(User user) {
        URI createUserUrl = UriComponentsBuilder
                .fromHttpUrl(this.farmersWelfareServiceUrl)
                .path("/user/create")
                .build()
                .toUri();
        ResponseEntity<User> responseEntity = null;

        try{
            HttpEntity<User> httpEntity = new HttpEntity<>(user);
            responseEntity = restTemplate.exchange(createUserUrl, HttpMethod.POST, httpEntity, User.class);

        }catch(Exception ex){
            throw ex;
        }
        return responseEntity.getBody();
    }
}
