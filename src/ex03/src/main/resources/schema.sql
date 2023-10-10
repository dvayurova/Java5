drop schema if exists chat cascade;

create schema if not exists chat;

create table chat.users (
    id serial primary key,
    login text unique not null ,
    password text not null
);

create table chat.rooms(
    id serial primary key,
    name text not null,
    owner int references chat.users(id) not null
);

create table chat.messages (
    id serial primary key,
    author int references chat.users(id) not null ,
    room int references chat.rooms(id) not null ,
    message text not null,
    date timestamp default null
);