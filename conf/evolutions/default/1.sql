# --- !Ups

CREATE TABLE Products (
 "prodId" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
 "tytul" VARCHAR  NOT NULL,
 "opis" TEXT NOT NULL,
 "imgUrl" VARCHAR NOT NULL,
 "cena" DOUBLE NOT NULL,
 "kategoriaId" INTEGER NOT NULL
);

CREATE TABLE Users (
  "id"          INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  "userID"      VARCHAR                           NOT NULL,
  "providerID"  VARCHAR                           NOT NULL,
  "providerKey" VARCHAR                           NOT NULL,
  "firstName"   VARCHAR,
  "lastName"    VARCHAR,
  "fullName"    VARCHAR,
  "email"       VARCHAR,
  "avatarURL"   VARCHAR
);

INSERT INTO Products (prodId, tytul, opis, imgUrl, cena, kategoriaId) VALUES (
1,'Zegarek #1','Opis produktu 1.','m1.jpg',2.5,2);

INSERT INTO Products (prodId, tytul, opis, imgUrl, cena, kategoriaId) VALUES (
2,'Zegarek #2','Super opis produktu 7','m2.jpg',21.37,1);

INSERT INTO Products (prodId, tytul, opis, imgUrl, cena, kategoriaId) VALUES (
3,'Zegarek #3','Opis produktu 9','m3.jpg',7.99,1);

INSERT INTO Products (prodId, tytul, opis, imgUrl, cena, kategoriaId) VALUES (
4,'Zegarek #4','Opis produktu 10','m4.jpg',1.5,4);

INSERT INTO Products (prodId, tytul, opis, imgUrl, cena, kategoriaId) VALUES (
6,'Zegarek #5','Opis produktu 17','m5.jpg',10.49,1);

INSERT INTO Products (prodId, tytul, opis, imgUrl, cena, kategoriaId) VALUES (
7,'Zegarek #6','opis 18','m6.jpg',19.99,1);

INSERT INTO Products (prodId, tytul, opis, imgUrl, cena, kategoriaId) VALUES (
8,'Zegarek #10','opis 19','m10.jpg',1999.99,2);

INSERT INTO Products (prodId, tytul, opis, imgUrl, cena, kategoriaId) VALUES (
9,'Zegarek #11','opis 20','m11.jpg',2099.99,2);

INSERT INTO Products (prodId, tytul, opis, imgUrl, cena, kategoriaId) VALUES (
10,'Zegarek #12','opis','m12.jpg',3099.99,2);

INSERT INTO Products (prodId, tytul, opis, imgUrl, cena, kategoriaId) VALUES (
11,'Zegarek #13','opis','m13.jpg',999.99,3);

INSERT INTO Products (prodId, tytul, opis, imgUrl, cena, kategoriaId) VALUES (
12,'Zegarek #14','opis','m14.jpg',899.99,3);

INSERT INTO Products (prodId, tytul, opis, imgUrl, cena, kategoriaId) VALUES (
13,'Zegarek #15','opis','m15.jpg',699.99,3);

INSERT INTO Products (prodId, tytul, opis, imgUrl, cena, kategoriaId) VALUES (
14,'Zegarek #16','opis','m16.jpg',399.99,1);

INSERT INTO Products (prodId, tytul, opis, imgUrl, cena, kategoriaId) VALUES (
15,'Zegarek #17','opis','m17.jpg',499.99,3);

INSERT INTO Products (prodId, tytul, opis, imgUrl, cena, kategoriaId) VALUES (
16,'Zegarek #18','opis','m18.jpg',99.99,4);

INSERT INTO Products (prodId, tytul, opis, imgUrl, cena, kategoriaId) VALUES (
17,'Zegarek #19','opis','m19.jpg',199.99,4);

INSERT INTO Products (prodId, tytul, opis, imgUrl, cena, kategoriaId) VALUES (
19,'Zegarek 50','opis','m15.jpg',50.99,3);


# --- !Downs

DROP TABLE Products;
DROP TABLE Users;
