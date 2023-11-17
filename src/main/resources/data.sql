insert into publisher (id, brand, prestige) values (100, 'Penguin', 2)
insert into publisher (id, brand, prestige) values (101, 'Sho', 10)
insert into book (id, author, title, genre, publisher_id) values (100, 'Mashu', 'KouQ', 'NonFiction', 100)
insert into book(id, author, title, genre, publisher_id) values (101, 'Johnny', 'TFBF', 'Fiction', 100)
insert into book(id, author, title, genre, publisher_id) values (102, 'JohnnyMashu', 'Zed', 'SciFi', 101)


insert into periodicals(id, dates, title, author, genre) values (100, '11/17/2023', 'Jii', 'Shingo', 'Romance')
insert into periodicals(id, dates, title, author, genre) values (101, '11/17/1223', 'Guma', 'Faker', 'Epic')

insert into movies(actors, director) values (100, 'JJ', 'Me')