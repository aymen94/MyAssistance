CREATE SCHEMA my_assistance CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE TABLE my_assistance.utente 
(
  id               INT PRIMARY KEY AUTO_INCREMENT,
  username         VARCHAR(20) UNIQUE NOT NULL,
  pass             VARCHAR(100)       NOT NULL,
  email            VARCHAR(80) UNIQUE NOT NULL,
  nome             VARCHAR(50)        NOT NULL,
  cognome          VARCHAR(50)        NOT NULL,
  sesso            TINYINT            NOT NULL, -- 0 = Altro, 1 = Maschio, 2 = Femmina
  data_di_nascita  DATE               NOT NULL,
  data_sospensione DATE,
  is_gestore       BOOLEAN DEFAULT FALSE
)AUTO_INCREMENT = 1;

CREATE TABLE my_assistance.ufficio_tecnico
(
  id         INT PRIMARY KEY AUTO_INCREMENT,
  nome       VARCHAR(50) UNIQUE NOT NULL,
  tel        CHAR(15),
  email      VARCHAR(55)        NOT NULL,
  ubicazione VARCHAR(50)
)AUTO_INCREMENT = 1;


CREATE TABLE my_assistance.tipologia
(
  id       INT PRIMARY KEY AUTO_INCREMENT,
  nome     VARCHAR(20) UNIQUE NOT NULL,
  priorita TINYINT            NOT NULL --  da 1 (priorità bassa) a 10 (priorità alta)
)AUTO_INCREMENT = 1;


CREATE TABLE my_assistance.segnalazione
(
  cod                 INT PRIMARY KEY AUTO_INCREMENT,
  titolo              VARCHAR(50) NOT NULL,
  descrizione         TEXT        NOT NULL,
  stato               TINYINT     NOT NULL, -- -1 = rifiutata, 0 = aperta, 1 = assegnata, 2 = risolta
  data_segnalazione   DATE        NOT NULL,
  data_rifiuto        DATE,
  data_assegnazione   DATE,
  data_risoluzione    DATE,
  motivazione_rifiuto VARCHAR(255),
  tipologia           INT NOT NULL,
  FOREIGN KEY (tipologia) REFERENCES my_assistance.tipologia (id) ON DELETE CASCADE ON UPDATE CASCADE,
  autore              INT NOT NULL,
  FOREIGN KEY (autore) REFERENCES my_assistance.utente (id) ON DELETE CASCADE ON UPDATE CASCADE,
  tecnico             INT,
  FOREIGN KEY (tecnico) REFERENCES my_assistance.ufficio_tecnico (id) ON DELETE CASCADE ON UPDATE CASCADE
)AUTO_INCREMENT = 1;

