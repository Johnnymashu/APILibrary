INSERT INTO Publisher (ID, BRAND, PRESTIGE) VALUES ( NEXTVAL('publisher_seq'), 'MashuBooks', 10);

INSERT INTO Book (ID, AUTHOR, TITLE, GENRE, RELEASER_ID) VALUES ( NEXTVAL('book_seq'), 'John', 'KouQ','AutoBiography', 1);
INSERT INTO Book (ID, AUTHOR, TITLE, GENRE, RELEASER_ID) VALUES ( NEXTVAL('book_seq'), 'Johnny', 'KouQ Ltd','Biography', 1);
