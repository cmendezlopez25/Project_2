--tables
create table user_table(
    email varchar(100) primary key,
    password varchar(100) not null,
    first_name varchar(100) not null,
    last_name varchar(100) not null
);

create table account(
    account_id int4 primary key,
    name varchar(100) not null
);

create table transaction_table(
    transaction_id int4 primary key,
    account_id int4,
    amount numeric(10, 2) not null,
    date date not null,
    name varchar(100) not null,
    note text not null,
    recurring varchar(25) null,
    constraint transaction_account_id_fk foreign key (account_id) REFERENCES account(account_id) on delete cascade
);

create table category(
    category_id serial primary key,
    name varchar(100) unique
);

create table role_table(
    role_id serial primary key,
    name varchar(25) unique
);

create table transaction_category(
    transaction_id int4,
    category_id int4,
    constraint tc_transaction_id_fk foreign key(transaction_id) references transaction_table(transaction_id) on delete cascade,
    constraint tc_category_id_fk foreign key(category_id) references category(category_id)
);

create table user_role_account(
    email varchar(100),
    role_id int4,
    account_id int4,
    constraint ura_email_fk foreign key(email) references user_table(email) on delete cascade,
    constraint ura_role_id_fk foreign key(role_id) references role_table(role_id),
    constraint ura_account_id_fk foreign key(account_id) references account(account_id) on delete cascade
);


--sequences
create sequence account_id_seq;
create sequence transaction_id_seq;
create sequence category_id_seq;
create sequence role_id_seq;