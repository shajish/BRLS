package library.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BookTest {

	IBook book;
	String title= "t";
	String author = "a";
	String callNumber = "c1";
	int id=1;
	
	@BeforeEach
	void setUp() throws Exception {
		book=new Book(author,title,callNumber,id);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testIsAvailable() {
		//arrange
		boolean expected = true;
		//act
		boolean actual = book.isAvailable();
		//assert
		assertEquals(expected, actual);
	}

}
