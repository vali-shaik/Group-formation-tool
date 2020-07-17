package dal.asd.catme.algorithm;

import java.util.HashSet;
import java.util.Set;

public class MCQMultiple
{
    public Set getAnswers()
    {
        return answers;
    }

    public void setAnswers(Set answers)
    {
        this.answers = answers;
    }

    Set answers;

    MCQMultiple(HashSet<Integer> ls)
    {
        this.answers = ls;
    }
}
