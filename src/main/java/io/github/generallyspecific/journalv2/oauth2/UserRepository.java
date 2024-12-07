package io.github.generallyspecific.journalv2.oauth2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, User.UserKey> {
    // Find User by user_id and service name
    @Query("SELECT u FROM User u WHERE u.key.serviceId = :serviceId AND u.key.service = :service")
    Optional<User> findUserByServiceIdAndService(@Param("serviceId") String serviceId, @Param("service") String service);

    // get the user_id from the service_id and service
    @Query("SELECT u.userId FROM User u WHERE u.key.serviceId = :serviceId AND u.key.service = :service")
    UUID getUserIdFromServiceIdAndService(@Param("serviceId") String serviceId, @Param("service") String service);
}
