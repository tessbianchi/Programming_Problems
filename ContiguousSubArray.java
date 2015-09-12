import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class ContiguousSubArray{
	int[] array;
	int[] myArray;
	int saved = 0;
	int length;

	public ContiguousSubArray(int[] array, int length){
		this.array = array;
		this.length = length;
		myArray = new int[length];
	}

	public boolean isAllPositive(){
		for(int i = 0; i != array.length; ++i){
			if(array[i] < 0){
				return false;
			}
		}
		return true;
	}

	public int addAllElements(){
		int sum = 0;
		for(int i = 0; i != array.length; ++i){
			sum += array[i];
		}

		return sum;
	};

	public int addAllPositiveElements(){
		int sum = 0;
		for(int i = 0; i != array.length; ++i){
			if(array[i] > 0){
				sum += array[i];
			}
		}
		return sum;
	}

	public int method(){
		for(int i = 0; i != array.length; ++i){
			if(i == 0){
				myArray[i] = array[0];
			}else{
				int one = myArray[i-1];
				int two = array[i];
				int sum = array[i] + myArray[i-1];
				if(one > two && one > sum){
					if(one > saved){
						saved = one;
					}
					myArray[i] = sum;
				}else{
					if(two > sum){
						myArray[i] = two;
					}else{
						myArray[i] = sum;
					}
				}
			}
		}
		if(this.saved > myArray[this.length -1]){
			return saved;
		}else{
			return myArray[this.length -1];
		}
	}

	public void go(){
		if(isAllPositive()){
			System.out.print(addAllElements() + " ");
		}else{
			System.out.print(method() + " ");
		}

		System.out.print(addAllPositiveElements());

	}

	public static void main(String[] args){
	Scanner s = new Scanner(System.in);
	int numTestCases = s.nextInt();

	for(int i = 0; i != numTestCases; ++i){
		int length = s.nextInt();
		int[] arr = new int[length];
		for(int j = 0; j != length; ++j){
			arr[j] = s.nextInt();	
		}
		ContiguousSubArray c = new ContiguousSubArray(arr,length);
		c.go();
		System.out.println();
	}
}

}




	
