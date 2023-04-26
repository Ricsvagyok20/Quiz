SET SERVEROUTPUT ON;

--Tárolt eljárás a meglévő táblák törlésére
CREATE OR REPLACE TYPE TABLAK_TYPE IS VARRAY(9) OF VARCHAR2(30);
/
DECLARE
    C NUMBER;
    TABLAK TABLAK_TYPE := TABLAK_TYPE('ALTEMA', 'FELTESZI', 'JATSZIK', 'KERDES', 'QUIZ', 'PONTSZAM', 'TEMA', 'VALASZ', 'JATEKOS');
    PROCEDURE TOROL_HA_VAN(TABLAK_P IN TABLAK_TYPE) IS
        BEGIN
        FOR I IN 1..TABLAK.COUNT LOOP
            SELECT COUNT(*) INTO C FROM USER_TABLES WHERE TABLE_NAME = UPPER(TABLAK_P(I));
            IF C = 1 THEN
                EXECUTE IMMEDIATE 'DROP TABLE "' || TABLAK_P(I) || '" CASCADE CONSTRAINTS';
                DBMS_OUTPUT.PUT_LINE(TABLAK_P(I) || ' TOROLVE.');
            END IF;
        END LOOP;
    END;
BEGIN
    TOROL_HA_VAN(TABLAK);
END;
/

CREATE TABLE "TEMA"
(	"NEV" VARCHAR2(20 BYTE) PRIMARY KEY NOT NULL );

CREATE TABLE "JATEKOS"
(	 "FELHASZNALONEV" VARCHAR2(30 BYTE) PRIMARY KEY NOT NULL,
     "JELSZO" VARCHAR2(30 BYTE),
     "EMAIL" VARCHAR2(50 BYTE),
     "TEMAJA" VARCHAR2(20 BYTE),
     FOREIGN KEY (TEMAJA) REFERENCES TEMA(NEV) ON DELETE CASCADE
);

CREATE TABLE "ALTEMA"
(	 "NEV" VARCHAR2(30 BYTE) PRIMARY KEY NOT NULL,
     "LEIRAS" VARCHAR2(200 BYTE),
     "TEMA" VARCHAR2(20 BYTE),
     FOREIGN KEY (TEMA) REFERENCES TEMA(NEV) ON DELETE CASCADE
);

CREATE TABLE "QUIZ"
(	  "QUIZID" NUMBER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) PRIMARY KEY NOT NULL,
      "QUIZTEMA" VARCHAR2(20 BYTE),
      FOREIGN KEY (QUIZTEMA) REFERENCES TEMA(NEV) ON DELETE CASCADE
);

CREATE TABLE "KERDES"
(	 "ID" NUMBER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) PRIMARY KEY NOT NULL,
     "KERDESTARTALMA" VARCHAR2(300 BYTE),
     "ALTEMA" VARCHAR2(20 BYTE),
     FOREIGN KEY (ALTEMA) REFERENCES ALTEMA(NEV) ON DELETE CASCADE
);

CREATE TABLE "VALASZ"
(	 "VALASZID" NUMBER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) PRIMARY KEY NOT NULL,
     "KERDESID" NUMBER,
     "VALASZTARTALMA" VARCHAR2(200 BYTE),
     "HELYESE" CHAR(1),
     FOREIGN KEY (KERDESID) REFERENCES KERDES(ID) ON DELETE CASCADE
);

CREATE TABLE "FELTESZI"
(	 "KERDES" NUMBER,
     "QUIZ" NUMBER,
     FOREIGN KEY (KERDES) REFERENCES KERDES(ID) ON DELETE CASCADE,
     FOREIGN KEY (QUIZ) REFERENCES QUIZ(QUIZID) ON DELETE CASCADE
);

CREATE TABLE "JATSZIK"
(	 "FELHASZNALO" VARCHAR2(30 BYTE),
     "QID" NUMBER,
     FOREIGN KEY (FELHASZNALO) REFERENCES JATEKOS(FELHASZNALONEV) ON DELETE CASCADE,
     FOREIGN KEY (QID) REFERENCES QUIZ(QUIZID) ON DELETE CASCADE
);

CREATE TABLE "PONTSZAM"
(
    "FNEV" VARCHAR2(30 BYTE),
    "TNEV" VARCHAR2(20 BYTE),
    "RANGSORPONTSZAM" NUMBER,
    FOREIGN KEY (FNEV) REFERENCES JATEKOS(FELHASZNALONEV) ON DELETE CASCADE,
    FOREIGN KEY (TNEV) REFERENCES TEMA(NEV) ON DELETE CASCADE
);

