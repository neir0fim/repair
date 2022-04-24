CREATE table if not exists authorities(
    username varchar(50) not null ,
    authority varchar(50) not null ,
    constraint fk_authorities_users foreign key (username) references users(username) on delete cascade
);

create unique index ix_auth_username on authorities (username, authority);