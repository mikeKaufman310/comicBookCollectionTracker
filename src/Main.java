import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //get selection for series from command line
        Scanner stdInScanner = new Scanner(System.in);

        printMenu();
        int selection = getMenuInput(stdInScanner);
        if(selection == 1){//add new issue

        }else if(selection == 2){//add new series

        }else{//quit
            
        }

        //test begin
        //test end

        stdInScanner.close();
        System.exit(0);
    }

    public static void printMenu(){
        System.out.println("OPTIONS:");
        System.out.println("-------------------");
        System.out.println("1. Add New Issue");
        System.out.println("2. Add New Series");
        System.out.println("3. Quit");
        System.out.println("-------------------");
        System.out.print("Enter a Number (1-3):");
    }

    public static int getMenuInput(Scanner stdInScanner){
        int selection = Integer.parseInt(stdInScanner.nextLine());
        if(!(selection > 0 && selection < 4)){
            System.out.println("Invalid Input, Try Again!");
            boolean go = false;
            do{
                System.out.print("\nEnter a Number (1-3):");
                selection = Integer.parseInt(stdInScanner.nextLine());
                if(!(selection > 0 && selection < 4)){
                    System.out.println("Invalid Input, Try Again!");
                }else{
                    go = true;
                }
            }while(go==false);
        }
        return selection;
    }

    public static int getVolumeInput(Scanner stdInScanner){
        return -1;//TODO
    }

    public static void addNewSeries(String seriesName, List issuesAlreadyOwned){
        //TODO
    }

    public static void printSeriesNames(){
        //TODO
        //note: get lists of series from series list text file
    }

    public static List getStoredList(int selection){
        List issues = null;
        if(selection == 1){
            issues = getFantasticFour();
            if(issues == null){
                System.out.println("We have an issue");
            }
        }else{
            issues = getDoctorStrange();
            if(issues == null){
                System.out.println("We have an issue");
            }
        }
        return issues;
    }

    public static List getFantasticFour(){
        ArrayList<Integer> issues = new ArrayList<>();
        File file = new File("collectionLists/fantasticFourVol1.txt");
        Scanner scan;
        try{
            scan = new Scanner(file);
        }
        catch(FileNotFoundException e){
            System.out.println("Cannot Find List Of Issues");
            return null;//make null check in calling function TODO
        }
        while(scan.hasNextLine()){
            issues.add(Integer.parseInt(scan.nextLine()));
        }
        scan.close();
        return issues;
    }

    public static List getDoctorStrange(){
        ArrayList<Integer> issues = new ArrayList<>();
        File file = new File("collectionLists/doctorStrangeVol2.txt");
        Scanner scan;
        try{
            scan = new Scanner(file);
        }
        catch(FileNotFoundException e){
            System.out.println("Cannot Find List Of Issues");
            return null;//make null check in calling function TODO
        }
        while(scan.hasNextLine()){
            issues.add(Integer.parseInt(scan.nextLine()));
        }
        scan.close();
        return issues;
    }
}