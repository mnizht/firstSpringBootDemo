drop table if exists people;
create table people (
	id bigint(20) not null AUTO_INCREMENT primary key COMMENT '主键',
	name varchar(20) not null comment '名称',
	age int(5) default null comment '年龄'
) engine=innodb default charset=utf8mb4;