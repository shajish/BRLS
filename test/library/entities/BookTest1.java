package library.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;


import javafx.beans.binding.When;
import library.entities.IBook.BookState;
import library.entities.helpers.BookHelper;
import library.entities.helpers.LoanHelper;
import library.entities.helpers.PatronHelper;
@ExtendWith(MockitoExtension.class)
class BookTest1 {
	
	IBook book;
	@Mock IBook BookMock;
	String title= "t";
	String author = "a";
	String callNumber = "c1";
	int id=1;
	
		@BeforeEach
	void setUp() throws Exception {
			MockitoAnnotations.initMocks(this);
		book=new Book(author,title,callNumber,id);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testBorrowFRomLibraryWhenAvailable() {
		//arrange
		book=new Book(author,title,callNumber,id,BookState.AVAILABLE);
		//act
		book.borrowFromLibrary();
		//assert
		assertTrue(book.isOnLoan());
	}

	@Test
	void testBorrowFromLibraryWhenOnLoan() {
		//arrange
		book=new Book(author,title,callNumber,id,BookState.ON_LOAN);
		//act
		Executable e = () ->book.borrowFromLibrary();
		Throwable t = assertThrows(RuntimeException.class, e);
		//assert
		assertEquals(t.getMessage(), "Book: cannot borrow while book is in state: ON_LOAN");
	}
}
