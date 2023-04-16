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
     "LEIRAS" VARCHAR2(200 BYTE),
     "TEMA" VARCHAR2(20 BYTE),
     FOREIGN KEY (TEMA) REFERENCES TEMA(NEV) ON DELETE CASCADE
);

CREATE TABLE "QUIZ"
(	"QUIZID" NUMBER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) PRIMARY KEY NOT NULL,
     "QUIZTEMA" VARCHAR2(20 BYTE),
     FOREIGN KEY (QUIZTEMA) REFERENCES TEMA(NEV) ON DELETE CASCADE
);

CREATE TABLE "KERDES"
(	"ID" NUMBER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) PRIMARY KEY NOT NULL,
     "KERDESTARTALMA" VARCHAR2(300 BYTE),
     "ALTEMA" VARCHAR2(20 BYTE),
     "HELYESVALASZ" NUMBER,
     FOREIGN KEY (ALTEMA) REFERENCES ALTEMA(NEV) ON DELETE CASCADE
);

CREATE TABLE "VALASZ"
(	"VALASZID" NUMBER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) PRIMARY KEY NOT NULL,
     "KERDESID" NUMBER,
     "VALASZTARTALMA" VARCHAR2(200 BYTE),
     "HELYESE" CHAR(1),
     FOREIGN KEY (KERDESID) REFERENCES KERDES(ID) ON DELETE CASCADE
);

CREATE TABLE "FELTESZI"
(	"KERDES" NUMBER,
     "QUIZ" NUMBER,
     FOREIGN KEY (KERDES) REFERENCES KERDES(ID) ON DELETE CASCADE,
     FOREIGN KEY (QUIZ) REFERENCES QUIZ(QUIZID) ON DELETE CASCADE
);

CREATE TABLE "JATSZIK"
(	"FELHASZNALO" VARCHAR2(30 BYTE),
     "QID" NUMBER,
     FOREIGN KEY (FELHASZNALO) REFERENCES JATEKOS(FELHASZNALONEV) ON DELETE CASCADE,
     FOREIGN KEY (QID) REFERENCES QUIZ(QUIZID) ON DELETE CASCADE
);

