create schema if not exists boss;
create table if not exists boss.loan
(
    id            INT      NOT NULL AUTO_INCREMENT UNIQUE,
    type_id       TINYINT  NOT NULL,
    user_id       INT      NOT NULL,
    branch_id     INT      NOT NULL,
    amount        FLOAT    NOT NULL,
    interest_rate FLOAT    NOT NULL,
    taken_at      DATETIME NOT NULL,
    due_by        DATE     NOT NULL,
    amount_due    FLOAT    NOT NULL,
    PRIMARY KEY (id)
);
