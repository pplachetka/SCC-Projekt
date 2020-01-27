SET CHARSET UTF8;
USE scc;

DROP TABLE IF EXISTS USER;

CREATE TABLE scc.USER
(   
UserID INT NOT NULL AUTO_INCREMENT
,Name VARCHAR(100) NOT NULL
,FamilyName VARCHAR(100) NOT NULL
,Password VARCHAR(100) NOT NULL
,isAdmin tinyint NOT NULL
, PRIMARY KEY (UserId)

);

CREATE TABLE scc.MENUITEM (
    MenuItemID INT NOT NULL AUTO_INCREMENT
    , Description VARCHAR(1000) NOT NULL
    , Description_short VARCHAR(100) NULL
    , Costs numeric(15,2)
    , PRIMARY KEY (MenuItemID)
);

CREATE TABLE scc.USER_TOKEN (
    User_TokenID INT NOT NULL AUTO_INCREMENT
    , UserID INT
    , Token VARCHAR (30)
    , ValidFrom timestamp
    , PRIMARY KEY (User_TokenID)
);

CREATE TABLE scc.MENUITEMSCHEDULE(
    menuItemScheduleID INT PRIMARY KEY AUTO_INCREMENT
    , date date
    , position tinyint
    , menuItemID int NOT NULL
    , UNIQUE (date, position)
);

CREATE TABLE scc.CUSTOMER_ORDER(
    Customer_OrderID INT PRIMARY KEY AUTO_INCREMENT
    , UserID INT
    , menuItemScheduleID INT
    , date date
    , UNIQUE (UserID, date)
);
-- ###########################################################################################
-- ############################ TESTDATEN ####################################################
-- ###########################################################################################


INSERT INTO USER (Name, FamilyName, Password, isAdmin)
VALUES
('admin', 'admin', MD5('admin'), '1'),('user','username', MD5('user'),'0');

INSERT INTO MENUITEM (Description, Costs)
VALUES 
('Rindergeschnetzeltes mit Paprika dazu Apfelrotkohl und Spätzle','2.63')
,('Brokkoli-Kichererbsen-Pfanne mit Vollkornbrot ','3.86')
,('Bio-Vollkornspirelli mit Rinderbolognese und Reibekäse','4.74')
,('Gebratene Putenbratwurst mit Currysoße und Kartoffelspalten','2.21')
,('Gekochte Eier in Senfsoße und Petersilienkartoffeln','3.83')
,('Canneloni mit Frischkäse gefüllt und Tomate gratiniert','4.47')
,('Köttbular in Paprikacremesoße mit Bio-Vollkornnudeln','2.8')
,('Kartoffelgnocchipfanne mit Kaisergemüse und Tomatensoße','3.56')
,('Kräuterspätzle mit Käsesoße','4.97')
,('Gedünstetes Alaska Seehechtfilet in Petersiliencremesoße mit Balkangemüse und Salzkartoffeln','2.84')
,('Milchreis mit Apfelmus und Zucker/Zimt','3.54')
,('Gebratene Mie-Nudeln mit Putenfleisch','4.06')
,('Mini Frikadellen vom Rind in Bratensoße mit Erbsengemüse und BIO-Vollkornnudeln','2.93')
,('Mais-Lauch-Rösti mit Kräutersoße, Kürbis-Kartoffelbrei und Chinakohlsalat','3.46')
,('Makkaroni dazu Champignonragout mit Kräutern verfeinert','4.86')
,('Geflügel-Bratwurst mit Bratensoße, Bayrisch Kraut und Kartoffelbrei','2.98')
,('Kartoffelpuffer mit Apfelkompott','3.75')
,('Bio-Penne mit geschwenktem Brokkoli dazu Mango-Dip','4.24')
,('Putensteak auf Gemüse in Currycremesoße und Vollkornreis','2.76')
,('Möhren-Kartoffel-Eintopf mit Vollkornbrot','3.93')
,('Nudelpfanne mit mediterranem Gemüse','4.07')
,('Gedünstete Hähnchenbrust in Geflügelsoße mit Erbsengemüse und Salzkartoffeln','2.63')
,('Rucola-Süßkartoffel-Schnitte mit Letschogemüse','3.75')
,('Bio-Spaghetti "Carbonara" mit Putenschinken dazu Reibekäse','4.41')

