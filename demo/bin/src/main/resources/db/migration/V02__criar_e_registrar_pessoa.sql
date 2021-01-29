CREATE TABLE pessoa(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT, 
	nome VARCHAR(50) NOT NULL,
	ativo bit, 
	logradouro varchar(50), 
	numero varchar(50),
	complemento varchar(50),
	bairro varchar(50),
	cep varchar(50),
	cidade varchar(50),
	estado varchar(50)
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
