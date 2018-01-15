package managedbean;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named(value = "vowelCount")
@RequestScoped
public class VowelCount
{
    private String text;
    private int[] vowelCount = {0, 0, 0, 0, 0};
    
    public String getText()
    {
        return text;
    }
    
    public int getNumberOfA()
    {
        return vowelCount[0];
    }

    public int getNumberOfE()
    {
        return vowelCount[1];
    }

    public int getNumberOfI()
    {
        return vowelCount[2];
    }

    public int getNumberOfO()
    {
        return vowelCount[3];
    }

    public int getNumberOfU()
    {
        return vowelCount[4];
    }

    public void setText(String text)
    {
        this.text = text;
        countVowels();
    }
    
    private void countVowels()
    {
        for (char c : text.toLowerCase().toCharArray())
        {
            switch (c)
            {
                case 'a':
                    vowelCount[0]++;
                    break;
                case 'e':
                    vowelCount[1]++;
                    break;
                case 'i':
                    vowelCount[2]++;
                    break;
                case 'o':
                    vowelCount[3]++;
                    break;
                case 'u':
                    vowelCount[4]++;
                    break;
            }
        }
    }
    
}
