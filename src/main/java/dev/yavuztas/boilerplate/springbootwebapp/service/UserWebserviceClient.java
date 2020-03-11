package dev.yavuztas.boilerplate.springbootwebapp.service;

import dev.yavuztas.boilerplate.springbootwebapp.view.UserItemsView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Our client to consume user web services. Web service url defined as "user.webservice.url" in application.properties
 * @author Yavuz Tas
 */
@Service
public class UserWebserviceClient implements IUserWebserviceClient {

    @Value("${user.webservice.url}")
    private String webServiceUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public UserItemsView getUserItems(String username) {
        return restTemplate.getForObject(webServiceUrl + "/user/" + username, UserItemsView.class);
    }

}
