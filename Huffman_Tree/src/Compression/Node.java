/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Compression;

/**
 *
 * @author asus
 */
	//******************************Node class********************************************************
public class Node 
{
	// In our program every node has 5 Properties  : character ,frequency , Huffman ,left node ,right node
    char ch; 			//character 
    int frequency;		//Frequency of character of our input text file
    String huffman;		//Huffman code of every character 
    Node left;			//Left node
    Node right;			//Right node
	
	//Constructor :Constructor in Java is a special type of method that is used to initialize the object.
	//Two Types of Basic Constructor :
	// 1> Default Constructor.
	// 2> Parametrized Constructor.
	//Properties of Constructor :
	// ->  name of class and constructor are always same.
	//******************************Default Constructor **********************************************
    Node()
    {
        ch = '\0';			//initializing character with null.
        frequency = 0;		//initializing frequency with 0. 
        left = null;		//initializing Left node  with null.
        right = null;		//initializing Right node  with null.
        huffman = "";		//initializing Huffman String with null. 
    }
	
	//******************************Single parametrised Constructor **********************************
    Node(int freq)			//Single parametrised constructor parameter is frequency.
    {
        ch = '\0';			//initializing character with null.
        frequency = freq;	//initializing frequency with which is pass in parameter. 
        left = null;		//initializing Left node  with null.
        right = null;		//initializing Right node  with null.
        huffman = "";		//initializing Huffman String with null. 
    }
	
	
	//******************************Double  parametrised Constructor *********************************
	
    Node(char c,int freq)	//Double parametrised constructor parameter. parameter are frequency and character.
    {
        ch = c;				//initializing character with which is pass in parameter.
        frequency = freq;	//initializing frequency with which is pass in parameter. 
        left = null;		//initializing Left node  with null.
        right = null;		//initializing Right node  with null.
        huffman = "";		//initializing Huffman String with null. 
    }
	
	//******************************Multi parametrised Constructor ***********************************
	
    Node(char c,int freq,Node leftchild,Node rightchild)	//Multi parameterConstructor  
    {
        ch = c;				//initializing character with which is pass in parameter.
        frequency = freq;	//initializing frequency with which is pass in parameter. 
        left = leftchild;	//initializing Left node with node which is pass in parameter.
        right = rightchild;	//initializing Right node with node which is pass in parameter.
        huffman = "";		//initializing Huffman String with null. 
    }
}
	//******************************End of Node class ************************************************