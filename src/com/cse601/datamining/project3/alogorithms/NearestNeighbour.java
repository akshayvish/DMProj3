package com.cse601.datamining.project3.alogorithms;


import java.util.Vector;

public class NearestNeighbour {

	private int k = 9;

	private Vector<Vector> train = new Vector<Vector>();
	private Vector<Vector> test = new Vector<Vector>();
	final double max = 1;
	final double min = 0;
	final double range = 1;

	@SuppressWarnings("unchecked")
	public void process(Vector<Vector> test_data, Vector<Vector> train_data) {

		// declare variables
		Vector<Vector<Double>> kNearest = null; // store k nearest samples
		Vector<Double> kNearestDistance = null; // store k nearest distances
		int count; // count to k
		double distance; //distance
		
		// use local copy of given data
		test = test_data;
		train = train_data;
		
		// normalize test & train data
		MinMaxNormalize(test);
		MinMaxNormalize(train);

		/*System.out.println("\n\n CHECKING NORMALIZATION of test data!");
		System.out.println("data size " + test.size());
		System.out.println("row size " + test.get(0).size());
		// for(int i=0;i<test.size();i++)
		System.out.println(test.get(0));

		System.out.println("\n\n CHECKING NORMALIZATION of train data!");
		System.out.println("data size " + train.size());
		System.out.println("row size " + train.get(0).size());
		// for(int i=0;i<test.size();i++)
		System.out.println(train.get(0));*/
		
		/*System.out.println("\n\n CHECKING MinMax of test data!");
		Vector<Double> minValues = new Vector<Double>();
		Vector<Double> maxValues = new Vector<Double>();
		
		for (int i = 0; i < train.get(0).size()-1; i++) {// for each row
			minValues.add(Double.MAX_VALUE);
			maxValues.add(Double.MIN_VALUE);
		}
		
		// find min & max values of each coulun
		for (int i = 0; i < train.size(); i++) {// for each row
			Vector<Double> row = train.get(i);
			for (int j = 0; j < row.size() - 1; j++) {// for each element in a column
				
				if(row.get(j)>maxValues.get(j)){ // check for max value
					maxValues.set(j, row.get(j));
				}
				if(row.get(j)<minValues.get(j)){ // check for min value
					minValues.set(j, row.get(j));
				}
			}
		}
		System.out.println(minValues);
		System.out.println(maxValues);*/
		
		
		
		// Algorithm
		for(int tc=0; tc<test.size(); tc++){ //initilize variables for each test case
			
			kNearest = new Vector<Vector<Double>>();
			kNearestDistance = new Vector<Double>();
			count = 0; //count to k
			int pos = 0; //farthest smaple or longest distance index
			int i= 0;

			for(int tr=0; tr<train.size(); tr++){
				
				distance = Distance(test.get(tc), train.get(tr), range);
				
				if(count < k) {
					kNearest.add(train.get(tr));
					kNearestDistance.add(distance);
					if(distance > kNearestDistance.get(pos)) pos = count;	// store the furtherest distance to test
					count++;
				} else{
					
					if (distance < kNearestDistance.get(pos)){
						
						kNearest.set(pos, train.get(tr)); //replace the largest farthest sample
						kNearestDistance.set(pos, distance); // replace the longest distance
						
						// then find which training value distance is longest
						for (i=0; i<kNearestDistance.size(); i++){
							if (kNearestDistance.get(i) > kNearestDistance.get(pos)){
								pos = i;
							}
						}
					}
						
				}
				
			}
			Classify(kNearest, kNearestDistance, test.get(tc));
			
		}

	}
	
	
	
	
	
	
	
	
	
	
	private void Classify(Vector<Vector<Double>> kNearest, Vector<Double> kNearestDistance, Vector<Double> tc) {
		
		String byCount = null;
		String byWeight = null;
		double classByCount;
		double classByWeight = 0;
		
		// By count
		int count0 = 0;
		int count1 = 0;
		
		for(int i=0; i<kNearest.size(); i++){
			Vector<Double> row = kNearest.get(i);
			if(row.get(row.size()-1) == 1){
				count1++;
			}
			if(row.get(row.size()-1) == 0){
				count0++;
			}
		}
		
		if(count0 > count1){
			classByCount = 0;
		}else{
			classByCount = 1;
		}
		
		
		// By weight
		double w0 = -1;
		double w1 = -1;
		Vector<Double> kWeights = new Vector<Double>();
		
		for(int i=0; i<kNearest.size(); i++){
			Vector<Double> row = kNearest.get(i);

			if(row.get(row.size()-1) == 0){
				if(w0 ==-1)
					w0 = 0;
				w0 = w0 + (1/kNearestDistance.get(i));
			}else{
				if(w1 ==-1)
					w1 = 0;
				w1 = w1 + (1/kNearestDistance.get(i));
			}
		}
		
		
		if(w0>w1){
			classByWeight = 0;
		}else{
			classByWeight = 1;
		}

		
		
		
		// compare 
		if(tc.get(tc.size()-1) == classByCount){
			byCount = "matched";
		}else{
			byCount = "mis-matched";
		}
		
		if(tc.get(tc.size()-1) == classByWeight){
			byWeight = "matched";
		}else{
			byWeight = "mis-matched";
		}
		
		// display 
		System.out.println("Class By Count: "+byCount+"\t\tClass By Weight: "+byWeight);

		
	}
	
	

