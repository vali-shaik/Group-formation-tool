package dal.asd.catme.algorithm;

import java.util.*;

public class Algorithm {
    class Question {
        int rule;
        int questionId;
        int priority;
        int totalNoOfOptions;

        public Question(int rule, int questionId, int priority) {
            this.rule = rule;
            this.questionId = questionId;
            this.priority = priority;
        }
        public Question(int rule, int questionId, int priority, int totalNoOfOptions) {
            this.rule = rule;
            this.questionId = questionId;
            this.priority = priority;
            this.totalNoOfOptions = totalNoOfOptions;
        }
    }
    class MCQMultiple{
        public Set getAnswers() {
            return answers;
        }

        public void setAnswers(Set answers) {
            this.answers = answers;
        }
        Set answers;
        MCQMultiple(HashSet<Integer> ls){
            this.answers = ls;
        }
    }
    List<Question> questionTypeOne = new ArrayList<>();
    List<Question> questionTypeTwo = new ArrayList<>();
    List students = new ArrayList<String>();

    //Size of one group, number of students
    int groupSize = 2;
    int noOfGroups = 4;
    int noOfStudents = 8;
    int noOfQuestionsMcqNumeric = 8;
    int noOfQuestionsMcqMultiple = 3;

    int answerTableMcqNumeric[][] = new int[noOfQuestionsMcqNumeric][noOfStudents];
    MCQMultiple answerTableMcqMultiple[][] = new MCQMultiple[noOfQuestionsMcqMultiple][noOfStudents];

    int priorityTable[][] = new int[noOfStudents][noOfStudents];
    String groups[][] = new String[noOfGroups][groupSize];
    Map finalGroups = new HashMap<String,ArrayList<String>>();
    List memoization = new ArrayList<String>();
    List remainingStudents;

