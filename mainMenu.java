import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
/**
 * mainMenu is a user interface that goes along with MaxMinHeap. it exposes you the user to all the
 * functions neccessary to use a max-min heap properly like: making a max-min heap from an exiting
 * array, removing and adding new values, extracting the min and the max values etc.
 * @author Elad Benjo
 * @version May 14th 2023
 */
public class mainMenu
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome! choose and option: ");
        System.out.println("1.Make a Max-Min Heap");
        System.out.println("2.Quit");
        int option = scanner.nextInt();
        if (option == 2)
        {
            System.exit(0);
        }
        if (option == 1)
        {
            MaxMinHeap yourHeap = new MaxMinHeap();
            userInput(yourHeap);
            System.out.println("\nCongrats! here is your new Max-Min heap, beautifuly sorted:\n" + yourHeap);
            System.out.println("\nit is capble of many functions!");
            heapFunctions(yourHeap);
        }
    }
    private static void userInput(MaxMinHeap yourHeap)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose an option:");
        System.out.println("1.Enter values manually");
        System.out.println("2.Read values from a text file");
    
        int option = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
    
        if (option == 1) 
        {
            System.out.println("Enter the values separated by white space or comma:");
            String input = scanner.nextLine();
            String[] values = input.split("[,\\s]+");
    
            yourHeap.setSize(values.length); // Set heap size to the number of values entered
            
            for (int i = 0; i < values.length; i++) 
            {
                double val = Double.parseDouble(values[i]);
                System.out.print(val + " ");
                yourHeap.setVal(i+1, val);
            }
            System.out.print("\n");
        } else if (option == 2) 
        {
            System.out.println("Enter the path of the text file:");
            String filePath = scanner.nextLine();
    
            try {
                File file = new File(filePath);
                scanner = new Scanner(file);
                scanner.useDelimiter(",|\\s+");
                int count = 0;
                double num;
                
                while (scanner.hasNextDouble()) 
                {
                    num = scanner.nextDouble();
                    System.out.print(num + " ");
                    yourHeap.setVal(++count, num);
                }
    
                yourHeap.setSize(count); // Set heap size to the number of values read from the file

            } catch (FileNotFoundException e) 
            {
                System.out.println("File not found. Please make sure the path is correct.");
                return;
            }
        } else 
        {
            System.out.println("Invalid option. Please choose 1 or 2.");
            return;
        }
    
        scanner.close();
        if (yourHeap.getSize() == 0)
        {
            System.out.println("Invalid input!");
            System.exit(0);  
        }
        yourHeap.buildHeap();

    }
    private static void heapFunctions(MaxMinHeap yourHeap)
    {
        Scanner scanner = new Scanner(System.in);
        int choice;
        System.out.println("You can choose from the following:");
        System.out.println("\n1.Extract the max value\n2.Extract the min value\n3.Add a new value\n4.Delete a specific index\n5.Goodbye I'm done with this!");
        System.out.println("\nEnter your choice: ");
        choice = scanner.nextInt();
        switch (choice)
        {
            case 1:
                if (yourHeap.getSize() < 1)
                {
                    System.out.println("Empty heap");
                    scanner.close();
                    heapFunctions(yourHeap);
                }
                System.out.println("You just removed the former max value: " + yourHeap.extractMax());
                System.out.println("Your new Max-Min Heap:\n" +yourHeap+"\n");
                scanner.close();
                heapFunctions(yourHeap);
            case 2:
                if (yourHeap.getSize() < 1)
                {
                    System.out.println("Empty heap");
                    scanner.close();
                    heapFunctions(yourHeap);
                }
                System.out.println("You just removed the former min value: " + yourHeap.extractMin());
                System.out.println("Your new Max-Min Heap:\n" +yourHeap+"\n");
                scanner.close();
                heapFunctions(yourHeap);
            case 3:
                if (yourHeap.getSize() == yourHeap.MAX_SIZE)
                {
                    System.out.println("Heap is full");
                    scanner.close();
                    heapFunctions(yourHeap);
                }
                double val;
                System.out.println("Enter the value you would like to add to the heap: ");
                val = scanner.nextDouble();
                System.out.println("Your input: " + val);
                yourHeap.insert(val);
                System.out.println("Your new Max-Min Heap:\n" +yourHeap+"\n");
                scanner.close();
                heapFunctions(yourHeap);
            case 4:
                int i;
                System.out.println("Here is your heap:\n" + yourHeap);
                System.out.println("Enter the index of the number you would like to remvoe from the heap (Range 1-" +yourHeap.getSize() +"): ");
                i = scanner.nextInt();
                if (!yourHeap.inRange(i))
                {
                    System.out.println("Out of range!");
                    scanner.close();
                    heapFunctions(yourHeap);
                }
                System.out.println("Your input: " + i);
                System.out.println("The number " + yourHeap.getVal(i) + " have been removed from your heap");
                yourHeap.delete(i);
                System.out.println("Your new Max-Min Heap:\n" +yourHeap+"\n");
                scanner.close();
                heapFunctions(yourHeap);
            case 5:
                System.out.println("Goodbye!");
                System.exit(0);
            default:
                System.out.println("Invalid input!");
                scanner.close();
                heapFunctions(yourHeap);
        }
    }
}