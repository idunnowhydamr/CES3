--
-- Base de datos: `universitas`
--
CREATE DATABASE universitas;


CREATE USER 'diego'@'localhost' IDENTIFIED BY '123';


GRANT ALL PRIVILEGES ON universitas.* TO 'diego'@'localhost';


FLUSH PRIVILEGES;
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user`
--

CREATE TABLE `user` (
  `id` int(5) NOT NULL,
  `mail` varchar(100) NOT NULL,
  `name` varchar(200) DEFAULT NULL,
  `pass` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
--Datos para la tabla `user`
--

INSERT INTO `user` (`id`, `mail`, `name`, `pass`) VALUES
(2, 'diegoa@gaamail.com', 'diego', '32'),
(3, 'diegoale1@gmail.com', 'diego', '123'),
(4, 'luis@hotmail.com', 'luis', '1234'),
(5, 'kevin123@gmail.com', 'kevin', '456'),
(7, 'meli@gmail.com', 'melisa', '098'),
(9, 'mateo@gmail.com', 'mateo', '456'),
(23, 'luisa@gmail.com', 'Luisa', '5678'),
(67, 'juan23@gmail.com', 'juan', '987'),
(76, 'miguel@hotmail.com', 'miguel', '234466'),
(98, 'camila234@gmail.com', 'camila', '256');

--
-- Indices de la tabla `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `uq_document_user` (`mail`);

--
-- AUTO_INCREMENT de la tabla `user`
--
ALTER TABLE `user`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=99;
COMMIT;

