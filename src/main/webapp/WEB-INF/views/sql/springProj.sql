-- 
create table tbl_board (
no int not null auto_increment,
titlte varchar(100) not null,
content text null,
writer varchar(50) not null,
regdate timestamp not null default now(),
viewcnt int default 0,
primary key(no)
);