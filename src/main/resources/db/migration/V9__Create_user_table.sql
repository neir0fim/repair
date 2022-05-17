CREATE TABLE if not exists users (
    username varchar(50) not null primary key ,
    password varchar not null ,
    enabled boolean not null ,
    type varchar not null ,

    FOREIGN KEY (type) REFERENCES units (type) on delete cascade,
    FOREIGN KEY (type) REFERENCES units (type) on update cascade

);