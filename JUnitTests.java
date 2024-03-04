import java.util.Iterator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JUnitTests {
	BinarySearchTree<Integer> intBst;
	
	@BeforeEach
	public void initEach(){
	     intBst = new BinarySearchTree<Integer>();
	}
	
	@Test
	void assertEmptyTreeSizeIsZero() {
		Assertions.assertEquals(intBst.size(), 0);
	}
	
	@Test
	void addRootNodeToTree() {
		intBst.add(4);
		
		Assertions.assertEquals(intBst.min(), 4);
		Assertions.assertEquals(intBst.max(), 4);
		Assertions.assertEquals(intBst.size(), 1);
		Assertions.assertTrue(intBst.contains(4));
	}
	
	@Test
	void addSeveralNodesToTree() {
		intBst.add(4);
		intBst.add(3);
		intBst.add(6);
		intBst.add(5);
		intBst.add(9);
		intBst.add(8);

		Assertions.assertEquals(intBst.min(), 3);
		Assertions.assertEquals(intBst.max(), 9);
		Assertions.assertEquals(intBst.size(), 6);
		Assertions.assertFalse(intBst.contains(11));
	}
	
	@Test
	void testGetMethod() {
		intBst.add(4);
		intBst.add(3);
		intBst.add(6);
		
		Assertions.assertEquals(intBst.get(3), 3);
	}
	
	@Test
	void testRemoveMethodDecrementsTreeSize() {
		intBst.add(11);
		intBst.add(1);
		intBst.add(-6);
		intBst.add(5);
		intBst.add(4);
		intBst.add(8);
		
		Assertions.assertEquals(intBst.size(), 6);
		
		intBst.remove(5);
		
		Assertions.assertEquals(intBst.size(), 5);
	}
	
	@Test
	void testRemoveMethodMaintainsBSTProperties1() {
		intBst.add(8);
		intBst.add(11);
		intBst.add(1);
		intBst.add(-6);
		intBst.add(5);
		intBst.add(4);
				
		intBst.remove(1);
		
		int[] expectedRevisedTree = {8, -6, 5, 4, 11};
		int[] revisedTree = new int[5];
		int i = 0;
		
		Iterator<Integer> preOrderIterator = intBst.getIterator(BSTInterface.Traversal.Preorder);
		while (preOrderIterator.hasNext()) {
		    revisedTree[i++] = preOrderIterator.next();
		}
		
		Assertions.assertArrayEquals(revisedTree, expectedRevisedTree);
	}
	
	@Test
	void testRemoveMethodMaintainsBSTProperties2() {
		intBst.add(8);
		intBst.add(11);
		intBst.add(1);
		intBst.add(-6);
		intBst.add(5);
		intBst.add(4);
				
		intBst.remove(8);
		
		int[] expectedRevisedTree2 = {5, 1, -6, 4, 11};
		int[] revisedTree2 = new int[5];
		int i = 0;
		
		Iterator<Integer> preOrderIterator = intBst.getIterator(BSTInterface.Traversal.Preorder);
		while (preOrderIterator.hasNext()) {
		    revisedTree2[i++] = preOrderIterator.next();
		}
		
		Assertions.assertArrayEquals(revisedTree2, expectedRevisedTree2);
	}
	
	@Test
	void checkPreOrderTraversal() {
		intBst.add(4);
		intBst.add(3);
		intBst.add(6);
		intBst.add(5);
		intBst.add(9);
		intBst.add(8);

		int[] expectedPreOrderNodes = {4, 3, 6, 5, 9, 8};
		int[] preOrderNodes = new int[6];
		int i = 0;
		
		Iterator<Integer> preOrderIterator = intBst.getIterator(BSTInterface.Traversal.Preorder);
		while (preOrderIterator.hasNext()) {
		    preOrderNodes[i++] = preOrderIterator.next();
		}
		
		Assertions.assertArrayEquals(expectedPreOrderNodes, preOrderNodes);
	}
	
	@Test
	void checkInOrderTraversal() {
		intBst.add(4);
		intBst.add(3);
		intBst.add(6);
		intBst.add(5);
		intBst.add(9);
		intBst.add(8);

		int[] expectedInOrderNodes = {3, 4, 5, 6, 8, 9};
		int[] inOrderNodes = new int[6];
		int i = 0;
		
		Iterator<Integer> preOrderIterator = intBst.getIterator(BSTInterface.Traversal.Inorder);
		while (preOrderIterator.hasNext()) {
		    inOrderNodes[i++] = preOrderIterator.next();
		}
		
		Assertions.assertArrayEquals(expectedInOrderNodes, inOrderNodes);
	}
	
	@Test
	void checkPostOrderTraversal() {
		intBst.add(4);
		intBst.add(3);
		intBst.add(6);
		intBst.add(5);
		intBst.add(9);
		intBst.add(8);

		int[] expectedPostOrderNodes = {3, 5, 8, 9, 6, 4};
		int[] postOrderNodes = new int[6];
		int i = 0;
		
		Iterator<Integer> postOrderIterator = intBst.getIterator(BSTInterface.Traversal.Postorder);
		while (postOrderIterator.hasNext()) {
		    postOrderNodes[i++] = postOrderIterator.next();
		}
		
		Assertions.assertArrayEquals(expectedPostOrderNodes, postOrderNodes);
	}

}
