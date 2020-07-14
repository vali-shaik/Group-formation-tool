package dal.asd.catme.questionmanager;

public interface IOption
{
    String getDisplayText();
    void setDisplayText(String displayText);
    int getStoredAs();
    void setStoredAs(int storedAs);
}
