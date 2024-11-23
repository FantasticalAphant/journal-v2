package io.github.generallyspecific.journalv2.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final UserService userService;

    @Autowired
    public CustomOAuth2UserService(UserService userService) {
        this.userService = userService;
    }

    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        // save user to database if they aren't already in it
        OAuth2User oAuth2User = super.loadUser(userRequest);

        Map<String, Object> attributes = new HashMap<>(oAuth2User.getAttributes());

        String service = userRequest.getClientRegistration().getRegistrationId();
        String serviceId;
        String attributeName;

//        //rate limiting
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        // get id for github and email for google
        if (service.equals("github")) {
            attributeName = "id";
            serviceId = String.valueOf(attributes.get(attributeName));
        }  else if (service.equals("google")) {
            attributeName = "email";
            serviceId = String.valueOf(attributes.get(attributeName));
        } else {
            attributeName = "unknown";
            serviceId = "unknown";
        }

        // Check if user is already in database
        Optional<User> optionalUser = userService.findUserByServiceIdAndService(serviceId, service);
        if (optionalUser.isEmpty()) {
            // Add User if not in database
            User newUser = new User();
            newUser.setKey(new User.UserKey(serviceId, service));
            newUser.setUserId(UUID.randomUUID());
            userService.saveUser(newUser);
        }

        return new DefaultOAuth2User(oAuth2User.getAuthorities(), attributes, attributeName);
    }

}
