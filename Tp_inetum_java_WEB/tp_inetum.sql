-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : sam. 15 juil. 2023 à 08:21
-- Version du serveur : 10.4.28-MariaDB
-- Version de PHP : 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `tp_inetum`
--

-- --------------------------------------------------------

--
-- Structure de la table `adresse`
--

CREATE TABLE `adresse` (
  `id` int(11) NOT NULL,
  `nomVoie` varchar(50) NOT NULL,
  `codePostal` varchar(5) NOT NULL,
  `ville` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `adresse`
--

INSERT INTO `adresse` (`id`, `nomVoie`, `codePostal`, `ville`) VALUES
(1, 'Rue de Paris', '75012', 'Paris'),
(2, 'rue de Madrid', '95200', 'Madrid'),
(3, 'Voie', '8000', 'Deuil'),
(4, 'Voie de', '95410', 'Villa');

-- --------------------------------------------------------

--
-- Structure de la table `stagiaire`
--

CREATE TABLE `stagiaire` (
  `id` int(11) NOT NULL,
  `prenom` varchar(20) NOT NULL,
  `email` varchar(80) NOT NULL,
  `mdp` text NOT NULL,
  `adresseId` int(11) NOT NULL,
  `ddn` date NOT NULL,
  `role` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `stagiaire`
--

INSERT INTO `stagiaire` (`id`, `prenom`, `email`, `mdp`, `adresseId`, `ddn`, `role`) VALUES
(1, 'martin', 'martin@inetum.fr', '1234', 1, '1996-04-22', 'ADMIN'),
(2, 'julie', 'julie@inetum.fr', '3265', 2, '1996-04-22', 'ADMIN'),
(3, 'Anouar', 'anouar@inetum.fr', 'user', 3, '1878-12-15', 'USER'),
(4, 'Simon', 'simon@inetum.fr', 'user', 4, '1888-12-15', 'USER');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `adresse`
--
ALTER TABLE `adresse`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `stagiaire`
--
ALTER TABLE `stagiaire`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_adr_stag` (`adresseId`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `stagiaire`
--
ALTER TABLE `stagiaire`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `stagiaire`
--
ALTER TABLE `stagiaire`
  ADD CONSTRAINT `fk_adr_stag` FOREIGN KEY (`adresseId`) REFERENCES `adresse` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