--CREATE TABLE "JATEKOS_LOG" ( LOG_DATUM DATE, LOG_FELHASZNALO VARCHAR2(30), LOG_EMAIL VARCHAR2(50));

ALTER TABLE FELTESZI
    ADD CONSTRAINT PK_FELTESZI
        PRIMARY KEY(KERDES, QUIZ);

ALTER TABLE JATSZIK
    ADD CONSTRAINT PK_JATSZIK
        PRIMARY KEY(FELHASZNALO, QID);
/*
--Email formátumot ellenőrző trigger, ha nem megfelelő a formátum, insert into vagy update exceptiont dob
CREATE OR REPLACE TRIGGER ROSSZ_EMAIL
    BEFORE INSERT OR UPDATE OF EMAIL ON JATEKOS
    FOR EACH ROW
BEGIN
    IF REGEXP_LIKE ('anyaddress@xyz123.com',
        '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4}$')
        THEN DBMS_OUTPUT.PUT_LINE('Játékos hozzáadva');
    ELSE
        RAISE_APPLICATION_ERROR(-20500, 'Email format is incorrect!');
    END IF;
END;
/

--Admin felhasználóval kapcsolatos bármilyen művelet esetén hibát dobó trigger
CREATE OR REPLACE TRIGGER ADMINT_NE_VALTOZTASD
    BEFORE DELETE OR INSERT OR UPDATE
    ON JATEKOS
    FOR EACH ROW
    WHEN (UPPER(OLD.FELHASZNALONEV) = 'ADMIN' OR UPPER(NEW.FELHASZNALONEV) = 'ADMIN')
BEGIN
    IF DELETING THEN
        RAISE_APPLICATION_ERROR(-20001, 'Admin bárkit tud törölni, őt azonban nem lehet!');
    ELSIF INSERTING THEN
        RAISE_APPLICATION_ERROR(-20002, 'Csak egy admin létezik, nem lehet kettő!');
    ELSIF UPDATING THEN
        RAISE_APPLICATION_ERROR(-20003, 'Admin adatai sérthetetlenek!');
    END IF;
END;
/

CREATE OR REPLACE TRIGGER JATEKOS_LOG_TRIGGER
    AFTER INSERT OR UPDATE OR DELETE
    ON JATEKOS
DECLARE
    VALTOZTATAS VARCHAR2(20);
BEGIN
    IF INSERTING THEN
        VALTOZTATAS := 'Added employee(s)';
    ELSIF UPDATING THEN
        VALTOZTATAS := 'Updated employee(s)';
    ELSIF DELETING THEN
        VALTOZTATAS := 'Deleted employee(s)';
    END IF;
    INSERT INTO JATEKOS_LOG VALUES (SYSDATE, USER, VALTOZTATAS);
END;
/

--Integritást ellenőrző triggerek
CREATE OR REPLACE TRIGGER I_JATEKOS
    BEFORE UPDATE OF FELHASZNALONEV ON JATEKOS
    FOR EACH ROW
BEGIN
UPDATE JATSZIK SET FELHASZNALO = :NEW.FELHASZNALONEV
WHERE FELHASZNALO = :OLD.FELHASZNALONEV;

UPDATE PONTSZAM SET FNEV = :NEW.FELHASZNALONEV
WHERE FNEV = :OLD.FELHASZNALONEV;
END;
/

CREATE OR REPLACE TRIGGER I_TEMA
    BEFORE UPDATE OF NEV ON TEMA
    FOR EACH ROW
BEGIN
UPDATE JATEKOS SET TEMAJA = :NEW.NEV
WHERE TEMAJA = :OLD.NEV;

UPDATE ALTEMA SET TEMA = :NEW.NEV
WHERE TEMA = :OLD.NEV;

UPDATE QUIZ SET QUIZTEMA = :NEW.NEV
WHERE QUIZTEMA = :OLD.NEV;

UPDATE PONTSZAM SET TNEV = :NEW.NEV
WHERE TNEV = :OLD.NEV;
END;
/

CREATE OR REPLACE TRIGGER I_QUIZ
    BEFORE UPDATE OF QUIZID ON QUIZ
    FOR EACH ROW
BEGIN
UPDATE FELTESZI SET QUIZ = :NEW.QUIZID
WHERE QUIZ = :OLD.QUIZID;

UPDATE JATSZIK SET QID = :NEW.QUIZID
WHERE QID = :OLD.QUIZID;
END;
/

CREATE OR REPLACE TRIGGER I_KERDES
    BEFORE UPDATE OF ID ON KERDES
    FOR EACH ROW
BEGIN
UPDATE VALASZ SET KERDESID = :NEW.ID
WHERE KERDESID = :OLD.ID;

UPDATE FELTESZI SET KERDES = :NEW.ID
WHERE KERDES = :OLD.ID;
END;
/

CREATE OR REPLACE TRIGGER I_ALTEMA
    BEFORE UPDATE OF NEV ON ALTEMA
    FOR EACH ROW
BEGIN
UPDATE KERDES SET ALTEMA = :NEW.NEV
WHERE ALTEMA = :OLD.NEV;
END;
/
*/


