use HospitalOutpatient

create table Doctor(
Doc_id int constraint pk_Doc_id primary key,
Doc_name text,
Doc_dept text,
Doc_patnum int);

create table Patient_queue(
Pat_name text,
Pat_sex text,
Pat_age int,
Pat_phone text,
Pat_num int,
Doc_id int constraint fk_Doc_id foreign key (Doc_id) references Doctor(Doc_id));

create table Charge_list(
Pro_name varchar(50) constraint pk_Pro_name primary key,
Pro_price float,
Pro_bfcode text);

create table Medicine(
Med_name varchar(50) constraint pk_Med_name primary key,
Med_price text,
Med_bfcode text,
Med_count int);

create table Pat_charge(
Pat_phone varchar(50) constraint pk_Pat_phone primary key,
Pat_name text,
Pat_feename text,
Pro_num int,
Pat_proprice float,
Pat_charged bit,
Pat_docid int constraint fk_Pat_docid foreign key(Pat_docid) references Doctor(Doc_id));


create table Pat_order(
Pat_name text,
Pat_sex text,
Pat_age int,
Pat_phone text,
Order_dept text,
Order_time datetime);


