package converter;

import dto.DiscountDTO;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "discountConverter")
public class DiscountConverter implements Converter
{
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String newValue) throws ConverterException
    {
        String[] part = newValue.split("[\\(\\%]");
        try
        {
            return new DiscountDTO(part[0], Double.parseDouble(part[1]));
        }
        catch (NumberFormatException nfe)
        {
            throw new ConverterException("Rate failed to convert");
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException
    {
        if (!(value instanceof DiscountDTO))
        {
            throw new ConverterException("Value not a DiscountDTO object");
        }
        
        DiscountDTO d = (DiscountDTO)value;
        return String.format("%s (%1.2f%s", d.getCode(), d.getRate(), "%)");
    }

}