INSERT INTO tema VALUES ('Sport');
INSERT INTO tema VALUES ('History');
INSERT INTO tema VALUES ('Movies');
INSERT INTO tema VALUES ('Science');


INSERT INTO jatekos VALUES ('sandorbenedek12', 'benoo21', 'beni12@gmail.com', 'History');
INSERT INTO jatekos VALUES ('kati', 'katibela', 'feherje@email.com', 'History');
INSERT INTO jatekos VALUES ('petermeter', 'metroid54', 'maipeter@email.com', 'History');
INSERT INTO jatekos VALUES ('lehel', 'kurtxd', 'lehelkehely@email.com', NULL);
INSERT INTO jatekos VALUES ('rebeka', 'frogvstoad', 'ilovefrogs@email.com', NULL);
INSERT INTO jatekos VALUES ('pista', 'bacsipista', 'nemvagyokoreg@email.com', NULL);
INSERT INTO jatekos VALUES ('magdika', 'magdi123', 'magdi12@gmail.com', NULL);
INSERT INTO jatekos VALUES ('zolipapa', 'zoli321', 'zoli56@yahoo.com', NULL);
INSERT INTO jatekos VALUES ('ancsa', 'ancafeher', 'ancifeher@mail.com', NULL);
INSERT INTO jatekos VALUES ('kornel', 'kornelko', 'koko@hotmail.com', NULL);
INSERT INTO jatekos VALUES ('davids', 'david1989', 'davids@freemail.hu', NULL);
INSERT INTO jatekos VALUES ('marianna', 'marianna123', 'marianna45@gmail.com', NULL);
INSERT INTO jatekos VALUES ('istvan', 'istvan96', 'istvanlaci@gmail.com', NULL);
INSERT INTO jatekos VALUES ('zsuzsika', 'zsuzsika77', 'zsuzsi@yahoo.com', NULL);
INSERT INTO jatekos VALUES ('mateo', 'mateo34', 'mateo345@mail.com', NULL);
INSERT INTO jatekos VALUES ('eniko', 'eniko1', 'enikocs@freemail.hu', NULL);
INSERT INTO jatekos VALUES ('andris', 'andris22', 'andris2@hotmail.com', NULL);
INSERT INTO jatekos VALUES ('erzsebet', 'erzsi567', 'erzsebet@freemail.hu', NULL);
INSERT INTO jatekos VALUES ('ferenc', 'feri4', 'ferenc111@yahoo.com', NULL);
INSERT INTO jatekos VALUES ('karcsi', 'karcsi45', 'karcsi1990@gmail.com', NULL);
INSERT INTO jatekos VALUES ('julcsika', 'julcsika89', 'julcsika@gmail.com', NULL);
INSERT INTO jatekos VALUES ('tomi', 'tomi111', 'tomi12@yahoo.com', NULL);
INSERT INTO jatekos VALUES ('monika', 'monikaa', 'monika45@hotmail.com', NULL);
INSERT INTO jatekos VALUES ('attila', 'attila555', 'attila@mail.com', NULL);
INSERT INTO jatekos VALUES ('anna', 'anna33', 'annababa@yahoo.com', NULL);
INSERT INTO jatekos VALUES ('tamas', 'tamas12', 'tamas34@freemail.hu', NULL);
INSERT INTO jatekos VALUES ('agneska', 'agneska123', 'agneska@gmail.com', NULL);
INSERT INTO jatekos VALUES ('gergo', 'gergo56', 'gergo56@hotmail.com', NULL);
INSERT INTO jatekos VALUES ('sanyi', 'XyWz1234', 'sanyi5678@gmail.com', NULL);
INSERT INTO jatekos VALUES ('barbi', 'FgHj5678', 'barbi12@hotmail.com', NULL);
INSERT INTO jatekos VALUES ('laci', 'AbCd9012', 'lacika@gmail.com', NULL);
INSERT INTO jatekos VALUES ('zsofi', 'KlMn3456', 'zsofike@hotmail.com', NULL);
INSERT INTO jatekos VALUES ('petike', 'OpQr7890', 'petike23@yahoo.com', NULL);
INSERT INTO jatekos VALUES ('dani', 'StUv2345', 'dani987@gmail.com', NULL);
INSERT INTO jatekos VALUES ('kriszta', 'YxZa6789', 'kriszta12@yahoo.com', NULL);
INSERT INTO jatekos VALUES ('patrik', 'BcDe3456', 'patrik56@hotmail.com', NULL);
INSERT INTO jatekos VALUES ('marika', 'GhIj9012', 'marika45@gmail.com', NULL);
INSERT INTO jatekos VALUES ('robi', 'MnOp1234', 'robi89@hotmail.com', NULL);
INSERT INTO jatekos VALUES ('nora', 'QrSt5678', 'norcsi34@gmail.com', NULL);
INSERT INTO jatekos VALUES ('balazs', 'WxYz9012', 'balazs12@yahoo.com', NULL);
INSERT INTO jatekos VALUES ('szilvi', 'CdEf2345', 'szilvi5678@gmail.com', NULL);
INSERT INTO jatekos VALUES ('marton', 'IjKl6789', 'marton12@hotmail.com', NULL);
INSERT INTO jatekos VALUES ('timi', 'NoPq1234', 'timike23@yahoo.com', NULL);
INSERT INTO jatekos VALUES ('adam', 'TuVw5678', 'adam87@gmail.com', NULL);
INSERT INTO jatekos VALUES ('agnes', 'XyZa9012', 'agneska12@hotmail.com', NULL);
INSERT INTO jatekos VALUES ('gergely', 'BcDe3456', 'gergely34@gmail.com', NULL);
INSERT INTO jatekos VALUES ('emese', 'FgHi5678', 'emese89@yahoo.com', NULL);
INSERT INTO jatekos VALUES ('martina', 'JkLm9012', 'martina56@hotmail.com', NULL);
INSERT INTO jatekos VALUES ('aron', 'NopQ2345', 'aron34@gmail.com', NULL);
INSERT INTO jatekos VALUES ('annacska', 'RsTu6789', 'anna1234@yahoo.com', NULL);
INSERT INTO jatekos VALUES ('mate', 'WxYz1234', 'mate67@gmail.com', NULL);
INSERT INTO jatekos VALUES ('andrea', 'BcDe5678', 'andrea12@hotmail.com', NULL);
INSERT INTO jatekos VALUES ('admin', 'admin', 'admin@admin', NULL);

