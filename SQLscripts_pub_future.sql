use pub_future;

create table conta (
	contaID integer primary key,
    saldo decimal(8,2),
    tipoConta varchar(50),
    instiuicaoFinanceira varchar(50)
);

create table despesa (
	despesaID integer primary key,
    dataPagamento date,
    dataPagamentoEsperado date,
    tipoDespesa varchar(50),
    contaID integer
);

create table receita (
	receita integer primary key,
    valor decimal(8, 2),
	dataRecebimento date,
	dataRecebimentoEsperado date,
	descrição varchar(50),
	tipoReceita varchar(50),
    contaID integer
);

ALTER TABLE despesa
ADD CONSTRAINT despesa_contaID_fk
FOREIGN KEY (contaID) REFERENCES conta(contaID);

ALTER TABLE receita
ADD CONSTRAINT receita_contaID_fk
FOREIGN KEY (contaID) REFERENCES conta(contaID);
