import java.util.Iterator;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		
		System.out.print("Starting with an empty tree: ");
		System.out.print("Size: " + bst.size() + "\n");

		documentAdd(bst, 1);

		documentAdd(bst, 5);
		
		documentAdd(bst, 7);
		
		documentRemove(bst, 1);
		
		documentAdd(bst, 8);
		documentAdd(bst, 2);
		documentAdd(bst, 4);
		documentAdd(bst, 9);
		documentAdd(bst, 10);
		documentAdd(bst, 6);
		
		documentRemove(bst, 5);
		
		System.out.println("\nT/F: The binary search tree CONTAINS 2: " + bst.contains(2));
		System.out.println("T/F: The binary search tree CONTAINS 8: " + bst.contains(8));
		System.out.println("T/F: The binary search tree CONTAINS 1: " + bst.contains(1));
		System.out.println("T/F: The binary search tree CONTAINS 6: " + bst.contains(6));
		
		System.out.println("\nThe minimum value of the tree is: " + bst.min());
		System.out.println("The maximum value of the tree is: " + bst.max());


		System.out.println("\nNow, for the traversals...");
		System.out.println("PREORDER:");
		traversal(bst, BSTInterface.Traversal.Preorder);
		
		System.out.println("INORDER:");
		traversal(bst, BSTInterface.Traversal.Inorder);
		
		System.out.println("POSTORDER");
		traversal(bst, BSTInterface.Traversal.Preorder);
		
		
		System.out.println("\n\nNow it's your turn to try it\n");
		
		System.out.println("Starting with an empty tree, this time for String values");
		BinarySearchTree<String> stringBst = new BinarySearchTree<String>();
						
		Scanner scan = new Scanner(System.in);
		
		while (true) {
			menu();
			System.out.println("\nEnter menu option (and get it right because there's no validation)");

			int menuOpt = scan.nextInt();
			scan.nextLine();
		
			switch(menuOpt) {
				case 1: 
					System.out.println("Enter string to add to tree");
					String data = scan.nextLine();
					stringBst.add(data);
					break;
					
				case 2:
					if (stringBst.isEmpty()) {
						System.out.println("Empty tree");
					} else {
						System.out.println("Enter string to remove from tree (must be exact");
						String removeData = scan.nextLine();
						stringBst.remove(removeData);
					}
					break;
					
				case 3:
					System.out.println("Size: " + stringBst.size());
					
					if (stringBst.isEmpty()) {
						System.out.println("Empty tree.");
					} else {
						System.out.println("Min: " + stringBst.min());
						System.out.println("Max: " + stringBst.max());
						
						System.out.println("Preorder traversal: ");
						traversal(stringBst, BSTInterface.Traversal.Preorder);
						
						System.out.println("Inorder traversal: ");
						traversal(stringBst, BSTInterface.Traversal.Inorder);
						
						System.out.println("Postorder traversal: ");
						traversal(stringBst, BSTInterface.Traversal.Postorder);
						
						System.out.println("Breadth-first traversa");
						System.out.println(stringBst.toString());
					}
	
					break;
				case 4:
					System.out.println("Goodbye");
					System.exit(0);
			}
		}
		
	}

	private static void documentAdd(BinarySearchTree bst, int num) {
		System.out.println("\nAdding \"" + num + "\"");
		bst.add(num);
		System.out.println("Size: " + bst.size());
		System.out.println("Tree so far (breadth-first): " + bst.toString());
	}
	
	private static void documentRemove(BinarySearchTree bst, Integer num) {
		System.out.println("\nRemoving \"" + num + "\"");
		bst.remove(num);
		System.out.println("Size: " + bst.size());
		System.out.println("Tree so far (breadth-first): " + bst.toString());
	}
	
	private static void traversal(BinarySearchTree bst, BSTInterface.Traversal traversalOrder) {
		Iterator<Integer> iterator = bst.getIterator(traversalOrder);
		while (iterator.hasNext()) {
		    System.out.print(iterator.next() + " ");
		}
		System.out.println("\n");
	}
	
	private static void menu() {
		System.out.println("\n~~~~~ MENU ~~~~~");
		System.out.println("1. Add");
		System.out.println("2. Remove");
		System.out.println("3. Stats");
		System.out.println("4. Exit");
	}

}