INSERT INTO altema(nev, leiras, tema) VALUES('F1', 'Formula 1 questions', 'Sport');
INSERT INTO altema(nev, leiras, tema) VALUES('Olympic Records', 'Questions related to the best performances in the history of the Olympic Games', 'Sport');
INSERT INTO altema(nev, leiras, tema) VALUES('Famous Athletes', 'Questions about well-known sports stars and their careers', 'Sport');
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


INSERT INTO quiz(quiztema) VALUES ('Sport');
INSERT INTO quiz(quiztema) VALUES ('History');
INSERT INTO quiz(quiztema) VALUES ('Movies');
INSERT INTO quiz(quiztema) VALUES ('Science');

--Sport tema
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('In 2016, who became F1 World Champion and then announced his retirement from the sport five days later?', 'F1');
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('Which F1 racing team, formed in 2007, is based in Silverstone?', 'F1');
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('Which team has won the most Constructors Championships in F1 history?', 'F1');
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('Which driver holds the record for the most Grand Prix wins in F1 history?', 'F1');
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('Which team won the first ever F1 Constructors Championship in 1958?', 'F1');

INSERT INTO kerdes (kerdestartalma, altema) VALUES ('Who holds the record for the most gold medals won at a single Olympic Games?', 'Olympic Records');
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('What is the world record time for the mens 100m sprint at the Olympic Games?', 'Olympic Records');
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('Who holds the record for the most Olympic medals won by a male athlete?', 'Olympic Records');
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('What is the world record for the mens long jump at the Olympic Games?', 'Olympic Records');
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('What is the world record for the mens 100 m freestyle in swimming at the Olympic Games?', 'Olympic Records');

