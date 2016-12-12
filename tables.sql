create table englishword (
id int primary key not null auto_increment,
word char(255) not null,
part enum('名詞','代名詞','動詞','形容詞','副詞','冠詞','助動詞','前置詞','疑問詞','接続詞') not null,
mean char(255) not null,
created_at timestamp not null default current_timestamp,
updated_at timestamp not null default current_timestamp on update current_timestamp
) default charset=utf8; /* Need to define charset expressly if you want to use Japanese in SQL statements on Linux. */
