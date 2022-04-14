CREATE TABLE IF NOT EXISTS Article (

    unit_id int not null ,
    FOREIGN KEY (unit_id) REFERENCES units (unit_id),
    type varchar not null ,
    article varchar UNIQUE NOT NULL PRIMARY KEY
);