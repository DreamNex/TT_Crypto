INSERT INTO USER_EMPLOYEES (user_name, email) VALUES ('traderUser','workemail@gmail.com');

INSERT INTO WALLET_TBL(user_id) VALUES (SELECT ID FROM USER_EMPLOYEES WHERE user_name = 'traderUser');