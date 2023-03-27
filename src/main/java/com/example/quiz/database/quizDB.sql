DROP TABLE ALTEMA CASCADE CONSTRAINTS;
DROP TABLE FELTESZI CASCADE CONSTRAINTS;
DROP TABLE JATSZIK CASCADE CONSTRAINTS;
DROP TABLE KERDES CASCADE CONSTRAINTS;
DROP TABLE QUIZ CASCADE CONSTRAINTS;
DROP TABLE TARTOZIK CASCADE CONSTRAINTS;
DROP TABLE TEMA CASCADE CONSTRAINTS;
DROP TABLE VALASZ CASCADE CONSTRAINTS;
DROP TABLE JATEKOS CASCADE CONSTRAINTS;

CREATE TABLE "TEMA" 
   (	"NEV" VARCHAR2(20 BYTE) PRIMARY KEY NOT NULL );

   CREATE TABLE "JATEKOS" 
   (	"FELHASZNALONEV" VARCHAR2(30 BYTE) PRIMARY KEY NOT NULL, 
	    "JELSZO" VARCHAR2(30 BYTE), 
	    "EMAIL" VARCHAR2(50 BYTE), 
	    "RANGSORPONTSZAM" NUMBER, 
	    "TEMAJA" VARCHAR2(20 BYTE),
    FOREIGN KEY (TEMAJA) REFERENCES TEMA(NEV) ON DELETE CASCADE
   );

  CREATE TABLE "ALTEMA" 
   (	"NEV" VARCHAR2(30 BYTE) PRIMARY KEY NOT NULL, 
	"LEIRAS" VARCHAR2(120 BYTE), 
	"TEMA" VARCHAR2(20 BYTE),
  FOREIGN KEY (TEMA) REFERENCES TEMA(NEV) ON DELETE CASCADE
   );

   CREATE TABLE "QUIZ" 
   (	"QUIZID" NUMBER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) PRIMARY KEY NOT NULL,
	    "QUIZTEMA" VARCHAR2(20 BYTE),
      FOREIGN KEY (QUIZTEMA) REFERENCES TEMA(NEV) ON DELETE CASCADE
   );

CREATE TABLE "KERDES" 
   (	"KERDESID" NUMBER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) PRIMARY KEY NOT NULL,
	    "KERDESTARTALMA" VARCHAR2(300 BYTE), 
	    "ALTEMA" VARCHAR2(20 BYTE),
    FOREIGN KEY (ALTEMA) REFERENCES ALTEMA(NEV) ON DELETE CASCADE
   );

 CREATE TABLE "VALASZ" 
   (	"VALASZID" NUMBER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) PRIMARY KEY NOT NULL,
	  "VALASZTARTALMA" VARCHAR2(200 BYTE), 
	  "HELYESE" CHAR(1)
   );

  CREATE TABLE "FELTESZI" 
   (	"KERDES" NUMBER, 
	    "QUIZ" NUMBER
   );

  

  CREATE TABLE "JATSZIK" 
   (	"FELHASZNALO" VARCHAR2(30 BYTE), 
	    "QID" NUMBER
   );

  CREATE TABLE "TARTOZIK" 
   (	"KID" NUMBER, 
	    "VID" NUMBER
   );

ALTER TABLE FELTESZI
ADD CONSTRAINT PK_FELTESZI
PRIMARY KEY(KERDES, QUIZ);

ALTER TABLE JATSZIK
ADD CONSTRAINT PK_JATSZIK
PRIMARY KEY(FELHASZNALO, QID);

ALTER TABLE TARTOZIK
ADD CONSTRAINT PK_TARTOZIK
PRIMARY KEY(KID, VID);

INSERT INTO tema VALUES ('Sport');
INSERT INTO tema VALUES ('History');
INSERT INTO tema VALUES ('Movies');

INSERT INTO jatekos VALUES ('sandorbenedek12', 'benoo21', 'beni12@gmail.com', 3, NULL);
INSERT INTO jatekos VALUES ('kati', 'katibela', 'feherje@email.com', 12, NULL);
INSERT INTO jatekos VALUES ('petermeter', 'metroid54', 'maipeter@email.com', 0, NULL);
INSERT INTO jatekos VALUES ('lehel', 'kurtxd', 'lehelkehely@email.com', 34, NULL);
INSERT INTO jatekos VALUES ('rebeka', 'frogvstoad', 'ilovefrogs@email.com', 8, NULL);
INSERT INTO jatekos VALUES ('pista', 'bacsipista', 'nemvagyokoreg@email.com', 86, NULL);

INSERT INTO altema(nev, leiras, tema) VALUES('F1', 'Formula 1 questions', 'Sport');
INSERT INTO altema(nev, leiras, tema) VALUES('Middle ages', 'Questions related to the history of the Middle Ages', 'History');
INSERT INTO altema(nev, leiras, tema) VALUES('Oscars', 'Questions related to the Oscars', 'Movies');