INSERT INTO kerdes (kerdestartalma, altema) VALUES ('Who is the all-time leading scorer in NBA history?', 'Famous Athletes');
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('Who is the all-time leading scorer for the Brazilian national soccer team?', 'Famous Athletes');
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('Who is the only player to have won the FIFA World Cup as both a player and a coach?', 'Famous Athletes');
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('Who is the only player to have won the Ballon d''Or six times?', 'Famous Athletes');
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('Who is the only boxer to have won world titles in eight different weight classes?', 'Famous Athletes');

INSERT INTO kerdes (kerdestartalma, altema) VALUES ('What is the official weight of a basketball used in international competitions?', 'Sports Equipment');
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('Which type of wood is commonly used to make the shaft of a badminton racket?', 'Sports Equipment');
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('What is the maximum weight of a golf ball according to the rules of golf?', 'Sports Equipment');
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('What is the material used to make the feathers on a shuttlecock for badminton?', 'Sports Equipment');
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('What is the maximum weight of a boxing glove in professional bouts?', 'Sports Equipment');

--History tema
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('What type of boats did the Vikings use when exploring and raiding?', 'Middle ages');
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('How many wives did Henry VIII have?', 'Middle ages');
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('What was the name of the period in European history that followed the fall of the Western Roman Empire and lasted until the Renaissance?', 'Middle ages');
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('What was the name of the disease that ravaged Europe during the Middle Ages and is estimated to have killed between 75 and 200 million people?', 'Middle ages');
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('Who was the leader of the Mongol Empire during the 13th century and conquered much of Eurasia?', 'Middle ages');

INSERT INTO kerdes (kerdestartalma, altema) VALUES ('Which two countries signed a non-aggression pact in August 1939, just one week before the outbreak of World War II?', 'World War II');
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('Which event is generally regarded as the start of World War II?', 'World War II');
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('Which country was the first to develop and use atomic weapons during World War II?', 'World War II');
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('Which conference between the Allied leaders took place in February 1945, and resulted in the division of Germany after the war?', 'World War II');
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('Which country suffered the most casualties during World War II?', 'World War II');

INSERT INTO kerdes (kerdestartalma, altema) VALUES ('What was the name of the highest governing body in Ancient Rome?', 'Ancient Rome');
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('What was the name of the famous Roman road that connected Rome to the south of Italy?', 'Ancient Rome');
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('Which ancient Roman structure is considered one of the greatest engineering feats of all time?', 'Ancient Rome');
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('What was the name of the ancient Roman goddess of love and beauty?', 'Ancient Rome');
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('Who was the first emperor of Ancient Rome?', 'Ancient Rome');

INSERT INTO kerdes (kerdestartalma, altema) VALUES ('What was the primary cause of the Cold War?', 'The Cold War');
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('Which of the following was NOT a key player in the Cold War?', 'The Cold War');
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('What was the purpose of the Marshall Plan?', 'The Cold War');
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('Which of the following countries was NOT a member of the Warsaw Pact?', 'The Cold War');
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('What event marked the end of the Cold War?', 'The Cold War');

--Movies tema
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('How many Oscars has Halle Berry won?', 'Oscars');
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('Who was the first Black person to win an Oscar?', 'Oscars');
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('Which movie holds the record for the most Oscar wins, with 11 statuettes?', 'Oscars');
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('Which actor has won the most Oscars overall?', 'Oscars');
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('Which director has won the most Oscars for Best Director?', 'Oscars');

INSERT INTO kerdes (kerdestartalma, altema) VALUES ('Which character did Sylvester Stallone play in the "Rocky" movies?', 'Movie Characters');
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('Who played the character of Tony Stark, also known as Iron Man, in the Marvel Cinematic Universe?', 'Movie Characters');
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('Who played the character of Darth Vader in the original "Star Wars" trilogy?', 'Movie Characters');
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('Who played the character of Michael Corleone in "The Godfather" trilogy?', 'Movie Characters');
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('Who played the character of Hannibal Lecter in "The Silence of the Lambs"?', 'Movie Characters');

