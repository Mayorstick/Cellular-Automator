package semester2;
/*This class is the class that handles user input, intergenerational calculations of cells.
While coding this class I assumed that my teammate who created LinkedList class for our project successfully implemented these functions:
1- a constructor function with a parameter of integer that creates a class with that number of nodes and changing the value in the middle to true or 1.
2- a clear() function that turns every node of a list to false or 0.
3- a print() function that prints every element in the linked list.
4- a calculate() function that will calculate next generation of cells according to current elements of the list
This class is done by Omer Hakan Kilic*/
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    //fields 
    Scanner s = new Scanner(System.in); //declares and initializes scanner class for input operations.
    LinkedList startingList; //A linked list representing first generation in the simulation
    LinkedList oldList;// A linked list representing currently old generation
    LinkedList newList;// A linked list representing currently new generation
    int genNum=10; // number of generations that will be created in the simulation
    int genWidth=9; // how many cells will be in every generation
    File database=new File("database.txt");

    public static void main(String[] args) throws Exception {
        new Main();// calls constructor
    }
    Main(){// constructor  
        runMenu(); // does logical operations needed for menu


    }
    /* This function's goal is creating a menu that will take input from the user and doing actions based on that.
     * 
     */
    public void runMenu(){ 
        boolean quit=false; // a variable to control while loop so we can end it whenever we want
        while (!quit) {//this wile loop presents user with options. Whenever user selects an option a iteration of loop is done and it prints menu again. This keeps happenning until we quit.
            printMenu(); //prints menu
            String choice = s.nextLine();
            switch(choice){
                case "4":
                    changeStartingList();//this function call is for creating a custom starting condition with user input
                    break;
                case"1":
                    changewidth();//this function call is for taking an input and change number of cells in a generation accordingly
                    break;
                case"2":
                    changeGenNum();//this function call is for taking an input and change number of generations accordingly
                    break;
                case"3":
                    start();////this function call is for starting the cellular automata
                    break;
                case"5":
                    quit=true;//changing this variable to true ends program
                    break;
                default:
                    System.out.println("Invalid input...");//in case of an invalid input
            }
        }

    }
    /*This function's goal is taking an input from user and use it to create first generation of the simulation.
     * This will help customizing simulation more.
     */
    private void changeStartingList() {
        //prompts for input
        System.out.println("You need to enter a first generation. Your entry should only include 1 and 0 characters");
        System.out.println("Example generation: 0001000");
        System.out.println("Please enter your generation: ");
        String choice = s.nextLine();
        startingList=convertStrToList(choice); //convert to linked list
        genWidth=choice.length();

    }
    /*Converts a string to a linked list by subtringing every char and making an element out of it */
    public LinkedList convertStrToList(String text){
        LinkedList temp=new LinkedList();
        for(int i=0;i<text.length();i++){//this for loop loops every char of the string and adds that to list temp
            temp.add(text.substring(i, i+1));
        }
        return temp;
    }
    private void start() {//this function call is for starting the cellular automata
        String[] temp=new String[genNum];
        if(startingList==null) startingList=new LinkedList(genWidth);//if there is no customly created starting list it creates one with default values  
        startingList.printInfo();//prints starting list
        oldList= startingList;
        newList= new LinkedList(genWidth);//creates second list
        for(int i=0;i<genNum-1;i++){//a loop to create every generation. Each iteration creates 1 generation
            newList=calcNext(oldList);
            temp[i]=newList.printInfo();//prints current generation
            refreshLists();//prepares lists for next iteration
        }
        try {
            FileWriter writer= new FileWriter("database.txt");
            writer.write(startingList.printInfo());
            for(int j=0;j<genNum-1;j++){
                writer.append(System.getProperty("line.separator") + temp[j] );
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        //reset every list so it will be suitable for next time
        startingList=null;
        newList=null;
        oldList=null;
    }
    private void changeGenNum() {//this function call is for taking an input and change number of generations accordingly
        System.out.println("Enter new number of generation: ");
        String choice = s.nextLine();
        genNum=Integer.valueOf(choice);
        System.out.println("Number of generation changed to "+genNum);
    }
    /*A function to change number of cells in a generation */
    private void changewidth() {
        System.out.println("Enter new width: ");
        String choice = s.nextLine();
        genWidth=Integer.valueOf(choice);
        System.out.println("Width changed to "+genWidth);
    }
    /*This function takes a list as parameter and calculates what should come next after this list
     * It returns the nextlist
     */
    private LinkedList calcNext(LinkedList list){
        LinkedList temp=new LinkedList();
        temp=list.calculateNextGeneration();
        return temp;
    }
    /*This function is for using when you are done with a generation and you want to start doing operations to the next generation.
     * It assigns newlist to oldlists and deletes the contents of the newlist to make room for next generation
    */
    private void refreshLists(){
        oldList=newList;
    }
    /*A function to print out menu items. */
    public void printMenu(){
        System.out.println("1-Change width");
        System.out.println("2-Change the number of generations");
        System.out.println("3-Start Simulation");
        System.out.println("4-Choose custom starting condition");
        System.out.println("5-Exit");

    }
}
