INSERT INTO `utilisateur` (`id`, `email`, `nom`, `login`, `password`, `prenom`, `role`, `tel`, `ville`) VALUES
(1, 'admin@admin.com', 'admin', 'admin', '{bcrypt}$2a$10$q60xtzVy279uzCYDe/dbqeCY7AXCzW08tcZZvDy8DOnZbeo5KBEne', 'admin', 'ROLE_ADMINISTRATEUR', NULL, NULL),
(2, 'client@client.com', 'client', 'user', '{bcrypt}$2a$10$qnEMkO7lfZsvsPGBeM4Is.yjOxWuIHbikqqHaUihWyEVR0dYgiVp2', 'client', 'ROLE_CLIENT', NULL, NULL);
