CREATE SCHEMA my_assistance;

CREATE TABLE my_assistance.utente
(
  id               INT PRIMARY KEY,
  username         VARCHAR(20) UNIQUE NOT NULL,
  `password`       VARCHAR(100)       NOT NULL,
  email            VARCHAR(80) UNIQUE NOT NULL,
  nome             VARCHAR(50)        NOT NULL,
  cognome          VARCHAR(50)        NOT NULL,
  data_sospensione DATE,
  is_gestore       BOOLEAN DEFAULT FALSE
);

CREATE TABLE my_assistance.ufficio_tecnico
(
  id           INT PRIMARY KEY,
  nome         VARCHAR(50) UNIQUE NOT NULL,
  tel          CHAR(15),
  email        VARCHAR(55) UNIQUE NOT NULL,
  ubriacazione VARCHAR(50)
);

CREATE TABLE my_assistance.tipologia
(
  id       INT PRIMARY KEY,
  nome     VARCHAR(20) UNIQUE NOT NULL,
  priorita TINYINT            NOT NULL
);

CREATE TABLE my_assistance.segnalazione
(
  cod                 INT PRIMARY KEY,
  titolo              VARCHAR(50) NOT NULL,
  descrizione         TEXT        NOT NULL,
  stato               TINYINT     NOT NULL, -- -1 = rifiutata, 0 = aperta, 1 = assegnata, 2 = risolta
  data_segnalazione   DATE        NOT NULL,
  data_rifiuto        DATE,
  data_assegnazione   DATE,
  data_risoluzione    DATE,
  motivazione_rifiuto VARCHAR(255),
  autore              INT REFERENCES utente (id) ON DELETE CASCADE ON UPDATE CASCADE,
  tecnico             INT REFERENCES ufficio_tecnico (id) ON DELETE CASCADE ON UPDATE CASCADE
);
