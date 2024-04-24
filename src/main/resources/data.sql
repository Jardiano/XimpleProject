insert into "book"("id","title", "genre", "resume", "reserved") values(1,'The Da Vinci Code', 'thriller', 'While in Paris, Harvard symbologist Robert Langdon is awakened by a phone call in the dead of the night. The elderly curator of the Louvre has been murdered inside the museum, his body covered in baffling symbols.', true);
insert into "book"("id","title", "genre", "resume", "reserved") values(2,'After the Darkness', 'drama','What happens when the woman who has everything loses everything . . . and the man who has nothing realizes he has nothing to lose? The young, naïve wife of a multi-billionaire financial superstar, Grace Brookstein..', false);
insert into "book"("id","title", "genre", "resume", "reserved") values(3,'The Lord Of The Rings','adventure', 'One Ring to rule them all, One Ring to find them, One Ring to bring them all and in the darkness bind them In ancient times the Rings of Power were crafted by the Elven-smiths, and Sauron, the Dark Lord...', false);

insert into "user"("id","name","email") values(1, 'UserTest1', 'email1@test.com');
insert into "user"("id","name","email") values(2, 'UserTest2', 'email2@test.com');

insert into "reservation"("id","book_id","user_id","start_date","end_date") values(1,1,1,'2024-04-24', null);
insert into "reservation"("id","book_id","user_id","start_date","end_date") values(2,3,2,'2024-01-05', '2024-02-12');

insert into "review"("id","book_id","user_id", "text", "rating") values(100,3,2, 'Excellent book', 3);
