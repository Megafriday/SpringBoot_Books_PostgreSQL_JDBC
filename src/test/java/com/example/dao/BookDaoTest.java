package com.example.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Book;


@RunWith(SpringRunner.class)
@SpringBootTest
@Sql("/testData.sql")
@Transactional
public class BookDaoTest {

	@Autowired
	BookDao bookDao;

	@Test
	public void testFindAll1() {

		Optional<List<Book>> optional = bookDao.findAll1();
		List<Book> books = optional.orElse(null);
		Book book = books.get(0);
		assertEquals(book.getTitle(), "テスト用Java入門");
	}

	@Test
	public void testFindCount() {

		int count = bookDao.findCount();
		assertEquals(count, 3);

	}

}
