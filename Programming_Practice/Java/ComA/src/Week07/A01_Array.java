package Week07;

public class A01_Array {
	public static void main(String[] args) {
		
//		int[] arr = new int[10];
//		
//		for (int i = 0; i < arr.length; i++) {
//			System.out.println(i);
//		}
//		
//		int[] sco = {0, 1, 2, 3, 4};
//		
//		for (int i = 0; i < sco.length; i++) {
//			System.out.println(i);
//		}
		
		int[][] arr2 = new int[4][5];
		for (int i = 0; i < arr2.length; i++) {
			for (int j = 0; j < arr2[i].length; j++) {
				System.out.println("arr2[" + i + "][" + j + "]");
			}
		}
	}
}
