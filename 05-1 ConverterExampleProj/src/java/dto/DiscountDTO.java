package dto;

public class DiscountDTO
{
    private final String code;
    private final double rate;

    public DiscountDTO(String code, double rate)
    {
        this.code = code;
        this.rate = rate;
    }

    public String getCode()
    {
        return code;
    }

    public double getRate()
    {
        return rate;
    }
}
