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
SELECT * FROM writingGroups