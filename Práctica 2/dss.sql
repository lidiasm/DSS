-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 08-01-2020 a las 21:48:37
-- Versión del servidor: 10.4.10-MariaDB
-- Versión de PHP: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `dss`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `almacen`
--

CREATE TABLE `almacen` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `direccion` varchar(200) DEFAULT NULL,
  `lat` double DEFAULT NULL,
  `lon` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `almacen`
--

INSERT INTO `almacen` (`id`, `nombre`, `direccion`, `lat`, `lon`) VALUES
(1, 'Almacén 1', 'Granada', 37.550094, -3.332722),
(2, 'Almacén 2', 'Málaga', 36.871245, -4.384202),
(3, 'Almacén Central', 'Sevilla', 37.415063, -5.813663),
(4, 'Almacén Principal', 'Jaén', 37.897767, -3.773503),
(5, 'Almacén 5', 'Córdoba', 37.82595, -4.904739);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `juguete`
--

CREATE TABLE `juguete` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `descripcion` varchar(200) DEFAULT NULL,
  `minEdadRecomendada` int(11) DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `almacen` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `juguete`
--

INSERT INTO `juguete` (`id`, `nombre`, `descripcion`, `minEdadRecomendada`, `precio`, `almacen`) VALUES
(2, 'Barbie Princesa', 'Muñeca', 4, 29.99, 3),
(3, 'Pony', 'Caballito para montar.', 5, 129.99, 2),
(4, 'Minion', 'Peluche', 2, 19.99, 4),
(5, 'Casa de muñecas', 'Casita ', 6, 159.89, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `username` varchar(50) NOT NULL,
  `password` varchar(128) NOT NULL,
  `name` varchar(250) DEFAULT NULL,
  `lat` double DEFAULT NULL,
  `lon` double DEFAULT NULL,
  `rol` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`username`, `password`, `name`, `lat`, `lon`, `rol`) VALUES
('admin', 'c7ad44cbad762a5da0a452f9e854fdc1e0e7a52a38015f23f3eab1d80b931dd472634dfac71cd34ebc35d16ab7fb8a90c81f975113d6c7538dc69dd8de9077ec', 'admin', 37.49394634645707, -2.6881901731814946, 0),
('lidia', '1acd4623f7195035a73d8ff839b14c33dcd5fead30c464d3f0ecbe73f67c6e04d2588170fac630db1a59a716e42ca1cb1dfd001039a2a6906e12be82b3a80b49', 'Lidia', 36.79698420114528, -2.768879654444559, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `almacen`
--
ALTER TABLE `almacen`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `juguete`
--
ALTER TABLE `juguete`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`username`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `almacen`
--
ALTER TABLE `almacen`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `juguete`
--
ALTER TABLE `juguete`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
