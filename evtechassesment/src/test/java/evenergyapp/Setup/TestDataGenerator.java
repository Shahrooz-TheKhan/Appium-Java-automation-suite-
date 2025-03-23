package evenergyapp.Setup;

import com.github.javafaker.Faker;

public class TestDataGenerator {
    private final Faker faker = new Faker();

    public String generateFullName() {
        return faker.name().fullName();
    }

    public String generateEmail() {
        return faker.internet().emailAddress();
    }

    public String generatePassword() {
        return faker.internet().password(8, 15);
    }
}
