package managedbeans;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "userValidator")
public class UserValidator implements Serializable, Validator
{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException
    {
        String message = "";

        if (component.getId().equals("firstname"))
        {
            String nameToCheck = (String) value;

            if (nameToCheck.equalsIgnoreCase("Jimmy"))
            {
                message = "The first name cannot be 'Jimmy'";
                throw new ValidatorException(new FacesMessage(message));
            }
        }
        else if (component.getId().equals("lastname"))
        {
            String nameToCheck = (String) value;

            if (!nameToCheck.matches("[a-zA-Z]+"))
            {
                message = "The last name can only contain alphabetic characters";
                throw new ValidatorException(new FacesMessage(message));
            }
        }
        else
        {
            message = "Validator has been asked to check an unknown component: " + component.getId();
            throw new ValidatorException(new FacesMessage(message));

        }
    }
}
