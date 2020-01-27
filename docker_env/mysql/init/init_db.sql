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

INSERT INTO MENUITEM (Description_short,Description, Costs)
VALUES 
('Rindergeschnetzeltes','Rindergeschnetzeltes mit Paprika dazu Apfelrotkohl und Spätzle','2.63')
,('Brokkoli-Kichererbsen-Pfanne','Brokkoli-Kichererbsen-Pfanne mit Vollkornbrot ','3.86')
,('Vollkornspirelli (Rind)','Bio-Vollkornspirelli mit Rinderbolognese und Reibekäse','4.74')
,('Putenbratwurst mit Currysoße','Gebratene Putenbratwurst mit Currysoße und Kartoffelspalten','2.21')
,('Eier in Senfsoße','Gekochte Eier in Senfsoße und Petersilienkartoffeln','3.83')
,('Canneloni (Frischkäse)','Canneloni mit Frischkäse gefüllt und Tomate gratiniert','4.47')
,('Köttbular','Köttbular in Paprikacremesoße mit Bio-Vollkornnudeln','2.8')
,('Kartoffelgnocchipfanne','Kartoffelgnocchipfanne mit Kaisergemüse und Tomatensoße','3.56')
,('Kräuterspätzle (Käsesoße)','Kräuterspätzle mit Käsesoße','4.97')
,('Alaska Seehechtfilet','Gedünstetes Alaska Seehechtfilet in Petersiliencremesoße mit Balkangemüse und Salzkartoffeln','2.84')
,('Milchreis','Milchreis mit Apfelmus und Zucker/Zimt','3.54')
,('Mie-Nudeln (Putenfleisch)','Gebratene Mie-Nudeln mit Putenfleisch','4.06')
,('Mini Frikadellen','Mini Frikadellen vom Rind in Bratensoße mit Erbsengemüse und BIO-Vollkornnudeln','2.93')
,('Mais-Lauch-Rösti','Mais-Lauch-Rösti mit Kräutersoße, Kürbis-Kartoffelbrei und Chinakohlsalat','3.46')
,('Makkaroni (Champignonragout)','Makkaroni dazu Champignonragout mit Kräutern verfeinert','4.86')
,('Geflügel-Bratwurst','Geflügel-Bratwurst mit Bratensoße, Bayrisch Kraut und Kartoffelbrei','2.98')
,('Kartoffelpuffer (Apfelkompott)','Kartoffelpuffer mit Apfelkompott','3.75')
,('Bio-Penne (Brokkoli)','Bio-Penne mit geschwenktem Brokkoli dazu Mango-Dip','4.24')
,('Putensteak (Currycremesoße)','Putensteak auf Gemüse in Currycremesoße und Vollkornreis','2.76')
,('Möhren-Kartoffel-Eintopf','Möhren-Kartoffel-Eintopf mit Vollkornbrot','3.93')
,('Nudelpfanne','Nudelpfanne mit mediterranem Gemüse','4.07')
,('Gedünstete Hähnchenbrust','Gedünstete Hähnchenbrust in Geflügelsoße mit Erbsengemüse und Salzkartoffeln','2.63')
,('Rucola-Süßkartoffel-Schnitte','Rucola-Süßkartoffel-Schnitte mit Letschogemüse','3.75')
,('Spaghetti "Carbonara"','Bio-Spaghetti "Carbonara" mit Putenschinken dazu Reibekäse','4.41')


