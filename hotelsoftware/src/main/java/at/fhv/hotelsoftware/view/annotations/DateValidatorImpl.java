package at.fhv.hotelsoftware.view.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DateValidatorImpl implements ConstraintValidator<DateNotNullOrEarlierThanToday, String> {


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        try {
            LocalDate date = LocalDate.parse(value);
            if(date.isBefore(LocalDate.now())){
                return false;
            }
        } catch (DateTimeParseException | NullPointerException e){
            return false;
        }

        return true;
    }


}
