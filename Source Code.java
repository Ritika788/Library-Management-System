/*
* PROBLEM STATEMENT : Implement a Library Management System using Binary Search Tree, File Organization,and Hash-Maps
*                     taking into consideration both ADMIN and USER perspective and implement ADT of Binary Search Tree.
*                     
* NAME              : 1. 2932 - Aparna Lulekar
*                     2. 2939 - Sakshi Nalawade
*                     3. 2943 - Ritika Pande
*
* DSA - 2           :   MINI PROJECT
 */



package com.internshala.javaapp;
//All required packages declared
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Map.Entry;

class Node               //class node
{
    String key;         //Data Members of class Node
    Node left, right;


    Node(String item)   //Node Parameterized Constructor
    {
        key = item;
        left = null;
        right=null;

    }

}
class Student               //class Student
{
    String name;           //Data Members of class Student
    int id_no;
    String Stream;
    String book1,book2;
    int book_no;

    Student(String name,int id_no,String Stream)    //Student Parameterized Constructor
    {
        this.name=name;
        this.id_no=id_no;
        this.Stream=Stream;

    }
}

class libraryManagement{                //class libraryManagement
    Node root;
    libraryManagement()                 //Default constructor
    {

        root = null;
    }
    void insert(String key)             //Function to insert a key
    {

        root = insertRec(root, key);
    }

    Node insertRec(Node root, String key)  //Recursive Insert function that returns root
    {
        if(root == null)                    //Empty BST
        {
            root = new Node(key);            //create root
            return root;
        }

        if (key.compareTo(root.key)<0)       //If book name < root then place it as left child
            root.left = insertRec(root.left, key);
        else if (key.compareTo(root.key)>0)   //If book name > root then place it as Right child
            root.right = insertRec(root.right, key);
        else
            System.out.println("error.");

        return root;
    }

    public boolean containsNode(String value)    //To Check if value is present
    {

        return containsNodeRecursive(root, value);
    }

    private boolean containsNodeRecursive(Node current, String key)   //Recursive Function that returns True if value is present
    {
        if (current == null)
        {
            return false;
        }
        //If book name < root then place it as left child
        if (key.equalsIgnoreCase(current.key))
        {
            return true;
        }

        //If book name < root then search at left side of root else right side
        return key.compareTo(current.key)<0
                ? containsNodeRecursive(current.left, key)
                : containsNodeRecursive(current.right, key);
    }
    void printTree()         //To Print Tree
    {

        root = printTreeRec(root, 0);
    }

    Node printTreeRec(Node t , int space)   //Recursive Function that returns root of Tree
    {
        if(t == null)
            return root;

        space += 10;                        //For space between nodes

        printTreeRec(t.right ,space);

        System.out.println();

        for(int i = 5 ; i < space ; i++)
            System.out.print( " ");
        System.out.print("[" +t.key+ "]");

        return printTreeRec(t.left ,space); //Recursive call of function to Print Tree
    }
    void deleteKey(String key)              //Key to be deleted is passed
    {

        root = deleteRec(root, key);        //calling recursive delete function
    }

    Node deleteRec(Node root, String key)  //Recursive delete function
    {
        if (root == null)                  //Tree is empty
            return null;

        //If book name < root then search it at left side and delete                     //TO RECUR DOWN THE TREE
        if (key.compareTo(root.key)<0)
            root.left = deleteRec(root.left, key);
        //If book name > root then search it at right side and delete
        else if (key.compareTo(root.key)>0)
            root.right = deleteRec(root.right, key);

        else
        {
            if (root.left == null)                                    // node with only one child or no child
                return root.right;
            else if (root.right == null)
                return root.left;

            root.key = minValue(root.right);                           /* node with two children: Get the inorder
                                                                        successor (smallest in the right subtree)*/
            root.right = deleteRec(root.right, root.key);
        }

        return root;
    }
    String minValue(Node root)                                       //Function for Minimum value
    {
        String minv = root.key;
        while (root.left != null)                                     /*To find inorder successor;
                                                                         smallest element in right subtree*/
        {
            minv = root.left.key;
            root = root.left;
        }
        return minv;
    }
    void printInorder(Node node)             //Recursive Inorder Function
    {
        if (node == null)                     //Tree is Empty
            return;
        //Left subtree is printed
        printInorder(node.left);
        //Root of Tree is printed
        System.out.print(node.key + "		");
        //Right subtree is printed
        printInorder(node.right);
    }

    void printInorder()                    //To Print Inorder Traversal
    {

        printInorder(root);
    }

    void display(Student[] arr)            //Display Student details
    {
        for (Student student : arr) {
            System.out.println("-------------------------------------------");
            System.out.println();
            System.out.println("\nName of Member     :" + student.name);
            System.out.println("\nId of Member       :" + student.id_no);
            System.out.println("\nStream of Member   :" + student.Stream);
            System.out.println();
            System.out.println("-------------------------------------------");
        }
    }

}

public class Main{
    private static Date Cday;

    public static void main(String[] args)  throws Exception {
        Scanner input = new Scanner(System.in);
        libraryManagement tree = new libraryManagement();
        HashMap<String, Integer> hashmapping = new HashMap<>();                          //creating object of Hash map
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); //Date and Time generator
        Calendar cal = Calendar.getInstance();
        Student[] array =new Student[5];
        //Add Member Details
        array[0]=new Student("Sakshi Nalawade",2939,"B.Tech-Computer");
        array[1]=new Student("Ritika Pande",2943,"B.Tech-Computer");
        array[2]=new Student("Aparna Lulekar",2932,"B.Tech-Computer");
        array[3]=new Student("Madhura Utekar",2989,"B.Tech-ENTC");
        array[4]=new Student("Tara Menon",2978,"B.Tech-Mechanical");
        int [][] arr=new int[100][4];

