INSERT INTO my_assistance.utente (username,`password`,email,nome,cognome,sesso)
VALUES ('a.naghmouchi','abcd1234','a.naghmouchi@test.it','aymen','naghmouchi',1),
('a.pauciello','abcd1234','a.pauciello3@test.it','alfonso','pauciello',1),
('a.mennillo','abcd1234','a.mennillo1@test.it','andrea','mennillo',1),
('g.albanese','abcd1234','g.albanese11@test.it','gaetano','albanese',1),
('a.mennillo','abcd1234','a.mennillo1@test.it','pio','d\'auria',1);

INSERT INTO my_assistance.ufficio_tecnico (nome,tel,email,ubriacazione)
VALUES ('Ifix','1112223334','assistenza@ifix.it','Via della Biblioteca, 84084 Fisciano SA'),
 ('MR.ripara','1112223334','assistenza@mrripara.it','Via della mensa, 84084 Fisciano SA'),
 ('Aggiustiamo sedie','1112223334','assistenza@sedieee.it','Via delle sedie, 84084 Fisciano SA');
 
 INSERT INTO my_assistance.tipologia (nome,priorita)
 VALUES ('Rottura',);  --  da 1 (priorità bassa) a 10 (priorità alta)
