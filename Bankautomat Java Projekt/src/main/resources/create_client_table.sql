/*
 * create_client_table.sql
 *
 * Created on 2020-10-02
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

drop table client;
create table client (

    name varchar(50),
    firstName varchar(50),
    iban char(22),
    pin char(4),
    bankBlance double,
    status boolean,
    loginTry integer,

    PRIMARY KEY(iban)
);

insert into client values (Mustermann,Max,AccountSyncClientA,1234,3370.0,true,0);
insert into client (name, firstName, iban, pin, bankBlance, status, loginTry) values ('Mustermann','Max','DE01 2345 6789 0123 4567 89','1234',100.0,true,0);
