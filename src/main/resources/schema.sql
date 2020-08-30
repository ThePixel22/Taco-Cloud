create table if not exists Ingredient (
 id varchar(4) not null,
 name varchar(25) not null,
 type varchar(10) not null,
);

ALTER TABLE Ingredient
ADD Primary KEY (id);

create table if not exists Taco (
	id BIGINT NOT NULL AUTO_INCREMENT,
    name varchar(50) not null,
    create_at timestamp not null,
    primary key (id)
);

create table if not exists  Taco_Ingredients(
	taco bigint not null,
	ingredient varchar(4) not null
);


ALTER TABLE Taco_Ingredients
ADD FOREIGN KEY (taco) REFERENCES Taco(id);

ALTER TABLE Taco_Ingredients
ADD FOREIGN KEY (ingredient) REFERENCES Ingredient(id);


create table if not exists Taco_Order (
 id BIGINT NOT NULL AUTO_INCREMENT,
 delivery_Name varchar(50) not null,
 delivery_Street varchar(50) not null,
 delivery_City varchar(50) not null,
 delivery_State varchar(2) not null,
 delivery_Zip varchar(10) not null,
 cc_Number varchar(16) not null,
 cc_Expiration varchar(5) not null,
 cc_CVV varchar(3) not null,
 placed_At timestamp not null,
 primary key (id)
);


create table if not exists Taco_Order_Tacos (
 tacoOrder bigint not null,
 taco bigint not null
);

alter table Taco_Order_Tacos
 add foreign key (tacoOrder) references Taco_Order(id);
alter table Taco_Order_Tacos
 add foreign key (taco) references Taco(id);