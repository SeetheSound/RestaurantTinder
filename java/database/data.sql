BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

INSERT INTO event (host_user_id, event_name, event_description, final_selection_restaurant_id, rsvp_deadline, event_date, location) VALUES (1, 'Tech Elevator party', 'Come celebrate my graduation!',  null, '2023-04-15 18:00:00.000000', '2023-04-20 18:00:00.000000', '19971');
INSERT INTO event (host_user_id, event_name, event_description, final_selection_restaurant_id, rsvp_deadline, event_date, location) VALUES (1, 'Birthday party', 'Come celebrate my birthday!', null,  '2023-04-19 18:00:00.000000', '2023-04-21 18:00:00.000000', 'Newark, DE');

INSERT INTO guest_event (email, event_id) values('user1@gmail.com', 1);
INSERT INTO guest_event (email, event_id) values('user2@gmail.com', 1);
INSERT INTO guest_event (email, event_id) values('user1@gmail.com', 2);


INSERT INTO restaurant_voting (yelp_restaurant_id, event_id, guest_event_id, vote, guest_comments) VALUES ('Myrv6Ju10iUQM6b4ftXrZA', 1, 1, true, 'abcd');
INSERT INTO restaurant_voting (yelp_restaurant_id, event_id, guest_event_id, vote, guest_comments) VALUES ('LLBrTAYL_DouRQiaQOF6ag', 1, 1, true, 'abcd');
INSERT INTO restaurant_voting (yelp_restaurant_id, event_id, guest_event_id, vote, guest_comments) VALUES ( 'LLBrTAYL_DouRQiaQOF6ag', 1, 2, false, null);
INSERT INTO restaurant_voting (yelp_restaurant_id, event_id, guest_event_id, vote, guest_comments) VALUES ( 'TSEjpQpqk7U3h9WdyYb5Ww', 1, 1, true, 'abcd');



COMMIT TRANSACTION;