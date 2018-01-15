package managed_bean;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named(value = "timeUtility")
@RequestScoped
public class TimeUtility
{
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
    
    public String getCurrentTime()
    {
        return sdf.format(Calendar.getInstance().getTime());
    }
}
