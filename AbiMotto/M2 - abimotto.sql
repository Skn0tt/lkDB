-- phpMyAdmin SQL Dump
-- version 3.1.3.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Erstellungszeit: 07. Februar 2010 um 23:58
-- Server Version: 5.1.33
-- PHP-Version: 5.2.9

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Datenbank: `abimotto`
--
CREATE DATABASE `abimotto` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `abimotto`;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `motto`
--

CREATE TABLE IF NOT EXISTS `motto` (
  `MID` int(150) NOT NULL AUTO_INCREMENT,
  `Motto` varchar(200) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`MID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Daten für Tabelle `motto`
--

INSERT INTO `motto` (`MID`, `Motto`) VALUES
(1, 'Pommes mit mABI, bitte!'),
(2, 'Abiene - Abi verleiht Flügel!'),
(3, 'ABItours'),
(5, 'ABITAL - 12 Jahre Klassenkampf'),
(6, 'Abikalypse 2018 ... und nach uns die Sintflut'),
(4, 'ABI total');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `UID` int(150) NOT NULL AUTO_INCREMENT,
  `Vorname` varchar(100) CHARACTER SET utf8 NOT NULL,
  `Nachname` varchar(100) CHARACTER SET utf8 NOT NULL,
  `Passwort` varchar(100) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`UID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Daten für Tabelle `user`
--

INSERT INTO `user` (`UID`, `Vorname`, `Nachname`, `Passwort`) VALUES
(1, 'Axel', 'Müller','geheim'),
(2, 'Peter', 'Bond','informatik'),
(3, 'Karl', 'Kraus','topsecret'),
(4, 'Anna', 'Neumann','komplex'),
(5, 'Vera', 'Heymann','geheim2');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `wahl`
--

CREATE TABLE IF NOT EXISTS `wahl` (
  `UID` int(200) NOT NULL,
  `MID` int(200) NOT NULL,
  PRIMARY KEY (`UID`,`MID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `wahl`
--

INSERT INTO `wahl` (`UID`, `MID`) VALUES
(1, 1),
(2, 2),
(3, 3),
(3, 4),
(4, 5),
(5, 6);
