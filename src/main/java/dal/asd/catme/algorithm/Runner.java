package dal.asd.catme.algorithm;

import java.util.ArrayList;
import java.util.Map;

public class Runner {
    public static void main(String arg[]){
        Algorithm obj = new Algorithm();

        /*for(short i = 0; i<8; i+=1){
            for(short j = 0; j<8; j+=1){
                System.out.print(obj.answerTableMcqNumeric[i][j] +"\t");
            }
            System.out.println();
        }*/
        //obj.fillPriorityTable();
        obj.createGroups();
        obj.test();
        /*for(short i = 0; i<8; i+=1){
            for(short j = 0; j<8; j+=1){
                System.out.print(obj.priorityTable[i][j] +"\t");
            }
            System.out.println();
        }*/
        System.out.println();
        obj.returnGroups();





        obj.createGroups();

        System.out.print("\n\n");

//obj.returnGroups();





        }
}
