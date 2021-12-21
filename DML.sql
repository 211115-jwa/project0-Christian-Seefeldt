insert into person (usr, psw, firstName, lastName, userRole) values
	('CDS', '1234', 'Christian', 'Seefeldt', 'Employee'),
	('TR', '2345', 'Tom', 'Robert', 'Employee'),
	('JD', '3456', 'Jane', 'Doe', 'Customer'),
	('MS', '4567', 'Mary', 'Sue', 'Customer'),
	('PS', '5678', 'Phil', 'Steward', 'Customer');

insert into bike (brand, model, color, brakes, wheels, available, electric, frameSize, speeds, price) values
	('Kent', 'Cruiser', 'blue', 'coaster', 'brown', 'Bought', false, 25, 1, 198),
	('Nishiki', 'Mountain', 'red', 'Alloy linear pull', 'steel', 'Bought', false, 22, 7, 279.99),
	('Nishiki', 'Mountain', 'grey', 'Alloy linear pull', 'steel', 'Bought', false, 18, 7, 279.99),
	('RadRover', 'Fat Bike', 'black', 'Aluminum alloy', 'black', 'available', true, 27, 7, 129.98),
	('Mongoose', 'Mountain', 'black', 'linear pull', 'pink', 'available', false, 25, 21, 164),
	('Huffy', 'Cranbrook', 'blue', 'coaster', 'black', 'available', false, 26, 1, 148),
	('Huffy', 'Cranbrook', 'silver', 'coaster', 'black', 'available', false, 26, 1, 148),
	('Huffy', 'Cranbrook', 'red', 'coaster', 'black', 'Bought', false, 26, 1, 148),	
	('Huffy', 'Cranbrook', 'white', 'coaster', 'black', 'available', false, 26, 1, 148),
	('Huffy', 'Rock Creek', 'purple', 'linear pull', 'black', 'Bought', false, 24, 18, 128),
	('Mongoose', 'Dolomite', 'black', 'linear pull', 'blue', 'available', false, 26, 7, 448),
	('Hyper', 'Assist Commuter', 'blue', 'V-brakes', 'black', 'available', true, 24, 6, 398),
	('Hyper', 'Assist Mountain', 'green', 'V-brakes', 'black', 'available', true, 26, 6, 398);

insert into bike_owner (Bike_id, Person_id) values
	(1, 1),
	(2, 3),
	(3, 1),
	(8, 3),
	(10, 4);
	
update person set firstname='Christian' where id=1;

commit;

	
