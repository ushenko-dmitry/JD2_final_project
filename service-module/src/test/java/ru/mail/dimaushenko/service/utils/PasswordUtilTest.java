package ru.mail.dimaushenko.service.utils;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import static ru.mail.dimaushenko.service.utils.PasswordUtil.generatePassword;

public class PasswordUtilTest {

    private static final Integer PASSWORD_SIZE = 8;
    
    @Test
    public void testGeneratePassword_IsValidLength() {
        String password = generatePassword();
        Assertions.assertThat(password.length()).isEqualByComparingTo(PASSWORD_SIZE);
    }
    
}