    Algorithm(){
        answerTableMcqNumeric[0][0] = 1;
        answerTableMcqNumeric[0][1] = 2;
        answerTableMcqNumeric[0][2] = 1;
        answerTableMcqNumeric[0][3] = 3;
        answerTableMcqNumeric[0][4] = 2;
        answerTableMcqNumeric[0][5] = 1;
        answerTableMcqNumeric[0][6] = 2;
        answerTableMcqNumeric[0][7] = 2;
        answerTableMcqNumeric[1][0] = 3;
        answerTableMcqNumeric[1][1] = 2;
        answerTableMcqNumeric[1][2] = 3;
        answerTableMcqNumeric[1][3] = 3;
        answerTableMcqNumeric[1][4] = 1;
        answerTableMcqNumeric[1][5] = 3;
        answerTableMcqNumeric[1][6] = 2;
        answerTableMcqNumeric[1][7] = 2;
        answerTableMcqNumeric[2][0] = 2;
        answerTableMcqNumeric[2][1] = 3;
        answerTableMcqNumeric[2][2] = 2;
        answerTableMcqNumeric[2][3] = 2;
        answerTableMcqNumeric[2][4] = 3;
        answerTableMcqNumeric[2][5] = 1;
        answerTableMcqNumeric[2][6] = 2;
        answerTableMcqNumeric[2][7] = 2;
        answerTableMcqNumeric[3][0] = 4;
        answerTableMcqNumeric[3][1] = 2;
        answerTableMcqNumeric[3][2] = 4;
        answerTableMcqNumeric[3][3] = 3;
        answerTableMcqNumeric[3][4] = 3;
        answerTableMcqNumeric[3][5] = 2;
        answerTableMcqNumeric[3][6] = 4;
        answerTableMcqNumeric[3][7] = 3;
        answerTableMcqNumeric[4][0] = 2;
        answerTableMcqNumeric[4][1] = 1;
        answerTableMcqNumeric[4][2] = 2;
        answerTableMcqNumeric[4][3] = 2;
        answerTableMcqNumeric[4][4] = 3;
        answerTableMcqNumeric[4][5] = 1;
        answerTableMcqNumeric[4][6] = 1;
        answerTableMcqNumeric[4][7] = 2;
        answerTableMcqNumeric[5][0] = 3;
        answerTableMcqNumeric[5][1] = 2;
        answerTableMcqNumeric[5][2] = 3;
        answerTableMcqNumeric[5][3] = 2;
        answerTableMcqNumeric[5][4] = 3;
        answerTableMcqNumeric[5][5] = 4;
        answerTableMcqNumeric[5][6] = 3;
        answerTableMcqNumeric[5][7] = 1;
        answerTableMcqNumeric[6][0] = 1;
        answerTableMcqNumeric[6][1] = 4;
        answerTableMcqNumeric[6][2] = 1;
        answerTableMcqNumeric[6][3] = 3;
        answerTableMcqNumeric[6][4] = 5;
        answerTableMcqNumeric[6][5] = 2;
        answerTableMcqNumeric[6][6] = 2;
        answerTableMcqNumeric[6][7] = 5;
        answerTableMcqNumeric[7][0] = 2;
        answerTableMcqNumeric[7][1] = 3;
        answerTableMcqNumeric[7][2] = 2;
        answerTableMcqNumeric[7][3] = 2;
        answerTableMcqNumeric[7][4] = 3;
        answerTableMcqNumeric[7][5] = 3;
        answerTableMcqNumeric[7][6] = 3;
        answerTableMcqNumeric[7][7] = 2;

        answerTableMcqMultiple[0][0] = new MCQMultiple(new HashSet<Integer>(Arrays.asList(1,2,3)));
        answerTableMcqMultiple[0][1] = new MCQMultiple(new HashSet<Integer>(Arrays.asList(1,2,3)));
        answerTableMcqMultiple[0][2] = new MCQMultiple(new HashSet<Integer>(Arrays.asList(5,2,3)));
        answerTableMcqMultiple[0][3] = new MCQMultiple(new HashSet<Integer>(Arrays.asList(1,3)));
        answerTableMcqMultiple[0][4] = new MCQMultiple(new HashSet<Integer>(Arrays.asList(1,2,5)));
        answerTableMcqMultiple[0][5] = new MCQMultiple(new HashSet<Integer>(Arrays.asList(3,5)));
        answerTableMcqMultiple[0][6] = new MCQMultiple(new HashSet<Integer>(Arrays.asList(1,2,3)));
        answerTableMcqMultiple[0][7] = new MCQMultiple(new HashSet<Integer>(Arrays.asList(2,3)));
        answerTableMcqMultiple[1][0] = new MCQMultiple(new HashSet<Integer>(Arrays.asList(2,1)));
        answerTableMcqMultiple[1][1] = new MCQMultiple(new HashSet<Integer>(Arrays.asList(2,3,5)));
        answerTableMcqMultiple[1][2] = new MCQMultiple(new HashSet<Integer>(Arrays.asList(2,1,5,3)));
        answerTableMcqMultiple[1][3] = new MCQMultiple(new HashSet<Integer>(Arrays.asList(2,3,4)));
        answerTableMcqMultiple[1][4] = new MCQMultiple(new HashSet<Integer>(Arrays.asList(2,4)));
        answerTableMcqMultiple[1][5] = new MCQMultiple(new HashSet<Integer>(Arrays.asList(2,1,5)));
        answerTableMcqMultiple[1][6] = new MCQMultiple(new HashSet<Integer>(Arrays.asList(2,3,1)));
        answerTableMcqMultiple[1][7] = new MCQMultiple(new HashSet<Integer>(Arrays.asList(4,2,3)));
        answerTableMcqMultiple[2][0] = new MCQMultiple(new HashSet<Integer>(Arrays.asList(5,2,1)));
        answerTableMcqMultiple[2][1] = new MCQMultiple(new HashSet<Integer>(Arrays.asList(2,1)));
        answerTableMcqMultiple[2][2] = new MCQMultiple(new HashSet<Integer>(Arrays.asList(2,3)));
        answerTableMcqMultiple[2][3] = new MCQMultiple(new HashSet<Integer>(Arrays.asList(2,4,1)));
        answerTableMcqMultiple[2][4] = new MCQMultiple(new HashSet<Integer>(Arrays.asList(2,3,5)));
        answerTableMcqMultiple[2][5] = new MCQMultiple(new HashSet<Integer>(Arrays.asList(2,5,3,1)));
        answerTableMcqMultiple[2][6] = new MCQMultiple(new HashSet<Integer>(Arrays.asList(2,3,4)));
        answerTableMcqMultiple[2][7] = new MCQMultiple(new HashSet<Integer>(Arrays.asList(2,1,4,5)));

        questionTypeOne.add(new Question(1,1,9));
        questionTypeOne.add(new Question(1,2,8));
        questionTypeOne.add(new Question(1,3,4));
        questionTypeOne.add(new Question(1,4,5));
        questionTypeOne.add(new Question(1,5,1));
        questionTypeOne.add(new Question(1,6,5));
        questionTypeOne.add(new Question(1,7,3));
        questionTypeOne.add(new Question(1,8,4));

        questionTypeTwo.add(new Question(1,9,10, 5));
        questionTypeTwo.add(new Question(1,10,5,5));
        questionTypeTwo.add(new Question(1,11,8, 5));
        students.add("s1");
        students.add("s2");
        students.add("s3");
        students.add("s4");
        students.add("s5");
        students.add("s6");
        students.add("s7");
        students.add("s8");
    }

