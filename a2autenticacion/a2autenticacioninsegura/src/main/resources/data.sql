insert into usuario (username, password) values ('owasp', '$2a$10$s.rU6xCF7M5sdRqKgJhf5.dlyW6hqkw20uMnp93qFI6.qswDRm7.q');
insert into usuario (username, password) values ('admin', '$2a$10$o2CSJ1P60.OlITPxkyMRD.r9xf1cMkiDL4Czh7UlYcThgfzZFnjJm');
insert into usuario (username, password) values ('root', '$2a$10$44RGKbcmMZVtuNI93Vy0f.iGe7CiCiJyaBEeTN5GUiKWQeeGYd./K');
insert into usuario (username, password) values ('otros', '$2a$10$s.rU6xCF7M5sdRqKgJhf5.dlyW6hqkw20uMnp93qFI6.qswDRm7.q');

insert into rol (username, rol) values ('owasp', 'ROLE_USER');
insert into rol (username, rol) values ('otros', 'ROLE_USER');
insert into rol (username, rol) values ('root', 'ROLE_USER');
insert into rol (username, rol) values ('admin', 'ROLE_USER');
insert into rol (username, rol) values ('admin', 'ROLE_ADMIN');
