# 概要
BooksのPostgreSQL版

# URL
https://books-postgresql.herokuapp.com/  にアクセス  
http://localhost:8080/  にアクセス

# JdbcTemplateのまとめ

■複数行取得
- JdbcTemplate.query() + BeanPropertyRowMapper
- JdbcTemplate.queryForList()


■１行取得
- JdbcTemplate.queryForMap()
- 検索結果が０行の時、DataAccessExceptionが、throwされる。
- （注意）使わないほうがいい => 複数行取得でやったほうがいい
- （参考）[DataAccessExceptionをcatchするプログラム](https://github.com/Megafriday/SpringBoot_Kaitai3/blob/master/src/main/java/com/example/dao/HelloRepository.java)


■１カラム取得
- JdbcTemplate.queryForObject()
- 検索結果が０行の時、DataAccessExceptionが、throwされる。


■INSERT, UPDATE, DELETE文
- JdbcTemplate.update()
- 戻り値は、対象行数
