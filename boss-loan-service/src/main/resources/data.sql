insert into boss.loan (type_id, user_id, branch_id, amount, interest_rate, taken_at, due_by, amount_due)
values (0, 1, 1, 50000, 0.02, NOW(), NOW(), 25000);

insert into boss.loan (type_id, user_id, branch_id, amount, interest_rate, taken_at, due_by, amount_due)
values (1, 1, 1, 25000, 0.1, NOW(), NOW(), 5000);