package com.cse601.datamining.project3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;

public class Main{
	public static String PRESENT = "Present";
	public static String ABSENT = "Absent";
	
	ArrayList<Vector> dataset1 = new ArrayList<>();
	ArrayList<Vector> dataset2 = new ArrayList<>();
	
	HashMap<Integer, Double> euclidDistanceDS1 = new HashMap<>();
	HashMap<Integer, Double> euclidDistanceDS2 = new HashMap<>();
	
	public static void main(String args[]){
		Main main = new Main();
		main.readFiles(System.getProperty("user.dir") + "/src/Data/project3_dataset1.txt", main.dataset1);
		main.readFiles(System.getProperty("user.dir") + "/src/Data/project3_dataset2.txt", main.dataset2);
		
		System.out.println("Euclidean Distance for dataset 1");
		for(int i=0;i<main.dataset1.size()-1;i++){
			System.out.println(main.EuclideanDistance(main.dataset1.get(i), main.dataset1.get(i+1)));
			main.euclidDistanceDS1.put(i, main.EuclideanDistance(main.dataset1.get(i), main.dataset1.get(i+1)));
		}
		
		System.out.println("Euclidean Distance for dataset 2");
		for(int i=0;i<main.dataset2.size()-1;i++){
			System.out.println(main.EuclideanDistance(main.dataset2.get(i), main.dataset2.get(i+1)));
			main.euclidDistanceDS2.put(i, main.EuclideanDistance(main.dataset2.get(i), main.dataset2.get(i+1)));
		}
		
	}
	public void readFiles(String filePath, ArrayList<Vector> dataset){

		FileReader fr;
		try {
			System.out.println("readfiles : " + filePath);
			String line;
			fr = new FileReader(filePath);
			BufferedReader br = new BufferedReader(fr);
			while((line = br.readLine())!=null){
				Vector vect = new Vector<Double>();
				String[] elements = line.split("\\s+"); // Extracting numbers in each line. Nodes per edge in our case.
				for(int i=0;i<elements.length;i++){
					try{
					vect.add(Double.parseDouble(elements[i]));
					}catch(NumberFormatException nfe){
						if(elements[i].equalsIgnoreCase(PRESENT))
							vect.add(1.0);
						else if(elements[i].equalsIgnoreCase(ABSENT))
							vect.add(0.0);
					}
				}
				dataset.add(vect);
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
	public double EuclideanDistance(Vector vect1, Vector vect2) {
		double distance = 0;
		
		Iterator<Double> iter1 = vect1.iterator();
		Iterator<Double> iter2 = vect2.iterator();
		
		if(vect1.size() == vect2.size()){
			double sum = 0;
			while(iter1.hasNext()){ 
				sum = iter1.next() - iter2.next();
				distance = distance + Math.pow(sum,2); 
			}
		}
		return Math.sqrt(distance);
	}
}
