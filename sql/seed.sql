create table trucks(id BIGSERIAL, model varchar(200), location varchar(200), primary key(id));

create table donors(id BIGSERIAL, name varchar(200), type varchar(20), number varchar(200), primary key(id));

create table drive(id BIGSERIAL, date date, time varchar(200), truck int, goal int, location varchar(255), actual int null, donors int null, primary key(id), foreign key(truck) references trucks(id));
