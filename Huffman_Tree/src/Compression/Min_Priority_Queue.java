/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//This is the Minimum Priority Queue which is implemented using Array.
//Minimum Priority Queue is used for Sorting.
/*Properties:
1.The parent node is always small then its child nodes.
2.The root node has the least element.
*/
package Compression;

/**
 *
 * @author asus
 */
	//******************************Min_Priority_Queue class******************************************/

public class Min_Priority_Queue 
{
    Node [] Queue;			//Array of nodes.
    int size;				//size of array.
	
    //******************************Single parametrised Constructor **********************************/
	
    Min_Priority_Queue(int length) 	
    {
        Queue = new Node[length];	//Initialising  array  Queue 
        size = 0;					////initializing size with 0.
    }
	
    //******************************Insert function  *************************************************/
	//Use to insert node in Min priority queue.
	//Return type Boolean.
	//Parameter node element which will be inserted.
    public boolean insert(Node element)
    {
		
        if(size >= Queue.length)		//if size is more then length.
        {
            System.out.println("The Minimum Priority Queue is Full.");	//If no space is available in queue then this will be print
            return false;												//element is not successfully inserted in queue.
        }
		//If condition not satisfies.
        else
        {
			
            Queue[size] = element;		//Assigning element in Queue.
            trickleup(size++);			//after Assigning call trickleup function and increase size by 1.
            return true;				//Retur true element is successfully inserted in queue.
        }
    }
	//******************************Delete  function  ************************************************
	//Return type node.
	//Use : delete any node from queue.
    public Node delete()
    {
        Node root = Queue[0];		//first element of queue is assign to root node.
        Queue[0] = Queue[--size];	//Size is decrement by one then swap that node with root.
        trickleDown(0);				//Calling the trickle Down condition to again sort the node with its properties. 
        return root;				//Return root node.
    }
	//******************************Trickle up   function  ************************************************
    //Return type void.
	//When we insert element in queue then this function  will be call
	//Parameter is index.
	public void trickleup(int index)
    {
        int parent = (index - 1) / 2;	//to find parent node index.
        Node bottom = Queue[index];		//node bottom.
		//If index is greater then zero and The frequency of parent is greater then its child 
        while(index > 0 && (Queue[parent].frequency > bottom.frequency))	
        {
            Queue[index] = Queue[parent];	//if while condition satisfy then swap parent with its child.
            index = parent;					
            parent = (parent - 1) / 2;		
        }
        Queue[index] = bottom;				//bottom is assigns to at index position in queue.
    }
	//******************************Trickle Down   function  ************************************************
    //Return type void.
	//When we delete element in queue then this function  will be call.
	//Parameter is index.
    public void trickleDown(int index)
    {
        Node top = Queue[index];		//index position node is assign at top position.
        int smallerchild;				//smaller child.
        while(index < size/2)			// node has at least one child.
        {
            int leftchild = ((2 * index) + 1);	//to find left child. 
            int rightchild = (leftchild + 1);	//to find right child.
			//If right child is less then size and compare frequency of Right and Left child.
            if((rightchild < size) && (Queue[rightchild].frequency < Queue[leftchild].frequency)) 		
            {
                smallerchild = rightchild;		//Right child will assigns to smaller child.
            }
            else
            {
                smallerchild = leftchild;		//Left child will assigns to smaller child.
            }
			//compare frequency of Right and Left child.
            if(Queue[index].frequency < Queue[smallerchild].frequency)
            {
                break;
            }
            else
            {
                Queue[index] = Queue[smallerchild];		//index position smaller child node will be  assign.
                index = smallerchild;					//smaller child will be assign to index.
            }
        }
		
        Queue[index] = top;					//If while condition is not satisfy.
    }
    public int peek()
    {
        return Queue[0].frequency;
    }
    public void display()
    {
        System.out.print("Minimum Priority Queue : ");
        for(int i = 0 ; i < size ; i++)
        {
            System.out.print(Queue[i].frequency + " ");
        }
    }
        }