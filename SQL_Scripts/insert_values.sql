--insert values
insert into user_table values('ricky@email.com', 'password', 'Ricky', 'Wang');
insert into user_table values('carlos@mymail.com', 'password', 'Carlos', 'Mendez');
insert into user_table values('jacob@email.email', 'password', 'Jacob', 'Boni');
insert into user_table values('jane@email.org', 'password', 'Jane', 'Shin');

insert into category(name) values('Auto & Transport');
insert into category(name) values('Bills & Utilities');
insert into category(name) values('Education');
insert into category(name) values('Entertainment');
insert into category(name) values('Fees & Charges');
insert into category(name) values('Food & Dining');
insert into category(name) values('Gifts & Donations');
insert into category(name) values('Health & Fitness');
insert into category(name) values('Home');
insert into category(name) values('Income');
insert into category(name) values('Investments');
insert into category(name) values('Miscellaneous');
insert into category(name) values('Personal Care');
insert into category(name) values('Pets');
insert into category(name) values('Shopping');
insert into category(name) values('Taxes');
insert into category(name) values('Transfer');
insert into category(name) values('Travel');

insert into role_table(name) values('Owner');
insert into role_table(name) values('Admin');
insert into role_table(name) values('Moderator');
insert into role_table(name) values('Employee');

insert into account(account_id, name) values(1, 'Checking Account');
insert into account(account_id, name) values(2, 'Savings Account');

insert into transaction_table(transaction_id, account_id, amount, date, name, note) values(1, 1, -300.49, current_date, 'Starbucks', 'I like coffee');
insert into transaction_table values(2, 1, -50.49, current_date, 'Chipotle', 'One burrito');
insert into transaction_table values(3, 2, 500.49, current_date, 'Paycheck', 'I have money.');

insert into transaction_category values(1, 1);
insert into transaction_category values(3, 1);
insert into transaction_category values(1, 2);

insert into user_role_account(email, role_id, account_id) values('ricky@email.com', 1, 1);
insert into user_role_account(email, role_id, account_id) values('jane@email.org', 2, 1);
insert into user_role_account(email, role_id, account_id) values('jane@email.org', 1, 2);
insert into user_role_account(email, role_id, account_id) values('jacob@email.email', 2, 2);
insert into user_role_account(email, role_id, account_id) values('carlos@mymail.com',3, 2);