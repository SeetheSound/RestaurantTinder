BEGIN TRANSACTION;

DROP TABLE IF EXISTS restaurant_voting;
DROP TABLE IF EXISTS guest_event;
DROP TABLE IF EXISTS event;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

create table event
(
    event_id serial primary key,
    event_name  varchar(100),
    event_description varchar(200),
    final_selection_restaurant_id varchar(100),
    host_user_id integer references users(user_id),
    rsvp_deadline timestamp,
    event_date timestamp,
    location varchar(200)
);

create table guest_event
(
    guest_event_id serial primary key,
    email varchar(100),
    event_id integer references event(event_id),
    UNIQUE(email, event_id)
);

create table restaurant_voting
(
    restaurant_votes_id SERIAL PRIMARY KEY,
    yelp_restaurant_id varchar(100),
    event_id integer references event(event_id),
    guest_event_id integer references guest_event(guest_event_id),
    vote  boolean,
    guest_comments varchar(200)
);

COMMIT TRANSACTION;
