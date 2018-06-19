/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  banashko.dv
 * Created: 18.06.2018
 */

create table person
(
   id integer auto_increment not null,
   age integer not null,
   name varchar(255) not null,
   primary key(id)
);

create table animal
(
   id integer auto_increment not null,
   name varchar(255) not null,
   desc varchar(255) not null,
   personid integer not null,
   primary key(id),
   foreign key (personid) 
   REFERENCES person(Id)
);

create table author
(
   id integer auto_increment not null,
   name varchar(255) not null,
   age integer not null,
   primary key(id)
);

create table book
(
   id integer auto_increment not null,
   title varchar(255) not null,
   primary key(id)
);

create table authorbook
(
   id integer auto_increment not null,
   authorid integer not null,
   bookid integer not null,
   primary key(id),
   foreign key (authorid) REFERENCES author(id),
   foreign key (bookid) REFERENCES book(id)
);