INSERT INTO quiz(quiztema) VALUES ('Sport');
INSERT INTO quiz(quiztema) VALUES ('History');
INSERT INTO quiz(quiztema) VALUES ('Movies');

INSERT INTO kerdes (kerdestartalma, altema) VALUES ('In 2016, who became F1 World Champion and then announced his retirement from the sport five days later?', 'F1');
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('Which F1 racing team, formed in 2007, is based in Silverstone?', 'F1');
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('What type of boats did the Vikings use when exploring and raiding?', 'Middle ages');
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('How many wives did Henry VIII have?', 'Middle ages');
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('How many Oscars has Halle Berry won?', 'Oscars');
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('Who was the first Black person to win an Oscar?', 'Oscars');

INSERT INTO valasz(valasztartalma, helyese) VALUES ('Nico Rosberg', 'Y');
INSERT INTO valasz(valasztartalma, helyese) VALUES ('Fernando Alonso', 'N');
INSERT INTO valasz(valasztartalma, helyese) VALUES ('Micheal Schumacher', 'N');
INSERT INTO valasz(valasztartalma, helyese) VALUES ('Lewis Hamilton', 'N');
INSERT INTO valasz(valasztartalma, helyese) VALUES ('Force India', 'Y');
INSERT INTO valasz(valasztartalma, helyese) VALUES ('AMG Mercedes', 'N');
INSERT INTO valasz(valasztartalma, helyese) VALUES ('McLaren', 'N');
INSERT INTO valasz(valasztartalma, helyese) VALUES ('Williams', 'N');
INSERT INTO valasz(valasztartalma, helyese) VALUES ('Longship', 'Y');
INSERT INTO valasz(valasztartalma, helyese) VALUES ('Keelboat', 'N');
INSERT INTO valasz(valasztartalma, helyese) VALUES ('Galley', 'N');
INSERT INTO valasz(valasztartalma, helyese) VALUES ('Sail boat', 'N');
INSERT INTO valasz(valasztartalma, helyese) VALUES ('8', 'Y');
INSERT INTO valasz(valasztartalma, helyese) VALUES ('3', 'N');
INSERT INTO valasz(valasztartalma, helyese) VALUES ('6', 'N');
INSERT INTO valasz(valasztartalma, helyese) VALUES ('9', 'N');
INSERT INTO valasz(valasztartalma, helyese) VALUES ('1', 'Y');
INSERT INTO valasz(valasztartalma, helyese) VALUES ('2', 'N');
INSERT INTO valasz(valasztartalma, helyese) VALUES ('0', 'N');
INSERT INTO valasz(valasztartalma, helyese) VALUES ('4', 'N');
INSERT INTO valasz(valasztartalma, helyese) VALUES ('Hattie McDaniel', 'Y');
INSERT INTO valasz(valasztartalma, helyese) VALUES ('Sidney Poitier', 'N');
INSERT INTO valasz(valasztartalma, helyese) VALUES ('Dorothy Dandridge', 'N');
INSERT INTO valasz(valasztartalma, helyese) VALUES ('James Earl Jones', 'N');

INSERT INTO tartozik VALUES (1, 1);
INSERT INTO tartozik VALUES (1, 2);
INSERT INTO tartozik VALUES (1, 3);
INSERT INTO tartozik VALUES (1, 4);
INSERT INTO tartozik VALUES (2, 5);
INSERT INTO tartozik VALUES (2, 6);
INSERT INTO tartozik VALUES (2, 7);
INSERT INTO tartozik VALUES (2, 8);
INSERT INTO tartozik VALUES (3, 9);
INSERT INTO tartozik VALUES (3, 10);
INSERT INTO tartozik VALUES (3, 11);
INSERT INTO tartozik VALUES (3, 12);
INSERT INTO tartozik VALUES (4, 13);
INSERT INTO tartozik VALUES (4, 14);
INSERT INTO tartozik VALUES (4, 15);
INSERT INTO tartozik VALUES (4, 16);
INSERT INTO tartozik VALUES (5, 17);
INSERT INTO tartozik VALUES (5, 18);
INSERT INTO tartozik VALUES (5, 19);
INSERT INTO tartozik VALUES (5, 20);
INSERT INTO tartozik VALUES (6, 21);
INSERT INTO tartozik VALUES (6, 22);
INSERT INTO tartozik VALUES (6, 23);
INSERT INTO tartozik VALUES (6, 24);

Insert INTO felteszi  VALUES (1, 1);
Insert INTO felteszi  VALUES (2, 1);
Insert INTO felteszi  VALUES (1, 2);
Insert INTO felteszi  VALUES (2, 2);
Insert INTO felteszi  VALUES (1, 3);
Insert INTO felteszi  VALUES (2, 3);

INSERT INTO jatszik VALUES('kati', 1);
INSERT INTO jatszik VALUES('lehel', 2);
INSERT INTO jatszik VALUES('pista', 3);
