CREATE TABLE writingGroups
  (
  groupName  VARCHAR(20)     NOT NULL,
  headWriter VARCHAR(20) NOT NULL,
  yearFormed CHAR(4) NOT NULL,
  subject    VARCHAR(20)         ,
  CONSTRAINT pk_writingGroups PRIMARY KEY (groupName)
  );

CREATE TABLE publishers
  (
  publisherName    VARCHAR(20) NOT NULL,
  publisherAddress VARCHAR(15) NOT NULL,
  publisherPhone   VARCHAR(12)             ,
  publisherEmail   VARCHAR(20) NOT NULL,
  CONSTRAINT pk_publishers PRIMARY KEY (publisherName)
  );

CREATE TABLE books
  (
  bookTitle     VARCHAR(20)     NOT NULL,
  yearPublished VARCHAR(4) NOT NULL,
  numberPages   VARCHAR(20) NOT NULL,
  groupName     VARCHAR(20) NOT NULL,
  publisherName VARCHAR(20) NOT NULL,
  CONSTRAINT pk_books PRIMARY KEY (bookTitle, groupName)
  );

ALTER TABLE books
          ADD CONSTRAINT books_writingGroups_fk
          FOREIGN KEY (groupName)
          REFERENCES writingGroups (groupName);

ALTER TABLE books
          ADD CONSTRAINT books_publishers_fk
          FOREIGN KEY (publisherName)
          REFERENCES publishers (publisherName);

INSERT INTO writingGroups VALUES('Writers Annonymous','Elon Musk','2018','Fiction');
INSERT INTO writingGroups VALUES('Awesome Writers','Steve Jobs','2013','Mystery');
INSERT INTO writingGroups VALUES('Resounding Maybe','John Doe','2000','Science Fiction');

INSERT INTO publishers VALUES('Publishers R Us', '123 Writers St', '555-555-5555', 'pubRus@writing.com');
INSERT INTO publishers VALUES('We Publish 4 U', '777 Lucky Lane', '777-777-7777', 'pub4hire@writing.com');
INSERT INTO publishers VALUES('Publisher For You', '321 State St', '012-345-6789', 'publishers@gmail.com');

INSERT INTO books VALUES('To Mars and Back', '2018', '1200', 'Writers Annonymous', 'Publishers R Us');
INSERT INTO books VALUES('Electric Cars', '2018', '1000', 'Writers Annonymous', 'Publishers R Us');
INSERT INTO books VALUES('Star Trek Wars', '2005', '20000', 'Resounding Maybe', 'Publisher For You');

SELECT * FROM writingGroups;
SELECT * FROM publishers;
SELECT * FROM books;
