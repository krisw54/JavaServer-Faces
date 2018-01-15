package managedbean;

import dbase.VowelCountTable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named(value = "vowelCount")
@RequestScoped
public class VowelCount
{

    private String text;
    private Map<String,Integer> vowelCount = new HashMap<>(5);
    private Map<String,Integer> totalVowelCount;
    
    public VowelCount()
    {
        vowelCount.put("A", 0);
        vowelCount.put("E", 0);
        vowelCount.put("I", 0);
        vowelCount.put("O", 0);
        vowelCount.put("U", 0);
    }

    public String getText()
    {
        return text;
    }

    public int getNumberOfA()
    {
        return vowelCount.get("A");
    }

    public int getNumberOfE()
    {
        return vowelCount.get("E");
    }

    public int getNumberOfI()
    {
        return vowelCount.get("I");
    }

    public int getNumberOfO()
    {
        return vowelCount.get("O");
    }

    public int getNumberOfU()
    {
        return vowelCount.get("U");
    }

    public int getTotalA()
    {
        return totalVowelCount.get("A");
    }

    public int getTotalE()
    {
        return totalVowelCount.get("E");
    }

    public int getTotalI()
    {
        return totalVowelCount.get("I");
    }

    public int getTotalO()
    {
        return totalVowelCount.get("O");
    }

    public int getTotalU()
    {
        return totalVowelCount.get("U");
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public String countVowels()
    {
        for (char c : text.toUpperCase().toCharArray())
        {
            if (vowelCount.containsKey(""+c))
            {
                vowelCount.replace(""+c, vowelCount.get(""+c)+1);
            }
        }
        updateTotalVowels();
        return "vowelCount";
    }

    private void updateTotalVowels()
    {
        VowelCountTable vcTable = new VowelCountTable();
        try
        {
            totalVowelCount = vcTable.updateTotalVowels(vowelCount);
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }

    }
}
