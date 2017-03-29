/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author asus
 */
//This is the Huffman tree Class.
/*
1.We are calculating the frequencies of the characters from the text file.
2.Nodes of all the nonzero frequency characters are created and are stored in an array.
3.Then All these nodes are sorted with the help of Minimum Priority Queue.
4.The untill there is only one element left in the Minimum Priority Queue we delete two elements from the tree combine
them and insert them back in the Minimum Priority Queue.
5.Now atlast there will only be one element left in the Minimum Priority Queue.
6.We Delete that element and we traverse through it and get the Huffman Codes of the respective characters which are the leaf
nodes of the Tree.
*/
package Compression;
import java.io.FileInputStream;     //For Taking input from a file.
import java.io.FileWriter;          //For Writing into a File.
/**************************************************Huffman_Tree************************************************************/
public class Huffman_Tree 
{
    static int count = 0;       //For Taking a count to write the element in to the text file.
    static int l;               
    /*Function which calculates the frequency of the small alphabets from the input text file.*/
    public static int[] get_smallalphabet_frequency(String text)    
    {
        int [] smallalphabets = new int[26];
        int alphabets = 0;
        char ch;
        for(ch = 'a' ; ch <= 'z' ; ch++)
        {
            for(int j = 0;j<text.length();j++)
            {
                if(text.charAt(j) == ch)
                {
                    smallalphabets[alphabets]++;
                }
            }
            alphabets++;
        }
        return smallalphabets;
    }
    /*Function which calculates the frequency of the capital alphabets from the input text file.*/
    public static int[] get_capitalalphabet_frequency(String text)
    {
        int [] capitalalphabets = new int[26];
        int alphabets = 0;
        char ch;
        for(ch = 'A' ; ch <= 'Z' ; ch++)
        {
            for(int j = 0;j<text.length();j++)
            {
                if(text.charAt(j) == ch)
                {
                    capitalalphabets[alphabets]++;
                }
            }
            alphabets++;
        }
        return capitalalphabets;
    }
    /*Function which calculates the frequency of the spaces from the input text file.*/
    
