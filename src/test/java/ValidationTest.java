import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

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

    // --- ARKADAŞININ TESTLERİ (NAME & EMAIL) ---

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

    // --- SENİN TESTLERİN (PASSWORD & DOB) ---

    @Test
    @DisplayName("Şifre: Çok kısa (Sınır değer altı)")
    void passwordTooShort() {
        assertFalse(validation.isPasswordValid("Ab1!")); 
    }

    @Test
    @DisplayName("Şifre: Tam sınır (6 karakter - Geçerli)")
    void passwordBoundary() {
        assertTrue(validation.isPasswordValid("Ab1!23")); 
    }

    @Test
    @DisplayName("Şifre: Büyük harf eksik")
    void noUppercase() {
        assertFalse(validation.isPasswordValid("abc123!@"));
    }

    @Test
    @DisplayName("Şifre: Küçük harf eksik")
    void noLowercase() {
        assertFalse(validation.isPasswordValid("ABC123!@"));
    }

    @Test
    @DisplayName("Şifre: Rakam eksik")
    void noDigit() {
        assertFalse(validation.isPasswordValid("Abcdef!@"));
    }

    @Test
    @DisplayName("Şifre: Özel karakter eksik")
    void noSpecialChar() {
        assertFalse(validation.isPasswordValid("Abc12345"));
    }

    @Test
    @DisplayName("Şifre: Null girişi (Hacker Test)")
    void passwordNull() {
        assertFalse(validation.isPasswordValid(null));
    }

    @Test
    @DisplayName("Şifre: Sadece boşluk (Hacker Test)")
    void passwordOnlySpaces() {
        assertFalse(validation.isPasswordValid("        "));
    }

    @Test
    @DisplayName("Şifre: Çok uzun karakter (Buffer Test)")
    void passwordTooLong() {
        assertFalse(validation.isPasswordValid("A".repeat(100)));
    }

    @Test
    @DisplayName("Şifre: Geçerli şifre")
    void validPassword() {
        assertTrue(validation.isPasswordValid("Abc123!@"));
    }

    @Test
    @DisplayName("Eşleşme: Başarılı")
    void passwordMatch() {
        assertTrue(validation.passwordsMatch("abc123!", "abc123!"));
    }

    @Test
    @DisplayName("Eşleşme: Hatalı (Farklı şifreler)")
    void passwordMismatch() {
        assertFalse(validation.passwordsMatch("abc", "xyz"));
    }

    @Test
    @DisplayName("Eşleşme: Biri null (Hacker Test)")
    void matchOneNull() {
        assertFalse(validation.passwordsMatch("abc", null));
    }

    @Test
    @DisplayName("Eşleşme: Büyük-Küçük harf duyarlılığı")
    void matchCaseSensitive() {
        assertFalse(validation.passwordsMatch("Abc", "abc"));
    }

    @Test
    @DisplayName("Eşleşme: Boşluk farkı")
    void matchWithSpace() {
        assertFalse(validation.passwordsMatch("abc ", "abc"));
    }

    @Test
    @DisplayName("DOB: Geçerli format")
    void validDOB() {
        assertTrue(validation.isDOBValid("12/05/2003"));
    }

    @Test
    @DisplayName("DOB: Yanlış format (Yıl-Ay-Gün)")
    void invalidDOBFormat() {
        assertFalse(validation.isDOBValid("2003-05-12"));
    }

    @Test
    @DisplayName("DOB: Harf içeren tarih (Hacker Test)")
    void dobWithLetters() {
        assertFalse(validation.isDOBValid("12/AB/2003"));
    }

    @Test
    @DisplayName("DOB: SQL Injection denemesi (Hacker Test)")
    void dobSQLInjection() {
        assertFalse(validation.isDOBValid("12/05/2003; DROP TABLE users"));
    }

    @Test
    @DisplayName("DOB: Boş giriş")
    void dobEmpty() {
        assertFalse(validation.isDOBValid(""));
    }
}