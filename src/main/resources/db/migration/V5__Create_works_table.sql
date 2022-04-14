CREATE TABLE IF NOT EXISTS Works (
    id serial UNIQUE PRIMARY KEY ,
    cod int NOT NULL ,
    name varchar NOT NULL ,
    codDk varchar NOT NULL ,
    UOM varchar NOT NULL ,
    value numeric NOT NULL,
    amount numeric NOT NULL ,
    repair_id serial NOT NULL ,
    FOREIGN KEY (repair_id) REFERENCES repair (repair_id) on delete cascade
)