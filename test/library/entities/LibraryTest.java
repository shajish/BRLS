package library.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

import javafx.beans.binding.When;
import library.entities.helpers.BookHelper;
import library.entities.helpers.LoanHelper;
import library.entities.helpers.PatronHelper;
@ExtendWith(MockitoExtension.class)
class LibraryTest {
	@Mock IPatron PatronMock;
	ILibrary library;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		library = new Library(new BookHelper(),new PatronHelper(),new LoanHelper());
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testPatronCanBorrowWhenAllConditionsTrue() {
		//arrange
		when(PatronMock.getNumberOfCurrentLoans()).thenReturn(0);
		when(PatronMock.getFinesPayable()).thenReturn((0.0));
		when(PatronMock.hasOverDueLoans()).thenReturn(false);
		boolean expected = true;
		//act
		boolean actual = library.patronCanBorrow(PatronMock);
		//assert
		assertEquals(expected, actual);
	}
	
	@Test
	void testPatronCanBorrowWhenLoanLimitIsCrossed() {
		//arrange
		when(PatronMock.getNumberOfCurrentLoans()).thenReturn(ILibrary.LOAN_LIMIT +1);
		boolean expected = false;
		//act
		boolean actual = library.patronCanBorrow(PatronMock);
		//assert
		assertEquals(expected, actual);
	}

}
