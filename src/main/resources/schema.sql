DROP TABLE IF EXISTS SITE;

CREATE TABLE SITE (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  address VARCHAR(250) NOT NULL,
  city VARCHAR(250) DEFAULT NULL,
  state VARCHAR(250) DEFAULT NULL,
  zipcode VARCHAR(250) DEFAULT NULL
);

DROP TABLE IF EXISTS USE_TYPE;

CREATE TABLE USE_TYPE (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL
);

DROP TABLE IF EXISTS SITE_USE;

CREATE TABLE SITE_USE (
    id INT AUTO_INCREMENT  PRIMARY KEY,
    site_id INT,
    FOREIGN KEY (site_id) REFERENCES SITE(id),
    description VARCHAR(250) NOT NULL,
    size INT,
    use_type_id INT,
    FOREIGN KEY (use_type_id) REFERENCES USE_TYPE(id)
);
