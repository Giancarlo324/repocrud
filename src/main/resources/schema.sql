CREATE TABLE IF NOT EXISTS `estado` (
  `codigo` int(11) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `usuario_creacion` varchar(20) NOT NULL,
  `fecha_modificacion` timestamp NULL DEFAULT NULL,
  `usuario_modificacion` varchar(20) NOT NULL,
  PRIMARY KEY (`codigo`)
);

CREATE TABLE IF NOT EXISTS `persona` (
  `codigo` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL,
  `identificacion` int(11) NOT NULL,
  `codigo_tipo_identificacion` int(11) NOT NULL,
  `codigo_estado` int(11) NOT NULL,
  PRIMARY KEY (`codigo`)
);

CREATE TABLE IF NOT EXISTS `tipo_identificacion` (
  `codigo` int(11) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `usuario_creacion` varchar(20) NOT NULL,
  `fecha_modificacion` timestamp NULL DEFAULT NULL,
  `usuario_modificacion` varchar(20) NOT NULL,
  PRIMARY KEY (`codigo`)
);

