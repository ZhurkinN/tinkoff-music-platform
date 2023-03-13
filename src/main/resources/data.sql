insert into song (id, author, genre, title, file_name, picture_name)
values (101, 'ATL', 'Hip-Hop', 'Under the green sky', 'under_the_green_sky.mp3', 'under_the_green_sky.jpg');
insert into song (id, author, genre, title, file_name, picture_name)
values (102, 'OG BUDA', 'Hip-Hop', 'Big boy slime', 'big_boy_slime.mp3', 'big_boy_slime.jpg');
insert into song (id, author, genre, title, file_name, picture_name)
values (103, 'Markul', 'Hip-Hop', 'Blues', 'blues.mp3', 'blues.jpg');
insert into song (id, author, genre, title, file_name, picture_name)
values (104, 'Travis Scott', 'Hip-Hop', 'Sicko mode', 'sicko_mode.mp3', 'sicko_mode.jpg');
insert into song (id, author, genre, title, file_name, picture_name)
values (105, 'Billie Eilish', 'Pop', 'Lovely', 'lovely.mp3', 'lovely.jpg');
insert into song (id, author, genre, title, file_name, picture_name)
values (106, 'DaBaby', 'Hip-Hop', 'Rockstar', 'rockstar.mp3', 'rockstar.jpg');
insert into song (id, author, genre, title, file_name, picture_name)
values (107, 'Rick Astly', 'Dance-pop', 'Never gonna give your up', 'never_gonna_give_your_up.mp3', 'never_gonna_give_your_up.jpg');
insert into song (id, author, genre, title, file_name, picture_name)
values (108, 'Queen', 'Rock', 'We will rock you', 'we_will_rock_you.mp3', 'we_will_rock_you.jpg');
insert into song (id, author, genre, title, file_name, picture_name)
values (109, 'Good Life', 'Rock', 'One Republic', 'one_republic.mp3', 'one_republic.jpg');
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