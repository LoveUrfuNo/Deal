package springbackend.validator;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import springbackend.model.User;

/**
 * Validator for {@link springbackend.model.User} class,
 * implements {@link Validator} interface.
 */

@Component
public class UserOptionsValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "Required");
        if (!errors.getAllErrors().contains(errors.getFieldError("firstName"))) {
            if (user.getFirstName().length() < 2 || user.getFirstName().length() > 32) {
                errors.rejectValue("firstName", "Size.userForm.firstname");
            }
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "Required");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "country", "Required");
    }
}
