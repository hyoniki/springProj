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
select * from tbl_reply;

insert into tbl_board(title, content, writer) values ('제목', '내용', '작성자');

update tbl_board set title = '제목 변경', content = '내용 변경' where no = 1 ;

insert into test.tbl_board(title, content, writer)
(select title, content, writer from test.tbl_board);

select * from test.tbl_board order by no desc limit 0, 20;

select * from test.tbl_board where title like '%수%';

select * from test.tbl_board where title like '%수%' order by no desc limit 0, 10;

-- 댓글 테이블 생성
create table test.tbl_reply (
no int not null auto_increment,
bno int not null default 0,
replytext varchar(500) not null,
replyer varchar(50) not null,
regdate timestamp default now(),
updatedate timestamp default now(),
primary key(no));

alter table test.tbl_reply add constraint fk_board foreign key (bno) references tbl_board(no);