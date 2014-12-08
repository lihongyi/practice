import java.util.*;

public class Practice {

	class Node {
		public Node next;
		public int item;
		public Node(int item, Node next){
			this.next = next;
			this.item = item;
		}
		public Node(){
			this(-99999, null);
		}
	}
	class TreeNode {
		public TreeNode left;
		public TreeNode right;
		public int item;
		public int id;
		public TreeNode(int item){
			this.item = item;
		}
		public TreeNode(){
			this(-99999);
		}
	}
	class graphNode{
		public int item;
		public boolean visited;
		ArrayList<graphNode> neighbors;
		public graphNode(int item){
			this.item = item;
			this.neighbors = new ArrayList();
			this.visited = false;
		}
	}

	public static String fizzBuzz(int input){
		if((input % 3 == 0) && (input % 5 == 0)){
			return "fizzBuzz";
		}else if(input % 3 == 0){
			return "fizz";
		} else {
			return "buzz";
		}
	}


	public static String arrayToString(int[] input){
		StringBuilder output = new StringBuilder();
		output.append("{");
		for(int i = 0; i < input.length-1; i++){
			output.append(input[i]+", ");
		}
		output.append(input[input.length-1]+"}");
		return output.toString();
	}

	public int findEven(int[] input){
		HashMap<Integer, Integer> map = new HashMap();

		for(int i = 0; i < input.length; i++){
			if(map.containsKey(input[i])){
				map.put(input[i], map.get(input[i]) + 1);
			} else {
				map.put(input[i], 1);
			}
		}
		for(Integer item: map.keySet()){
			if(map.get(item) % 2 == 0){
				return item;
			}
		}
		return -1;
	}

	public int[] alternateNumbers(int[] input){
		int[] output = new int[input.length];

		ArrayList<Integer> positives = new ArrayList();
		ArrayList<Integer> negatives = new ArrayList();

		for(int i=1; i < input.length; i++){
			if(input[i]<0){
				negatives.add(input[i]);
			} else{
				positives.add(input[i]);
			}
		}

		int lastNum = input[0];
		output[0] = input[0];
		for(int j = 1; j < output.length; j++){
			if(positives.size() > 0 && negatives.size() > 0){
				if(lastNum < 0){
					lastNum = positives.get(0);
					positives.remove(0);
				} else {
					lastNum = negatives.get(0);
					negatives.remove(0);
				}
				output[j] = lastNum;
			} else if(negatives.size() > 0){
				output[j] = negatives.get(0);
				negatives.remove(0);
			} else {
				output[j] = positives.get(0);
				positives.remove(0);
			}
		}
		return output;
	}

