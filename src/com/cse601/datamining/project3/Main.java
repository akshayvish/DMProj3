package com.cse601.datamining.project3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class Main{
	public static String PRESENT = "Present";
	public static String ABSENT = "Absent";
	
	public static void main(String args[]){
		Main main = new Main();
		main.readFiles(System.getProperty("user.dir") + "/src/Data/project3_dataset1.txt");
		main.readFiles(System.getProperty("user.dir") + "/src/Data/project3_dataset2.txt");
	}
	public void readFiles(String filePath){

		FileReader fr;
		try {
			System.out.println("readfiles : " + filePath);
			String line;
			fr = new FileReader(filePath);
			BufferedReader br = new BufferedReader(fr);
			ArrayList<Vector> dataset1 = new ArrayList<>();
			while((line = br.readLine())!=null){
				Vector vect = new Vector<Integer>();
				String[] elements = line.split("\\s+"); // Extracting numbers in each line. Nodes per edge in our case.
				for(int i=0;i<elements.length;i++){
					try{
					vect.add(Integer.parseInt(elements[i]));
					}catch(NumberFormatException nfe){
						if(elements[i].equalsIgnoreCase(PRESENT))
							vect.add(1);
						else if(elements[i].equalsIgnoreCase(ABSENT))
							vect.add(0);
						
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String getInfofrmUser(String str){
		Scanner userInput = new Scanner(System.in);
		System.out.println("*********** IMPLEMENTATION OF Markov(MCL) ALGORITHM **********");
		System.out.println("Enter the file path for " + str ); // to read file path
		Scanner scanner = new Scanner(System.in);
		String filePath = scanner.nextLine();
		return filePath;
	}
}
