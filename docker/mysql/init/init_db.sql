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
('admin', 'admin', 'admin', '1'),('user','username', 'userfamily','0');

INSERT INTO MENUITEM (Description, Costs)
VALUES ('Huhn','3.5')
,('Schwein','13.5')
,('Rind','3.54');
