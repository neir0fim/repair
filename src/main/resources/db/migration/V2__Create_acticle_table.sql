CREATE TABLE IF NOT EXISTS Article (

    unit_id int not null,
    FOREIGN KEY (unit_id) REFERENCES units (unit_id) on delete cascade  ,
    FOREIGN KEY (unit_id) REFERENCES units (unit_id) on update cascade ,
    type varchar not null ,
    article varchar UNIQUE NOT NULL PRIMARY KEY,
    id serial
);