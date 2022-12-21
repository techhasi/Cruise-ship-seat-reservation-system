import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Task_03 {
    public static Scanner input = new Scanner(System.in);
    public static int cabinNum = 0;
    static Queue queue = new Queue();
    static int filledcabins = 0;

    public static void main(String[] args){
        Cabin[] cabin = new Cabin[12];

        for (int i = 0; i < cabin.length; i++) {
            cabin[i] = new Cabin("e");
        }

        while(cabinNum < 12){

            System.out.println("\n Select from the menu \n");
            System.out.println(" A: Add a customer to a cabin \n" +
                    " V: View all cabins \n" +
                    " E: Display Empty cabins \n" +
                    " D: Delete customer from cabin \n" +
                    " F: Find cabin from customer name \n" +
                    " S: Store program data into file \n" +
                    " L: Load program data from file \n" +
                    " O: View passengers Ordered alphabetically by name \n" +
                    " T: Calculate expenses \n" +
                    " Q: Quit");

            String choice = input.next().toUpperCase();

            switch (choice) {
                case "A" -> AddsCustomerToCabin(cabin);
                case "V" -> ViewsAllCabins(cabin);
                case "E" -> DisplayEmptyCabins(cabin);
                case "D" -> DeleteCustomer(cabin);
                case "F" -> FindCustomer(cabin);
                case "S" -> StoreData(cabin);
                case "L" -> LoadData();
                case "O" -> SortCustomers(cabin);
                case "T" -> CalculateExpenses(cabin);
                case "Q" -> Quit();
                default -> System.out.println("Wrong input");
            }
        }

    }


    public static void AddsCustomerToCabin(Cabin A[]) {

        try {
            System.out.print("Enter cabin number (0-11) to add customer: ");
            int cabinNum = input.nextInt();

            if (cabinNum >= 0 && cabinNum < 12) {
                if (filledcabins == 12)
                    queue();
                 else if (!A[cabinNum].isFull()) {
                    System.out.println("Enter number of passengers (Max 3): ");
                    int passengerCount = input.nextInt();

                    for (int i = 0; i < passengerCount; i++) {

                        System.out.print("Enter first name of passenger " + (i + 1) + " : ");
                        String firstName = input.next().toLowerCase();

                        System.out.print("Enter last name of passenger " + (i + 1) + " : ");
                        String lastName = input.next().toLowerCase();

                        System.out.print("Enter expenses of passenger " + (i + 1) + " : ");
                        double expenses = input.nextDouble();

                        Passenger details = new Passenger(firstName, lastName, expenses);

                        A[cabinNum].addPassengers(details);
                        A[cabinNum].initialize = "Filled";

                        if (A[cabinNum].isPassengerFull()) {
                            filledcabins += 1;
                        }
                    }
                }
            }
        }
        catch (InputMismatchException e) {
            System.out.println("Wrong input");
        }
    }

    public static void ViewsAllCabins(Cabin V[]){
        for (int i = 0; i < 12; i++) {
            System.out.println("\n" + "Cabin " + i);
            for (int j = 0; j < 3; j++) {
                if (V[i].getPassengers()[j] != null) {
                    System.out.println("Room " + j + " occupied by " + V[i].getPassengers()[j].getFirstName() +
                            " " + V[i].getPassengers()[j].getLastName());
                }
                else {
                    System.out.println("Room " + j + " is empty");
                }
            }
        }
    }

    public static void DisplayEmptyCabins(Cabin E[]){
        for (int i = 0; i < 12; i++) {
            if (E[i].initialize.equals("e"))
                System.out.println("Cabin " + i + " is empty");
        }
    }

    public static void DeleteCustomer(Cabin D[]){
        System.out.print("Enter customer first name : ");
        String CustomerName = input.next().toLowerCase();

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 3; j++) {
                if (D[i].getPassengers()[j] != null) {
                    if (D[i].getPassengers()[j].getFirstName().equals(CustomerName)) {
                        D[i].getPassengers()[j] = null;
                        System.out.println("Customer " + CustomerName + " deleted");
                        if (!queue.isEmpty())          //Checking if queue is not empty
                            {
                            D[i].addPassengers(queue.dequeue());         //Adding passenger from queue to cabin
                            System.out.println("The empty slot in cabin " + (i) + " got filled");
                        }
                    }
                }
            }
        }
    }

    public static void FindCustomer(Cabin F[]){
        System.out.print("Enter the first name of the customer to find : ");
        String cabinName = input.next().toLowerCase();

        int set = 0;
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 3; j++) {
                if (F[i].getPassengers()[j] != null) {
                    if (F[i].getPassengers()[j].getFirstName().equals(cabinName)) {
                        System.out.println("Customer found in cabin " + i);
                    }
                }
            }
        }
    }

    public static void StoreData(Cabin S[]){
        try {
            FileWriter myWriter = new FileWriter("cabindata.txt");
            for (int i = 0; i < S.length; i++) {
                myWriter.write("Cabin " + i + "\n");
                for (int j = 0; j < 3; j++) {
                    if (S[i].getPassengers()[j] != null) {
                        myWriter.write("Room " + (j) + " - " + S[i].getPassengers()[j] + "\n");
                    } else {
                        myWriter.write("Room " + (j) + " - Empty\n");
                    }
                }
            }
            myWriter.flush();
            myWriter.close();
            System.out.println("Data saved successfully");
        }
        catch (IOException e)
        {
            System.out.println("An error occurred.");
        }

    }

    public static void LoadData(){
        try {
            File inputFile = new File("cabindata.txt");
            Scanner rf = new Scanner(inputFile);
            String fileLine;
            while (rf.hasNext()) {
                fileLine = rf.nextLine();
                System.out.println(fileLine);
            }
            rf.close();
        }
        catch (IOException e)
        {
            System.out.println("An error occurred.");
        }
    }

    public static void SortCustomers(Cabin O[]) {
        for (int i = 0; i < O.length; i++) {
            for (int j = i + 1; j < O.length; j++) {
                if (!(O[j] == null)) {
                    if (O[i].getPassengers()[j].getFirstName().compareTo(O[j].getPassengers()[j].getFirstName()) > 0) {
                        String temp = O[i].getPassengers()[j].getFirstName();
                        O[i].getPassengers()[j].setFirstName(O[j].getPassengers()[j].getFirstName());
                        O[j].getPassengers()[j].setFirstName(temp);
                        System.out.println(O[j]);
                    }
                }
            }
        }
    }

    public static void CalculateExpenses(Cabin T[]) {
        double totalExpenses = 0;
        for (int i = 0; i < T.length; i++) {
            if (!T[i].initialize.equals("e")) {
                System.out.println("Expenses of cabin " +i);
                for (int j = 0; j < 3; j++) {
                    if (T[i].getPassengers()[j] != null) {
                        totalExpenses += T[i].getPassengers()[j].getExpenses();
                        System.out.println(T[i].getPassengers()[j]);
                    }

                }
                System.out.println("Total expenses of the cabin: " + totalExpenses + "\n");
                totalExpenses=0;
            }
        }
    }


    public static void Quit(){
        cabinNum = 12;
    }

    public static void queue () {
        if (!queue.isFull()){           //Check if the queue is full
            System.out.println("All cabins are full. Adding to waiting list");
            System.out.print("Enter first name : ");
            String firstName = input.next().toLowerCase();

            System.out.print("Enter last name : ");
            String lastName = input.next().toLowerCase();

            System.out.print("Enter expenses : ");
            int expenses = input.nextInt();

            Passenger details = new Passenger(firstName, lastName, expenses);

            queue.enqueue(details);

            System.out.println("\nAdded to waiting list");
        }
        else {
            System.out.println("Waiting list is full");   //If queue is full
        }
    }
}


