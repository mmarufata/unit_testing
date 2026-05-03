import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidationTest {

    private Validation validation;

    @BeforeEach
    void setUp() {
        validation = new Validation();
    }

    @AfterEach
    void tearDown() {
        validation = null;
    }

    @Test
    void firstNameEmptyShouldFail() {
        assertFalse(validation.isNameValid(""));
    }

    @Test
    void firstNameLongerThanTwentyShouldFail() {
        assertFalse(validation.isNameValid("ABCDEFGHIJKLMNOPQRSTUVWXYZ"));
    }

    @Test
    void validFirstNameShouldPass() {
        assertTrue(validation.isNameValid("Meral"));
    }

    @Test
    void lastNameWithSpecialCharacterShouldFail() {
        assertFalse(validation.isNameValid("Ata!"));
    }

    @Test
    void singleCharacterLastNameShouldFail() {
        assertFalse(validation.isNameValid("A"));
    }

    @Test
    void validLastNameShouldPass() {
        assertTrue(validation.isNameValid("Yilmaz"));
    }

    @Test
    void emailWithoutAtOrDomainShouldFail() {
        assertFalse(validation.isEmailValid("meral.example.com"));
        assertFalse(validation.isEmailValid("meral@"));
    }

    @Test
    void validEmailShouldPass() {
        assertTrue(validation.isEmailValid("meral@example.com"));
    }
}