	private double Distance(Vector<Double> tc, Vector<Double> tr, double range) {
		
		double distance = 0;
		double element = 0;
		
		//System.out.println(test.get(0).size()-1);
		for(int i = 0; i < test.get(0).size()-1; i++){
			element = tc.get(i) - tr.get(i);
			element = Math.pow(element, 2);
			element /= range; // no effect as range is constant at 1
			distance += element;
		}
		//distance = Math.sqrt(distance);
		return  distance; // return squared distance  for efficiency avaoid sqrt!
	}	
	
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void MinMaxNormalize(Vector<Vector> dataSet) {
		
		Vector<Double> minValues = new Vector<Double>();
		Vector<Double> maxValues = new Vector<Double>();
		
		for (int i = 0; i < dataSet.get(0).size()-1; i++) {// for each row
			minValues.add(Double.MAX_VALUE);
			maxValues.add(Double.MIN_VALUE);
		}
		
		// find min & max values of each coulun
		for (int i = 0; i < dataSet.size(); i++) {// for each row
			Vector<Double> row = dataSet.get(i);
			for (int j = 0; j < row.size() - 1; j++) {// for each element in a column
				
				if(row.get(j)>maxValues.get(j)){ // check for max value
					maxValues.set(j, row.get(j));
				}
				if(row.get(j)<minValues.get(j)){ // check for min value
					minValues.set(j, row.get(j));
				}
			}
		}
		
		for (int i = 0; i < dataSet.size(); i++) {// for each row
			Vector<Double> row = dataSet.get(i);
			for (int j = 0; j < row.size() - 1; j++) {// for each column
				double element = ((row.get(j)-minValues.get(j)) / (maxValues.get(j)-minValues.get(j))) * (max-min) + min;
				row.set(j, element);
			}
			dataSet.set(i, row);
		}
		
		
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void Normalize(Vector<Vector> dataSet) {

		Vector<Double> sum = new Vector<Double>();

		// compute sum for each column
		for (int i = 0; i < dataSet.size(); i++) {// for each row

			Vector<Double> row = dataSet.get(i);

			for (int j = 0; j < row.size() - 1; j++) {// for each column
				if (i == 0) {
					sum.add(row.get(j));
				} else {
					double element = sum.get(j) + row.get(j);
					sum.set(j, element);
				}
			}
		}

		for (int i = 0; i < dataSet.size(); i++) {// for each row
			Vector<Double> row = dataSet.get(i);
			for (int j = 0; j < row.size() - 1; j++) {// for each column
				double element = row.get(j) / sum.get(j);
				row.set(j, element);
			}
		}

	}

}
