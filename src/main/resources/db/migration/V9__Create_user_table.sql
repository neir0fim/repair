CREATE TABLE if not exists users (
    username varchar(50) not null primary key ,
    password varchar not null ,
    enabled boolean not null ,
    unit_id int not null ,

    FOREIGN KEY (unit_id) REFERENCES units (unit_id) on delete cascade
);