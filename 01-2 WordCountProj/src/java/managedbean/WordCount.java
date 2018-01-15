package managedbean;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named(value = "wordCount")
@RequestScoped
public class WordCount
{
    private String text;
    private int numberOfWords = 0;
    
    public String getText()
    {
        return text;
    }
    
    public int getNumberOfWords()
    {
        return numberOfWords;
    }

    public void setText(String text)
    {
        this.text = text;
        countWords();
    }
    
    private void countWords()
    {
        String[] words = text.trim().split(" +");
        numberOfWords = words.length;
    }
    
}
