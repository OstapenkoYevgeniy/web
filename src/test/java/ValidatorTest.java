import action.HttpServletRequestForTest;
import com.john.validator.*;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidatorTest {
    private HttpServletRequestForTest req = new HttpServletRequestForTest();

    @Test
    public void testNameValidation() {
        assertTrue(NameValidator.isValidName("Евгений", req));

        assertFalse(NameValidator.isValidName(null, req));
        assertFalse(NameValidator.isValidName("", req));
        assertFalse(NameValidator.isValidName("   ", req));
        assertFalse(NameValidator.isValidName("Yevg99eniy", req));
        assertFalse(NameValidator.isValidName("Yevg__eniy", req));
        assertFalse(NameValidator.isValidName("Ye!@#$%^&*", req));
        assertFalse(NameValidator.isValidName(" Yevgeniy", req));
        assertFalse(NameValidator.isValidName("Yevgeniy ", req));
    }

    @Test
    public void testIinValidation() {
        assertTrue(IinValidator.isValid("910321350527", req));
        assertFalse(IinValidator.isValid(" 910321350527", req));
        assertFalse(IinValidator.isValid("910321350527 ", req));

        assertFalse(IinValidator.isValid(null, req));
        assertFalse(IinValidator.isValid("", req));
        assertFalse(IinValidator.isValid("   ", req));
        assertFalse(IinValidator.isValid("91032 350527", req));
        assertFalse(IinValidator.isValid("91032q350527", req));
        assertFalse(IinValidator.isValid("910321350528", req));
        assertFalse(IinValidator.isValid("9103213502", req));
        assertFalse(IinValidator.isValid("910321350277", req));
    }

    @Test
    public void testPhoneValidation() {
        assertTrue(PhoneValidator.isValid("7751112233", req));
        assertFalse(PhoneValidator.isValid(" 7751112233", req));
        assertFalse(PhoneValidator.isValid("7751112233 ", req));

        assertFalse(PhoneValidator.isValid(null, req));
        assertFalse(PhoneValidator.isValid("", req));
        assertFalse(PhoneValidator.isValid("   ", req));
        assertFalse(PhoneValidator.isValid("7751f12233", req));
        assertFalse(PhoneValidator.isValid("775111223", req));
        assertFalse(PhoneValidator.isValid("77511122033", req));
    }

    @Test
    public void testIdentifierValidator() {
        assertTrue(IdentifierValidator.isValid("111222333444", req));
        assertFalse(IdentifierValidator.isValid(" 111222333444", req));
        assertFalse(IdentifierValidator.isValid("111222333444 ", req));

        assertFalse(IdentifierValidator.isValid("", req));
        assertFalse(IdentifierValidator.isValid(null, req));
        assertFalse(IdentifierValidator.isValid("   ", req));
        assertFalse(IdentifierValidator.isValid("1112q2333444", req));
        assertFalse(IdentifierValidator.isValid("11122233344", req));
        assertFalse(IdentifierValidator.isValid("1112223334444", req));
    }

    @Test
    public void testSecurityCodeValidator() {
        assertTrue(SecurityCodeValidator.isValid("111", req));
        assertFalse(SecurityCodeValidator.isValid(" 111", req));
        assertFalse(SecurityCodeValidator.isValid("111 ", req));

        assertFalse(SecurityCodeValidator.isValid("", req));
        assertFalse(SecurityCodeValidator.isValid("   ", req));
        assertFalse(SecurityCodeValidator.isValid(null, req));
        assertFalse(SecurityCodeValidator.isValid("1q11", req));
        assertFalse(SecurityCodeValidator.isValid("1111", req));
        assertFalse(SecurityCodeValidator.isValid("11", req));
    }

    @Test
    public void testPasswordValidator() {
        assertTrue(PasswordValidator.isValid("123y 678", req));

        assertFalse(PasswordValidator.isValid("1234567", req));
        assertFalse(PasswordValidator.isValid("123456789012345678901", req));
        assertFalse(PasswordValidator.isValid("", req));
        assertFalse(PasswordValidator.isValid("   ", req));
        assertFalse(PasswordValidator.isValid(null, req));
    }

    @Test
    public void testCodeWordValidator() {
        assertTrue(CodewordValidator.isValid("55q9", req));
        assertFalse(CodewordValidator.isValid(" 55q9", req));
        assertFalse(CodewordValidator.isValid("55q9 ", req));

        assertFalse(CodewordValidator.isValid("123", req));
        assertFalse(CodewordValidator.isValid("12345678901", req));
        assertFalse(CodewordValidator.isValid(null, req));
        assertFalse(CodewordValidator.isValid("", req));
        assertFalse(CodewordValidator.isValid("   ", req));
    }

    @Test
    public void testCardNumberValidator() {
        assertFalse(CardNumberValidator.isValid(null, req));
        assertFalse(CardNumberValidator.isValid("", req));
        assertFalse(CardNumberValidator.isValid("   ", req));
        assertFalse(CardNumberValidator.isValid("111122223333444", req));
        assertFalse(CardNumberValidator.isValid("11112222333344444", req));

        assertFalse(CardNumberValidator.isValid("11112222q3334444", req));
        assertFalse(CardNumberValidator.isValid(" 1111222233334444", req));
        assertFalse(CardNumberValidator.isValid("1111222233334444 ", req));
        assertTrue(CardNumberValidator.isValid("1111222233334444", req));
    }

    @Test
    public void testCardExpiryDateValidator() {
        assertTrue(CardExpiryDateValidator.isValid("0135", req));

        assertFalse(CardExpiryDateValidator.isValid(null, req));
        assertFalse(CardExpiryDateValidator.isValid("", req));
        assertFalse(CardExpiryDateValidator.isValid(" ", req));
        assertFalse(CardExpiryDateValidator.isValid("1818", req));
        assertFalse(CardExpiryDateValidator.isValid("118", req));
        assertFalse(CardExpiryDateValidator.isValid("01016", req));
    }

    @Test
    public void testBirthdayValidator() {
        assertTrue(BirthdayValidator.isValid("1991-03-21", req));

        assertFalse(BirthdayValidator.isValid("1991-13-21", req));
        assertFalse(BirthdayValidator.isValid("21-03-1991", req));
        assertFalse(BirthdayValidator.isValid(null, req));
        assertFalse(BirthdayValidator.isValid("", req));
        assertFalse(BirthdayValidator.isValid("  ", req));
    }

    @Test
    public void testAmountValidator() {
        assertTrue(AmountValidator.isValid("50000.85", req));

        assertFalse(AmountValidator.isValid("50w00", req));
        assertFalse(AmountValidator.isValid(null, req));
        assertFalse(AmountValidator.isValid("", req));
        assertFalse(AmountValidator.isValid("  ", req));
        assertFalse(AmountValidator.isValid("-500", req));
        assertFalse(AmountValidator.isValid("150001", req));
    }
}
