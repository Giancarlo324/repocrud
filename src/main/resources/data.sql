INSERT INTO `tipo_identificacion` (codigo, nombre, fecha_creacion, usuario_creacion, fecha_modificacion, usuario_modificacion)
VALUES (1, 'Cédula', '2021-02-01 00:00:01', 'user1', '2021-02-01 00:00:01', 'user1');

INSERT INTO `tipo_identificacion` (codigo, nombre, fecha_creacion, usuario_creacion, fecha_modificacion, usuario_modificacion)
VALUES (2, 'Tarjeta identidad', '2021-02-02 00:00:01', 'user2', '2021-02-02 00:00:01', 'user2');

INSERT INTO `estado` (codigo, nombre, fecha_creacion, usuario_creacion, fecha_modificacion, usuario_modificacion)
VALUES (1, 'Activo', '2022-01-01 00:00:01', 'user1', '2021-02-01 00:00:01', 'user1');

INSERT INTO `estado` (codigo, nombre, fecha_creacion, usuario_creacion, fecha_modificacion, usuario_modificacion)
VALUES (2, 'Inactivo', '2022-01-01 00:00:01', 'user2', '2021-02-01 00:00:01', 'user2');

INSERT INTO `persona` (codigo, nombre, apellido, fecha_nacimiento, username, password, identificacion, codigo_tipo_identificacion, codigo_estado)
VALUES (1, 'Usuario 1', 'Apellido 1', '1997-12-17', 'user1', '123', 123, 1, 1);

INSERT INTO `persona` (codigo, nombre, apellido, fecha_nacimiento, username, password, identificacion, codigo_tipo_identificacion, codigo_estado)
VALUES (2, 'Usuario 2', 'Apellido 2', '1998-11-16', 'user2', '124', 124, 2, 1);

INSERT INTO `persona` (codigo, nombre, apellido, fecha_nacimiento, username, password, identificacion, codigo_tipo_identificacion, codigo_estado)
VALUES (3, 'Usuario 3', 'Apellido 3', '1996-10-15', 'user3', '125', 125, 1, 2);