        /*The File Reader to read data from file*/
        FileReader file = new FileReader("C:\\Users\\sakshi\\Desktop\\x.txt");
        /*The BufferedReader is used to read the stream of characters
        from the specified source (character-input stream).*/
        BufferedReader reader = new BufferedReader(file);

        FileReader file2= new FileReader("C:\\Users\\sakshi\\Desktop\\y.txt");
        BufferedReader reader2 = new BufferedReader(file2);

        FileReader file3= new FileReader("C:\\Users\\sakshi\\Desktop\\z.txt");
        BufferedReader reader3 = new BufferedReader(file3);


        Date Rday1 = null,Rday2 = null,Cday=null;
        boolean e1=false;

        int i=0;

        while(!e1)
        {
            System.out.println("************* WELCOME TO ALGORITHM LIBRARY *************");
            System.out.println();
            System.out.println("\n..................................................... " );
            System.out.println("1. LIBRARIAN LOGIN ");
            System.out.println("2. MEMBER LOGIN ");
            System.out.println("3. EXIT ");
            System.out.println();
            System.out.println("\n....................................................." );

            System.out.println("\nPLEASE SELECT YOUR LOGIN : ");
            int ch1 = input.nextInt();

            switch (ch1) {
                                    //For Librarian login
                case 1 -> {
                    String pwd1 = "ccoew@123";
                    String id1 = "dsamp";
                    System.out.println("--------- LOGIN DETAILS ---------");
                    System.out.println();
                    System.out.println("\nENTER USER-ID:   ");
                    String id2 = input.next();
                    System.out.println("\nENTER PASSWORD: ");
                    String pwd2 = input.next();
                    if (!id1.equals(id2))
                        System.out.println("INVALID USERID.");
                    else if (!pwd1.equals(pwd2))
                        System.out.println("INVALID PASSWORD.");
                    else {
                        System.out.println("*** LOGIN SUCCESSFULLY ***");
                        boolean e2 = false;
                        while (!e2) {
                            System.out.println("\n.....................................");

                            System.out.println("1. LIBRARIAN CAN ADD BOOKS !! ");
                            System.out.println("2. LIBRARIAN CAN DELETE BOOKS !!  ");
                            System.out.println("3. LIBRARIAN CAN UPDATE BOOKS !!  ");
                            System.out.println("4. PRINT BOOK DETAILS !! ");
                            System.out.println("5. PRINT BOOK DETAILS INORDER TRAVERSAL !! ");
                            System.out.println("6. PRINT TREE !! ");
                            System.out.println("7. DISPLAY MEMBER DETAILS!! ");
                            System.out.println("8. LOG-OUT !!");

                            System.out.println("\n.....................................");

                            System.out.println("\nEnter Your choice:");
                            int ch2 = input.nextInt();

                            switch (ch2) {

                                case 1 -> {                                       //To add a book
                                    String line;
                                    while ((line = reader.readLine()) != null) {  // used to read data line by line by readLine() method

                                        tree.insert(line);
                                        hashmapping.put(line, i);                 //put method is used to insert elements in a hash map

                                        i++;
                                    }
                                    int j = i;
                                    int o = 0;
                                    String number;
                                    while ((number = reader2.readLine()) != null) {
                                        int result = Integer.parseInt(number);     //returns an integer value
                                        if (j != o)
                                            arr[o][0] = result;                     //Storing data of file into array
                                        o++;
                                    }
                                    int pq = 0;
                                    String number1;
                                    while ((number1 = reader3.readLine()) != null) {
                                        int result1 = Integer.parseInt(number1);   //returns an integer value
                                        if (j != pq)
                                            arr[pq][1] = result1;
                                        pq++;
                                    }
                                    System.out.println("\n ENTER NAME OF BOOK WANT TO ADD :");
                                    String name = input.next();
                                    boolean z1 = tree.containsNode(name);
                                    if (z1) {
                                        System.out.println("\nBOOK ALREADY EXISTS !!");
                                    } else {
                                        System.out.println("\nENTER THE QUANTITY OF BOOK : ");
                                        int quantity = input.nextInt();

                                        tree.insert(name);
                                        hashmapping.put(name, i);
                                        hashmapping.get(name);
                                        arr[i][0] += quantity;                     //Total quantity of books
                                        arr[i][1] += quantity;                     //Available quantity of books
                                        i++;
                                    }
                                    System.out.println("  BOOK ADDED SUCCESSFULLY!!  ");
                                }

                                case 2 -> {                                        //To delete a book
                                    System.out.println("\nENTER THE NAME OF BOOK TO DELETED : ");
                                    String b1 = input.next();
                                    boolean x = tree.containsNode(b1);
                                    if (x) {
                                        tree.deleteKey(b1);
                                        hashmapping.remove(b1);
                                    }
                                    System.out.println("  BOOK DELETED SUCCESSFULLY!!  ");
                                }

                                case 3 -> {                                         //To update any book
                                    System.out.println("\nENTER THE BOOK NAME TO UPDATE :");
                                    String b2 = input.next();
                                    boolean z = tree.containsNode(b2);
                                    if (z) {
                                        int a = hashmapping.get(b2);
                                        System.out.println("\nENTER QUANTITY OF BOOK TO UPDATE :");
                                        int q = input.nextInt();
                                        arr[a][0] += q;

                                    }
                                    System.out.println("  BOOKS UPDATED SUCCESSFULLY!!  ");
                                }

                                case 4 -> {                                        //Print Books Details
                                    Set<Entry<String, Integer>> setOfEntries = hashmapping.entrySet();
                                    for (Entry<String, Integer> entry : setOfEntries) {
                                        int r = entry.getValue();
                                        System.out.println("****************************************************");
                                        System.out.println("NAME OF BOOK               : " + entry.getKey());
                                        System.out.println("TOTAL QUANTITY OF BOOK     : " + arr[r][0]);
                                        System.out.println("AVAILABLE QUANTITY OF BOOK : " + arr[r][1]);
                                        System.out.println("****************************************************");
                                        System.out.println();
                                    }
                                }

                                case 5 -> tree.printInorder();                    //To Print Books in-order

                                case 6 -> tree.printTree();                       //To Print binary search tree

                                case 7 ->{                                        //To Display Student details
                                    System.out.println("----------------- MEMBER OF LIBRARY -------------------");
                                    tree.display(array);
                                }

                                case 8 -> e2 = true;                              //Exit condition
                            }
                        }
                    }
                }

                case 2 -> {                                                      //For Member Login
                    boolean e3 = false;
                    while (!e3) {
                        System.out.println("------- WELCOME TO MEMBER LOGIN --------");
                        System.out.println("\n.....................................");
                        System.out.println("1. TO ISSUE A BOOK  !! ");
                        System.out.println("2. TO RETURN A BOOK !!");
                        System.out.println("3. LOG-OUT");
                        System.out.println();
                        System.out.println("\n.....................................");

                        System.out.println("\nEnter Your choice:");
                        int ch3 = input.nextInt();

                        switch (ch3) {
                            case 1:                                             //To issue a book
                                int index = -1;
                                System.out.println("\nENTER YOUR USER ID : ");
                                int id = input.nextInt();

                                //display(array);
                                for(int k=0;k<5;k++)
                                {
                                    if(array[k].id_no==id)
                                        index=k;

                                }
                                if(index!=-1)
                                {
                                    if(array[index].book_no==2)
                                    {
                                        System.out.println("\nYOU CANNOT ISSUE MORE THAN TWO BOOKS !! ");
                                    }
                                    else
                                    {
                                        System.out.println("\nENTER NAME OF BOOK :");
                                        String book = input.next();
                                        boolean x=tree.containsNode(book);
                                        if(x)
                                        {
                                            int a=hashmapping.get(book);
                                            if(arr[a][1]>0)
                                            {
                                                if(array[index].book1==null)
                                                    array[index].book1=book;
                                                else
                                                    array[index].book2=book;
                                                System.out.println("MEMBER ISSUED BOOK - USER ID : " +id);
                                                System.out.println("BOOK ISSUED SUCCESSFULLY !! ");
                                                arr[a][1]--;
                                                Cday=cal.getTime();
                                                System.out.println("Current Date Time : " + formatter.format(cal.getTime()));
                                                cal.add(Calendar.SECOND, 5);
                                                Rday1 = cal.getTime();
                                                System.out.println("Due Date Time: " + formatter.format(Rday1));
                                                array[index].book_no++;


                                            }
                                            else
                                                System.out.println("YOU CANNOT ISSUE BOOK NOW !! \nTRY AGAIN AFTER SOME-DAYS!!");
                                        }

                                    }

                                }
                                else
                                    System.out.println("INVALID ID");
                                break;

                            case 2:                                     //to return a book

                                try {                                   //Try block for INVALID INPUTS
                                    int ind = -1;
                                    System.out.println("\nENTER YOUR USER ID : ");
                                    int s_id = input.nextInt();
                                    System.out.println("\nENTER NAME OF BOOK :");
                                    String Rbook = input.next();
                                    for (int k = 0; k < 5; k++) {
                                        if (array[k].id_no == s_id && (array[k].book1.equalsIgnoreCase(Rbook) || array[k].book2.equalsIgnoreCase(Rbook)))
                                            ind = k;

                                    }

                                    if (ind != -1) {

                                        boolean y = tree.containsNode(Rbook);

                                        if (y) {

                                            if (array[ind].book1.equalsIgnoreCase(Rbook))
                                                array[ind].book1 = null;
                                            else
                                                array[ind].book2 = null;

                                            cal = Calendar.getInstance();
                                            Rday2 = cal.getTime();


                                            if (Rday2.after(Rday1)) {
                                                System.out.println("-- BOOK IS OVERDUE --");
                                                assert Rday1 != null;
                                                long diff = Rday2.getTime() - Rday1.getTime();
                                                int noofdays = (int) (diff / (2000/*24*60*60*/));
                                                System.out.println("Due Date Time: " + formatter.format(Rday2));
                                                System.out.println("Book is delayed by " + noofdays + "seconds." + diff);
                                                double charge = noofdays * 1;
                                                System.out.println("Your charge is: " + charge + "Rs.");
                                            } else {
                                                System.out.println(" BOOK RETURNED SUCCESSFULLY ");
                                            }


                                            int a = hashmapping.get(Rbook);
                                            arr[a][1]++;
                                            array[ind].book_no--;
                                        }
                                    } else
                                        System.out.println("INVALID ID");
                                } catch (Exception e) {
                                    System.out.println("*** SOMETHING IS GOING WRONG ***");
                                }
                                break;

                            case 3:
                                e3 = true;

                                break;
                        }
                    }
                }
                case 3 -> e1 = true;
            }

        }
        reader.close();                  /* The reader close used to close the stream and release
                                          the resources that were busy in the stream, if any*/
        reader2.close();
        reader3.close();

    }
}
/*
TIME COMPLEXITY :

1.INSERT FUNCTION :
    * BEST CASE   : O(logn)
    * WORST CASE  : O(n)
2.DELETE FUNCTION :
    * BEST CASE   : O(logn)
    * WORST CASE  : O(n)
3.DISPLAY INORDER :
    * BEST CASE   : O(n)
    * WORST CASE  : O(n)
4.DISPLAY DETAILS :
    * BEST CASE   : O(n)
    * WORST CASE  : O(n)
5.DISPLAY TREE    :
    * BEST CASE   : O(n)
    * WORST CASE  : O(n)
 */

