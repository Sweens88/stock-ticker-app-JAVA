/** create the stocks database */

DROP DATABASE stocks;
CREATE DATABASE stocks;
USE stocks;

DROP TABLE IF EXISTS stocks.quotes CASCADE;
CREATE TABLE stocks.quotes(
   id INT NOT NULL AUTO_INCREMENT,
   symbol VARCHAR(4) NOT NULL,
   time DATETIME NOT NULL,
   price DECIMAL(8, 2) NOT NULL,
   PRIMARY KEY ( id )
);

DROP TABLE IF EXISTS stocks.person CASCADE;
CREATE TABLE stocks.person (
   id INT NOT NULL AUTO_INCREMENT,
   first_name VARCHAR(20) NOT NULL,
   last_name VARCHAR(25) NOT NULL,
   symbol VARCHAR(4) NOT NULL,
   PRIMARY KEY ( id )
);

DROP TABLE IF EXISTS stocks.stock CASCADE;
CREATE TABLE stocks.stock (
   id INT NOT NULL AUTO_INCREMENT,
   symbol VARCHAR(4) NOT NULL,
   PRIMARY KEY ( id )
);

INSERT INTO stocks.person (first_name, last_name, symbol) VALUES ('Chris', 'Weakley', 'AAPL');

INSERT INTO stocks.stock (symbol) VALUES ('GOOG');
INSERT INTO stocks.stock (symbol) VALUES ('AAPL');
INSERT INTO stocks.stock (symbol) VALUES ('TSLA');

INSERT INTO stocks.quotes (symbol,time,price) VALUES ('GOOG','2004-08-19 09:00:00','88.00');
INSERT INTO stocks.quotes (symbol,time,price) VALUES ('GOOG','2004-08-19 12:45:00','85.00');
INSERT INTO stocks.quotes (symbol,time,price) VALUES ('GOOG','2004-08-19 16:30:00','86.00');
INSERT INTO stocks.quotes (symbol,time,price) VALUES ('GOOG','2017-02-03 09:00:00','527.35');
INSERT INTO stocks.quotes (symbol,time,price) VALUES ('AAPL','2014-03-01 12:45:00','118.27');
INSERT INTO stocks.quotes (symbol,time,price) VALUES ('AMZN','2015-02-03 12:45:00','363.21');
INSERT INTO stocks.quotes (symbol,time,price) VALUES ('MSFT','2017-05-23 12:45:00','43.00');
INSERT INTO stocks.quotes (symbol,time,price) VALUES ('TSLA','2015-09-14 12:45:00','235.39');
INSERT INTO stocks.quotes (symbol,time,price) VALUES ('FB','2000-12-23 12:45:00','43.84');
INSERT INTO stocks.quotes (symbol,time,price) VALUES ('XOM','2015-04-08 12:45:00','81.44');
INSERT INTO stocks.quotes (symbol,time,price) VALUES ('GOOG','2004-08-20 09:00:00','547.64');
INSERT INTO stocks.quotes (symbol,time,price) VALUES ('GOOG','2004-08-20 12:45:00','548.87');
INSERT INTO stocks.quotes (symbol,time,price) VALUES ('GOOG','2004-08-20 16:30:00','549.34');
INSERT INTO stocks.quotes (symbol,time,price) VALUES ('GOOG','2004-08-21 09:00:00','527.35');
INSERT INTO stocks.quotes (symbol,time,price) VALUES ('GOOG','2004-08-21 12:45:00','528.35');
INSERT INTO stocks.quotes (symbol,time,price) VALUES ('GOOG','2004-08-21 16:30:00','529.35');
INSERT INTO stocks.quotes (symbol,time,price) VALUES ('GOOG','2004-08-22 09:00:00','529.00');
INSERT INTO stocks.quotes (symbol,time,price) VALUES ('GOOG','2004-08-22 12:45:00','530.60');
INSERT INTO stocks.quotes (symbol,time,price) VALUES ('GOOG','2004-08-22 16:30:00','531.50');
INSERT INTO stocks.quotes (symbol,time,price) VALUES ('GOOG','2004-08-23 09:00:00','527.38');
INSERT INTO stocks.quotes (symbol,time,price) VALUES ('GOOG','2004-08-23 12:45:00','526.95');
INSERT INTO stocks.quotes (symbol,time,price) VALUES ('GOOG','2004-08-23 16:30:00','528.55');
INSERT INTO stocks.quotes (symbol,time,price) VALUES ('AAPL','2000-01-07 09:00:00','119.27');
INSERT INTO stocks.quotes (symbol,time,price) VALUES ('AAPL','2000-01-07 10:00:00','120.27');
INSERT INTO stocks.quotes (symbol,time,price) VALUES ('AAPL','2000-01-07 11:00:00','121.27');
INSERT INTO stocks.quotes (symbol,time,price) VALUES ('AAPL','2000-01-07 12:00:00','124.27');
INSERT INTO stocks.quotes (symbol,time,price) VALUES ('AAPL','2000-01-07 12:45:00','123.27');
INSERT INTO stocks.quotes (symbol,time,price) VALUES ('AAPL','2000-01-07 13:00:00','125.27');
INSERT INTO stocks.quotes (symbol,time,price) VALUES ('AAPL','2000-01-07 14:00:00','127.27');
INSERT INTO stocks.quotes (symbol,time,price) VALUES ('AAPL','2000-01-07 15:00:00','128.27');
INSERT INTO stocks.quotes (symbol,time,price) VALUES ('AAPL','2000-01-07 16:00:00','125.27');
INSERT INTO stocks.quotes (symbol,time,price) VALUES ('AAPL','2000-01-07 16:30:00','126.27');