    void bubbleSort(int i, String [] arr)
    {
        int n = noOfStudents;
        for (int k = 0; k < n-1; k++)
        {
            for (int j = 0; j < n-k-1; j++)
            {
                if (priorityTable[i][j] < priorityTable[i][j+1])
                {
                    int temp = priorityTable[i][j];
                    String temp2 = arr[j];
                    priorityTable[i][j] = priorityTable[i][j+1];
                    arr[j] = arr[j+1];
                    priorityTable[i][j+1] = temp;
                    arr[j+1] = temp2;
                }
            }
        }
    }

    void createGroups(){
        String[] studentsCopy;
        int k = 0;
        for(int i=0;i<noOfGroups;i+=1){
            studentsCopy = (String[]) students.toArray(new String[students.size()]);
            while(true){
                boolean a = memoization.contains(students.get(k));
                if(memoization.size()==0){
                    break;
                }
                if(memoization.size()>0 && a==false){
                    break;
                }
                ++k;
            }
            bubbleSort(k,studentsCopy);
            groups[i][0] = (String) students.get(k);
            memoization.add(students.get(k));
            for(int j=0;j<groupSize-1;j+=1)
            {
                if(priorityTable[k][j]==0 || memoization.contains(studentsCopy[j])){
                    continue;
                }
                else {
                    groups[i][j+1] = studentsCopy[j];
                    memoization.add(studentsCopy[j]);
                    priorityTable[i][j] = 0;
                    priorityTable[j][i] = 0;
                }
            }
            k+=1;
        }
        distributeRemaining();
    }

    void distributeRemaining(){
        remainingStudents = new ArrayList<String>();
        for(int i = 0;i<students.size();i+=1){
            if(!memoization.contains(students.get(i))){
                remainingStudents.add(students.get(i));
            }
        }
        for(int i =0;i<noOfGroups;i+=1){
            for(int j =0;j<groupSize;j+=1){
                if(groups[i][j] == null){
                    if(remainingStudents.size()>0){
                        groups[i][j] = (String)remainingStudents.get(remainingStudents.size() - 1);
                        remainingStudents.remove(remainingStudents.size() - 1);
                    }
                }
            }
        }
    }

