--------------------------------------------------------
--  File created - Wednesday-March-15-2023   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table ALTEMA
--------------------------------------------------------

  CREATE TABLE "C##SX8W3U"."ALTEMA" 
   (	"ALTEMANEV" VARCHAR2(30 BYTE), 
	"LEIRAS" VARCHAR2(120 BYTE), 
	"TEMANEV" VARCHAR2(20 BYTE)
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table FELTESZI
--------------------------------------------------------

  CREATE TABLE "C##SX8W3U"."FELTESZI" 
   (	"KERDESID" NUMBER, 
	"QUIZID" NUMBER
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table JATEKOS
--------------------------------------------------------

  CREATE TABLE "C##SX8W3U"."JATEKOS" 
   (	"FELHASZNALONEV" VARCHAR2(30 BYTE), 
	"JELSZO" VARCHAR2(30 BYTE), 
	"EMAIL" VARCHAR2(50 BYTE), 
	"RANGSORPONTSZAM" NUMBER, 
	"TEMANEV" VARCHAR2(20 BYTE)
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table JATSZIK
--------------------------------------------------------

  CREATE TABLE "C##SX8W3U"."JATSZIK" 
   (	"FELHASZNALONEV" VARCHAR2(30 BYTE), 
	"QUIZID" NUMBER
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table KERDES
--------------------------------------------------------

  CREATE TABLE "C##SX8W3U"."KERDES" 
   (	"KERDESID" NUMBER GENERATED ALWAYS AS IDENTITY MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE , 
	"KERDESTARTALMA" VARCHAR2(300 BYTE), 
	"ALTEMANEV" VARCHAR2(20 BYTE)
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table QUIZ
--------------------------------------------------------

  CREATE TABLE "C##SX8W3U"."QUIZ" 
   (	"QUIZID" NUMBER GENERATED ALWAYS AS IDENTITY MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE , 
	"TEMANEV" VARCHAR2(20 BYTE)
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table TARTOZIK
--------------------------------------------------------

  CREATE TABLE "C##SX8W3U"."TARTOZIK" 
   (	"KERDESID" NUMBER, 
	"VALASZID" NUMBER
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table TEMA
--------------------------------------------------------

  CREATE TABLE "C##SX8W3U"."TEMA" 
   (	"TEMANEV" VARCHAR2(20 BYTE)
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table VALASZ
--------------------------------------------------------

  CREATE TABLE "C##SX8W3U"."VALASZ" 
   (	"VALASZID" NUMBER GENERATED ALWAYS AS IDENTITY MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE , 
	"VALASZTARTALMA" VARCHAR2(200 BYTE), 
	"HELYESE" NUMBER(*,0)
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
REM INSERTING into C##SX8W3U.ALTEMA
SET DEFINE OFF;
REM INSERTING into C##SX8W3U.FELTESZI
SET DEFINE OFF;
REM INSERTING into C##SX8W3U.JATEKOS
SET DEFINE OFF;
REM INSERTING into C##SX8W3U.JATSZIK
SET DEFINE OFF;
REM INSERTING into C##SX8W3U.KERDES
SET DEFINE OFF;
REM INSERTING into C##SX8W3U.QUIZ
SET DEFINE OFF;
REM INSERTING into C##SX8W3U.TARTOZIK
SET DEFINE OFF;
REM INSERTING into C##SX8W3U.TEMA
SET DEFINE OFF;
REM INSERTING into C##SX8W3U.VALASZ
SET DEFINE OFF;
--------------------------------------------------------
--  DDL for Index ALTEMA_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "C##SX8W3U"."ALTEMA_PK" ON "C##SX8W3U"."ALTEMA" ("ALTEMANEV") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index FELTESZI_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "C##SX8W3U"."FELTESZI_PK" ON "C##SX8W3U"."FELTESZI" ("KERDESID", "QUIZID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index JATEKOS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "C##SX8W3U"."JATEKOS_PK" ON "C##SX8W3U"."JATEKOS" ("FELHASZNALONEV") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index JATSZIK_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "C##SX8W3U"."JATSZIK_PK" ON "C##SX8W3U"."JATSZIK" ("FELHASZNALONEV", "QUIZID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index KERDES_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "C##SX8W3U"."KERDES_PK" ON "C##SX8W3U"."KERDES" ("KERDESID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index QUIZ_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "C##SX8W3U"."QUIZ_PK" ON "C##SX8W3U"."QUIZ" ("QUIZID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index TARTOZIK_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "C##SX8W3U"."TARTOZIK_PK" ON "C##SX8W3U"."TARTOZIK" ("KERDESID", "VALASZID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index TEMA_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "C##SX8W3U"."TEMA_PK" ON "C##SX8W3U"."TEMA" ("TEMANEV") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index VALASZ_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "C##SX8W3U"."VALASZ_PK" ON "C##SX8W3U"."VALASZ" ("VALASZID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  Constraints for Table ALTEMA
--------------------------------------------------------

  ALTER TABLE "C##SX8W3U"."ALTEMA" ADD CONSTRAINT "ALTEMA_PK" PRIMARY KEY ("ALTEMANEV")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "C##SX8W3U"."ALTEMA" MODIFY ("ALTEMANEV" NOT NULL ENABLE);
  ALTER TABLE "C##SX8W3U"."ALTEMA" MODIFY ("TEMANEV" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table FELTESZI
--------------------------------------------------------

  ALTER TABLE "C##SX8W3U"."FELTESZI" ADD CONSTRAINT "FELTESZI_PK" PRIMARY KEY ("KERDESID", "QUIZID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "C##SX8W3U"."FELTESZI" MODIFY ("KERDESID" NOT NULL ENABLE);
  ALTER TABLE "C##SX8W3U"."FELTESZI" MODIFY ("QUIZID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table JATEKOS
--------------------------------------------------------

  ALTER TABLE "C##SX8W3U"."JATEKOS" ADD CONSTRAINT "JATEKOS_PK" PRIMARY KEY ("FELHASZNALONEV")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "C##SX8W3U"."JATEKOS" MODIFY ("FELHASZNALONEV" NOT NULL ENABLE);
  ALTER TABLE "C##SX8W3U"."JATEKOS" MODIFY ("JELSZO" NOT NULL ENABLE);
  ALTER TABLE "C##SX8W3U"."JATEKOS" MODIFY ("EMAIL" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table JATSZIK
--------------------------------------------------------

  ALTER TABLE "C##SX8W3U"."JATSZIK" ADD CONSTRAINT "JATSZIK_PK" PRIMARY KEY ("FELHASZNALONEV", "QUIZID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "C##SX8W3U"."JATSZIK" MODIFY ("FELHASZNALONEV" NOT NULL ENABLE);
  ALTER TABLE "C##SX8W3U"."JATSZIK" MODIFY ("QUIZID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table KERDES
--------------------------------------------------------

  ALTER TABLE "C##SX8W3U"."KERDES" ADD CONSTRAINT "KERDES_PK" PRIMARY KEY ("KERDESID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "C##SX8W3U"."KERDES" MODIFY ("KERDESID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table QUIZ
--------------------------------------------------------

  ALTER TABLE "C##SX8W3U"."QUIZ" ADD CONSTRAINT "QUIZ_PK" PRIMARY KEY ("QUIZID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "C##SX8W3U"."QUIZ" MODIFY ("QUIZID" NOT NULL ENABLE);
  ALTER TABLE "C##SX8W3U"."QUIZ" MODIFY ("TEMANEV" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table TARTOZIK
--------------------------------------------------------

  ALTER TABLE "C##SX8W3U"."TARTOZIK" MODIFY ("KERDESID" NOT NULL ENABLE);
  ALTER TABLE "C##SX8W3U"."TARTOZIK" MODIFY ("VALASZID" NOT NULL ENABLE);
  ALTER TABLE "C##SX8W3U"."TARTOZIK" ADD CONSTRAINT "TARTOZIK_PK" PRIMARY KEY ("KERDESID", "VALASZID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table TEMA
--------------------------------------------------------

  ALTER TABLE "C##SX8W3U"."TEMA" MODIFY ("TEMANEV" NOT NULL ENABLE);
  ALTER TABLE "C##SX8W3U"."TEMA" ADD CONSTRAINT "TEMA_PK" PRIMARY KEY ("TEMANEV")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table VALASZ
--------------------------------------------------------

  ALTER TABLE "C##SX8W3U"."VALASZ" MODIFY ("VALASZID" NOT NULL ENABLE);
  ALTER TABLE "C##SX8W3U"."VALASZ" ADD CONSTRAINT "VALASZ_PK" PRIMARY KEY ("VALASZID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table ALTEMA
--------------------------------------------------------

  ALTER TABLE "C##SX8W3U"."ALTEMA" ADD CONSTRAINT "ALTEMA_FK1" FOREIGN KEY ("TEMANEV")
	  REFERENCES "C##SX8W3U"."TEMA" ("TEMANEV") ON DELETE CASCADE ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table FELTESZI
--------------------------------------------------------

  ALTER TABLE "C##SX8W3U"."FELTESZI" ADD CONSTRAINT "FELTESZI_FK1" FOREIGN KEY ("KERDESID")
	  REFERENCES "C##SX8W3U"."KERDES" ("KERDESID") ON DELETE CASCADE ENABLE;
  ALTER TABLE "C##SX8W3U"."FELTESZI" ADD CONSTRAINT "FELTESZI_FK2" FOREIGN KEY ("QUIZID")
	  REFERENCES "C##SX8W3U"."QUIZ" ("QUIZID") ON DELETE CASCADE ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table JATEKOS
--------------------------------------------------------

  ALTER TABLE "C##SX8W3U"."JATEKOS" ADD CONSTRAINT "JATEKOS_FK1" FOREIGN KEY ("TEMANEV")
	  REFERENCES "C##SX8W3U"."TEMA" ("TEMANEV") ON DELETE CASCADE ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table JATSZIK
--------------------------------------------------------

  ALTER TABLE "C##SX8W3U"."JATSZIK" ADD CONSTRAINT "JATSZIK_FK1" FOREIGN KEY ("FELHASZNALONEV")
	  REFERENCES "C##SX8W3U"."JATEKOS" ("FELHASZNALONEV") ON DELETE CASCADE ENABLE;
  ALTER TABLE "C##SX8W3U"."JATSZIK" ADD CONSTRAINT "JATSZIK_FK2" FOREIGN KEY ("QUIZID")
	  REFERENCES "C##SX8W3U"."QUIZ" ("QUIZID") ON DELETE CASCADE ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table KERDES
--------------------------------------------------------

  ALTER TABLE "C##SX8W3U"."KERDES" ADD CONSTRAINT "KERDES_FK1" FOREIGN KEY ("ALTEMANEV")
	  REFERENCES "C##SX8W3U"."ALTEMA" ("ALTEMANEV") ON DELETE CASCADE ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table QUIZ
--------------------------------------------------------

  ALTER TABLE "C##SX8W3U"."QUIZ" ADD CONSTRAINT "QUIZ_FK1" FOREIGN KEY ("TEMANEV")
	  REFERENCES "C##SX8W3U"."TEMA" ("TEMANEV") ON DELETE CASCADE ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table TARTOZIK
--------------------------------------------------------

  ALTER TABLE "C##SX8W3U"."TARTOZIK" ADD CONSTRAINT "TARTOZIK_FK1" FOREIGN KEY ("KERDESID")
	  REFERENCES "C##SX8W3U"."KERDES" ("KERDESID") ON DELETE CASCADE ENABLE;
  ALTER TABLE "C##SX8W3U"."TARTOZIK" ADD CONSTRAINT "TARTOZIK_FK2" FOREIGN KEY ("VALASZID")
	  REFERENCES "C##SX8W3U"."VALASZ" ("VALASZID") ON DELETE CASCADE ENABLE;
