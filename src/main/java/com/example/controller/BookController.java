package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.Book;
import com.example.form.BookForm;
import com.example.service.BookService;

@Controller
public class BookController {

	@Autowired
	BookService bookService;

	// JdbcTemplate.query() + BeanPropertyRowMapper
	@GetMapping("/books1")
	public String getBooks1(Model model) {

		List<Book> books = bookService.findAll1().orElse(null);
		model.addAttribute("books", books);

		return "books1";
	}

	// JdbcTemplate.queryForList()
	@GetMapping("/books2")
	public String getBooks2(Model model) {

		List<Map<String, Object>> books
		= bookService.findAll2().orElse(null);

		model.addAttribute("books", books);

		return "books2";
	}

	@GetMapping("/search")
	public String getSearch(Model model) {
		return "search";
	}

	@PostMapping("/search")
	public String postSearch(@RequestParam String id,
			Model model) {

		List<Map<String,Object>> books
		= bookService.findOne(id).orElse(null);

		model.addAttribute("id", id);
		model.addAttribute("books", books);

		return "search";
	}

	@GetMapping("/count")
	public String getCount(Model model) {
		int count = bookService.findCount();
		model.addAttribute("count", count);
		return "count";
	}

	@GetMapping("/insert")
	public String getInsert(
			@ModelAttribute BookForm bookForm,
			Model model) {

		model.addAttribute("authors", getAuthors());
		return "insert";
	}

	@PostMapping("/insert")
	public String postInsert(
			@ModelAttribute @Validated BookForm bookForm,
			BindingResult bindingResult,
			Model model
			) {

		if (bindingResult.hasErrors()) {
			// return をつけないとプログラムが止まらない
			return getInsert(bookForm, model);
		}

		bookService.insert(bookForm);

		return "redirect:/books2";
	}

	@DeleteMapping("/delete/{id}")
	public String deleteDelete(@PathVariable int id) {

		bookService.delete(id);
		return "redirect:/books2";
	}

	@GetMapping("/update/{id}")
	public String getUpdate(
			@ModelAttribute BookForm bookForm,
			@PathVariable int id,
			Model model
			) {
		model.addAttribute("authors", getAuthors());
		System.out.println("id:" + id);
		return "update";
	}

	@PutMapping("/update")
	public String putUpdate(
			@ModelAttribute	@Validated BookForm bookForm,
			BindingResult bindingResult,
			Model model
			) {

		if(bindingResult.hasErrors()) {
			//			return getUpdate(bookForm, model);
		}

		bookService.update(bookForm);
		return "redirect:/book2";
	}




	private Map<String, String> getAuthors() {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("dynamite", "ダイナマイト・キッド");
		map.put("tiger", "タイガーマスク");
		map.put("inoki", "アントニオ猪木");

		return map;
	}



}
