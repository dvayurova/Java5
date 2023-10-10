-- Вставка пользователей
insert into chat.users (login, password)
values ('user1', 'password1');
insert into chat.users (login, password)
values ('user2', 'password2');
insert into chat.users (login, password)
values ('user3', 'password3');
insert into chat.users (login, password)
values ('user4', 'password4');
insert into chat.users (login, password)
values ('user5', 'password5');

insert into chat.users (login, password)
values ('user6', 'password6');
insert into chat.users (login, password)
values ('user7', 'password7');
insert into chat.users (login, password)
values ('user8', 'password8');
insert into chat.users (login, password)
values ('user9', 'password9');

-- Вставка комнат
insert into chat.rooms (name, owner)
values ('first', (select id from chat.users where login = 'user1'));
insert into chat.rooms (name, owner)
values ('second', (select id from chat.users where login = 'user2'));
insert into chat.rooms (name, owner)
values ('third', (select id from chat.users where login = 'user3'));
insert into chat.rooms (name, owner)
values ('fourth', (select id from chat.users where login = 'user4'));
insert into chat.rooms (name, owner)
values ('fifth', (select id from chat.users where login = 'user5'));

insert into chat.rooms (name, owner)
values ('another chat', (select id from chat.users where login = 'user6'));
insert into chat.rooms (name, owner)
values ('another chat1', (select id from chat.users where login = 'user7'));
insert into chat.rooms (name, owner)
values ('another chat2', (select id from chat.users where login = 'user8'));
insert into chat.rooms (name, owner)
values ('another chat3', (select id from chat.users where login = 'user9'));

-- Вставка сообщений
insert into chat.messages (author, room, message, date)
values ((select id from chat.users where login = 'user1'),
        (select id from chat.rooms where owner = (select id from chat.users where login = 'user5')), 'hello1',
        current_timestamp);
insert into chat.messages (author, room, message, date)
values ((select id from chat.users where login = 'user2'),
        (select id from chat.rooms where owner = (select id from chat.users where login = 'user4')), 'hello2',
        current_timestamp);
insert into chat.messages (author, room, message, date)
values ((select id from chat.users where login = 'user3'),
        (select id from chat.rooms where owner = (select id from chat.users where login = 'user2')), 'hello3',
        current_timestamp);
insert into chat.messages (author, room, message, date)
values ((select id from chat.users where login = 'user4'),
        (select id from chat.rooms where owner = (select id from chat.users where login = 'user3')), 'hello4',
        current_timestamp);
insert into chat.messages (author, room, message, date)
values ((select id from chat.users where login = 'user5'),
        (select id from chat.rooms where owner = (select id from chat.users where login = 'user1')), 'hello5',
        current_timestamp);
insert into chat.messages (author, room, message, date)
values ((select id from chat.users where login = 'user1'),
        (select id from chat.rooms where owner = (select id from chat.users where login = 'user5')), 'hello6',
        current_timestamp);
insert into chat.messages (author, room, message, date)
values ((select id from chat.users where login = 'user2'),
        (select id from chat.rooms where owner = (select id from chat.users where login = 'user4')), 'hello7',
        current_timestamp);
insert into chat.messages (author, room, message, date)
values ((select id from chat.users where login = 'user3'),
        (select id from chat.rooms where owner = (select id from chat.users where login = 'user3')), 'hello8',
        current_timestamp);
insert into chat.messages (author, room, message, date)
values ((select id from chat.users where login = 'user4'),
        (select id from chat.rooms where owner = (select id from chat.users where login = 'user2')), 'hello9',
        current_timestamp);
insert into chat.messages (author, room, message, date)
values ((select id from chat.users where login = 'user5'),
        (select id from chat.rooms where owner = (select id from chat.users where login = 'user1')), 'hello10',
        current_timestamp);

insert into chat.messages (author, room, message, date)
values ((select id from chat.users where login = 'user6'),
        (select id from chat.rooms where owner = (select id from chat.users where login = 'user8')), 'how are you?',
        current_timestamp);
insert into chat.messages (author, room, message, date)
values ((select id from chat.users where login = 'user7'),
        (select id from chat.rooms where owner = (select id from chat.users where login = 'user9')), 'how are you?',
        current_timestamp);
insert into chat.messages (author, room, message, date)
values ((select id from chat.users where login = 'user8'),
        (select id from chat.rooms where owner = (select id from chat.users where login = 'user7')), 'how are you?',
        current_timestamp);
insert into chat.messages (author, room, message, date)
values ((select id from chat.users where login = 'user9'),
        (select id from chat.rooms where owner = (select id from chat.users where login = 'user6')), 'how are you?',
        current_timestamp);


-- WITH r AS (
--     SELECT chat.rooms.id   AS room_id,
--            chat.rooms.name AS room_name,
--            owner,
--            author
--     FROM chat.rooms
--              JOIN chat.messages ON rooms.id = messages.room
-- )
-- SELECT u.id,
--        u.login,
--        u.password,
--        CASE
--            WHEN r.owner = u.id THEN 'owner'
--            WHEN r.author = u.id THEN 'author'
--            ELSE 'other'
--            END AS status,
--     r.room_id, r.room_name
-- FROM chat.users u
--          JOIN r ON u.id = r.owner OR u.id = r.author LIMIT 3 OFFSET  6;
