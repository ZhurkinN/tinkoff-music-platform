insert into song (id, author, genre, title, file_name, picture_name)
values (101, 'PUSSYKILLER', 'Хип-Хоп', 'Гори ясно', 'Гори_ясно.mp3', '1.jpg');
insert into song (id, author, genre, title, file_name, picture_name)
values (102, 'PUSSYKILLER', 'Хип-Хоп', 'Поздно', 'Поздно.mp3', '2.png');
insert into song (id, author, genre, title, file_name, picture_name)
values (103, 'Markul', 'Хип-Хоп', 'Blues', 'Blues.mp3', '3.png');
insert into song (id, author, genre, title, file_name, picture_name)
values (104, 'Markul', 'Хип-Хоп', 'Moulin rouge', 'Rouge.mp3', '4.png');
insert into song (id, author, genre, title, file_name, picture_name)
values (105, 'ХуйЗнает', 'Хип-Хоп', 'Гори ясно', 'Гори_ясно2.mp3', '5.png');
insert into song (id, author, genre, title, file_name, picture_name)
values (106, 'PUSSYKILLER', 'Франция', 'Гори ясно', 'Франция.mp3', '6.png');
insert into song (id, author, genre, title, file_name, picture_name)
values (107, 'DIMA ROUX', 'Хип-Хоп', '0 to 100', '0to100.mp3', '7.png');
insert into song (id, author, genre, title, file_name, picture_name)
values (108, 'PHARAOH', 'Хип-Хоп', 'Эми', 'Emi.mp3', '8.png');
insert into song (id, author, genre, title, file_name, picture_name)
values (109, 'PHARAOH', 'Хип-Хоп', 'В ванне', 'Bath.mp3', '9.png');
insert into playlist values (101,'Хип-хоп треки, выходившие в 2023 году','Хип-хоп 2023');
insert into playlist values (102,'Хип-хоп треки, выходившие в 2022 году','Хип-хоп 2022');
insert into playlist values (103,'Хип-хоп треки, выходившие в 2021 году','Хип-хоп 2021');
insert into playlist values (104,'Лучшие треки за 2022 год, РЗТ','Плейлист 2022 РЗТ');
insert into playlist values (105,'Лучшие треки за 2021 год, РЗТ','Плейлист 2021 РЗТ');
insert into playlist_songs values (101, 1, 101, 101);
insert into playlist_songs values (102, 2, 101, 102);
insert into playlist_songs values (103, 3, 101, 103);
insert into playlist_songs values (104, 4, 101, 104);
insert into playlist_songs values (105, 5, 101, 105);
insert into playlist_songs values (106, 1, 102, 101);
insert into playlist_songs values (107, 1, 103, 106);
insert into playlist_songs values (108, 2, 102, 107);
insert into playlist_songs values (109, 2, 103, 108);
insert into playlist_songs values (110, 1, 104, 109);