/*
OUTPUT:

************* WELCOME TO ALGORITHM LIBRARY *************


..................................................... 
1. LIBRARIAN LOGIN 
2. MEMBER LOGIN 
3. EXIT 


.....................................................

PLEASE SELECT YOUR LOGIN : 
1
--------- LOGIN DETAILS ---------


ENTER USER-ID:   
dsamp

ENTER PASSWORD: 
ccoew@123
*** LOGIN SUCCESSFULLY ***

.....................................
1. LIBRARIAN CAN ADD BOOKS !! 
2. LIBRARIAN CAN DELETE BOOKS !!  
3. LIBRARIAN CAN UPDATE BOOKS !!  
4. PRINT BOOK DETAILS !! 
5. PRINT BOOK DETAILS INORDER TRAVERSAL !! 
6. PRINT TREE !! 
7. DISPLAY MEMBER DETAILS!! 
8. LOG-OUT !!

.....................................

Enter Your choice:
1

 ENTER NAME OF BOOK WANT TO ADD :
castor

ENTER THE QUANTITY OF BOOK : 
5
  BOOK ADDED SUCCESSFULLY!!  

.....................................
1. LIBRARIAN CAN ADD BOOKS !! 
2. LIBRARIAN CAN DELETE BOOKS !!  
3. LIBRARIAN CAN UPDATE BOOKS !!  
4. PRINT BOOK DETAILS !! 
5. PRINT BOOK DETAILS INORDER TRAVERSAL !! 
6. PRINT TREE !! 
7. DISPLAY MEMBER DETAILS!! 
8. LOG-OUT !!

.....................................

Enter Your choice:
4
****************************************************
NAME OF BOOK               : Fynemen
TOTAL QUANTITY OF BOOK     : 1
AVAILABLE QUANTITY OF BOOK : 1
****************************************************

****************************************************
NAME OF BOOK               : BTS
TOTAL QUANTITY OF BOOK     : 5
AVAILABLE QUANTITY OF BOOK : 5
****************************************************

****************************************************
NAME OF BOOK               : House of cards
TOTAL QUANTITY OF BOOK     : 2
AVAILABLE QUANTITY OF BOOK : 2
****************************************************

****************************************************
NAME OF BOOK               : Sapiens
TOTAL QUANTITY OF BOOK     : 3
AVAILABLE QUANTITY OF BOOK : 3
****************************************************

****************************************************
NAME OF BOOK               : Management
TOTAL QUANTITY OF BOOK     : 5
AVAILABLE QUANTITY OF BOOK : 5
****************************************************

****************************************************
NAME OF BOOK               : Alchemist
TOTAL QUANTITY OF BOOK     : 6
AVAILABLE QUANTITY OF BOOK : 6
****************************************************

****************************************************
NAME OF BOOK               : Algorithms
TOTAL QUANTITY OF BOOK     : 5
AVAILABLE QUANTITY OF BOOK : 5
****************************************************

****************************************************
NAME OF BOOK               : Algebra
TOTAL QUANTITY OF BOOK     : 4
AVAILABLE QUANTITY OF BOOK : 4
****************************************************

****************************************************
NAME OF BOOK               : Harry Potter
TOTAL QUANTITY OF BOOK     : 4
AVAILABLE QUANTITY OF BOOK : 4
****************************************************

****************************************************
NAME OF BOOK               : Grewal
TOTAL QUANTITY OF BOOK     : 3
AVAILABLE QUANTITY OF BOOK : 3
****************************************************

****************************************************
NAME OF BOOK               : Intelligent Investor
TOTAL QUANTITY OF BOOK     : 2
AVAILABLE QUANTITY OF BOOK : 2
****************************************************

****************************************************
NAME OF BOOK               : Wings of Fire
TOTAL QUANTITY OF BOOK     : 4
AVAILABLE QUANTITY OF BOOK : 4
****************************************************

****************************************************
NAME OF BOOK               : Galvin
TOTAL QUANTITY OF BOOK     : 2
AVAILABLE QUANTITY OF BOOK : 2
****************************************************

****************************************************
NAME OF BOOK               : Evolution
TOTAL QUANTITY OF BOOK     : 5
AVAILABLE QUANTITY OF BOOK : 5
****************************************************

****************************************************
NAME OF BOOK               : Ignite
TOTAL QUANTITY OF BOOK     : 6
AVAILABLE QUANTITY OF BOOK : 6
****************************************************

****************************************************
NAME OF BOOK               : Reinventing India
TOTAL QUANTITY OF BOOK     : 5
AVAILABLE QUANTITY OF BOOK : 5
****************************************************

****************************************************
NAME OF BOOK               : IKIGAI
TOTAL QUANTITY OF BOOK     : 9
AVAILABLE QUANTITY OF BOOK : 9
****************************************************

****************************************************
NAME OF BOOK               : Astronomy
TOTAL QUANTITY OF BOOK     : 4
AVAILABLE QUANTITY OF BOOK : 4
****************************************************

****************************************************
NAME OF BOOK               : castor
TOTAL QUANTITY OF BOOK     : 5
AVAILABLE QUANTITY OF BOOK : 5
****************************************************

****************************************************
NAME OF BOOK               : Compilers
TOTAL QUANTITY OF BOOK     : 3
AVAILABLE QUANTITY OF BOOK : 3
****************************************************

****************************************************
NAME OF BOOK               : Rich Dad Poor Dad
TOTAL QUANTITY OF BOOK     : 4
AVAILABLE QUANTITY OF BOOK : 4
****************************************************


.....................................
1. LIBRARIAN CAN ADD BOOKS !! 
2. LIBRARIAN CAN DELETE BOOKS !!  
3. LIBRARIAN CAN UPDATE BOOKS !!  
4. PRINT BOOK DETAILS !! 
5. PRINT BOOK DETAILS INORDER TRAVERSAL !! 
6. PRINT TREE !! 
7. DISPLAY MEMBER DETAILS!! 
8. LOG-OUT !!

.....................................

Enter Your choice:
2

ENTER THE NAME OF BOOK TO DELETED : 
BTS
  BOOK DELETED SUCCESSFULLY!!  

.....................................
1. LIBRARIAN CAN ADD BOOKS !! 
2. LIBRARIAN CAN DELETE BOOKS !!  
3. LIBRARIAN CAN UPDATE BOOKS !!  
4. PRINT BOOK DETAILS !! 
5. PRINT BOOK DETAILS INORDER TRAVERSAL !! 
6. PRINT TREE !! 
7. DISPLAY MEMBER DETAILS!! 
8. LOG-OUT !!

.....................................

Enter Your choice:
4
****************************************************
NAME OF BOOK               : Fynemen
TOTAL QUANTITY OF BOOK     : 1
AVAILABLE QUANTITY OF BOOK : 1
****************************************************

****************************************************
NAME OF BOOK               : House of cards
TOTAL QUANTITY OF BOOK     : 2
AVAILABLE QUANTITY OF BOOK : 2
****************************************************

****************************************************
NAME OF BOOK               : Sapiens
TOTAL QUANTITY OF BOOK     : 3
AVAILABLE QUANTITY OF BOOK : 3
****************************************************

****************************************************
NAME OF BOOK               : Management
TOTAL QUANTITY OF BOOK     : 5
AVAILABLE QUANTITY OF BOOK : 5
****************************************************

****************************************************
NAME OF BOOK               : Alchemist
TOTAL QUANTITY OF BOOK     : 6
AVAILABLE QUANTITY OF BOOK : 6
****************************************************

****************************************************
NAME OF BOOK               : Algorithms
TOTAL QUANTITY OF BOOK     : 5
AVAILABLE QUANTITY OF BOOK : 5
****************************************************

****************************************************
NAME OF BOOK               : Algebra
TOTAL QUANTITY OF BOOK     : 4
AVAILABLE QUANTITY OF BOOK : 4
****************************************************

****************************************************
NAME OF BOOK               : Harry Potter
TOTAL QUANTITY OF BOOK     : 4
AVAILABLE QUANTITY OF BOOK : 4
****************************************************

****************************************************
NAME OF BOOK               : Grewal
TOTAL QUANTITY OF BOOK     : 3
AVAILABLE QUANTITY OF BOOK : 3
****************************************************

****************************************************
NAME OF BOOK               : Intelligent Investor
TOTAL QUANTITY OF BOOK     : 2
AVAILABLE QUANTITY OF BOOK : 2
****************************************************

****************************************************
NAME OF BOOK               : Wings of Fire
TOTAL QUANTITY OF BOOK     : 4
AVAILABLE QUANTITY OF BOOK : 4
****************************************************

****************************************************
NAME OF BOOK               : Galvin
TOTAL QUANTITY OF BOOK     : 2
AVAILABLE QUANTITY OF BOOK : 2
****************************************************

****************************************************
NAME OF BOOK               : Evolution
TOTAL QUANTITY OF BOOK     : 5
AVAILABLE QUANTITY OF BOOK : 5
****************************************************

****************************************************
NAME OF BOOK               : Ignite
TOTAL QUANTITY OF BOOK     : 6
AVAILABLE QUANTITY OF BOOK : 6
****************************************************

****************************************************
NAME OF BOOK               : Reinventing India
TOTAL QUANTITY OF BOOK     : 5
AVAILABLE QUANTITY OF BOOK : 5
****************************************************

****************************************************
NAME OF BOOK               : IKIGAI
TOTAL QUANTITY OF BOOK     : 9
AVAILABLE QUANTITY OF BOOK : 9
****************************************************

****************************************************
NAME OF BOOK               : Astronomy
TOTAL QUANTITY OF BOOK     : 4
AVAILABLE QUANTITY OF BOOK : 4
****************************************************

****************************************************
NAME OF BOOK               : castor
TOTAL QUANTITY OF BOOK     : 5
AVAILABLE QUANTITY OF BOOK : 5
****************************************************

****************************************************
NAME OF BOOK               : Compilers
TOTAL QUANTITY OF BOOK     : 3
AVAILABLE QUANTITY OF BOOK : 3
****************************************************

****************************************************
NAME OF BOOK               : Rich Dad Poor Dad
TOTAL QUANTITY OF BOOK     : 4
AVAILABLE QUANTITY OF BOOK : 4
****************************************************


.....................................
1. LIBRARIAN CAN ADD BOOKS !! 
2. LIBRARIAN CAN DELETE BOOKS !!  
3. LIBRARIAN CAN UPDATE BOOKS !!  
4. PRINT BOOK DETAILS !! 
5. PRINT BOOK DETAILS INORDER TRAVERSAL !! 
6. PRINT TREE !! 
7. DISPLAY MEMBER DETAILS!! 
8. LOG-OUT !!

.....................................

Enter Your choice:
3

ENTER THE BOOK NAME TO UPDATE :
castor

ENTER QUANTITY OF BOOK TO UPDATE :
6
  BOOKS UPDATED SUCCESSFULLY!!  

.....................................
1. LIBRARIAN CAN ADD BOOKS !! 
2. LIBRARIAN CAN DELETE BOOKS !!  
3. LIBRARIAN CAN UPDATE BOOKS !!  
4. PRINT BOOK DETAILS !! 
5. PRINT BOOK DETAILS INORDER TRAVERSAL !! 
6. PRINT TREE !! 
7. DISPLAY MEMBER DETAILS!! 
8. LOG-OUT !!

.....................................

Enter Your choice:
4
****************************************************
NAME OF BOOK               : Fynemen
TOTAL QUANTITY OF BOOK     : 1
AVAILABLE QUANTITY OF BOOK : 1
****************************************************

****************************************************
NAME OF BOOK               : House of cards
TOTAL QUANTITY OF BOOK     : 2
AVAILABLE QUANTITY OF BOOK : 2
****************************************************

****************************************************
NAME OF BOOK               : Sapiens
TOTAL QUANTITY OF BOOK     : 3
AVAILABLE QUANTITY OF BOOK : 3
****************************************************

****************************************************
NAME OF BOOK               : Management
TOTAL QUANTITY OF BOOK     : 5
AVAILABLE QUANTITY OF BOOK : 5
****************************************************

****************************************************
NAME OF BOOK               : Alchemist
TOTAL QUANTITY OF BOOK     : 6
AVAILABLE QUANTITY OF BOOK : 6
****************************************************

****************************************************
NAME OF BOOK               : Algorithms
TOTAL QUANTITY OF BOOK     : 5
AVAILABLE QUANTITY OF BOOK : 5
****************************************************

****************************************************
NAME OF BOOK               : Algebra
TOTAL QUANTITY OF BOOK     : 4
AVAILABLE QUANTITY OF BOOK : 4
****************************************************

****************************************************
NAME OF BOOK               : Harry Potter
TOTAL QUANTITY OF BOOK     : 4
AVAILABLE QUANTITY OF BOOK : 4
****************************************************

****************************************************
NAME OF BOOK               : Grewal
TOTAL QUANTITY OF BOOK     : 3
AVAILABLE QUANTITY OF BOOK : 3
****************************************************

****************************************************
NAME OF BOOK               : Intelligent Investor
TOTAL QUANTITY OF BOOK     : 2
AVAILABLE QUANTITY OF BOOK : 2
****************************************************

****************************************************
NAME OF BOOK               : Wings of Fire
TOTAL QUANTITY OF BOOK     : 4
AVAILABLE QUANTITY OF BOOK : 4
****************************************************

****************************************************
NAME OF BOOK               : Galvin
TOTAL QUANTITY OF BOOK     : 2
AVAILABLE QUANTITY OF BOOK : 2
****************************************************

****************************************************
NAME OF BOOK               : Evolution
TOTAL QUANTITY OF BOOK     : 5
AVAILABLE QUANTITY OF BOOK : 5
****************************************************

****************************************************
NAME OF BOOK               : Ignite
TOTAL QUANTITY OF BOOK     : 6
AVAILABLE QUANTITY OF BOOK : 6
****************************************************

****************************************************
NAME OF BOOK               : Reinventing India
TOTAL QUANTITY OF BOOK     : 5
AVAILABLE QUANTITY OF BOOK : 5
****************************************************

****************************************************
NAME OF BOOK               : IKIGAI
TOTAL QUANTITY OF BOOK     : 9
AVAILABLE QUANTITY OF BOOK : 9
****************************************************

****************************************************
NAME OF BOOK               : Astronomy
TOTAL QUANTITY OF BOOK     : 4
AVAILABLE QUANTITY OF BOOK : 4
****************************************************

****************************************************
NAME OF BOOK               : castor
TOTAL QUANTITY OF BOOK     : 11
AVAILABLE QUANTITY OF BOOK : 5
****************************************************

****************************************************
NAME OF BOOK               : Compilers
TOTAL QUANTITY OF BOOK     : 3
AVAILABLE QUANTITY OF BOOK : 3
****************************************************

****************************************************
NAME OF BOOK               : Rich Dad Poor Dad
TOTAL QUANTITY OF BOOK     : 4
AVAILABLE QUANTITY OF BOOK : 4
****************************************************


.....................................
1. LIBRARIAN CAN ADD BOOKS !! 
2. LIBRARIAN CAN DELETE BOOKS !!  
3. LIBRARIAN CAN UPDATE BOOKS !!  
4. PRINT BOOK DETAILS !! 
5. PRINT BOOK DETAILS INORDER TRAVERSAL !! 
6. PRINT TREE !! 
7. DISPLAY MEMBER DETAILS!! 
8. LOG-OUT !!

.....................................

Enter Your choice:
5
Alchemist		Algebra		Algorithms		Astronomy		Compilers		Evolution		Fynemen		Galvin		Grewal		Harry Potter		House of cards		IKIGAI		Ignite		Intelligent Investor		Management		Reinventing India		Rich Dad Poor Dad		Sapiens		Wings of Fire		castor		
.....................................
1. LIBRARIAN CAN ADD BOOKS !! 
2. LIBRARIAN CAN DELETE BOOKS !!  
3. LIBRARIAN CAN UPDATE BOOKS !!  
4. PRINT BOOK DETAILS !! 
5. PRINT BOOK DETAILS INORDER TRAVERSAL !! 
6. PRINT TREE !! 
7. DISPLAY MEMBER DETAILS!! 
8. LOG-OUT !!

.....................................

Enter Your choice:
6

                                   [castor]
                         [Wings of Fire]
               [Sapiens]
                                   [Rich Dad Poor Dad]
                                             [Reinventing India]
                         [Management]
     [Intelligent Investor]
                                             [Ignite]
                                                                 [IKIGAI]
                                                       [House of cards]
                                                                 [Harry Potter]
                                   [Grewal]
                                             [Galvin]
                         [Fynemen]
                                   [Evolution]
                                             [Compilers]
               [Astronomy]
                         [Algorithms]
                                   [Algebra]
                                             [Alchemist]
.....................................
1. LIBRARIAN CAN ADD BOOKS !! 
2. LIBRARIAN CAN DELETE BOOKS !!  
3. LIBRARIAN CAN UPDATE BOOKS !!  
4. PRINT BOOK DETAILS !! 
5. PRINT BOOK DETAILS INORDER TRAVERSAL !! 
6. PRINT TREE !! 
7. DISPLAY MEMBER DETAILS!! 
8. LOG-OUT !!

.....................................

Enter Your choice:
7
----------------- MEMBER OF LIBRARY -------------------
-------------------------------------------


Name of Member     :Sakshi Nalawade

Id of Member       :2939

Stream of Member   :B.Tech-Computer

-------------------------------------------
-------------------------------------------


Name of Member     :Ritika Pande

Id of Member       :2943

Stream of Member   :B.Tech-Computer

-------------------------------------------
-------------------------------------------


Name of Member     :Aparna Lulekar

Id of Member       :2932

Stream of Member   :B.Tech-Computer

-------------------------------------------
-------------------------------------------


Name of Member     :Madhura Utekar

Id of Member       :2989

Stream of Member   :B.Tech-ENTC

-------------------------------------------
-------------------------------------------


Name of Member     :Tara Menon

Id of Member       :2978

Stream of Member   :B.Tech-Mechanical

-------------------------------------------

.....................................
1. LIBRARIAN CAN ADD BOOKS !! 
2. LIBRARIAN CAN DELETE BOOKS !!  
3. LIBRARIAN CAN UPDATE BOOKS !!  
4. PRINT BOOK DETAILS !! 
5. PRINT BOOK DETAILS INORDER TRAVERSAL !! 
6. PRINT TREE !! 
7. DISPLAY MEMBER DETAILS!! 
8. LOG-OUT !!

.....................................

Enter Your choice:
8
************* WELCOME TO ALGORITHM LIBRARY *************


..................................................... 
1. LIBRARIAN LOGIN 
2. MEMBER LOGIN 
3. EXIT 


.....................................................

PLEASE SELECT YOUR LOGIN : 
2
------- WELCOME TO MEMBER LOGIN --------

.....................................
1. TO ISSUE A BOOK  !! 
2. TO RETURN A BOOK !!
3. LOG-OUT


.....................................

Enter Your choice:
1

ENTER YOUR USER ID : 
2939

ENTER NAME OF BOOK :
Grewal
MEMBER ISSUED BOOK - USER ID : 2939
BOOK ISSUED SUCCESSFULLY !! 
Current Date Time : 01/06/2021 08:32:28
Due Date Time: 01/06/2021 08:32:33
------- WELCOME TO MEMBER LOGIN --------

.....................................
1. TO ISSUE A BOOK  !! 
2. TO RETURN A BOOK !!
3. LOG-OUT


.....................................

Enter Your choice:
1

ENTER YOUR USER ID : 
2939

ENTER NAME OF BOOK :
Management
MEMBER ISSUED BOOK - USER ID : 2939
BOOK ISSUED SUCCESSFULLY !! 
Current Date Time : 01/06/2021 08:32:33
Due Date Time: 01/06/2021 08:32:38
------- WELCOME TO MEMBER LOGIN --------

.....................................
1. TO ISSUE A BOOK  !! 
2. TO RETURN A BOOK !!
3. LOG-OUT


.....................................

Enter Your choice:
1

ENTER YOUR USER ID : 
2939

YOU CANNOT ISSUE MORE THAN TWO BOOKS !! 
------- WELCOME TO MEMBER LOGIN --------

.....................................
1. TO ISSUE A BOOK  !! 
2. TO RETURN A BOOK !!
3. LOG-OUT


.....................................

Enter Your choice:
1

ENTER YOUR USER ID : 
2943

ENTER NAME OF BOOK :
Algebra
MEMBER ISSUED BOOK - USER ID : 2943
BOOK ISSUED SUCCESSFULLY !! 
Current Date Time : 01/06/2021 08:32:38
Due Date Time: 01/06/2021 08:32:43
------- WELCOME TO MEMBER LOGIN --------

.....................................
1. TO ISSUE A BOOK  !! 
2. TO RETURN A BOOK !!
3. LOG-OUT


.....................................

Enter Your choice:
2

ENTER YOUR USER ID : 
2939

ENTER NAME OF BOOK :
Grewal
-- BOOK IS OVERDUE --
Due Date Time: 01/06/2021 08:39:34
Book is delayed by 205seconds.411350
Your charge is: 205.0Rs.
------- WELCOME TO MEMBER LOGIN --------

.....................................
1. TO ISSUE A BOOK  !! 
2. TO RETURN A BOOK !!
3. LOG-OUT


.....................................

Enter Your choice:
3
************* WELCOME TO ALGORITHM LIBRARY *************


..................................................... 
1. LIBRARIAN LOGIN 
2. MEMBER LOGIN 
3. EXIT 


.....................................................

PLEASE SELECT YOUR LOGIN : 
1
--------- LOGIN DETAILS ---------


ENTER USER-ID:   
dsamp

ENTER PASSWORD: 
ccoew@123
*** LOGIN SUCCESSFULLY ***

.....................................
1. LIBRARIAN CAN ADD BOOKS !! 
2. LIBRARIAN CAN DELETE BOOKS !!  
3. LIBRARIAN CAN UPDATE BOOKS !!  
4. PRINT BOOK DETAILS !! 
5. PRINT BOOK DETAILS INORDER TRAVERSAL !! 
6. PRINT TREE !! 
7. DISPLAY MEMBER DETAILS!! 
8. LOG-OUT !!

.....................................

Enter Your choice:
4
****************************************************
NAME OF BOOK               : Fynemen
TOTAL QUANTITY OF BOOK     : 1
AVAILABLE QUANTITY OF BOOK : 1
****************************************************

****************************************************
NAME OF BOOK               : House of cards
TOTAL QUANTITY OF BOOK     : 2
AVAILABLE QUANTITY OF BOOK : 2
****************************************************

****************************************************
NAME OF BOOK               : Sapiens
TOTAL QUANTITY OF BOOK     : 3
AVAILABLE QUANTITY OF BOOK : 3
****************************************************

****************************************************
NAME OF BOOK               : Management
TOTAL QUANTITY OF BOOK     : 5
AVAILABLE QUANTITY OF BOOK : 4
****************************************************

****************************************************
NAME OF BOOK               : Alchemist
TOTAL QUANTITY OF BOOK     : 6
AVAILABLE QUANTITY OF BOOK : 6
****************************************************

****************************************************
NAME OF BOOK               : Algorithms
TOTAL QUANTITY OF BOOK     : 5
AVAILABLE QUANTITY OF BOOK : 5
****************************************************

****************************************************
NAME OF BOOK               : Algebra
TOTAL QUANTITY OF BOOK     : 4
AVAILABLE QUANTITY OF BOOK : 3
****************************************************

****************************************************
NAME OF BOOK               : Harry Potter
TOTAL QUANTITY OF BOOK     : 4
AVAILABLE QUANTITY OF BOOK : 4
****************************************************

****************************************************
NAME OF BOOK               : Grewal
TOTAL QUANTITY OF BOOK     : 3
AVAILABLE QUANTITY OF BOOK : 3
****************************************************

****************************************************
NAME OF BOOK               : Intelligent Investor
TOTAL QUANTITY OF BOOK     : 2
AVAILABLE QUANTITY OF BOOK : 2
****************************************************

****************************************************
NAME OF BOOK               : Wings of Fire
TOTAL QUANTITY OF BOOK     : 4
AVAILABLE QUANTITY OF BOOK : 4
****************************************************

****************************************************
NAME OF BOOK               : Galvin
TOTAL QUANTITY OF BOOK     : 2
AVAILABLE QUANTITY OF BOOK : 2
****************************************************

****************************************************
NAME OF BOOK               : Evolution
TOTAL QUANTITY OF BOOK     : 5
AVAILABLE QUANTITY OF BOOK : 5
****************************************************

****************************************************
NAME OF BOOK               : Ignite
TOTAL QUANTITY OF BOOK     : 6
AVAILABLE QUANTITY OF BOOK : 6
****************************************************

****************************************************
NAME OF BOOK               : Reinventing India
TOTAL QUANTITY OF BOOK     : 5
AVAILABLE QUANTITY OF BOOK : 5
****************************************************

****************************************************
NAME OF BOOK               : IKIGAI
TOTAL QUANTITY OF BOOK     : 9
AVAILABLE QUANTITY OF BOOK : 9
****************************************************

****************************************************
NAME OF BOOK               : Astronomy
TOTAL QUANTITY OF BOOK     : 4
AVAILABLE QUANTITY OF BOOK : 4
****************************************************

****************************************************
NAME OF BOOK               : castor
TOTAL QUANTITY OF BOOK     : 11
AVAILABLE QUANTITY OF BOOK : 5
****************************************************

****************************************************
NAME OF BOOK               : Compilers
TOTAL QUANTITY OF BOOK     : 3
AVAILABLE QUANTITY OF BOOK : 3
****************************************************

****************************************************
NAME OF BOOK               : Rich Dad Poor Dad
TOTAL QUANTITY OF BOOK     : 4
AVAILABLE QUANTITY OF BOOK : 4
****************************************************


.....................................
1. LIBRARIAN CAN ADD BOOKS !! 
2. LIBRARIAN CAN DELETE BOOKS !!  
3. LIBRARIAN CAN UPDATE BOOKS !!  
4. PRINT BOOK DETAILS !! 
5. PRINT BOOK DETAILS INORDER TRAVERSAL !! 
6. PRINT TREE !! 
7. DISPLAY MEMBER DETAILS!! 
8. LOG-OUT !!

.....................................

Enter Your choice:
8
************* WELCOME TO ALGORITHM LIBRARY *************


..................................................... 
1. LIBRARIAN LOGIN 
2. MEMBER LOGIN 
3. EXIT 


.....................................................

PLEASE SELECT YOUR LOGIN : 
3

Process finished with exit code 0
*/