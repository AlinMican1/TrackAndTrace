import java.util.Scanner;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import the IOException class to handle errors

public class TrackAndTrace{
   //declaring a global variable, for input.
   public static Scanner keyboardinput = new Scanner(System.in);
   
   
   //declaring an time period overlap method with the arguments: start1,end1,start2 and end 2.
   public static boolean overlap(int start1, int end1, int start2, int end2){
       while(start1 >= 0 || start2 >= 0 || end1 >= 0 || end2 >= 0 || start1 <= 24 || start2 <= 24 || end1 <=24 || end2 <= 24){ //Setting the range of the arguments.
           //checks if the times overlap
           if(end2 < end1 && end2 > start1){
             return true;
           }
           else if(start2 > start1 && start2 <end1){
             return true;
           }
           
           
           //midnight overlap e.g 23 hour to 3 hour
           else if(start1 >end1 || start2 > end2){
               if(start2 > end2 && start1 > end1){
                  return true;
               }
               else if(end2 > start2 && start1 > end2){
                  return false;
               }
               else{
                  return true;
               }          
           }
           else{
              return false;
           }
       }
       return false;
}   
   public static int getOverlaps(int start , int end){//This is function takes in the info for the customer.
       int tests = 0;//setting a tests variable to increment if the person needs a test.
       
       System.out.println("Enter the number of customers: ");//asking what information needs to be inputted.
       
       int num_Customer = keyboardinput.nextInt();
       for (int i = 0;i<num_Customer;i++){
           
          System.out.println("Enter the customer's name: ");
          String Customer_name = keyboardinput.next();
          
          System.out.println("Enter the arrival time: ");
          int arrival = keyboardinput.nextInt();
          
          System.out.println("Enter the departure time: ");
          int departure = keyboardinput.nextInt();
        
          
          if (arrival > start && arrival < end){
             System.out.println(Customer_name + " needs a test.");
             tests += 1;// increments the tests counter if the person time overlaps with the infectious time.
             
          }
          
          else{
             System.out.println(Customer_name + " does not need a test.");// if the person is not 
          }
       }
       return tests;//the function returns the number of tests.
   }
   public static void main(String[] args){//Main program starts here.
        System.out.println("Enter the start time: "); 
        int start = keyboardinput.nextInt();
        System.out.println("Enter the end time: ");
        int end = keyboardinput.nextInt();
        //start and end variables are passed into the getOverlaps method.
        
        if(args.length > 0){//checks if something is written in the command line, if so it executes the code thats indented.
           int tests2 = 0;//this is a counter for only the tests needed that are inside the file.
           for (int i = 0; i< args.length; i++){//for loop set to check all the arguments written.
           try{
           File file = new File(args[i]);//file is assign to the first index written in the command line.
           Scanner reader = new Scanner(file);//Reads through the file.
           
           if (file.exists()){//checks if the file exists.
           while (reader.hasNext()){//reader reads through the file until it has nothing to read anymore.
                  String name = reader.next();//variables
                  int arrival_file = reader.nextInt();
                  int departure_file = reader.nextInt();
                  if (overlap(start,end,arrival_file,departure_file)==true){//uses the first overlap method to check if the customer needs a test.
                     System.out.println(name +" needs a test.");
                     tests2 += 1;//increments tests.
                  }
          
                }
             }
           reader.close();//close reader,so the file doesn't get corrupted.
           }catch(FileNotFoundException e){
             System.err.println("WARNING: "+args[i]+" not found.");//will print out what is written in the command line at that index is not found.
           }
        }
        System.out.println("There are "+tests2+" tests needed.");
        }       
        else{//if there's nothing written in the command line, then it run s the getOverlaps method.
        System.out.println("There are "+ getOverlaps(start , end) +" tests needed.");
        }
   

}
}