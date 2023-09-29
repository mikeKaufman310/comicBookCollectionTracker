import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //get selection for series from command line
        Scanner stdInScanner = new Scanner(System.in);

        printMenu();
        int selection = getMenuInput(stdInScanner);//NOTE: need to loop this so you can do multiple things before app system exits; will do this in a seperate branch
        if(selection == 1){//add new issue
            int seriesCount = printSeriesMenu();
            int seriesSelection = getSeriesMenuInput(stdInScanner, seriesCount);
            issueNumberPrompt();
            int issueNumber = getIssueNumber(stdInScanner);
            List series = addIssueToSeries(seriesSelection, issueNumber);
            updateSeries(series, seriesSelection);
        }else if(selection == 2){//add new series
            //not yet implemented
        //}else if(selection ==3){//display issues of a series (functionality not yet considered in methods) TODO
            //not yet implemented
        }else{//quit
            System.exit(0);
        }

        stdInScanner.close();
        System.exit(0);
    }

    public static void addNewSeries(String seriesName, List issuesAlreadyOwned){
        //TODO
    }

    public static int updateSeries(List series, int seriesSelectionNumber){
        String seriesString = getSeriesString(seriesSelectionNumber);
        String filePath = "collectionLists/";
        if(seriesString.equals("Fantastic Four Vol. 1")){
            filePath+="fantasticFourVol1.txt";
        }else if(seriesString.equals("Doctor Strange Vol. 1")){
            filePath+="doctorStrangeVol1.txt";
        }else if(seriesString.equals("Fantastic Four Vol. 2")){
            filePath+="fantasticFourVol2.txt";
        }else if(seriesString.equals("Fantastic Four Vol. 3")){
            filePath+="fantasticFourVol3.txt";
        }else if(seriesString.equals("Fantastic Four Vol. 4")){
            filePath+="fantasticFourVol4.txt";
        }else if(seriesString.equals("Fantastic Four Vol. 5")){
            filePath+="fantasticFourVol5.txt";
        }else if(seriesString.equals("Fantastic Four Vol. 6")){
            filePath+="fantasticFourVol6.txt";
        }else if(seriesString.equals("Star Wars Vol. 1")){
            filePath+="starWarsVol1.txt";
        }else if(seriesString.equals("Star Wars Vol. 4")){
            filePath+="starWarsVol4.txt";
        }else if(seriesString.equals("Fantastic Four Annual Vol. 1")){
            filePath+="fantasticFourAnnualVol1.txt";
        }else if(seriesString.equals("Fantastic Four Annual Vol. 3")){
            filePath+="fantasticFourAnnualVol3.txt";
        }else if(seriesString.equals("Fantastic Four Annual Vol. 4")){
            filePath+="fantasticFourAnnualVol4.txt";
        }
        File file = new File(filePath);
        try{
            FileWriter write = new FileWriter(file);
            write.write("");//delete current contents of file
            for(int i = 0; i < series.size() - 1; i++){
                //System.out.println(series.get(i));
                write.append(series.get(i).toString()+"\n");
            }
            write.append(series.get(series.size()-1).toString());
            write.close();
        }
        catch(IOException e){
            System.out.println("Fatal: CHECK GIT COMMIT HISTORY!!!");
            System.exit(2);
        }
        return 0;
    }

    public static void printSeries(int selection){
        String series = getSeriesString(selection);
        //coding for functionality at the moment, not to be smart yet TODO dynamically add series to switch statement
        if(series.equals("Fantastic Four Vol. 1")){
            //print fan four method TODO
        }else if(series.equals("Doctor Strange Vol. 1")){
            //print doc strange method TODO
        }
        //add other series TODO
    }

    public static List addIssueToSeries(int seriesSelection, int issueNumber){
        String seriesString = getSeriesString(seriesSelection);
        List<Integer> series = null;
        //lame, will dynamically add series to this function later
        if(seriesString.equals("Doctor Strange Vol. 1")){
            series = getDoctorStrangeVol1();//need to refactor this method name
        }else if(seriesString.equals("Fantastic Four Vol. 1")){
            series = getFantasticFourVol1();//need to refactor this method name
        }else if(seriesString.equals("Fantastic Four Vol. 2")){
            series = getFantasticFourVol2();
        }else if(seriesString.equals("Fantastic Four Vol. 3")){
            series = getFantasticFourVol3();
        }else if(seriesString.equals("Fantastic Four Vol. 4")){
            series = getFantasticFourVol4();
        }else if(seriesString.equals("Fantastic Four Vol. 5")){
            series = getFantasticFourVol5();
        }else if(seriesString.equals("Fantastic Four Vol. 6")){
            series = getFantasticFourVol6();
        }else if(seriesString.equals("Star Wars Vol. 1")){
            series = getStarWarsVol1();
        }else if(seriesString.equals("Star Wars Vol. 4")){
            series = getStarWarsVol4();
        }else if(seriesString.equals("Fantastic Four Annual Vol. 1")){
            series = getFantasticFourAnnualVol1();
        }else if(seriesString.equals("Fantastic Four Annual Vol. 3")){
            series = getFantasticFourAnnualVol3();
        }else if(seriesString.equals("Fantastic Four Annual Vol. 4")){
            series = getFantasticFourAnnualVol4();
        }
        series.add(new Integer(issueNumber));//deprecated whoops, will fix this later TODO
        Collections.sort(series);
        return series;
    }

    public static void issueNumberPrompt(){
        System.out.print("Enter an Issue Number:\t");
    }

    public static Integer getIssueNumber(Scanner stdInScanner){
        boolean go = false;
        Integer issue = null;
        do{
            try{
                issue = Integer.parseInt(stdInScanner.nextLine());
                go = true;
            }
            catch(Exception e){
                System.out.println("Not a Number, Try Again\n");
            }
        }while(!go);
        return issue;
    }

    public static String getSeriesString(int selection){
        File file = new File("transcripts/series.txt");
        Scanner scan = null;
        try{
            scan = new Scanner(file);
        }
        catch(FileNotFoundException e){
            System.out.println("Cannot Find List Of Series: Fatal");
            System.exit(1);
        }
        int count = 0;
        String series = null;
        while(count < selection && scan.hasNextLine()){
            series = scan.nextLine();
            count++;
        }
        scan.close();
        return series;
    }

    public static int getSeriesMenuInput(Scanner stdInScanner, int upperBound){
        int selection = Integer.parseInt(stdInScanner.nextLine());
        if(!(selection > 0 && selection < (upperBound+1))){
            System.out.println("Invalid Input, Try Again!");
            boolean go = false;
            do{
                System.out.print("\nEnter a Number (1-"+upperBound+"):");
                selection = Integer.parseInt(stdInScanner.nextLine());
                if(!(selection > 0 && selection < (upperBound+1))){
                    System.out.println("Invalid Input, Try Again!");
                }else{
                    go = true;
                }
            }while(go==false);
        }
        return selection;
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

    public static int printSeriesMenu(){
        System.out.println("Series:");
        System.out.println("-------------------");
        int seriesCount = 1;
        File file = new File("transcripts/series.txt");
        Scanner scan = null;
        try{
            scan = new Scanner(file);
        }
        catch(FileNotFoundException e){
            System.out.println("Cannot Find List Of Series: Fatal");
            System.exit(1);
        }
        while(scan.hasNextLine()){
            String series = scan.nextLine();
            System.out.println(seriesCount +  ". " + series);
            seriesCount++;
        }
        scan.close();
        System.out.println("-------------------");
        System.out.print("Enter a Number (1-"+(seriesCount-1)+"):");
        return (seriesCount-1);
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

    public static List getStoredList(int selection){
        List issues = null;
        if(selection == 1){
            issues = getFantasticFourVol1();
            if(issues == null){
                System.out.println("We have an issue");
            }
        }else if(selection == 2){
            issues = getFantasticFourVol2();
            if(issues == null){
                System.out.println("We have an issue");
            }
        }else if(selection == 3){
            issues = getFantasticFourVol3();
            if(issues == null){
                System.out.println("We have an issue");
            }
        }else if(selection == 4){
            issues = getFantasticFourVol4();
            if(issues == null){
                System.out.println("We have an issue");
            }
        }else if(selection == 5){
            issues = getFantasticFourVol5();
            if(issues == null){
                System.out.println("We have an issue");
            }
        }else if(selection == 6){
            issues = getFantasticFourVol6();
            if(issues == null){
                System.out.println("We have an issue");
            }
        }else if(selection == 7){
            issues = getDoctorStrangeVol1();
            if(issues == null){
                System.out.println("We have an issue");
            }
        }else if(selection == 8){
            issues = getStarWarsVol1();
            if(issues == null){
                System.out.println("We have an issue");
            }
        }else if(selection == 9){
            issues = getStarWarsVol4();
            if(issues == null){
                System.out.println("We have an issue");
            }
        }else if(selection == 10){
            issues = getFantasticFourAnnualVol1();
            if(issues == null){
                System.out.println("We have an issue");
            }
        }else if(selection == 11){
            issues = getFantasticFourAnnualVol3();
            if(issues == null){
                System.out.println("We have an issue");
            }
        }else if(selection == 12){
            issues = getFantasticFourAnnualVol4();
            if(issues == null){
                System.out.println("We have an issue");
            }
        }
        return issues;
    }

    public static List getFantasticFourVol1(){
        ArrayList<Integer> issues = new ArrayList<>();
        File file = new File("collectionLists/fantasticFourVol1.txt");
        Scanner scan;
        try{
            scan = new Scanner(file);
        }
        catch(FileNotFoundException e){
            System.out.println("Cannot Find List Of Issues: Fantastic Four Vol 1");
            return null;//make null check in calling function TODO
        }
        while(scan.hasNextLine()){
            issues.add(Integer.parseInt(scan.nextLine()));
        }
        scan.close();
        return issues;
    }

    public static List getFantasticFourVol2(){
        ArrayList<Integer> issues = new ArrayList<>();
        File file = new File("collectionLists/fantasticFourVol2.txt");
        Scanner scan;
        try{
            scan = new Scanner(file);
        }
        catch(FileNotFoundException e){
            System.out.println("Cannot Find List Of Issues: Fantastic Four Vol 1");
            return null;//make null check in calling function TODO
        }
        while(scan.hasNextLine()){
            issues.add(Integer.parseInt(scan.nextLine()));
        }
        scan.close();
        return issues;
    }

    public static List getFantasticFourVol3(){
        ArrayList<Integer> issues = new ArrayList<>();
        File file = new File("collectionLists/fantasticFourVol3.txt");
        Scanner scan;
        try{
            scan = new Scanner(file);
        }
        catch(FileNotFoundException e){
            System.out.println("Cannot Find List Of Issues: Fantastic Four Vol 1");
            return null;//make null check in calling function TODO
        }
        while(scan.hasNextLine()){
            issues.add(Integer.parseInt(scan.nextLine()));
        }
        scan.close();
        return issues;
    }

    public static List getFantasticFourVol4(){
        ArrayList<Integer> issues = new ArrayList<>();
        File file = new File("collectionLists/fantasticFourVol4.txt");
        Scanner scan;
        try{
            scan = new Scanner(file);
        }
        catch(FileNotFoundException e){
            System.out.println("Cannot Find List Of Issues: Fantastic Four Vol 1");
            return null;//make null check in calling function TODO
        }
        while(scan.hasNextLine()){
            issues.add(Integer.parseInt(scan.nextLine()));
        }
        scan.close();
        return issues;
    }

    public static List getFantasticFourVol5(){
        ArrayList<Integer> issues = new ArrayList<>();
        File file = new File("collectionLists/fantasticFourVol5.txt");
        Scanner scan;
        try{
            scan = new Scanner(file);
        }
        catch(FileNotFoundException e){
            System.out.println("Cannot Find List Of Issues: Fantastic Four Vol 1");
            return null;//make null check in calling function TODO
        }
        while(scan.hasNextLine()){
            issues.add(Integer.parseInt(scan.nextLine()));
        }
        scan.close();
        return issues;
    }

    public static List getFantasticFourVol6(){
        ArrayList<Integer> issues = new ArrayList<>();
        File file = new File("collectionLists/fantasticFourVol6.txt");
        Scanner scan;
        try{
            scan = new Scanner(file);
        }
        catch(FileNotFoundException e){
            System.out.println("Cannot Find List Of Issues: Fantastic Four Vol 1");
            return null;//make null check in calling function TODO
        }
        while(scan.hasNextLine()){
            issues.add(Integer.parseInt(scan.nextLine()));
        }
        scan.close();
        return issues;
    }

    public static List getDoctorStrangeVol1(){
        ArrayList<Integer> issues = new ArrayList<>();
        File file = new File("collectionLists/doctorStrangeVol1.txt");
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

    public static List getStarWarsVol1(){
        ArrayList<Integer> issues = new ArrayList<>();
        File file = new File("collectionLists/starWarsVol1.txt");
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

    public static List getStarWarsVol4(){
        ArrayList<Integer> issues = new ArrayList<>();
        File file = new File("collectionLists/starWarsVol4.txt");
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

    public static List getFantasticFourAnnualVol1(){
        ArrayList<Integer> issues = new ArrayList<>();
        File file = new File("collectionLists/fantasticFourAnnualVol1.txt");
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

    public static List getFantasticFourAnnualVol3(){
        ArrayList<Integer> issues = new ArrayList<>();
        File file = new File("collectionLists/fantasticFourAnnualVol3.txt");
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

    public static List getFantasticFourAnnualVol4(){
        ArrayList<Integer> issues = new ArrayList<>();
        File file = new File("collectionLists/fantasticFourAnnualVol4.txt");
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