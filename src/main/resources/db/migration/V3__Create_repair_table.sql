CREATE TABLE IF NOT EXISTS Repair (

    repair_id serial PRIMARY KEY ,
    description varchar UNIQUE not null ,
    article varchar not null ,
    type varchar NOT NULL ,


    FOREIGN KEY (article) REFERENCES article (article)

)