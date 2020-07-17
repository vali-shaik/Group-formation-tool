package dal.asd.catme.algorithm;

public class AlgorithmRunner {
    public static void main(String arg[]){
        Algorithm obj = new Algorithm();
        obj.fillPriorityTable();
        for(short i = 0; i<8; i+=1){
            for(short j = 0; j<8; j+=1){
                System.out.print(obj.priorityTable[i][j] +"\t");
            }
            System.out.println();
        }
        obj.createGroups();
        obj.returnGroups();
    }
}
