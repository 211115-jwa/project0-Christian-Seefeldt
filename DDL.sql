drop table Person cascade;
drop table Bike cascade;
drop table Bike_Owner cascade;


create table Person (
	id serial primary key,
	usr varchar(100) not null,
	psw varchar(100) not null,
	firstName varchar(100) not null,
	lastName varchar(100) not null,
	userRole varchar(100) not null
);

create table Bike (
	id serial primary key,
	brand varchar(100) not null,
	model varchar(100) not null,
	color varchar(100) not null,
	brakes varchar(100) not null,
	wheels varchar(100) not null,
	available varchar(100) not null,
	electric bool not null,
	frameSize integer not null,
	speeds integer not null,
	price integer not null
);

create table Bike_Owner (
	Bike_id integer references bike,
	Person_id integer references person
);
