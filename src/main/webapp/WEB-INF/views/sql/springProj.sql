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

use test;

-- 회원 테이블 생성
create table tbl_user(
uid varchar(50) not null,
upw varchar(50) not null,
uname varchar(100),
upoint int(11),
primary key (uid));

alter table tbl_user modify upoint int default 0;

-- message 테이블 생성
create table tbl_message(
mid int(11) not null auto_increment,
targetid varchar(50),
sender varchar(50),
message varchar(100),
opendate timestamp,
senddate timestamp,
primary key (mid));

-- targetid fk 설정
alter table tbl_message add constraint fk_targetid foreign key(targetid) references tbl_user (uid);

-- sender fk 설정
alter table tbl_message add constraint fk_sender foreign key(sender) references tbl_user (uid);

-- writer fk 설정
alter table tbl_board add constraint fk_writer2 foreign key(writer) references tbl_user (uid);

-- replyer fk 설정
alter table tbl_reply add constraint fk_reply_replyer foreign key(replyer) references tbl_user (uid);

select distinct writer from tbl_board;

insert into tbl_user(uid, upw, uname) values ('작성자', '1234', 'writer');
insert into tbl_user(uid, upw, uname) values ('어드민', '1234', 'adminnn');
insert into tbl_user(uid, upw, uname) values ('admin', '1234', '관리자');
insert into tbl_user(uid, upw, uname) values ('hyoniki', '1234', '효니키');
insert into tbl_user(uid, upw, uname) values ('shw', '1234', '서효원');

select distinct replyer from tbl_reply;

select * from tbl_user;

-- 쿠키의 소멸 시간을 저장할 컬럼 (쿠키 유효 시간)
alter table tbl_user add column seskey varchar(50) not null default '';
alter table tbl_user add column sesdeadline timestamp;
