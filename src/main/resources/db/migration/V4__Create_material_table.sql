CREATE TABLE IF NOT EXISTS Material (
    cod int UNIQUE NOT NULL PRIMARY KEY ,
    name varchar UNIQUE NOT NULL ,
    codDk varchar NOT NULL ,
    UOM varchar NOT NULL ,
    value numeric NOT NULL
)