	public boolean binarySearch(int[] input, int number){
		int low = 0;
		int high = input.length-1;
		int mid;
		while(low <= high){
			mid = (low+high)/2;
			if(input[mid] == number){
				return true;
			} else if(input[mid] < number){
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		return false;
	}

	public TreeNode postOrder(TreeNode root){
		return null;
	}

	//keep doing this
	// public void allAnagrams(String str, int length, String sofar){ 
	// 	if(length == str.length()){
	// 		System.out.println("!!!: "+str);
	// 	} else {
	// 		for(int i = 1; i <= str.length(); i++){
	// 			allAnagrams(str, , );
	// 		}
	// 	}
	// }

	public int height(TreeNode root){
		if(root == null){
			return 0;
		} else {
			return 1 + Math.max(height(root.left), height(root.right));
		}
	}
	public boolean isBalanced(TreeNode root){
		if(root == null){
			return true;
		} 
		if(height(root.left) != height(root.right)){
			return false;
		} else {
			return isBalanced(root.left) && isBalanced(root.right);
		}
	}

	public boolean isPath(graphNode start, graphNode end){
		ArrayList<graphNode> queue = new ArrayList();
		queue.add(start);
		start.visited = true;
		graphNode current;
		while(!queue.isEmpty()){
			current = queue.remove(0);
			if(current.item == end.item){
				return true;
			}
			current.visited = true;
			for(graphNode node : current.neighbors){
				if(!node.visited){
					queue.add(node);	
				}
				
			}
		}
		return false;
	}

	public TreeNode makeBST(int[] input, int start, int end){
		if(start > end){
			return null;
		} 
		int mid = (start + end) / 2;
		TreeNode node = new TreeNode(input[mid]);
		node.left = makeBST(input, start, mid-1);
		node.right = makeBST(input, mid+1, end);
		return node;
	}

	public void levelOrder(TreeNode root){
		StringBuilder sb = new StringBuilder();
		int level = 0;
		int levelCounter = 0;
		if(root == null){
			sb.append("=DONE=");
			System.out.println(sb.toString());
			return;
		}
		LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
		TreeNode temp = null;
		queue.add(root);
		while(queue.size() > 0){
			temp = queue.removeFirst();
			//if it's last item on row, refresh after appending & printing
			if(levelCounter == Math.pow(2.0, level)-1){
				sb.append(temp.item);
				System.out.println(sb.toString());
				sb = new StringBuilder();
				level++;
				levelCounter = 0;
			} else {
				sb.append(temp.item + ", ");
				levelCounter++;
			}
			if(temp.left != null){
				queue.add(temp.left);
			}
			if(temp.right != null){
				queue.add(temp.right);
			}
			
		}

	}

	public void levelLinkedList(TreeNode root, int level, LinkedList<ArrayList<TreeNode>> lists){
		if(root == null){
			return;
		}
		ArrayList<TreeNode> currentList = null;
		if(lists.size() == level){
			currentList = new ArrayList();
			lists.add(currentList);
		} else {
			currentList = lists.get(level);
		}
		currentList.add(root);
		levelLinkedList(root.left, level+1, lists);
		levelLinkedList(root.right, level+1, lists);
	}

	public static boolean isUnique(String str){
		HashMap<Character, Integer> map = new HashMap();
		for(int i = 0; i < str.length(); i++){
			if(map.get(str.charAt(i)) == null){
				map.put(str.charAt(i), 1);
			} else {
				return false;
			}
		}
		return true;
	}

	public boolean isPermutation(String str1, String str2){
		if(str1.length() != str2.length()){
			return false;
		}
		int[] letters = new int[256];
		char[] chars = str1.toCharArray();
		for(char c : chars){
			letters[c]++;
		} 
		for(int i = 0; i < str2.length(); i++){
			int c = (int)str2.charAt(i);
			letters[c] = letters[c] - 1;
			if(letters[c] < 0){
				return false;
			}
		}
		return true;
	}

	public String replaceSpace(String str){
		char[] original = str.toCharArray();
		int spaces = 0;
		for(char c : original){
			if(c == ' '){
				spaces++;
			}
		}
		char[] replaced = new char[original.length + 2*spaces];
		int replacedPointer = 0;
		for(int i = 0; i < original.length; i++){
			if(original[i] == ' '){
				replaced[replacedPointer] = '%';
				replaced[replacedPointer+1] = '2';
				replaced[replacedPointer+2] = '0';
				replacedPointer+=3;
			} else {
				replaced[replacedPointer] = original[i];
				replacedPointer++;
			}
		}
		return new String(replaced);
	}

	public String compressString(String str){
		int runCount = 0;
		char prev = str.charAt(0);
		char[] original = str.toCharArray();
		StringBuilder sb = new StringBuilder();

		for(char c : original){
			if(c == prev){
				runCount++;
			} else {
				sb.append(prev);
				sb.append(runCount);
				prev = c;
				runCount = 1;
			}
		}
		sb.append(prev);
		sb.append(runCount);
		String returnString = sb.toString();
		return (returnString.length() < str.length()) ? returnString : str;
	}


	public static void printMatrix(int[][] matrix){
		String tempRow;
		for(int i = 0; i < matrix.length; i++){
			tempRow = "[";
			for(int j = 0; j<matrix[0].length-1; j++){
				tempRow = tempRow + matrix[i][j];
				if(matrix[i][j] < 10){
					tempRow+= "  ";
				} else {
					tempRow+= " ";
				}
			}
			tempRow = tempRow + matrix[i][matrix[0].length-1]; 
			if(matrix[i][matrix[0].length-1] < 10){
				tempRow+= " ";
			} else {
				tempRow+= "";
			}
			tempRow+= "]";
			System.out.println(tempRow);
		}
	}

	public int[][] rotate90Degrees(int[][] image){
		//int layer = 0;
		int n = image.length;
		int boxes;
		int temp;
		for(int layer = 0; layer < n/2; layer++){
			boxes = n - (2*layer) - 1;
			for(int i = 0; i < boxes; i++){
				temp = image[layer][layer+i];
				image[layer][layer+i] = image[layer+boxes-i][layer];
				image[layer+boxes-i][layer] = image[layer+boxes][layer+boxes-i];
				image[layer+boxes][layer+boxes-i] = image[layer+i][layer+boxes];
				image[layer+i][layer+boxes] = temp;
			}
		}
		return image;
	}

	public int[][] facebookSpiral(int[] input){
		int n = (int)Math.sqrt(input.length);
		int[][] output = new int[n][n];
		int layer = 0;
		int boxes = n-(2*layer)-1;
		int changeCounter = 0;

		int inputLength = input.length;
		if(n%2==1){
			inputLength = input.length-1;
		} 
		for(int pointer = 0; pointer < inputLength; pointer++){
			if(changeCounter < boxes){
				output[layer][layer+changeCounter] = input[pointer];
				System.out.println("1: "+input[pointer]);
				changeCounter++;
			} else if(changeCounter < 2*boxes){
				output[layer+(changeCounter-boxes)][layer+boxes] = input[pointer];
				System.out.println("2: "+input[pointer]);
				changeCounter++;
			} else if(changeCounter < 3 * boxes){
				output[layer+boxes][layer+boxes-(changeCounter-(2*boxes))] = input[pointer];
				System.out.println("3: "+input[pointer]);
				changeCounter++;
			} else {
				if(changeCounter >= 4*boxes){
					changeCounter = 0;
					layer++;
					boxes = n-(2*layer)-1;
					pointer--;
					System.out.println("reset!!===");
				} else {
					output[layer+boxes-(changeCounter-(3*boxes))][layer] = input[pointer];
					System.out.println("4: "+input[pointer]);
					changeCounter++;
				}
			}
		}
		if(n%2==1){
			output[n/2][n/2] = input[input.length-1];
		}
		return output;

	}

	public int[][] zeroRowColumn(int[][] matrix){
		int m = matrix.length;
		int n = matrix[0].length;
		boolean[] rowArray = new boolean[m];
		boolean[] colArray = new boolean[n];

		for(int row = 0; row < m; row++){
			for(int col = 0; col < n; col++){
				if(matrix[row][col] == 0){
					rowArray[row] = true;
					colArray[col] = true;
				}
			}
		}

		for(int row = 0; row < m; row++){
			for(int col = 0; col < n; col++){
				if(rowArray[row] || colArray[col]){
					matrix[row][col] = 0;
				}
			}
		}
		return matrix;
	}


	////////////////////CHAPTER 4//////////////////////////////
	public TreeNode kthFromNumBST(TreeNode root, int num, int k){
		//first find n
		//set n as currFloor
		//find next smallest item larger than currFloor
		LinkedList<TreeNode> list = new LinkedList<TreeNode>();
		int kthSmallest = num;
		for(int i = 0; i < k; i++){
			TreeNode x = smallestNumGreaterThan(root, kthSmallest, null);
			list.add(x);
			kthSmallest = x.item + 1;
			System.out.println("x: "+x.item);
		}
		return list.removeLast();
	}


	public TreeNode findLowestNumBST(TreeNode root, int num, TreeNode tempBest){
		//System.out.println(root.item);
		if(root == null){
			return tempBest;
		}
		if(root.item == num){
			if(root.left == null && root.right == null){
				System.out.println("leaf: "+root.id);
				return root;
			} else {
				return findLowestNumBST(root.right, num, root);	
			}			
		} else if(root.item < num){
			//System.out.println(root.item);
			return findLowestNumBST(root.right, num, tempBest);
		} else {
			return findLowestNumBST(root.left, num, tempBest);
		}
	}

	public TreeNode smallestNumGreaterThan(TreeNode root, int num, TreeNode tempBest){
		if(root == null){
			return tempBest;
		}
		if(root.item == num){
			return root;
		} else if(root.item < num){
			return smallestNumGreaterThan(root.right, num, tempBest);
		} else {
			return smallestNumGreaterThan(root.left, num, root);
		}
	}


	public int countTree(TreeNode root){
		int left = 0;
		int right = 0;
		if(root == null){
			return 0;
		}
		if(root.left != null){
			left = countTree(root.left);
		}
		if(root.right != null){
			right = countTree(root.right);
		}
		return 1 + left + right;
	}
	

	public boolean isBST(TreeNode root){
		if(root == null){
			return true;
		}
		if(root.left != null){
			if(root.left.item > root.item){
				return false;
			}	
		}		
		if (root.right != null){
			if(root.right.item <= root.item){
				return false;
			}
		} 
		if(root.left == null && root.right == null){
			return true;
		}
		return isBST(root.left) && isBST(root.right);
	}

	public int[] mergeSortArray(int[] input){
		if(input.length <= 1){
			return input;
		}
		int mid = input.length / 2;
		int[] left = new int[mid];
		int[] right = new int[input.length -mid];

		int i;
		int j;
		for(i = 0; i < mid; i++){
			left[i] = input[i];
		}
		for(j = i; j < input.length; j++){
			right[j-i] = input[j];
		}
		return mergeArrays(mergeSortArray(left), mergeSortArray(right));

	}

	public int[] mergeArrays(int[] input1, int[] input2){
		int pointer1 = 0;
		int pointer2 = 0;
		int outputPointer = 0;
		int[] output = new int[input1.length + input2.length];
		while(outputPointer < output.length){
			if(pointer1 == input1.length){
				output[outputPointer] = input2[pointer2];
				pointer2++;
			} else if(pointer2 == input2.length){
				output[outputPointer] = input1[pointer1];
				pointer1++;
			}
			else if(input1[pointer1] <= input2[pointer2]){
				output[outputPointer] = input1[pointer1];
				pointer1++;
			} else {
				output[outputPointer] = input2[pointer2];
				pointer2++;
			}
			outputPointer++;
		}
		return output;
	}
	
	public static int randInt(int min, int max) {
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
	public void quickSortArray(int[] input, int low, int high){
		if(low < high){
			int pivotIndex = randInt(low, high-1);
			int pivot = input[pivotIndex];
			int i = low;
			int j = high - 1;
			input[pivotIndex] = input[high];
			input[high] = pivot; 

			while(i < j){
				while(input[i] < pivot){
					i++;
				}
				while(input[j] > pivot && j > low){
					j--;
				}
				int temp = input[i];
				input[i] = input[j];
				input[j] = temp;
			}
			input[high] = input[i];
			input[i] = pivot;

			quickSortArray(input, low, i - 1);
			quickSortArray(input, i + 1, high);
		}
	}
	public int[] quickSort(int[] input){
		quickSortArray(input, 0, input.length-1);
		return input;
	}

	// public static boolean regex(String str, String expr){
	// 	if(str.length <= 1){
	// 		if()
	// 	}
	// 	if(!expr.substring(0, 2).contains("*")){ //It's a normal group

	// 	}
	// }

	public int findFirstIndex(int[] input, int num){
		int low = 0;
		int high = input.length;
		while(low <= high){
			int mid = (low + high) / 2;
			if(input[mid] == num){
				if(mid < 1 || input[mid-1] != num){
					return mid;
				} 
				high = mid - 1;
			} else if(input[mid] < num){
				low = mid + 1;
			} else {
				high = mid - 1;
			}			
		}
		return -1;
	}

	public LinkedList<String> stringPermRecursive(String str){
		LinkedList<String> list = new LinkedList();
		stringPermRecursiveHelper("", str, list);
		return list;
	}
	public void stringPermRecursiveHelper(String prefix, String rest, LinkedList<String> list){
		if(rest.length() == 0){
			list.add(prefix);
		}
		for(int i = 0; i < rest.length(); i++){
			stringPermRecursiveHelper(prefix + rest.charAt(i), rest.substring(0, i) + rest.substring(i+1), list);
		}
	}



	public static void main(String[] args){
		System.out.println("hello world\n=====================");
		Practice prac = new Practice();
		System.out.println("fizzbuzz: "+fizzBuzz(25));
		
		int[] evenAndOdds = new int[]{1, 3, 2, 1, 9, 1, 2, 2, 9, 2, 9, 2, 4, 5, 4, 8};
		System.out.println("findEven: "+prac.findEven(evenAndOdds));

		int[] posAndNeg = new int[]{-1, -3, -2, 1, -9, 1, 2, -2, 9, 2, -9, -2, -4, 5, 4, -8, -4};
		System.out.println("alternateNumbers: "+arrayToString(prac.alternateNumbers(posAndNeg)));

		int[] sorted = new int[]{-4, -3, -2, -2, -2, -2, 0, 4, 9, 12, 15, 19, 70, 71, 74, 100, 104, 205};
		System.out.println("binarySearch: " +prac.binarySearch(sorted, -3));

		Practice.TreeNode root = prac.new TreeNode(0); 
		TreeNode node1 = prac.new TreeNode(1); TreeNode node2 = prac.new TreeNode(2);
		TreeNode node3 = prac.new TreeNode(3); TreeNode node4 = prac.new TreeNode(4); 
		TreeNode node5 = prac.new TreeNode(5); TreeNode node6 = prac.new TreeNode(6); 
		root.left = node1; root.right=node2;
		node1.left = node3; node1.right=node4;
		node2.left = node5; node2.right=node6;
		//TreeNode node7 = new TreeNode(7); TreeNode node1 = new TreeNode(1);
		System.out.println("isBalanced: "+prac.isBalanced(root));
		System.out.println("levelOrder: ");
		prac.levelOrder(root);


		System.out.println("string isUnique: "+Practice.isUnique("helo"));
		System.out.println("string isPermutation: "+prac.isPermutation("hello", "loehh"));
		System.out.println("string replaceSpace: "+prac.replaceSpace(" son of a  bitch"));
		System.out.println("string compressString: "+prac.compressString("aabcccccaaa"));

		int[] imageRow1 = new int[]{1, 2, 3, 4, 5};
		int[] imageRow2 = new int[]{6, 7, 8, 9, 10};
		int[] imageRow3 = new int[]{11, 12, 13, 14, 15};
		int[] imageRow4 = new int[]{16, 17, 18, 19, 20};
		int[] imageRow5 = new int[]{21, 22, 23, 24, 25};
		int[][] image = new int[][]{imageRow1, imageRow2, imageRow3, imageRow4, imageRow5};
		Practice.printMatrix(image);
		System.out.println("matrix rotate90Degrees: ");
		Practice.printMatrix(prac.rotate90Degrees(image));

		int[] spiralInput = new int[]{1, 2, 3, 4, 5, 6, 7, 8,9,10,11,12,13,14,15,16, 17, 18, 19, 20, 21, 22, 23,24,25};
		System.out.println("facebookSpiral: ");
		Practice.printMatrix(prac.facebookSpiral(spiralInput));

		int[] zero1 = new int[]{1, 2, 0, 4, 5};
		int[] zero2 = new int[]{6, 7, 8, 9, 10};
		int[] zero3 = new int[]{11, 12, 13, 14, 15};
		int[] zero4 = new int[]{16, 0, 18, 19, 20};
		int[] zero5 = new int[]{21, 22, 23, 24, 0};
		int[][] matrixWithZero = new int[][]{zero1, zero2, zero3, zero4, zero5};
		System.out.println("zeroRowColumn: ");
		Practice.printMatrix(prac.zeroRowColumn(matrixWithZero));


		System.out.println("=============CHAPTER 4========================");
		Practice.TreeNode broot = prac.new TreeNode(6); 
		TreeNode bnode1 = prac.new TreeNode(3); TreeNode bnode2 = prac.new TreeNode(10);
		TreeNode bnode3 = prac.new TreeNode(1); TreeNode bnode4 = prac.new TreeNode(5); 
		TreeNode bnode5 = prac.new TreeNode(8); TreeNode bnode6 = prac.new TreeNode(13); 
		TreeNode bnode7 = prac.new TreeNode(4); TreeNode bnode8 = prac.new TreeNode(7); 
		TreeNode bnode9 = prac.new TreeNode(9); TreeNode extrabnode = prac.new TreeNode(4);
		broot.left = bnode1; broot.right=bnode2;
		bnode1.left = bnode3; bnode1.right=bnode4;
		bnode2.left = bnode5; bnode2.right=bnode6;
		bnode4.left = bnode7; bnode5.left = bnode8;
		bnode5.right = bnode9; bnode7.right = extrabnode;
		bnode7.id = 0; extrabnode.id = 1;
		System.out.println("findLowestNumBST: "+prac.findLowestNumBST(broot, 4, null).item);
		System.out.println("smallestNumGreaterThan: "+prac.smallestNumGreaterThan(broot, 2, null).item);
		System.out.println("kthFromNumBST: "+prac.kthFromNumBST(broot, 5, 3).item);
		System.out.println("isBST: "+prac.isBST(broot));
		System.out.println("countTree: "+prac.countTree(broot));


		System.out.println("==============\n\n\n");
		int[] arrayToMerge = new int[]{5, 1, 3,2,1,7,4, 10, 0};
		System.out.println("mergesort: "+Practice.arrayToString(prac.mergeSortArray(arrayToMerge)));
		System.out.println("quicksort: "+Practice.arrayToString(prac.quickSort(arrayToMerge)));

		System.out.println("BSTFINDFIRSTINDEX: "+prac.findFirstIndex(new int[]{1, 1, 2, 4, 7, 7, 7, 7, 7, 7, 7}, 7));
		System.out.println("=\n=\n=");
		int n = 0;
		for(String str : prac.stringPermRecursive("ABC")){
			n++;
			System.out.println("stringPermRecursive: "+str + ", n = "+n);
		}
		
	}


}