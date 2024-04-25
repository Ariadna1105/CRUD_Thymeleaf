DROP
DATABASE IF EXISTS tienda2;
CREATE
DATABASE tienda2 CHARACTER SET utf8mb4;
USE
tienda2;

create table proveedor
(
    id        int unsigned auto_increment primary key,
    name      varchar(100) not null,
    lastName  varchar(100) not null,
    telephone varchar(20) null,
    mail      varchar(100) null,
    country   varchar(100) null,
    type      varchar(50) null
);