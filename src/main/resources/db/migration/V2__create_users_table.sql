CREATE TABLE users
(
    user_id    UUID,
    service_id VARCHAR(255) NOT NULL,
    service    VARCHAR(255) NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (service_id, service)
);