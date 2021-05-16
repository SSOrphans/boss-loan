CREATE SCHEMA IF NOT EXISTS boss;
CREATE TABLE IF NOT EXISTS boss.loan
(
    id            INT         NOT NULL AUTO_INCREMENT UNIQUE,
    loan_number   VARCHAR(64) NOT NULL UNIQUE,
    type_id       TINYINT     NOT NULL,
    user_id       INT         NOT NULL,
    branch_id     INT         NOT NULL,
    amount        FLOAT       NOT NULL,
    interest_rate FLOAT       NOT NULL,
    taken_at      DATETIME    NOT NULL,
    due_by        DATE        NOT NULL,
    amount_due    FLOAT       NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS boss.loan_type
(
    id   TINYINT     NOT NULL AUTO_INCREMENT UNIQUE,
    name VARCHAR(32) NOT NULL,
    PRIMARY KEY (id)
);
