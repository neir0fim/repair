CREATE TABLE IF NOT EXISTS Units (

    unit_id int UNIQUE ,
    type varchar UNIQUE NOT NULL ,
    PRIMARY KEY (unit_id)

);
