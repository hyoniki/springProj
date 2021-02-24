-- 
create table tbl_board (
no int not null auto_increment,
title varchar(100) not null,
content text null,
writer varchar(50) not null,
regdate timestamp not null default now(),
viewcnt int default 0,
primary key(no)
);

select * from tbl_board;

insert into tbl_board(title, content, writer) values ('제목', '내용', '작성자');

update tbl_board set title = '제목 변경', content = '내용 변경' where no = 1 ;

insert into test.tbl_board(title, content, writer)
(select title, content, writer from test.tbl_board);

select * from test.tbl_board order by no desc limit 0, 10;