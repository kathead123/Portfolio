SELECT * 
FROM tenmo_user;

SELECT account_id, user_id, balance
FROM account
WHERE account_id= 1001;

SELECT *
FROM account
JOIN tenmo_user ON account.user_id= tenmo_user.user_id; 


SELECT *
FROM tenmo_user;

SELECT *
FROM transfer_status;

SELECT account.user_id, username
FROM account
JOIN tenmo_user ON account.user_id= tenmo_user.user_id;

UPDATE account SET balance= 1000
WHERE user_id= ?;

SELECT *
FROM transfer_type;

SELECT *
FROM transfer

SELECT account_id
FROM account
JOIN tenmo_user ON account.user_id= tenmo_user.user_id
WHERE username='kathleen';

SELECT *
FROM transfer_status;

INSERT INTO transfer (transfer_type_id, transfer_status_id, account_from, account_to, amount)
	VALUES (?,?,?,?,?);
	
SELECT tenmo_user.user_id, username
FROM account
JOIN tenmo_user ON account.user_id= tenmo_user.user_id;


SELECT transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount
FROM transfer
WHERE account_from= ?;

SELECT account.user_id
FROM account
JOIN tenmo_user ON account.user_id= tenmo_user.user_id
WHERE username='kathleen';

SELECT transfer_id, transfer.transfer_type_id, transfer.transfer_status_id, account_from, account_to, amount, transfer_type_desc, transfer_status_desc
FROM transfer
JOIN transfer_status ON transfer.transfer_status_id= transfer_status.transfer_status_id
JOIN transfer_type ON transfer.transfer_type_id= transfer_type.transfer_type_id;



SELECT transfer_type_desc
FROM transfer_type
WHERE transfer_type_id= 1;

SELECT username
FROM account
JOIN tenmo_user ON account.user_id= tenmo_user.user_id
WHERE account_id= 2003;

SELECT 
	
