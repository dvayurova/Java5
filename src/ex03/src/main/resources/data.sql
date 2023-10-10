-- Вставка пользователей
insert into chat.users (login, password) values ('user1', 'password1');
insert into chat.users (login, password) values ('user2', 'password2');
insert into chat.users (login, password) values ('user3', 'password3');
insert into chat.users (login, password) values ('user4', 'password4');
insert into chat.users (login, password) values ('user5', 'password5');

-- Вставка комнат
insert into chat.rooms (name, owner) values ('first', (select id from chat.users where login = 'user1'));
insert into chat.rooms (name, owner) values ('second', (select id from chat.users where login = 'user2'));
insert into chat.rooms (name, owner) values ('third', (select id from chat.users where login = 'user3'));
insert into chat.rooms (name, owner) values ('fourth', (select id from chat.users where login = 'user4'));
insert into chat.rooms (name, owner) values ('fifth', (select id from chat.users where login = 'user5'));

-- Вставка сообщений
insert into chat.messages (author, room, message, date) values ((select id from chat.users where login = 'user1'), (select id from chat.rooms where owner = (select id from chat.users where login = 'user1')), 'hello1', current_timestamp);
insert into chat.messages (author, room, message, date) values ((select id from chat.users where login = 'user2'), (select id from chat.rooms where owner = (select id from chat.users where login = 'user2')), 'hello2', current_timestamp);
insert into chat.messages (author, room, message, date) values ((select id from chat.users where login = 'user3'), (select id from chat.rooms where owner = (select id from chat.users where login = 'user3')), 'hello3', current_timestamp);
insert into chat.messages (author, room, message, date) values ((select id from chat.users where login = 'user4'), (select id from chat.rooms where owner = (select id from chat.users where login = 'user4')), 'hello4', current_timestamp);
insert into chat.messages (author, room, message, date) values ((select id from chat.users where login = 'user5'), (select id from chat.rooms where owner = (select id from chat.users where login = 'user5')), 'hello5', current_timestamp);
insert into chat.messages (author, room, message, date) values ((select id from chat.users where login = 'user1'), (select id from chat.rooms where owner = (select id from chat.users where login = 'user5')), 'hello6', current_timestamp);
insert into chat.messages (author, room, message, date) values ((select id from chat.users where login = 'user2'), (select id from chat.rooms where owner = (select id from chat.users where login = 'user4')), 'hello7', current_timestamp);
insert into chat.messages (author, room, message, date) values ((select id from chat.users where login = 'user3'), (select id from chat.rooms where owner = (select id from chat.users where login = 'user3')), 'hello8', current_timestamp);
insert into chat.messages (author, room, message, date) values ((select id from chat.users where login = 'user4'), (select id from chat.rooms where owner = (select id from chat.users where login = 'user2')), 'hello9', current_timestamp);
insert into chat.messages (author, room, message, date) values ((select id from chat.users where login = 'user5'), (select id from chat.rooms where owner = (select id from chat.users where login = 'user1')), 'hello10', current_timestamp);