    public static int get_space_frequency(String text)
    {
        int space = 0;
        for(int i = 0;i < text.length();i++)
        {
            if(text.charAt(i) == ' ')
            {
                space++;
            }
        }
        return space;
    }
    /*Function which traverses through the Tree and assigns the Huffman Code to the individual Nodes.*/
    public static void printCodes(Node root, int arr[], int top)
    {
        if (root.left != null)                  // Assign 1 to left edge and recur
        {
            arr[top] = 1;
            printCodes(root.left, arr,top + 1);
        }
        if (root.right != null)                 // Assign 1 to right edge and recur
        {
            arr[top] = 0;
            printCodes(root.right, arr, top + 1);
        }
        if (isLeaf(root))                      // If this is a leaf node then it contains one of the input characters
        {                                      //print the character and its code from arr[]
            for(int i = 0 ; i < top ; i++)
            {
                root.huffman += arr[i];
            }
        }
    }
    public static boolean isLeaf(Node node)   //Checks whether the Node given is leaf or not.
    {
        return node.left == null && node.right == null;
    }
    public static void main(String[] args) throws Exception     //The Main Function.
    {
        int totalalphabets = 0;                             
        char ch;
        int alphabets;
        int i;
        int []smallalphabets = new int[26];         //int array for small alphabets , capital alphabets and spaces.
        int []capitalalphabets = new int[26];
        Node []arrayofNodes = new Node[100];        //Array of nodes for storing all the nodes.
        Min_Priority_Queue min = new Min_Priority_Queue(100);   //Creating an instance of the Minimum Priotiry Queue.
        int nodepos = 0;
        int spaces = 0;        
        String text = "";
        FileInputStream fin = new FileInputStream("Huffman.txt");   //For reading from the text file.
        while((alphabets = fin.read()) != -1)
        {
            text = text + (char)alphabets;
        }
        smallalphabets = get_smallalphabet_frequency(text);     //Calculates the frequency of the alphabets
        capitalalphabets = get_capitalalphabet_frequency(text);
        spaces = get_space_frequency(text);
        ch = 'a';
        for(i = 0 ; i < 26; i++)                            //Creates node of the alphabets with non zero frequencies.
        {                                                   //Stores it in the Array of Nodes.
            if(smallalphabets[i] != 0)
            {
                totalalphabets++;
                arrayofNodes[nodepos] = new Node(ch,smallalphabets[i]); //Character and Frequency.
                nodepos++;
            }
            ch++;
        }
        ch = 'A';
        for(i = 0 ; i < 26; i++)
        {
            if(capitalalphabets[i] != 0)
            {
                totalalphabets++;
                arrayofNodes[nodepos] = new Node(ch,capitalalphabets[i]); //Character and Frequency.
                nodepos++;            
            }
            ch++;
        }

        if(spaces != 0)
        {
            totalalphabets++;
            arrayofNodes[nodepos] = new Node(' ',spaces); //Character and Frequency.       
        }
           
        
        ch = 'a';
        for(alphabets = 0;alphabets<26;alphabets++)
        {
            ch++;
        }
        ch = 'A';
        for(alphabets = 0;alphabets<26;alphabets++)
        {
            ch++;
        }
        
        for(i = 0 ; i < arrayofNodes.length ; i++)      //Inserting all the Nodes in the Minimum Priority Queue.
        {
            if(arrayofNodes[i]!=null)
            {
                min.insert(arrayofNodes[i]);
            }
        }
        /*
        We are removing two elements from the tree --> Adding their frequencies and creating a new node and assigning
        its left and right pointers to the corresponding nodes. -->And we are inserting the new node back again the 
        Minimum Priority Queue.We are repeating untill there is only one element left in the tree.
        */
        while(min.size > 1)                 
        {
            Node left = min.delete();
            Node right = min.delete();
            Node combine = new Node('?',(left.frequency+right.frequency));
            combine.left = left;
            combine.right = right;
            min.insert(combine);
        }
        /*
        Now we are removing the last element from the tree and by traversing it we are getting the huffman Codes of the alphabets.
        which are at the leaf of the tree.
        */
        Node Final = min.delete();
       
        int arr[] = new int[100];
        int top = 0;
        printCodes(Final,arr,top);  //Assigns the Huffman Code.
        int letter;
        char alphabet;
        int index;
        //Huffman Encoding.
        FileWriter fw = new FileWriter("Huffman_Encoded.txt");  //For Writing in th text file.
        FileInputStream en = new FileInputStream("Huffman.txt");//For reading from the text file.
        while((letter = en.read()) != -1)
        {
           alphabet = (char)letter;     
           for(index = 0 ; index < nodepos+1 ; index++)
            {
                if(alphabet == (arrayofNodes[index].ch))    //When the alphabet from the text matches with the array of nodes then we are writing its huffman code in to the text file.
                {   
                    fw.write("" + arrayofNodes[index].huffman);
                }
            }
        }
        fw.close();  //Closing the File Writer.
        //Huffman Decoding.
        String encoded = "";
        FileInputStream de = new FileInputStream("Huffman_Encoded.txt");//For reading from the text file.
        int charac;
        while((charac = de.read()) != -1)
        {
            encoded = encoded + (char)charac;
        
        }
        FileWriter dec = new FileWriter("Huffman_Decoded.txt");     //For writing in the text file the decoded data.
        char Decodetext;
        for(int detext = 0 ; l<encoded.length();detext++)
        {
            Decodetext = getdecodedString(encoded, l, Final);
          
            dec.write(Decodetext);
        }
        dec.close();        //Closing the File Writer.
        
        /*Displaying the output to the user.*/
        System.out.println("\nCharacter       Frequency        Huffman Code \n");
        for(int dalal = 0; dalal < nodepos+1; dalal++)
        {
            if (arrayofNodes[dalal].frequency != 0)
            {
                System.out.println(arrayofNodes[dalal].ch + "                " + arrayofNodes[dalal].frequency + "                " + arrayofNodes[dalal].huffman);
            }
        }
    }
    /*This function decodes the String from the huffman code to their respective characters.It returns the character from
    the input which is the huffman code.It uses the Huffman tree for finding the character using the Huffman Code.*/
    public static char getdecodedString(String encode,int decodeindex,Node root)
    {
        count++;
        if(root.left == null && root.right == null)
        {
            l=decodeindex;
            return root.ch;
        }
        else if(encode.charAt(decodeindex) == '1')
        {
            return getdecodedString(encode, decodeindex+1, root.left);
        }
        else 
        {
            return getdecodedString(encode, decodeindex+1, root.right);
        }   
    }
}
/**************************************************End Of Huffman_Tree**************************************************/