CREATE TABLE "TARTOZIK"
(	"KID" NUMBER,
     "VID" NUMBER,
     FOREIGN KEY (KID) REFERENCES QUIZ(QUIZID) ON DELETE CASCADE,
     FOREIGN KEY (VID) REFERENCES VALASZ(VALASZID) ON DELETE CASCADE
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
INSERT INTO tema VALUES ('Science');
INSERT INTO tema VALUES ('General knowledge');
INSERT INTO tema VALUES ('Geography');
INSERT INTO tema VALUES ('Food and Drink');

INSERT INTO jatekos VALUES ('sandorbenedek12', 'benoo21', 'beni12@gmail.com', 3, NULL);
INSERT INTO jatekos VALUES ('kati', 'katibela', 'feherje@email.com', 12, NULL);
INSERT INTO jatekos VALUES ('petermeter', 'metroid54', 'maipeter@email.com', 0, NULL);
INSERT INTO jatekos VALUES ('lehel', 'kurtxd', 'lehelkehely@email.com', 34, NULL);
INSERT INTO jatekos VALUES ('rebeka', 'frogvstoad', 'ilovefrogs@email.com', 8, NULL);
INSERT INTO jatekos VALUES ('pista', 'bacsipista', 'nemvagyokoreg@email.com', 86, NULL);
INSERT INTO jatekos VALUES ('magdika', 'magdi123', 'magdi12@gmail.com', 45, NULL);
INSERT INTO jatekos VALUES ('zolipapa', 'zoli321', 'zoli56@yahoo.com', 87, NULL);
INSERT INTO jatekos VALUES ('ancsa', 'ancafeher', 'ancifeher@mail.com', 22, NULL);
INSERT INTO jatekos VALUES ('kornel', 'kornelko', 'koko@hotmail.com', 68, NULL);
INSERT INTO jatekos VALUES ('davids', 'david1989', 'davids@freemail.hu', 1, NULL);
INSERT INTO jatekos VALUES ('marianna', 'marianna123', 'marianna45@gmail.com', 57, NULL);
INSERT INTO jatekos VALUES ('istvan', 'istvan96', 'istvanlaci@gmail.com', 5, NULL);
INSERT INTO jatekos VALUES ('zsuzsika', 'zsuzsika77', 'zsuzsi@yahoo.com', 94, NULL);
INSERT INTO jatekos VALUES ('mateo', 'mateo34', 'mateo345@mail.com', 12, NULL);
INSERT INTO jatekos VALUES ('eniko', 'eniko1', 'enikocs@freemail.hu', 33, NULL);
INSERT INTO jatekos VALUES ('andris', 'andris22', 'andris2@hotmail.com', 7, NULL);
INSERT INTO jatekos VALUES ('erzsebet', 'erzsi567', 'erzsebet@freemail.hu', 89, NULL);
INSERT INTO jatekos VALUES ('ferenc', 'feri4', 'ferenc111@yahoo.com', 73, NULL);
INSERT INTO jatekos VALUES ('karcsi', 'karcsi45', 'karcsi1990@gmail.com', 24, NULL);
INSERT INTO jatekos VALUES ('julcsika', 'julcsika89', 'julcsika@gmail.com', 52, NULL);
INSERT INTO jatekos VALUES ('tomi', 'tomi111', 'tomi12@yahoo.com', 66, NULL);
INSERT INTO jatekos VALUES ('monika', 'monikaa', 'monika45@hotmail.com', 31, NULL);
INSERT INTO jatekos VALUES ('attila', 'attila555', 'attila@mail.com', 44, NULL);
INSERT INTO jatekos VALUES ('anna', 'anna33', 'annababa@yahoo.com', 98, NULL);
INSERT INTO jatekos VALUES ('tamas', 'tamas12', 'tamas34@freemail.hu', 16, NULL);
INSERT INTO jatekos VALUES ('agneska', 'agneska123', 'agneska@gmail.com', 80, NULL);
INSERT INTO jatekos VALUES ('gergo', 'gergo56', 'gergo56@hotmail.com', 9, NULL);
INSERT INTO jatekos VALUES ('sanyi', 'XyWz1234', 'sanyi5678@gmail.com', 49, NULL);
INSERT INTO jatekos VALUES ('barbi', 'FgHj5678', 'barbi12@hotmail.com', 8, NULL);
INSERT INTO jatekos VALUES ('laci', 'AbCd9012', 'lacika@gmail.com', 29, NULL);
INSERT INTO jatekos VALUES ('zsofi', 'KlMn3456', 'zsofike@hotmail.com', 74, NULL);
INSERT INTO jatekos VALUES ('petike', 'OpQr7890', 'petike23@yahoo.com', 17, NULL);
INSERT INTO jatekos VALUES ('dani', 'StUv2345', 'dani987@gmail.com', 42, NULL);
INSERT INTO jatekos VALUES ('kriszta', 'YxZa6789', 'kriszta12@yahoo.com', 64, NULL);
INSERT INTO jatekos VALUES ('patrik', 'BcDe3456', 'patrik56@hotmail.com', 31, NULL);
INSERT INTO jatekos VALUES ('marika', 'GhIj9012', 'marika45@gmail.com', 95, NULL);
INSERT INTO jatekos VALUES ('robi', 'MnOp1234', 'robi89@hotmail.com', 22, NULL);
INSERT INTO jatekos VALUES ('nora', 'QrSt5678', 'norcsi34@gmail.com', 76, NULL);
INSERT INTO jatekos VALUES ('balazs', 'WxYz9012', 'balazs12@yahoo.com', 57, NULL);
INSERT INTO jatekos VALUES ('szilvi', 'CdEf2345', 'szilvi5678@gmail.com', 9, NULL);
INSERT INTO jatekos VALUES ('marton', 'IjKl6789', 'marton12@hotmail.com', 41, NULL);
INSERT INTO jatekos VALUES ('timi', 'NoPq1234', 'timike23@yahoo.com', 71, NULL);
INSERT INTO jatekos VALUES ('adam', 'TuVw5678', 'adam87@gmail.com', 18, NULL);
INSERT INTO jatekos VALUES ('agnes', 'XyZa9012', 'agneska12@hotmail.com', 82, NULL);
INSERT INTO jatekos VALUES ('gergely', 'BcDe3456', 'gergely34@gmail.com', 38, NULL);
INSERT INTO jatekos VALUES ('emese', 'FgHi5678', 'emese89@yahoo.com', 60, NULL);
INSERT INTO jatekos VALUES ('martina', 'JkLm9012', 'martina56@hotmail.com', 26, NULL);
INSERT INTO jatekos VALUES ('aron', 'NopQ2345', 'aron34@gmail.com', 67, NULL);
INSERT INTO jatekos VALUES ('annacska', 'RsTu6789', 'anna1234@yahoo.com', 49, NULL);
INSERT INTO jatekos VALUES ('mate', 'WxYz1234', 'mate67@gmail.com', 12, NULL);
INSERT INTO jatekos VALUES ('andrea', 'BcDe5678', 'andrea12@hotmail.com', 93, NULL);

INSERT INTO altema(nev, leiras, tema) VALUES('F1', 'Formula 1 questions', 'Sport');
INSERT INTO altema(nev, leiras, tema) VALUES('Olympic Records', 'Questions related to the best performances in the history of the Olympic Games', 'Sport');
INSERT INTO altema(nev, leiras, tema) VALUES('Famous Athletes', 'Questions about well-known sports stars and their careers', 'Sport');
INSERT INTO altema(nev, leiras, tema) VALUES('Sports Venues', 'Questions about the different stadiums and arenas where sports events take place', 'Sport');
INSERT INTO altema(nev, leiras, tema) VALUES('Sports Equipment', 'Questions about the gear and equipment used in different sports', 'Sport');

INSERT INTO altema(nev, leiras, tema) VALUES('Middle ages', 'Questions related to the history of the Middle Ages', 'History');
INSERT INTO altema(nev, leiras, tema) VALUES('World War II', 'A global conflict that lasted from 1939 to 1945, involving many of the world is major powers', 'History');
INSERT INTO altema(nev, leiras, tema) VALUES('Ancient Rome', 'The civilization that emerged from the city of Rome in the 8th century BC, known for its art, architecture, and military conquests', 'History');
INSERT INTO altema(nev, leiras, tema) VALUES('The Cold War', 'A period of geopolitical tension between the Western powers, led by the United States, and the Eastern bloc, led by the Soviet Union, from the end of World War II to the early 1990s', 'History');

INSERT INTO altema(nev, leiras, tema) VALUES('Oscars', 'Questions related to the Oscars', 'Movies');
INSERT INTO altema(nev, leiras, tema) VALUES('Movie Characters', 'Questions about iconic characters from films, their backstories, and their impact on popular culture', 'Movies');
INSERT INTO altema(nev, leiras, tema) VALUES('Film Trivia', 'Questions about behind-the-scenes information, trivia, and fun facts related to movies', 'Movies');

INSERT INTO altema(nev, leiras, tema) VALUES('Biology', 'The study of living organisms and their interactions with each other and the environment', 'Science');
INSERT INTO altema(nev, leiras, tema) VALUES('Chemistry', 'The study of the composition, structure, properties, and reactions of matter', 'Science');
INSERT INTO altema(nev, leiras, tema) VALUES('Physics', 'The study of matter, energy, and their interactions', 'Science');

/*
INSERT INTO altema(nev, leiras, tema) VALUES('', '', 'General knowledge');
INSERT INTO altema(nev, leiras, tema) VALUES('', '', 'General knowledge');
INSERT INTO altema(nev, leiras, tema) VALUES('', '', 'General knowledge');
*/

INSERT INTO quiz(quiztema) VALUES ('Sport');
INSERT INTO quiz(quiztema) VALUES ('History');
INSERT INTO quiz(quiztema) VALUES ('Movies');
INSERT INTO quiz(quiztema) VALUES ('Science');
INSERT INTO quiz(quiztema) VALUES ('General knowledge');
INSERT INTO quiz(quiztema) VALUES ('Geography');
INSERT INTO quiz(quiztema) VALUES ('Food and Drink');


INSERT INTO kerdes (kerdestartalma, altema, helyesvalasz) VALUES ('In 2016, who became F1 World Champion and then announced his retirement from the sport five days later?', 'F1', 1);
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('Which F1 racing team, formed in 2007, is based in Silverstone?', 'F1');
/*INSERT INTO kerdes (kerdestartalma, altema) VALUES ('What type of boats did the Vikings use when exploring and raiding?', 'Middle ages');
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('How many wives did Henry VIII have?', 'Middle ages');
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('How many Oscars has Halle Berry won?', 'Oscars');
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('Who was the first Black person to win an Oscar?', 'Oscars');*/

INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (1, 'Nico Rosberg', 'Y');
/*INSERT INTO valasz(valasztartalma, helyese) VALUES ('Fernando Alonso', 'N');
INSERT INTO valasz(valasztartalma, helyese) VALUES ('Micheal Schumacher', 'N');
INSERT INTO valasz(valasztartalma, helyese) VALUES ('Lewis Hamilton', 'N');*/
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (2, 'Force India', 'Y');
/*INSERT INTO valasz(valasztartalma, helyese) VALUES ('AMG Mercedes', 'N');
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
INSERT INTO valasz(valasztartalma, helyese) VALUES ('James Earl Jones', 'N');*/

INSERT INTO tartozik VALUES (1, 1);
/*INSERT INTO tartozik VALUES (1, 2);
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
INSERT INTO tartozik VALUES (6, 24);*/

Insert INTO felteszi  VALUES (1, 1);
/*Insert INTO felteszi  VALUES (2, 1);
Insert INTO felteszi  VALUES (1, 2);
Insert INTO felteszi  VALUES (2, 2);
Insert INTO felteszi  VALUES (1, 3);
Insert INTO felteszi  VALUES (2, 3);*/

INSERT INTO jatszik VALUES('kati', 1);
/*INSERT INTO jatszik VALUES('lehel', 2);
INSERT INTO jatszik VALUES('pista', 3);*/