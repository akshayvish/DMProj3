package com.cse601.datamining.project3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;

import com.cse601.datamining.project3.alogorithms.NaiveBayes;

public class Main{
	public static String PRESENT = "Present";
	public static String ABSENT = "Absent";
	
	Vector<Vector> dataset1 = new Vector<>();
	Vector<Vector> dataset2 = new Vector<>();
	//Vector<Vector> dataset1_test = new Vector<>();
	//Vector<Vector> dataset2_test = new Vector<>();
	
	public static int datasetBooleanDimension = -1;
	public static int kValue = 9;	
	HashMap<Integer, Double> euclidDistanceDS1_train = new HashMap<>();
	HashMap<Integer, Double> euclidDistanceDS2_train = new HashMap<>();
	HashMap<Integer, Double> euclidDistanceDS1_test = new HashMap<>();
	HashMap<Integer, Double> euclidDistanceDS2_test = new HashMap<>();
	NaiveBayes nb;
	
	int dimensionDS1; // Store dimension count of dataset1
	int dimensionDS2; // Store dimension count of dataset2
	
	public static void main(String args[]){

		Main main = new Main();
		main.readFiles(System.getProperty("user.dir") + "/src/Data/project3_dataset1.txt", main.dataset1);
		//main.readFiles(System.getProperty("user.dir") + "/src/Data/project3_dataset2.txt", main.dataset2);
		
		System.out.println(main.dataset1.get(0));
		main.nb = new NaiveBayes(main.dataset1,kValue);
		//main.nb = new NaiveBayes(main.dataset2,3);
				
		/*for(int i=0;i<main.dataset1_train.size()-1;i++){
			main.euclidDistanceDS1_train.put(i, main.EuclideanDistance(main.dataset1_train.get(i), main.dataset1_train.get(i+1)));
		}
		for(int i=0;i<main.dataset1_test.size()-1;i++){
			main.euclidDistanceDS1_test.put(i, main.EuclideanDistance(main.dataset1_test.get(i), main.dataset1_test.get(i+1)));
		}
		
		for(int i=0;i<main.dataset2_train.size()-1;i++){
			main.euclidDistanceDS2_train.put(i, main.EuclideanDistance(main.dataset2_train.get(i), main.dataset2_train.get(i+1)));
		}
		for(int i=0;i<main.dataset2_test.size()-1;i++){
			//System.out.println(main.EuclideanDistance(main.dataset2_test.get(i), main.dataset2_test.get(i+1)));
			main.euclidDistanceDS2_test.put(i, main.EuclideanDistance(main.dataset2_test.get(i), main.dataset2_test.get(i+1)));
<<<<<<< HEAD
		}*/
	}
	
	public void readFiles(String filePath, Vector<Vector> dataset){
		
		FileReader fr;
		String[] elements = null;
		try {
			System.out.println("readfiles : " + filePath);
			String line;
			fr = new FileReader(filePath);
			BufferedReader br = new BufferedReader(fr);
			while((line = br.readLine())!=null){
				Vector vect = new Vector();
				//Vector<Boolean> vectBool = new Vector<Boolean>();
				elements = line.split("\\s+"); // Extracting numbers in each line. Nodes per edge in our case.
				for(int i=0;i<elements.length;i++){
					try{
					vect.add(Double.parseDouble(elements[i]));
					}catch(NumberFormatException nfe){
						if(elements[i].equalsIgnoreCase(PRESENT)){
							datasetBooleanDimension = i;
							vect.add(Boolean.TRUE);
							//vectBool.add(Boolean.TRUE);
							}
						else if(elements[i].equalsIgnoreCase(ABSENT)){
							datasetBooleanDimension = i;
							vect.add(Boolean.FALSE);
						}
					}
				}
				if(datasetBooleanDimension!=-1)
					System.out.println(datasetBooleanDimension);
				dataset.add(vect);
			}
			Vector<Vector<Double>> arr = new Vector<>();
			for(int i = 0; i<main.dimensionDS1;i++)
				arr.add(main.findRange(main.dataset1_train,i));
			
			for(int i = 0; i<main.dimensionDS1;i++)
				System.out.println("***" + arr.get(i));
			
			/*
			//######################### NEAREST NEIGHBOUR BEGIN ############################
			NearestNeighbour nearestNeighbour = new NearestNeighbour();
			System.out.println("\n\n######################### NEAREST NEIGHBOUR BEGIN ############################");
			System.out.println("\nFor Data 1\n");
			nearestNeighbour.process(main.dataset1_test,main.dataset1_train);
			System.out.println("\n\nFor Data 2\n");
			nearestNeighbour.process(main.dataset2_test,main.dataset2_train);
			System.out.println("\n######################### NEAREST NEIGHBOUR END ############################\n\n");
			//######################### NEAREST NEIGHBOUR END ############################
			
			*/
			
			//######################### DECISION TREE BEGIN ############################
			DecisionTree decisionTree = new DecisionTree();
			System.out.println("\n\n######################### DECISION TREE BEGIN ############################");
			System.out.println("\nFor Data 1\n");
			decisionTree.process(main.dataset1_test, main.dataset1_train);
			//System.out.println("\n\nFor Data 2\n");
			//decisionTree.process(main.dataset2_test, main.dataset2_train);
			System.out.println("\n######################### DECISION TREE End ############################\n\n");
			//######################### DECISION TREE END ############################

			//System.out.println(dimCount);
			int len = (int) (dataset.size() * (0.75));
			for(int i = 0; i< len;i++){
				dataset1.add(dataset.get(i));
			}
			for(int j = 0; j<len ;j++){
				dataset.remove(0);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Partitions the training dataset into K partitions 
	public void kPartition(Vector<Vector> trainingSet,Vector<Vector> testingSet, int kfactor){
		
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
	//calculate the probability of 0s and return the value
	public double probabilityLabels(Vector<Vector> inputList){
		double count0 = 0;
		double count1 = 0;
		for(int i=0;i<inputList.size();i++){
			Vector vect = inputList.get(i);
			if((double)vect.get(vect.size()-1)==1.0)
				count1++;
			else if((double)vect.get(vect.size()-1)==0.0)
				count0++;
		}
		System.out.println("count0 " + count0 + " count1 " + count1 + " total " + inputList.size());
		return (double)(count0/(inputList.size()));
	}
	
	//Find the range of the given dataset

	public Vector<Double> findRange(Vector<Vector> inputArr, int n){
		Vector<Double> vect = new Vector<>();
		double[] arr = new double[inputArr.size()];
		for(int i=0;i<inputArr.size()-1;i++){
			arr[i] = (double) inputArr.get(i).get(n);
		}
		Arrays.sort(arr);
		vect.add(arr[0]);
		vect.add(arr[arr.length-1]);
		return vect;
	}
	//Find the conditional probability using the Normal Distribution P(observation|Class)
	public double normalDist(double mean, double variance, double observation){
		double denom = Math.pow(2*Math.PI * variance, 0.5); // variance is signma squared
		double EPow = (Math.pow((observation-mean),2)) / (2*variance);
		return (Math.pow(Math.E, EPow)/denom);
	}
}
