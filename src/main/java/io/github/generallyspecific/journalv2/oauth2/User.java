package io.github.generallyspecific.journalv2.oauth2;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    @EmbeddedId
    private UserKey key;
    @Column(name = "user_id")
    private UUID userId;

    public User() {
    }

    public User(UserKey key, UUID userId) {
        this.key = key;
        this.userId = userId;
    }

    public UserKey getKey() {
        return key;
    }

    public void setKey(UserKey key) {
        this.key = key;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "User{" +
                "key=" + key +
                ", userId=" + userId +
                '}';
    }

    @Embeddable
    public static class UserKey implements Serializable {
        @Column(name = "service_id")
        private String serviceId;

        @Column(name = "service")
        private String service;

        public UserKey() {
        }

        public UserKey(String serviceId, String service) {
            this.serviceId = serviceId;
            this.service = service;
        }

        public String getServiceId() {
            return serviceId;
        }

        public void setServiceId(String serviceId) {
            this.serviceId = serviceId;
        }

        public String getService() {
            return service;
        }

        public void setService(String service) {
            this.service = service;
        }

        @Override
        public String toString() {
            return "UserKey{" +
                    "serviceId='" + serviceId + '\'' +
                    ", service='" + service + '\'' +
                    '}';
        }
    }
}
