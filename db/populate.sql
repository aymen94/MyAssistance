INSERT INTO my_assistance.utente (username, pass, email, nome, cognome, sesso, data_di_nascita, is_gestore) -- pass: 12341234
VALUES ('anaghmouchi', '2:c2:2839196b7a72fad805e83d39cead8139edcf1e063387ae970f4afe5148bae4bb335a0ee5da7b43e570bc5e98ce', 'a.naghmouchi@test.it', 'aymen', 'naghmouchi', 1,'1990-01-01', 0),
       ('apauciello', '2:c2:2839196b7a72fad805e83d39cead8139edcf1e063387ae970f4afe5148bae4bb335a0ee5da7b43e570bc5e98ce', 'a.pauciello3@test.it', 'alfonso', 'pauciello', 1,'1990-01-01', 0),
       ('amennillo', '2:c2:2839196b7a72fad805e83d39cead8139edcf1e063387ae970f4afe5148bae4bb335a0ee5da7b43e570bc5e98ce', 'a.mennillo1@test.it', 'andrea', 'mennillo', 1,'1990-01-01', 0),
       ('galbanese', '2:c2:2839196b7a72fad805e83d39cead8139edcf1e063387ae970f4afe5148bae4bb335a0ee5da7b43e570bc5e98ce', 'g.albanese11@test.it', 'gaetano', 'albanese', 1,'1990-01-01', 0),
       ('pdauria', '2:c2:2839196b7a72fad805e83d39cead8139edcf1e063387ae970f4afe5148bae4bb335a0ee5da7b43e570bc5e98ce', 'mario.rossi12@studenti.unisa.it', 'pio', 'd auria ', 1,'1990-01-01', 0),
       ('gestore', '2:c2:2839196b7a72fad805e83d39cead8139edcf1e063387ae970f4afe5148bae4bb335a0ee5da7b43e570bc5e98ce', 'gestore@test.it', 'gestore', 'gestore ', 1,'1989-01-01', 1);

INSERT INTO my_assistance.ufficio_tecnico (nome, tel, email, ubicazione)
VALUES (' Ifix ', ' 1112223334', ' assistenza@ifix.it ', ' Via della Biblioteca, 84084 Fisciano SA '),
       (' MR.ripara ', ' 1112223334', ' assistenza@mrripara.it ', ' Via della mensa, 84084 Fisciano SA '),
       (' Aggiustiamo sedie ', ' 1112223334', ' assistenza@sedieee.it ', ' Via delle sedie, 84084 Fisciano SA ');

INSERT INTO my_assistance.tipologia (nome, priorita)
VALUES ('Rottura', 10),
       ('Difetto Occasionale', 3),
       ('Difetto Frequente', 5),
       ('Accessorio Mancante', 6),
       ('Strumento Inadeguato', 2);

INSERT INTO my_assistance.segnalazione (titolo, descrizione, stato, data_segnalazione, data_rifiuto, data_assegnazione,
                                        data_risoluzione, motivazione_rifiuto, tipologia, autore, tecnico)
VALUES ('Lorem ipsum',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Lectus urna duis convallis convallis tellus. Vitae proin sagittis nisl rhoncus mattis rhoncus. Platea dictumst vestibulum rhoncus est pellentesque elit ullamcorper. Quisque sagittis purus sit amet volutpat consequat mauris nunc congue.',
        0, '2018-10-06', NULL, NULL, NULL, NULL, 2, 2, 1),
       ('Eiusmod tempor incididunt',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Lectus urna duis convallis convallis tellus. Vitae proin sagittis nisl rhoncus mattis rhoncus. Platea dictumst vestibulum rhoncus est pellentesque elit ullamcorper. Quisque sagittis purus sit amet volutpat consequat mauris nunc congue.',
        1, '2018-09-05', NULL, '2018-09-11', NULL, NULL, 3, 3, 2),
       ('Lorem ipsum dolor sit amet',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Lectus urna duis convallis convallis tellus. Vitae proin sagittis nisl rhoncus mattis rhoncus. Platea dictumst vestibulum rhoncus est pellentesque elit ullamcorper. Quisque sagittis purus sit amet volutpat consequat mauris nunc congue.',
        2, '2018-11-14', NULL, '2018-11-22', '2018-11-24', NULL, 4, 5, 2),
       ('Consectetur adipiscing elit,',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Lectus urna duis convallis convallis tellus. Vitae proin sagittis nisl rhoncus mattis rhoncus. Platea dictumst vestibulum rhoncus est pellentesque elit ullamcorper. Quisque sagittis purus sit amet volutpat consequat mauris nunc congue.',
        -1, '2018-08-15', '2018-09-01', NULL, NULL, 'Il 15 Agosto?', 5, 4, 3);
