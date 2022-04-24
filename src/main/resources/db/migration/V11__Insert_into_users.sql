INSERT INTO users (username, password, enabled, unit_id)
VALUES (
        'Sergej', '$2a$05$79N.4Yi9SL6fW6muJCNIkeVViNu19ksATLiRVt/U7XnNTPPpL7quC', true, 5
       );

INSERT INTO authorities (username, authority)
VALUES (
        'Sergej', 'ROLE_ADMIN'
       );

INSERT INTO authorities (username, authority)
VALUES (
           'Sergej', 'article:all'
       );

INSERT INTO authorities (username, authority)
VALUES (
           'Sergej', 'person:all'
       );

INSERT INTO authorities (username, authority)
VALUES (
           'Sergej', 'units:all'
       );


INSERT INTO users (username, password, enabled, unit_id)
VALUES (
           'Michail', '$2a$05$OtfOySt8sAhXfJBUMS8qmelh3xPXUoTxQB5hYWcYsQJ.zaK.0Iwh.', true, 1
       );

INSERT INTO authorities (username, authority)
VALUES (
           'Michail', 'ROLE_WORKER'
       );


INSERT INTO authorities (username, authority)
VALUES (
           'Michail', 'person:get'
       );
INSERT INTO authorities (username, authority)
VALUES (
           'Michail', 'article:get'
       );
INSERT INTO authorities (username, authority)
VALUES (
           'Michail', 'units:get'
       );

INSERT INTO authorities (username, authority)
VALUES (
           'Michail', 'repair:all'
       );



INSERT INTO users (username, password, enabled, unit_id)
VALUES (
           'Oleg', '$2a$05$76G7zshIJxofVzxwuo3ywe5/QoQ6/20sciEtEs4rB7vZmePzQSS8C', true, 2
       );

INSERT INTO authorities (username, authority)
VALUES (
           'Oleg', 'ROLE_WORKER'
       );

INSERT INTO authorities (username, authority)
VALUES (
           'Oleg', 'repair:all'
       );

INSERT INTO authorities (username, authority)
VALUES (
           'Oleg', 'person:get'
       );
INSERT INTO authorities (username, authority)
VALUES (
           'Oleg', 'article:get'
       );
INSERT INTO authorities (username, authority)
VALUES (
           'Oleg', 'units:get'
       );
