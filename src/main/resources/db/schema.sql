drop table if exists category CASCADE;
create table category (
      id bigint not null,
      cate_name varchar(255) not null,
      disp_yn boolean not null,
      large_cate_id bigint,
      level int not null,
      medium_cate_id bigint,
      small_cate_id bigint,
      primary key (id)
);