INSERT INTO kerdes (kerdestartalma, altema) VALUES ('In the movie "The Godfather," what fruit does Don Corleone famously play with during a meeting?', 'Film Trivia');
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('Who directed the movie "Jaws"?', 'Film Trivia');
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('What is the name of the first movie ever made?', 'Film Trivia');
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('Which classic horror movie featured the line, "Here''s Johnny!"?', 'Film Trivia');
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('In the movie "Forrest Gump," what is Forrest''s favorite saying?', 'Film Trivia');

--Science tema
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('What is the name for the process by which organisms convert food into energy?', 'Biology');
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('Which of the following is the building block of all living organisms?', 'Biology');
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('What is the name for the process by which plants produce oxygen?', 'Biology');
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('What is the name for the process by which organisms release excess water through pores in their leaves?', 'Biology');
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('What is the name for the study of the interactions between organisms and their environment?', 'Biology');

INSERT INTO kerdes (kerdestartalma, altema) VALUES ('What is the smallest unit of an element?', 'Chemistry');
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('What is the name for the type of chemical bond that involves the sharing of electrons between atoms?', 'Chemistry');
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('Which of the following is an example of a heterogeneous mixture?', 'Chemistry');
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('What is the name for the process by which a liquid turns into a gas?', 'Chemistry');
INSERT INTO kerdes (kerdestartalma, altema) VALUES ('Which of the following is an example of an acidic solution?', 'Chemistry');


