DROP TABLE if exists article;

CREATE TABLE IF NOT EXISTS article (
    unit_id int not null,
    type varchar not null ,
    article varchar,
    id numeric
);

INSERT INTO article (unit_id, type, article, id) values (1, 'RTM', '1.3.4.2.4.1', 1);