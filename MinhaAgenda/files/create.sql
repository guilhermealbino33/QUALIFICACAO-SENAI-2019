CREATE TABLE PESSOA (
	ID 				integer identity,
	NOME 			varchar(50),
	CELULAR			varchar(50),
	TELEFONE		varchar(50),
	EMAIL			varchar(50),
	ANIVERSARIO 	date,
	PAIS			varchar(50),
	ENDERECO		varchar(50),
	CIDADE			varchar(50),
	UF				varchar(2)
);
COMMIT ;