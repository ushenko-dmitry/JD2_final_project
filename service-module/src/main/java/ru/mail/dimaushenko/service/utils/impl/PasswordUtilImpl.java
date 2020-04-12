package ru.mail.dimaushenko.service.utils.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Component;
import ru.mail.dimaushenko.service.utils.PasswordUtil;

@Component
public class PasswordUtilImpl implements PasswordUtil {

    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String PUNCTUATION = "!@#$%&*()_+-=[]|,./?><";
    private static final Integer LENGTH = 8;

    @Override
    public String generatePassword() {
        StringBuilder password = new StringBuilder(LENGTH);
        Random random = new Random(System.nanoTime());

        List<String> charCategories = new ArrayList<>(4);
        charCategories.add(LOWER);
        charCategories.add(UPPER);
        charCategories.add(DIGITS);
        charCategories.add(PUNCTUATION);

        for (int i = 0; i < LENGTH; i++) {
            String charCategory = charCategories.get(random.nextInt(charCategories.size()));
            int position = random.nextInt(charCategory.length());
            password.append(charCategory.charAt(position));
        }
        return new String(password);
    }

}
