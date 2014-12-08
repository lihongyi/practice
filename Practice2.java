import java.util.*;

public class Practice2 {

	class Node {
		public Node next;
		public int item;
		public Node(int item, Node next){
			this.next = next;
			this.item = item;
		}
		public Node(int item){
			this(item, null);
		}
		public Node(){
			this(-99999, null);
		}
	}
	class TreeNode {
		public TreeNode left;
		public TreeNode right;
		public int item;
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

	public static String arrayToString(int[] input){
		StringBuilder output = new StringBuilder();
		output.append("{");
		for(int i = 0; i < input.length-1; i++){
			output.append(input[i]+", ");
		}
		output.append(input[input.length-1]+"}");
		return output.toString();
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


	public void printSList(Node head){
		Node current = head;
		StringBuilder sb = new StringBuilder();
		while(current.next != null){
			sb.append("["+current.item+"] --> ");
			current = current.next;
		}
		sb.append("[" + current.item +"]");
		System.out.println(sb.toString());
	}


	public void deleteDup(Node head){
		HashMap<Integer, Integer> map = new HashMap();
		Node previous = null;
		Node current = head;
		while(current != null){
			if(map.containsKey(current.item)){
				previous.next = current.next;
			} else {
				map.put(current.item, 1);
				previous = current;
			}
			current = current.next;
		}
	}

	public void reverseSList(Node head){
		Node prev = null;
		Node temp = null;
		Node current = head;
		while(current != null){
			temp = current.next;
			current.next = prev;
			prev = current;
			current = temp;
		}
	}

	public Node kthToLast(Node head, int k){
		Node current = head;
		Node runner = head;
		for(int i = 0; i < k; i++){
			runner = runner.next;
		}
		while(runner != null){
			runner = runner.next;
			current = current.next;
		}
		return current;
	}

	public Node partition(Node head, int x){
		Node smaller = null;
		Node smallerRunner = null;
		Node larger = null;
		Node largerRunner = null;
		Node current = head;

		while(current != null){
			if(current.item < x){
				if(smaller == null){
					smaller = current;
					smallerRunner = smaller;
				} else {
					smallerRunner.next = current;
					smallerRunner = smallerRunner.next;
				}
			} else {
				if(larger == null){
					larger = current;
					largerRunner = larger;
				} else {
					largerRunner.next = current;
					largerRunner = largerRunner.next;
				}
			}
			current = current.next;
		}
		smallerRunner.next = larger;
		largerRunner.next = null;
		return smaller;
	}

	public Node reverseAdd(Node num1, Node num2){
		int carry = 0;
		Node answerNodeBeginning = null;
		Node answerNode = null;
		int answer = 0;
		Node temp = null;
		while(num1 != null || num2 != null){
			if(num1 != null && num2 != null){
				answer = carry + num1.item + num2.item;
				carry = answer/10;
				answer = answer%10;
				temp = new Node(answer);
				if(answerNode == null){
					answerNode = temp;
					answerNodeBeginning = answerNode;
				} else {
					answerNode.next = temp;
					answerNode = answerNode.next;
				}
				if(num1.next == null && num2.next == null){
					answerNode.next = new Node(carry);
				}
				num1 = num1.next;
				num2 = num2.next;				
			} else if(num1 != null){
				answer = carry + num1.item;
				answerNode.next = new Node(answer%10);
				answerNode = answerNode.next;
				if(num1.next == null && answer >= 10){
					answerNode.next = new Node(answer/10);	
				}
				num1 = num1.next;				
			} else if(num2 != null){
				answer = carry + num2.item;
				answerNode.next = new Node(answer%10);
				answerNode = answerNode.next;
				if(num2.next == null && answer >= 10){
					answerNode.next = new Node(answer/10);	
				}
				num2 = num2.next;	
			} 	
		}
		return answerNodeBeginning;
	}

	public int countNodes(Node node){
		int count = 0;
		while(node != null){
			count++;
			node = node.next;
		}
		return count;
	}

	public Node forwardAdd(Node num1, Node num2){
		return null;
	}

	public boolean isPalindrome(Node head){
		return false;
	}


	//str = android
	//sub = rod
	//str = drdrdrdr
	// sub = rrd
	// public boolean isAnagramSubstring(String str, String substring){
	// 	HashMap<String, Integer> map = new HashMap();

	// 	int window = substring.length();
	// 	int counter = 0;

	// 	//initialize
	// 	for(int i = 0; i < window; i++){
	// 		if(map.get(""+str.charAt(i)) == null){
	// 			map.add(""+str.charAt(i), 1);
	// 		} else {
	// 			map.add(""+str.charAt(i), map.get(""+str.charAt(i)) + 1);
	// 		}
	// 	}

	// 	while(counter + window < str.length()){
	// 		for(int i = 0; i < window; i++){
	// 			if(map.get(""+substring.charAt(i)) != null && map.get(""+substring.charAt(i)) > 0){

	// 			}
	// 		}
	// 	}
	// }


	//do isomorphic matching

	//find common ancestor

	public boolean regexMatch(String str, String regex){
		if(regex.length() == 0){
			if(str.length() > 0){
				return false;
			}
			return true;
		}
		if(str.length() == 0){ // regex length is not zero
			if(regex.charAt(0) != '*'){
				return false;
			} else {
				return regexMatch(str, regex.substring(1));
			}
		}

		if( regex.charAt(0) == '.' || regex.charAt(0) == str.charAt(0)){
			return regexMatch(str.substring(1), regex.substring(1));
		} else if(regex.charAt(0) == '*'){
			return regexMatch(str, regex.substring(1)) || regexMatch(str.substring(1), regex);
		} else {
			return false;
		}
	}


	///////////////////////////////LINKEDIN GLASSDOOR PRACTICE////////////////////

	public void printLevelOrder(TreeNode node){
		LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
		StringBuilder sb = new StringBuilder();
		int level = 0;
		int levelCounter = 0;
		queue.add(node);
		sb.append("level "+level+": ");
		while(queue.size()>0){
			TreeNode curr = queue.removeFirst();
			if(levelCounter < Math.pow(2, level+1)-1){
				sb.append(""+curr.item + ", ");
				levelCounter++;
			} else {
				System.out.println(sb.toString());
				sb = new StringBuilder();
				level++;
				levelCounter = 0;
				sb.append("level "+level+": ");
			}
			if(curr.left != null){
				queue.add(curr.left);
			}
			if(curr.right != null){
				queue.add(curr.right);
			}
		}
	}


	public static int repeatedExpRecursive(int num, int exp){
		if(exp == 0){
			return 1;
		} else if(exp == 1){
			return num;
		}
		if(exp % 2 == 0){
			return repeatedExpRecursive(num * num, exp/2);
		} else {
			return num * repeatedExpRecursive(num * num, (exp-1)/2);
		}
	}
	public static int repeatedExpIterative(int num, int exp){
		int result = 1;

		while(exp >= 1){
			if(exp % 2 == 1){
				result *= num;
				exp -= 1;
			} 
			num *= num;
			exp /= 2;
		}
		return result;
	}

	public void printPreOrder(TreeNode root){
		if(root == null){
			return;
		}
		System.out.println(root.item);
		if(root.left != null){
			printPreOrder(root.left);
		}
		if(root.right != null){
			printPreOrder(root.right);
		}
	}
	public void printPostOrder(TreeNode root){
		if(root == null){
			return;
		}
		if(root.left != null){
			printPostOrder(root.left);
		}
		if(root.right != null){
			printPostOrder(root.right);
		}
		System.out.println(root.item);
	}
	public void printInOrder(TreeNode root){
		if(root == null){
			return;
		}
		if(root.left != null){
			printInOrder(root.left);
		}
		System.out.println(root.item);
		if(root.right != null){
			printInOrder(root.right);
		}
	}


	public boolean matchAnagramSubstring(String string, String sub){
		int subLength = sub.length();
		char[] strArray = string.toCharArray();
		HashMap<Character, Integer> subMap = new HashMap();
		for(char c : sub.toCharArray()){
			if(!subMap.containsKey(c)){
				subMap.put(c, 1);
			} else {
				subMap.put(c, subMap.get(c)+1);
			}
		}

		int sum = 0;
		for(int i = 0; i < string.length(); i++){
			if(i - subLength < 0){
				if(subMap.containsKey(strArray[i])){
					int num = subMap.get(strArray[i]) - 1;
					subMap.put(strArray[i], num);
					if(num == 0){
						sum++;
					} 
				} 
			} else {
				if(subMap.containsKey(strArray[i-subLength])){
					int num = subMap.get(strArray[i-subLength]) + 1;
					subMap.put(strArray[i-subLength], num);
					if(num == 1){
						sum--;
					}
				}
				if(subMap.containsKey(strArray[i])){
					int num = subMap.get(strArray[i]) - 1;
					subMap.put(strArray[i], num);
					if(num == 0){
						sum++;
					}
				} 
			}
			if(sum == subMap.size()){
				return true;
			}
		}
		return false;
	}

	public void pairSumXHash(int[] arr, int x){
		HashMap<Integer, Integer> map = new HashMap();
		for(int i = 0; i < arr.length; i++){
			if(!map.containsKey(arr[i])){
				map.put(arr[i], i);
			} 
			if(map.containsKey(x - arr[i])){
				System.out.println("FOUND A PAIR: "+arr[i] + ", "+(x-arr[i]));
			}
		}
	}
	public void pairSumXSort(int[] arr, int x){
		Arrays.sort(arr);
		int first = 0;
		int last = arr.length - 1;
		while(first < last){
			int sum = arr[first] + arr[last];
			if(sum < x){
				first++;
			} else if(sum > x) {
				last--;
			} else {
				System.out.println("FOUND A PAIR: "+arr[first] + ", "+arr[last]);
				last--;
			}
		}
	} 
	public int[] weaveNegatives(int[] arr){
		int[] result = new int[arr.length];
		LinkedList<Integer> negList = new LinkedList();
		LinkedList<Integer> posList = new LinkedList();
		for(int i = 0; i < arr.length; i++){
			if(arr[i] < 0){
				negList.add(arr[i]);
			} else {
				posList.add(arr[i]);
			}
		}

		LinkedList<Integer> longerList = (negList.size() > posList.size()) ? negList : posList;
		boolean isNextNeg = false;
		for(int i = 0; i < arr.length; i++){
			if(isNextNeg && negList.size() != 0){
				result[i] = negList.removeFirst().intValue();
				isNextNeg = false;
			} else if(!isNextNeg && posList.size() != 0){
				result[i] = posList.removeFirst().intValue();
				isNextNeg = true;
			} else {
				result[i] = longerList.removeFirst().intValue();
			}
		}
		return result;
	}

	
	

 
	public static void main(String[] args){
		System.out.println("hello world\n=====================");
		Practice2 prac = new Practice2();

		// Node node1 = prac.new Node(10); Node node2 = prac.new Node(2); Node node3 = prac.new Node(3);
		// Node node4 = prac.new Node(4); Node node5 = prac.new Node(2); Node node6 = prac.new Node(6);
		// Node node7 = prac.new Node(5); Node node8 = prac.new Node(1);
		// node1.next = node2; node2.next = node3; node3.next = node4; node4.next = node5; node5.next = node6;
		// node6.next = node7; node7.next = node8; 

		// prac.printSList(node1);
		// prac.printSList(prac.partition(node1, 4));
		// prac.reverseSList(node1);
		// prac.printSList(node6);
		// prac.deleteDup(node1);
		// prac.printSList(node1);

		////////
		Node num1one = prac.new Node(5); Node num1two = prac.new Node(7); Node num1three = prac.new Node(3);
		num1one.next = num1two; num1two.next = num1three;
		Node num2one = prac.new Node(3); Node num2two = prac.new Node(9); Node num2three = prac.new Node(8); Node num2four = prac.new Node(9);
		num2one.next = num2two; num2two.next = num2three; num2three.next = num2four;

		System.out.println("reverseAdd: \n");
		prac.printSList(prac.reverseAdd(num1one, num2one));
		
		System.out.println("\n===============\n");
		System.out.println("regexMatch: "+prac.regexMatch("abbbaab", ".b*b..."));
		System.out.println("regexMatch2: "+prac.regexMatch("bc", "**bc"));


		Practice2.TreeNode root = prac.new TreeNode(0); 
		TreeNode node1 = prac.new TreeNode(1); TreeNode node2 = prac.new TreeNode(2);
		TreeNode node3 = prac.new TreeNode(3); TreeNode node4 = prac.new TreeNode(4); 
		TreeNode node5 = prac.new TreeNode(5); TreeNode node6 = prac.new TreeNode(6); 
		root.left = node1; root.right=node2;
		node1.left = node3; node1.right=node4;
		node2.left = node5; node2.right=node6;
		//TreeNode node7 = new TreeNode(7); TreeNode node1 = new TreeNode(1);
		//System.out.println("isBalanced: "+prac.isBalanced(root));
		System.out.println("levelOrder: ");
		prac.printLevelOrder(root);
		prac.printPreOrder(root);
		System.out.println("===");
		prac.printPostOrder(root);
		System.out.println("===");
		prac.printInOrder(root);


		//////////////////////
		System.out.println("\n\n\n\nREPEATEDEXPRECURSIVE: "+Practice2.repeatedExpRecursive(2, 9));
		System.out.println("REPEATEDEXPITERATIVE: "+Practice2.repeatedExpIterative(2, 9));
		System.out.println("booleanAnagramSubstring: "+prac.matchAnagramSubstring("dddrdd", "drd"));
		System.out.println("\n\nPairSumXHash: ");
		int[] pairSumArr = new int[]{3, 2, 5, 4, 0, 9};
		//prac.pairSumXHash(pairSumArr, 5);
		prac.pairSumXSort(pairSumArr, 5);
		int [] weaveNegativesArr = new int[]{-2, 1, 5, 7, -3, -1, -20, -70, -100};
		int [] weaveNegativesResult = prac.weaveNegatives(weaveNegativesArr);
		for(int weave: weaveNegativesResult){
			System.out.println("weave: "+weave);
		}

	}

}