    void fillFromMcqNumeric(){
        Question q;
        int priority;
        int rule;
        for(int j = 0; j<noOfStudents-1; j+=1) {
            for (int k = j + 1; k < noOfStudents; k += 1) {
                for (int i = 0; i < noOfQuestionsMcqNumeric; i += 1) {
                    q = questionTypeOne.get(i);
                    priority = q.priority;
                    rule = q.rule;
                    if (rule == 1) {
                        if (answerTableMcqNumeric[i][j] == answerTableMcqNumeric[i][k]) {
                            priorityTable[j][k] += priority;
                            priorityTable[k][j] += priority;
                        }
                    }
                    if (rule == 2) {
                        if (answerTableMcqNumeric[i][j] == answerTableMcqNumeric[i][k]) {
                            continue;
                        } else {
                            priorityTable[j][k] += priority;
                            priorityTable[k][j] += priority;
                        }
                    }
                }
            }
        }
    }

    void fillFromMcqMultiple() {
        Question q;
        int priority;
        int rule;
        int totalNoOfOptions;

        for (int j = 0; j < noOfStudents - 1; j += 1) {
            for (int k = j + 1; k < noOfStudents; k += 1) {
                for(int i = 0; i< noOfQuestionsMcqMultiple; i+=1){
                    q = questionTypeTwo.get(i);
                    priority = q.priority;
                    rule = q.rule;
                    totalNoOfOptions = q.totalNoOfOptions;

                    MCQMultiple mcqMultiple1 =answerTableMcqMultiple[i][j];
                    MCQMultiple mcqMultiple2 =answerTableMcqMultiple[i][k];
                    Set<Integer> set1 = mcqMultiple1.getAnswers();
                    Set<Integer> set2 = mcqMultiple2.getAnswers();

                    Set<Integer> intersection = new HashSet<Integer>(set1);
                    intersection.retainAll(set2);
                    int priorityPerOption = priority/totalNoOfOptions;

                    Set<Integer> difference;
                    if(set1.size()>set2.size()){
                        difference = new HashSet<Integer>(set1);
                        difference.removeAll(set2);
                    }
                    else {
                        difference = new HashSet<Integer>(set2);
                        difference.removeAll(set1);
                    }
                    if(rule == 1){
                        if(intersection.size()>0){
                            priorityTable[j][k] += (priorityPerOption * intersection.size());
                            priorityTable[k][j] += (priorityPerOption * intersection.size());
                        }
                    }
                    if(rule == 2){
                        if(difference.size()>0){
                            priorityTable[j][k] += (priorityPerOption * difference.size());
                            priorityTable[k][j] += (priorityPerOption * difference.size());
                        }
                    }
                }
            }
        }
    }

    void fillPriorityTable(){
        fillFromMcqNumeric();
        fillFromMcqMultiple();
    }


    Map returnGroups(){
        int i = 0;
        ArrayList<String> bannerIds = null;
        for( ;i<noOfGroups;i++){
            finalGroups.put("Group"+i+1, new ArrayList<String>());
            System.out.print("\n"+"Group"+(i+1)+"\t");
            for(int j =0;j<groupSize;j++){
                bannerIds = (ArrayList<String>) finalGroups.get("Group"+i+1);
                bannerIds.add(groups[i][j]);
            }
            for(String v:bannerIds){
                System.out.print(v+"\t");
            }
        }
        if(remainingStudents.size()>0 && remainingStudents.size()+1 == groupSize){
            finalGroups.put("Group"+i+1, remainingStudents);
            System.out.print("\n"+"Group"+(i+1)+"\t");
            for(String v:(ArrayList<String>)finalGroups.get("Group"+i+1)){
                System.out.print(v+"\t");
            }
        }
        if(remainingStudents.size()>0 && remainingStudents.size()+1 < groupSize){
            for(int j=0; j<remainingStudents.size(); j++){
                if(remainingStudents.size()>0){
                    bannerIds = (ArrayList<String>) finalGroups.get("Group"+j);
                    bannerIds.add((String)remainingStudents.get(remainingStudents.size()-1));
                }
            }
            for(String v:(ArrayList<String>)finalGroups.get("Group"+i+1)){
                System.out.print(v+"\t");
            }
        }
        return finalGroups;
    }
}

