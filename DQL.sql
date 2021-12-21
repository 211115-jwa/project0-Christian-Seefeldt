select * from person;

select * from bike;

select * from bike_owner;

select * from bike where available='Available';

select * from bike where brand='Huffy';

select * from bike where model='Cranbrook';

select * from bike where color='black';

select * from bike where brakes='coaster';

select * from bike where wheels='steel';

select * from bike where electric=true;

select * from bike where frameSize=25;

select * from bike where speeds=7;

select * from bike where price<300;

select * from bike where price>150 intersect select * from bike where price<300;

select count(distinct model) from bike;

select avg(price), brand from bike group by brand;

select * from bike join bike_owner on bike.id=bike_owner.bike_id where person_id=3