--F1 altema
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (1, 'Nico Rosberg', 'Y');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (1, 'Fernando Alonso', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (1, 'Micheal Schumacher', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (1, 'Lewis Hamilton', 'N');

INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (2, 'Force India', 'Y');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (2, 'AMG Mercedes', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (2, 'McLaren', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (2, 'Williams', 'N');

INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (3, 'McLaren', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (3, 'Red Bull Racing', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (3, 'Scuderia Ferrari', 'Y');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (3, 'Mercedes-AMG Petronas Formula One Team', 'N');

INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (4, 'Ayrton Senna', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (4, 'Michael Schumacher', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (4, 'Lewis Hamilton', 'Y');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (4, 'Alain Prost', 'N');

INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (5, 'Ferrari', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (5, 'Cooper Car Company', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (5, 'Maserati', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (5, 'Lotus', 'N');

--Olympic Records altema
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (6, 'Michael Phelps', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (6, 'Mark Spitz', 'Y');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (6, 'Usain Bolt', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (6, 'Nadia Comaneci', 'N');

INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (7, '9.58 seconds', 'Y');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (7, '9.63 seconds', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (7, '9.69 seconds', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (7, '9.72 seconds', 'N');

INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (8, 'Michael Phelps', 'Y');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (8, 'Carl Lewis', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (8, 'Paavo Nurmi', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (8, 'Ray Ewry', 'N');

INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (9, '8.90 meters', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (9, '8.95 meters', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (9, '8.98 meters', 'Y');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (9, '9.02 meters', 'N');

INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (10, '47.02 seconds', 'Y');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (10, '47.98 seconds', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (10, '46.14 seconds', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (10, '49.52 seconds', 'N');

--Famous Athletes
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (11, 'Kobe Bryant', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (11, 'Kareem Abdul-Jabbar', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (11, 'Michael Jordan', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (11, 'LeBron James', 'Y');

INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (12, 'Pele', 'Y');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (12, 'Ronaldo', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (12, 'Neymar', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (12, 'Romario', 'N');

INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (13, 'Franz Beckenbauer', 'Y');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (13, 'Johan Cruyff', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (13, 'Michel Platini', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (13, 'Diego Maradona', 'N');

INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (14, 'Lionel Messi', 'Y');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (14, 'Cristiano Ronaldo', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (14, 'Michel Platini', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (14, 'Johan Cruyff', 'N');

INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (15, 'Sugar Ray Leonard', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (15, 'Floyd Mayweather Jr.', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (15, 'Manny Pacquiao', 'Y');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (15, 'Oscar De La Hoya', 'N');

--Sports Equipment altema
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (16, '600 grams', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (16, '650 grams', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (16, '700 grams', 'Y');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (16, '750 grams', 'N');

INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (17, 'Maple', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (17, 'Ash', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (17, 'Cedar', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (17, 'Carbon fiber', 'Y');

INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (18, '40 grams', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (18, '42 grams', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (18, '44 grams', 'Y');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (18, '46 grams', 'N');

INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (19, 'Plastic', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (19, 'Nylon', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (19, 'Goose or duck feathers', 'Y');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (19, 'Synthetic fibers', 'N');

INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (20, '8 ounces', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (20, '12 ounces', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (20, '14 ounces', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (20, '16 ounces', 'Y');

--Middle ages altema
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (21, 'Longship', 'Y');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (21, 'Keelboat', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (21, 'Galley', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (21, 'Sail boat', 'N');

INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (22, '8', 'Y');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (22, '3', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (22, '6', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (22, '9', 'N');

INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (23, 'The Dark Ages', 'Y');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (23, 'The Golden Age', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (23, 'The Renaissance', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (23, 'The Enlightenment', 'N');

INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (24, 'Smallpox', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (24, 'Influenza', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (24, 'Tuberculosis', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (24, 'The Black Death', 'Y');

INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (25, 'Genghis Khan', 'Y');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (25, 'Kublai Khan', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (25, 'Tamerlane', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (25, 'Attila the Hun', 'N');

--World War II altema
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (26, 'Germany and Japan', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (26, 'Germany and the Soviet Union', 'Y');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (26, 'Germany and Italy', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (26, 'Japan and the Soviet Union', 'N');

INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (27, 'The German invasion of Poland', 'Y');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (27, 'The Japanese attack on Pearl Harbor', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (27, 'The British declaration of war on Germany', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (27, 'The German invasion of France', 'N');

INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (28, 'Germany', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (28, 'Italy', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (28, 'Japan', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (28, 'The United States', 'Y');

INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (29, 'The Yalta Conference', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (29, 'The Tehran Conference', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (29, 'The Potsdam Conference', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (29, 'The Casablanca Conference', 'Y');

INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (30, 'The Soviet Union', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (30, 'The United States', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (30, 'Germany', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (30, 'Japan', 'N');

--Ancient Rome altema
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (31, 'Senate', 'Y');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (31, 'Congress', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (31, 'Parliament', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (31, 'House of Representatives', 'N');

INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (32, 'Via Appia', 'Y');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (32, 'Via Aurelia', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (32, 'Via Flaminia', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (32, 'Via Egnatia', 'N');

INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (33, 'The Colosseum', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (33, 'The Pantheon', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (33, 'The Aqueducts', 'Y');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (33, 'The Forum', 'N');

INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (34, 'Venus', 'Y');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (34, 'Diana', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (34, 'Athena', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (34, 'Hera', 'N');

INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (35, 'Julius Caesar', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (35, 'Augustus', 'Y');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (35, 'Trajan', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (35, 'Caligula', 'N');

--The Cold War altema
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (36, 'Disagreements over the post-World War II occupation of Europe', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (36, 'Ideological differences between communism and capitalism', 'Y');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (36, 'Tensions over the control of Middle Eastern oil resources', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (36, 'The Cuban Missile Crisis', 'N');

INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (37, 'United States', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (37, 'Soviet Union', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (37, 'Japan', 'Y');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (37, 'China', 'N');

INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (38, 'To provide military aid to European countries', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (38, 'To provide humanitarian aid to European countries', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (38, 'To rebuild the infrastructure of European countries', 'Y');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (38, 'To contain the spread of communism in Europe', 'N');

INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (39, 'Poland', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (39, 'East Germany', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (39, 'Hungary', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (39, 'Yugoslavia', 'Y');

INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (40, 'The fall of the Berlin Wall', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (40, 'The dissolution of the Soviet Union', 'Y');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (40, 'The signing of the Intermediate-Range Nuclear Forces Treaty', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (40, 'The reunification of Germany', 'N');

--Oscars altema
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (41, '1', 'Y');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (41, '2', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (41, '0', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (41, '4', 'N');

INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (42, 'Hattie McDaniel', 'Y');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (42, 'Sidney Poitier', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (42, 'Dorothy Dandridge', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (42, 'James Earl Jones', 'N');

INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (43, 'The Lord of the Rings: The Return of the King', 'Y');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (43, 'Titanic', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (43, 'Ben-Hur', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (43, 'West Side Story', 'N');

INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (44, 'Meryl Streep', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (44, 'Katharine Hepburn', 'Y');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (44, 'Jack Nicholson', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (44, 'Daniel Day-Lewis', 'N');

INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (45, 'Martin Scorsese', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (45, 'Steven Spielberg', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (45, 'Clint Eastwood', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (45, 'John Ford', 'Y');

--Movie Characters altema
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (46, 'Apollo Creed', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (46, 'Clubber Lang', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (46, 'Rocky Balboa', 'Y');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (46, 'Ivan Drago', 'N');

INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (47, 'Robert Downey Jr.', 'Y');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (47, 'Chris Hemsworth', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (47, 'Chris Evans', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (47, 'Mark Ruffalo', 'N');

INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (48, 'James Earl Jones', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (48, 'David Prowse', 'Y');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (48, 'Mark Hamill', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (48, 'Harrison Ford', 'N');

INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (49, 'Al Pacino', 'Y');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (49, 'Robert De Niro', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (49, 'Marlon Brando', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (49, 'James Caan', 'N');

INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (50, 'Anthony Hopkins', 'Y');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (50, 'Sean Connery', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (50, 'Robert De Niro', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (50, 'Al Pacino', 'N');

INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (51, 'Apple', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (51, 'Orange', 'Y');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (51, 'Banana', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (51, 'Grapefruit', 'N');

INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (52, 'Steven Spielberg', 'Y');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (52, 'George Lucas', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (52, 'James Cameron', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (52, 'Francis Ford Coppola', 'N');

INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (53, 'The Great Train Robbery', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (53, 'The Birth of a Nation', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (53, 'Roundhay Garden Scene', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (53, 'Arrival of a Train at La Ciotat', 'Y');

INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (54, 'Halloween', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (54, 'Friday the 13th', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (54, 'A Nightmare on Elm Street', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (54, 'The Shining', 'Y');

INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (55, '"Stupid is as stupid does."', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (55, '"Life is like a box of chocolates."', 'Y');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (55, '"Run, Forrest, run!"', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (55, '"I am not a smart man, but I know what love is."', 'N');

--Biology altema
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (56, 'Respiration', 'Y');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (56, 'Photosynthesis', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (56, 'Digestion', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (56, 'Circulation', 'N');

INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (57, 'Tissues', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (57, 'Organs', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (57, 'Cells', 'Y');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (57, 'Organisms', 'N');

INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (58, 'Respiration', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (58, 'Photosynthesis', 'Y');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (58, 'Digestion', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (58, 'Circulation', 'N');

INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (59, 'Transpiration', 'Y');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (59, 'Photosynthesis', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (59, 'Respiration', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (59, 'Digestion', 'N');

INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (60, 'Ecology', 'Y');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (60, 'Genetics', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (60, 'Physiology', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (60, 'Zoology', 'N');

--Chemistry altema
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (61, 'Atom', 'Y');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (61, 'Molecule', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (61, 'Compound', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (61, 'Ion', 'N');

INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (62, 'Ionic bond', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (62, 'Covalent bond', 'Y');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (62, 'Metallic bond', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (62, 'Van der Waals bond', 'N');

INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (63, 'Saltwater', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (63, 'Air', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (63, 'Milk', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (63, 'Salad', 'Y');

INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (64, 'Freezing', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (64, 'Melting', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (64, 'Evaporation', 'Y');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (64, 'Condensation', 'N');

INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (65, 'Vinegar (pH 3)', 'Y');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (65, 'Milk (pH 6)', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (65, 'Pure water (pH 7)', 'N');
INSERT INTO valasz(kerdesid, valasztartalma, helyese) VALUES (65, 'Baking soda (pH 8)', 'N');

INSERT INTO pontszam VALUES ('kati', 'Sport', 45);
INSERT INTO pontszam VALUES ('kati', 'Sport', 47);
INSERT INTO pontszam VALUES ('lehel', 'Movies', 44);
INSERT INTO pontszam VALUES ('lehel', 'History', 49);
INSERT INTO pontszam VALUES ('pista', 'History', 46);

Insert INTO felteszi  VALUES (1, 1);
Insert INTO felteszi  VALUES (2, 1);
Insert INTO felteszi  VALUES (1, 2); --
Insert INTO felteszi  VALUES (2, 2); --
Insert INTO felteszi  VALUES (1, 3);
Insert INTO felteszi  VALUES (2, 3);
Insert INTO felteszi  VALUES (5, 2); --
Insert INTO felteszi  VALUES (2, 4); --
Insert INTO felteszi  VALUES (3, 4); --
Insert INTO felteszi  VALUES (4, 4); --


INSERT INTO jatszik VALUES('kati', 1);
INSERT INTO jatszik VALUES('lehel', 2);
INSERT INTO jatszik VALUES('pista', 3);
INSERT INTO jatszik VALUES('lehel', 4);