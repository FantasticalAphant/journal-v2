package io.github.generallyspecific.journalv2.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findUserByServiceIdAndService(String serviceId, String service) {
        return userRepository.findUserByServiceIdAndService(serviceId, service);
    }

    public UUID getUserIdFromServiceIdAndService() {
        // map service to the attribute
        Map<String, String> attributeMappings;
        attributeMappings = Map.of(
                "google", "email",
                "github", "id"
        );

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof OAuth2AuthenticationToken token) {
            OAuth2User user = token.getPrincipal();
            String service = token.getAuthorizedClientRegistrationId();

            Object attributeObj = user.getAttribute(attributeMappings.get(service));
            String serviceId;

            // TODO: handle without type checking if possible
            if (attributeObj instanceof Integer) {
                serviceId = String.valueOf(attributeObj);
            } else if (attributeObj instanceof char[]) {
                serviceId = new String((char[]) attributeObj);
            } else if (attributeObj != null) {
                serviceId = attributeObj.toString();
            } else {
                serviceId = "unknown";
            }

            return userRepository.getUserIdFromServiceIdAndService(serviceId, service);
        }

        // FIXME - handle this case
        return UUID.randomUUID();
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }
}
