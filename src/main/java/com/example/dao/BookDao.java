package com.example.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.entity.Book;
import com.example.form.BookForm;

@Repository
public class BookDao {

	@Autowired
	JdbcTemplate jdbcTemplate;


	// JdbcTemplate.query() + BeanPropertyRowMapper
	public Optional<List<Book>> findAll1() {

		String sql = "select * from books";

		BeanPropertyRowMapper<Book> rowMapper
		= new BeanPropertyRowMapper<Book>(Book.class);

		List<Book> books = jdbcTemplate.query(sql, rowMapper);

		return Optional.ofNullable(books);

	}


	// JdbcTemplate.queryForList()
	public Optional<List<Map<String, Object>>> findAll2() {

		String sql = "select * from books";

		List<Map<String, Object>> books
		= jdbcTemplate.queryForList(sql);

		return Optional.ofNullable(books);
	}


	// １行取得なら、JdbcTemplate.queryForMap() ⇒　使わない方が良い
	public Optional<List<Map<String, Object>>> findOne(String id) {

		String sql = "select * from books where id = ?";

		List<Map<String,Object>> books
		= jdbcTemplate.queryForList(sql, id);

		return Optional.ofNullable(books);
	}


	// １カラム取得なら、JdbcTemplate.queryForObject()
	public int findCount() {

		String sql = "select count(*) from books";

		return jdbcTemplate.queryForObject(sql, Integer.class);

	}


	// insert, update, delete は、JdbcTemplate.update()を使う
	public int insert(BookForm bookForm) {

		// これをメンバ変数で書いていたらエラーになる。フレームワーク怖い。
		String sql = "insert into books(title, author, publish_date, price) " +
				"values (?,?,?,?)";

		return jdbcTemplate.update(
				sql,
				bookForm.getTitle(),
				bookForm.getAuthor(),
				bookForm.getPublishDate(),
				bookForm.getPrice()
				);
	}


	public int delete(int id) {

		String sql = "delete from books where id = ?";

		return jdbcTemplate.update(sql, id);
	}


	public int update(BookForm bookForm) {

		String sql = "update books set title=?, author=?, publish_date=?, price=? where id=?";

		return jdbcTemplate.update(
				sql,
				bookForm.getTitle(),
				bookForm.getAuthor(),
				bookForm.getPublishDate(),
				bookForm.getPrice(),
				bookForm.getId()
				);
	}

}
