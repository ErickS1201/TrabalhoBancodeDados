package modelos;
import java.sql.*;
public class ConexaoSGBD {
	private static final String URL ="jdbc:postgresql://localhost:5432/StockFlow";
	private static final String usuario ="postgres";
	private static final String senha ="senha123";
	
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(URL, usuario, senha);
	}
	
	
}
/*
 -- Database: StockFlow

-- DROP DATABASE IF EXISTS "StockFlow";
--drop table cliente;
--select * from cliente;
create table cliente (
cod_cliente serial primary key,
nome_cliente varchar not null,
cpf_cliente char(11),
email_cliente varchar
);
--insert into cliente (nome_cliente, cpf_cliente) values ('Erick', '33311122245');
create table pagamento(
cod_pagamento serial primary key,
forma_pagamento varchar,
status_pagamento varchar,
data_pagamento timestamp default current_timestamp
);
create table pedido(
cod_pedido serial primary key,
cod_cliente int,
cod_pagamento int,
data_pedido timestamp default current_timestamp,
valor_pedido real,
status_pedido varchar,
constraint fk_cod_cliente foreign key (cod_cliente) references cliente(cod_cliente),
constraint fk_cod_pagamento foreign key (cod_pagamento) references pagamento(cod_pagamento)
);

create table categoria(
cod_categoria serial primary key,
nome_categoria varchar,
descricao varchar
);

create table produto(
cod_produto serial primary key,
cod_categoria int,
nome_produto varchar,
preco_produto real,
qtd_estoque int,
constraint fk_cod_categoria foreign key (cod_categoria) references categoria(cod_categoria)
);

create table pedido_produto(
cod_pedido int,
cod_produto int,
qtd_comprada int,
constraint pk_pedido_produto primary key (cod_pedido, cod_produto),
constraint fk_cod_pedido foreign key (cod_pedido) references pedido(cod_pedido),
constraint fk_cod_produto foreign key (cod_produto) references produto(cod_produto)
);
create table fornecedor (
cod_fornecedor serial primary key,
razao_social varchar,
cnpj varchar,
telefone_fornecedor varchar,
email_fornecedor varchar
);
create table produto_fornecedor(
cod_produto int,
cod_fornecedor int,
preco_custo real,
prazo_entrega int,
constraint pk_produto_fornecedor primary key (cod_produto, cod_fornecedor),
constraint fk_cod_produto foreign key(cod_produto) references produto(cod_produto),
CONSTRAINT fk_cod_fornecedor FOREIGN key (cod_fornecedor) references fornecedor(cod_fornecedor)
);

alter table cliente add column telefone_cliente varchar;
 * */
 