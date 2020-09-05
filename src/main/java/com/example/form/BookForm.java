package com.example.form;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class BookForm {

	private String id;

	@NotBlank
	private String title;

	private String author;

	@NotNull
	@DateTimeFormat(pattern="yyyy/MM/dd")
	private Date publishDate;

	@Min(100)
	private int price;

}
