DROP TABLE if exists units;

CREATE table if not exists units (
                                     unit_id bigint auto_increment ,
                                     type varchar
);

DROP TABLE if exists article;

CREATE TABLE IF NOT EXISTS article (
                                       unit_id int not null,
                                       type varchar not null ,
                                       article varchar,
                                       id bigint auto_increment
);

DROP TABLE if exists material;

CREATE TABLE IF NOT EXISTS material (
cod INTEGER PRIMARY KEY ,
name varchar  NOT NULL ,
codDk varchar NOT NULL ,
UOM varchar NOT NULL ,
value1 DOUBLE NOT NULL
);

DROP TABLE if exists repair;

CREATE TABLE IF NOT EXISTS repair (
repair_id number PRIMARY KEY ,
description varchar  not null ,
article varchar not null ,
type varchar NOT NULL
);

DROP TABLE if exists works;

CREATE TABLE IF NOT EXISTS works (
id number ,
cod int NOT NULL ,
name varchar NOT NULL ,
codDk varchar NOT NULL ,
UOM varchar NOT NULL ,
valuesOne numeric NOT NULL,
amount numeric NOT NULL ,
repair_id number NOT NULL
);
