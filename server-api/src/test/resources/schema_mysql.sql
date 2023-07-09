drop table customer;
drop table owner;
drop table rider;

create table customer
(
    id         bigint primary key auto_increment,
    email      varchar(40)  not null unique,
    password   varchar(100) not null,
    name       varchar(40)  not null,
    phone      varchar(40)  not null unique,
    address    varchar(255) not null,
    created_at timestamp default NOW(),
    updated_at timestamp default NOW(),
    salt       varchar(100)
);

create table owner
(
    id         bigint primary key auto_increment,
    password   varchar(100) not null,
    name       varchar(40)  not null,
    phone      varchar(40)  not null unique,
    created_at timestamp default NOW(),
    updated_at timestamp default NOW(),
    salt       varchar(100)
);

create table rider
(
    id          bigint primary key auto_increment,
    password    varchar(100)                  not null,
    name        varchar(40)                   not null,
    phone       varchar(40)                   not null unique,
    work_status varchar(40) default 'OFFLINE' not null,
    created_at  timestamp   default NOW(),
    updated_at  timestamp   default NOW(),
    salt        varchar(100)
);

create table orders
(
    id           bigint unsigned auto_increment
        primary key,
    order_status varchar(40)  not null,
    address      varchar(255) not null,
    created_at   timestamp default NOW() null,
    updated_at   timestamp default NOW() null,
    customer_id  bigint unsigned not null,
    store_id     bigint unsigned not null,
    rider_id     bigint unsigned null
);

