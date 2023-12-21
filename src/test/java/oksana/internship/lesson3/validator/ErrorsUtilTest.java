package oksana.internship.lesson3.validator;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BindingResult;
import org.testcontainers.shaded.org.checkerframework.checker.units.qual.A;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

class ErrorsUtilTest {

    @Test
    public void shouldValidationException() {
        String errors = ("FullName is null,YearOfBirth is invalid,GroupName is invalid");


        var exceptions = new Exceptions(errors);
        var response = new Errors(exceptions.getMessage());
        assertNotEquals(response,null);

    }
}
