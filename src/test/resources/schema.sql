DROP TABLE if exists article;

CREATE TABLE IF NOT EXISTS article (
                                       unit_id int not null,
                                       type varchar not null ,
                                       article varchar,
                                       id number
);

