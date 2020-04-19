Before start use MySQL commands:
1. create database stasInduk;
2. use stasInduk;
3. create table orders (id bigint not null, bird varchar(255), date varchar(255), number bigint, totalPrice double precision, totalWeight double precision, unitPrice double precision, primary key (id));
4. create table hibernate_sequence (next_val bigint);
5. insert into hibernate_sequence values ( 1 );