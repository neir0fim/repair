INSERT INTO users (username, password, enabled, type)
VALUES (
        'Kuzin S.S.', '$2a$05$79N.4Yi9SL6fW6muJCNIkeVViNu19ksATLiRVt/U7XnNTPPpL7quC', true, 'ADMIN'
       );

INSERT INTO authorities (username, authority)
VALUES (
        'Kuzin S.S.', 'ROLE_ADMIN'
       );

INSERT INTO authorities (username, authority)
VALUES (
           'Kuzin S.S.', 'article:all'
       );

INSERT INTO authorities (username, authority)
VALUES (
           'Kuzin S.S.', 'person:all'
       );

INSERT INTO authorities (username, authority)
VALUES (
           'Kuzin S.S.', 'units:all'
       );


INSERT INTO users (username, password, enabled, type)
VALUES (
           'Khyzhnyak M.V.',
        '$2a$05$OtfOySt8sAhXfJBUMS8qmelh3xPXUoTxQB5hYWcYsQJ.zaK.0Iwh.', true, 'RTM'
       );

INSERT INTO authorities (username, authority)
VALUES (
           'Khyzhnyak M.V.', 'ROLE_WORKER'
       );


INSERT INTO authorities (username, authority)
VALUES (
           'Khyzhnyak M.V.', 'person:get'
       );
INSERT INTO authorities (username, authority)
VALUES (
           'Khyzhnyak M.V.', 'article:get'
       );
INSERT INTO authorities (username, authority)
VALUES (
           'Khyzhnyak M.V.', 'units:get'
       );

INSERT INTO authorities (username, authority)
VALUES (
           'Khyzhnyak M.V.', 'repair:all'
       );

INSERT INTO users (username, password, enabled, type)
VALUES (
           'Davidenko S.M.',
           '$2a$05$OtfOySt8sAhXfJBUMS8qmelh3xPXUoTxQB5hYWcYsQJ.zaK.0Iwh.', true, 'KTC'
       );

INSERT INTO authorities (username, authority)
VALUES (
           'Davidenko S.M.', 'ROLE_WORKER'
       );


INSERT INTO authorities (username, authority)
VALUES (
           'Davidenko S.M.', 'person:get'
       );
INSERT INTO authorities (username, authority)
VALUES (
           'Davidenko S.M.', 'article:get'
       );
INSERT INTO authorities (username, authority)
VALUES (
           'Davidenko S.M.', 'units:get'
       );

INSERT INTO authorities (username, authority)
VALUES (
           'Davidenko S.M.', 'repair:all'
       );


INSERT INTO users (username, password, enabled, type)
VALUES (
           'Kropiva D.S',
        '$2a$05$76G7zshIJxofVzxwuo3ywe5/QoQ6/20sciEtEs4rB7vZmePzQSS8C', true, 'SUPP'
       );

INSERT INTO authorities (username, authority)
VALUES (
           'Kropiva D.S', 'ROLE_SUPP'
       );

INSERT INTO authorities (username, authority)
VALUES (
           'Kropiva D.S', 'repair:all'
       );

INSERT INTO authorities (username, authority)
VALUES (
           'Kropiva D.S', 'person:get'
       );
INSERT INTO authorities (username, authority)
VALUES (
           'Kropiva D.S', 'article:get'
       );
INSERT INTO authorities (username, authority)
VALUES (
           'Kropiva D.S', 'units:get'
       );
