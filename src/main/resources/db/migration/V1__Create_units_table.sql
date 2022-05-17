CREATE TABLE IF NOT EXISTS Units (

    unit_id serial,
    type varchar UNIQUE NOT NULL ,
    PRIMARY KEY (unit_id)

);
