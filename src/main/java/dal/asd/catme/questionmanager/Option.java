package dal.asd.catme.questionmanager;

public class Option
{
    String displayText;
    int storedAs;

    public Option()
    {
    }

    public Option(String displayText, int storedAs)
    {
        this.displayText = displayText;
        this.storedAs = storedAs;
    }

    public Option(int storedAs)
    {
        this.storedAs = storedAs;
    }

    public String getDisplayText()
    {
        return displayText;
    }

    public void setDisplayText(String displayText)
    {
        this.displayText = displayText;
    }

    public int getStoredAs()
    {
        return storedAs;
    }

    public void setStoredAs(int storedAs)
    {
        this.storedAs = storedAs;
    }

}
