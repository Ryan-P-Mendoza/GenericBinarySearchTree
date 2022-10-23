import java.util.*;

public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{
   
	// check if the tree is empty before performing a node count
	public int nodeCount()
    {
		if( isEmpty() ) {
            System.out.println( "Empty tree" );
            return 0;
        }
       return nodeCount(root);
    }
	// reads in a root node to start counting
	private int nodeCount(BinaryNode<AnyType> t) 
	{ 
		// checking again if the tree is empty
		if (t == null) 
		    return 0; 
		
		int res = 0; 
    	// check if branch
		if (t.left != null || t.right != null) 
    	    res++; 
    	// check if leaf
		else if(t.left == null && t.right == null)
    	    res++;
    	
		// Recursively call upon the count till we run out of nodes
    	res += (nodeCount(t.left) + nodeCount(t.right)); 
    	return res;
	    
	} 
	
	// check if tree empty
	public boolean isFull( )
    {
		if( isEmpty() ) {
            System.out.println( "Empty tree" );
            return false;
        }
       return isFull(root);
    }
	
	// reads in a root node to start
	private boolean isFull(BinaryNode<AnyType> t)
    {
        // if it is an empty tree then it is full
        if(t == null)
        	return true;
          
        // if it is a leaf node
        if(t.left == null && t.right == null )
            return true;
          
        // if both left and right aren't null then it is a full tree
        if((t.left!=null) && (t.right!=null))
            // recursive call till we check the whole tree
        	return (isFull(t.left) && isFull(t.right));
          
        // If it isn't a full tree then return false
        return false;
    }
	
	// For comparing it reads in another tree to compare to the calling tree
	public boolean compareStructure(BinarySearchTree<AnyType> y )
    {
		// to start iterating through the trees we need to root of the second tree
		BinaryNode<AnyType> x = y.root;
		// check if either tree is empty
		if( isEmpty() ) {
            System.out.println( "Tree 1 is an empty tree" );
            return false;
        }
		if( y.isEmpty() ) {
            System.out.println( "Tree 2 is an empty tree" );
            return false;
        }
       return compareStructure(root, x);
    }
	
	// reads in the roots of the two tress to traverse and compare them
	private boolean compareStructure(BinaryNode<AnyType> t, BinaryNode<AnyType> y) {
		
		// Check if empty
	    if (t == null && y == null)
	        return true;
	         
	    // If one tree is empty and the other isn't then they are not the same structure
	    else if (t != null && y == null)
	        return false;
	    
	    else if (t == null && y != null)
	        return false;
	    else
	    {
	        // Recursive check to keep comparing the trees till we finish and see if they share the same structure
	        if (compareStructure(t.left, y.left) == true && compareStructure(t.right, y.right) == true)
	            return true;
	        else
	            return false;
	    }
	}
	
	// reads in a second tree to compare if they are equal to calling tree
	public boolean equals(BinarySearchTree<AnyType> y )
    {
		// need a root of second tree to traverse from
		BinaryNode<AnyType> x = y.root;
		// check if either tree is empty
		if( isEmpty() ) {
            System.out.println( "Tree 1 is an empty tree" );
            return false;
        }
		if( y.isEmpty() ) {
            System.out.println( "Tree 2 is an empty tree" );
            return false;
        }
       return equals(root, x);
    }
	
	// read in the roots of the two tress to traverse from
	// This method is the same as the compareStructure except it also checks element values 
	private boolean equals(BinaryNode<AnyType> t, BinaryNode<AnyType> y) {
		
		// Check if empty
	    if (t == null && y == null)
	        return true;
	         
	    // If one empty and other isn't
	    else if (t != null && y == null)
	        return false;
	    
	    else if (t == null && y != null)
	        return false;
	    
	    else
	    {
	        // Recursive compare the structure AND elements, if they are the same then true
	    	// otherwise if structure OR values are different then they are not equal
	        if (t.element == y.element && equals(t.left, y.left) == true && equals(t.right, y.right) == true)
	            return true;
	        else
	            return false;
	    }
	}
	
	// reads in a tree to copy over the values from calling tree to
	public void copy(BinarySearchTree<AnyType> y )
    {
		// check if empty
		if( isEmpty() ) {
            System.out.println( "Empty tree" );
            return;
        }
		// set the root of the new cloned BST
		y.root = copy(root, y.root);
    }
	
	// read in two roots to start traversal for copying
	private BinaryNode<AnyType> copy(BinaryNode<AnyType> root, BinaryNode<AnyType> y)
	  {
	      // if empty then return
	      if (root == null) {
	          return null;
	      }
	 
	      // save the value
	      y = root;
	 
	      // copy the left and right recursively
	      y.left = copy(root.left, y);
	      y.right = copy(root.right, y);
	 
	      // return the copied root
	      return y;
	  }
	
	// reads in a tree to be the mirror of the calling tree
	public void mirror(BinarySearchTree<AnyType> y )
    {
		if( isEmpty() ) {
            System.out.println( "Empty tree" );
            return;
        }
       y.root = mirror(root);
    }
	
	// this method is very similar to the copy method but copies the mirrored version
	private BinaryNode<AnyType> mirror(BinaryNode<AnyType> root)
	  {
	    
		// Create a new temp node
		BinaryNode<AnyType> y = new BinaryNode<AnyType>(null);
		// if empty
	     if (root == null) {
	         return null;
	     }
	
	      // copy the element over
	      y.element = root.element;
	 
	      // copy the left and right into temp nodes
	      BinaryNode<AnyType> left = mirror(root.left);
	      BinaryNode<AnyType> right = mirror(root.right);
	      
	      // mirror the left and right
	      y.right = left;
	      y.left = right;
	 
	      // return
	      return y;
	  }
	
	// read in a BST to compare to calling BST
	public boolean isMirror(BinarySearchTree<AnyType> y )
    {
		// create a temp BST to un mirror the BST for comparison
		BinarySearchTree<AnyType> x = new BinarySearchTree<>();
		if( isEmpty() ) {
            System.out.println( "Empty tree" );
            return false;
        }
		
		// If the two trees are equal then they aren't mirrored
		if((equals(root, y.root)) == true) {
			return false;
		}
		
		// mirror the tree to the temp tree to use for comparison
		x.root = mirror(y.root);
		
        // If the mirrored of the second BSt is equal to the calling BST then they are mirrors
		if((equals(root, x.root)) == true) {
			return true;
		}
		else
			return false;
       
       
    }
	
	// read in a value to rotate at
	public void rotateRight(AnyType n)
    {
		
		if( isEmpty() ) {
            System.out.println( "Empty tree" );
            return;
        }
		// change the root to the new root after rotating
		root=rotateRight(root,n,false);
    }    
	
	// read in a node, a value, and for recursion purposes a boolean
    private BinaryNode<AnyType> rotateRight(BinaryNode<AnyType> t, AnyType n, boolean foundNode)
    {
    	// If we found the node then move on to rotating, otherwise keep searching for it
    	if (foundNode == false)
    	{
    		// if the node isn't null
    		if (t!=null)
    		{
    			// if the value we are looking for is less than the element of the node then go left
    			if (n.compareTo(t.element) < 0)
    				t.left=rotateRight(t.left,n,false);
    			
    			// if it is greater then go right
    			else if (t.element.compareTo(n) < 0)
    				t.right=rotateRight(t.right,n,false);
    			
    			// otherwise we found the value we are looking for and can move on to the next step
    			else
    				t=rotateRight(t,n,true);   
               
    			return t;  
    		}   
    		// If the value isn't in the tree
    		else
    			return null;                     
    	}
    	// This section is for actually rotating the tree
    	else
    	{
    		// create some temp nodes
    		BinaryNode<AnyType> newRoot=t.left;
    		// if the the left child of t isn't null
    		if (newRoot != null)
    		{
    			// rotate the tree 
    			BinaryNode<AnyType> temp = newRoot.right;
    			t.left=temp;
    			newRoot.right=t;
    			return newRoot;   
    		}
    		// if it is null then we don't have to do anything else just change root
    		else
    			return t;            
    	}         
    }
    
    // the same as rotate right just for left, read in a value to search for
    public void rotateLeft(AnyType n)
    {
    	if( isEmpty() ) {
            System.out.println( "Empty tree" );
            return;
        }
    	root=rotateLeft(root,n,false);
    }   
    
    // this method is identical to rotate right just going left instead
    private BinaryNode<AnyType> rotateLeft(BinaryNode<AnyType> t, AnyType n, boolean foundNode)
    {
    	if (foundNode == false)
    	{
    		if (t!=null)
    		{
    			if (n.compareTo(t.element) < 0)
    				t.left=rotateLeft(t.left,n,false);
    			
    			else if (t.element.compareTo(n) < 0)
    				t.right=rotateLeft(t.right,n,false);
    			
    			else
    				t=rotateLeft(t,n,true);         
    			
    			return t;  
    		}   
    		else
    			return null;                     
    	}
    	// the difference is with rotating, we are going left instead of right
    	else
      	{
    	  BinaryNode<AnyType> newRoot = t.right;
    	  if (newRoot != null)
    	  {
    		  BinaryNode<AnyType> temp = newRoot.left;
    		  t.right=temp;
    		  newRoot.left=t;   
    		  return newRoot;
    	  }   
    	  else
    		  return t;             
      	}         
    }
    
    // for printing levels this method prints the level of a certain height
    void printLevels(BinaryNode<AnyType> root, int level)
    {
    	// if empty tree then nothing to do
    	if (root == null)
            return;
        // if there is 1 level then print the root
    	if (level == 1) {
            System.out.print(root.element + " ");
        }
        // otherwise we need to print the left and right children
    	else if (level > 1) {
    		printLevels(root.left, level - 1);
    		printLevels(root.right, level - 1);
        }
    }
 
    // printing in level order
    void printLevels()
    {
        // height of the root as a starting point and then going down
    	int h = height(root);
        // recursively print the levels going down
    	for (int i = 1; i <= h+1; i++) {
    		printLevels(root, i);
            System.out.println(' ');
        }
    }

    
    // THE REST OF THIS IS THE GIVEN CODE BY AUTHOR

	/**
     * Construct the tree.
     */
    public BinarySearchTree( )
    {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     * @param x the item to insert.
     */
    public void insert( AnyType x )
    {
        root = insert( x, root );
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     * @param x the item to remove.
     */
    public void remove( AnyType x )
    {
        root = remove( x, root );
    }

    /**
     * Find the smallest item in the tree.
     * @return smallest item or null if empty.
     */
    public AnyType findMin( )
    {
        if( isEmpty() )
			throw new NoSuchElementException();
        return findMin( root ).element;
    }

    /**
     * Find the largest item in the tree.
     * @return the largest item of null if empty.
     */
    public AnyType findMax( )
    {
        if( isEmpty() )
			throw new NoSuchElementException();
        return findMax( root ).element;
    }

    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return true if not found.
     */
    public boolean contains( AnyType x )
    {
        return contains( x, root );
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty( )
    {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty()
    {
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree( )
    {
        if( isEmpty() )
            System.out.println( "Empty tree" );
        else
            printTree( root );
    }

    /**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return new BinaryNode<>( x, null, null );
        
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = insert( x, t.left );
        else if( compareResult > 0 )
            t.right = insert( x, t.right );
        else
            ;  // Duplicate; do nothing
        return t;
    }

    /**
     * Internal method to remove from a subtree.
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> remove( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return t;   // Item not found; do nothing
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = remove( x, t.left );
        else if( compareResult > 0 )
            t.right = remove( x, t.right );
        else if( t.left != null && t.right != null ) // Two children
        {
            t.element = findMin( t.right ).element;
            t.right = remove( t.element, t.right );
        }
        else
            t = ( t.left != null ) ? t.left : t.right;
        return t;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the smallest item.
     */
    private BinaryNode<AnyType> findMin( BinaryNode<AnyType> t )
    {
        if( t == null )
            return null;
        else if( t.left == null )
            return t;
        return findMin( t.left );
    }

    /**
     * Internal method to find the largest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the largest item.
     */
    private BinaryNode<AnyType> findMax( BinaryNode<AnyType> t )
    {
        if( t != null )
            while( t.right != null )
                t = t.right;

        return t;
    }

    /**
     * Internal method to find an item in a subtree.
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     * @return node containing the matched item.
     */
    private boolean contains( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return false;
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            return contains( x, t.left );
        else if( compareResult > 0 )
            return contains( x, t.right );
        else
            return true;    // Match
    }

    /**
     * Internal method to print a subtree in sorted order.
     * @param t the node that roots the subtree.
     */
    private void printTree( BinaryNode<AnyType> t )
    {
        if( t != null )
        {
        	System.out.println( t.element );
        	printTree( t.left );
        	printTree( t.right );
        }
    }

    /**
     * Internal method to compute height of a subtree.
     * @param t the node that roots the subtree.
     */
    private int height(BinaryNode<AnyType> t )
    {
        if( t == null )
            return -1;
        else
            return 1 + Math.max( height( t.left ), height( t.right ) );    
    }
    
    // Basic node stored in unbalanced binary search trees
    private static class BinaryNode<AnyType>
    {
            // Constructors
    	BinaryNode( AnyType theElement )
        {
            this(theElement, null, null );
        }

        BinaryNode( AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt )
        {
            element  = theElement;
            left     = lt;
            right    = rt;
        }

        AnyType element;            // The data in the node
        BinaryNode<AnyType> left;   // Left child
        BinaryNode<AnyType> right;  // Right child
    }
    
    
    


      /** The tree root. */
    public BinaryNode<AnyType> root;


    // Test program
    public static void main( String [ ] args )
    {
       // create the trees that are going to be used for testing
    	BinarySearchTree<Integer> t = new BinarySearchTree<>( );
        BinarySearchTree<Integer> t2 = new BinarySearchTree<>( );
        BinarySearchTree<Integer> t3 = new BinarySearchTree<>( );
        BinarySearchTree<Integer> t4 = new BinarySearchTree<>( );

        
        
        t.insert(100);
        t.insert(50);
        t.insert(150);
        t.insert(40);
        t.insert(45);
        
        t2.insert(20);
        t2.insert(10);
        t2.insert(25);
        t2.insert(8);
        t2.insert(9);
        
        System.out.println("Tree 1 has " + t.nodeCount() + " nodes"); 
        System.out.println("Is Tree 1 a full tree? " + t.isFull());
        System.out.println("Printing Tree 1 with pre-order traversial");
        t.printTree();
        System.out.println();
        
        System.out.println("Tree 2 has " + t2.nodeCount() + " nodes"); 
        System.out.println("Is Tree 1 a full tree? " + t2.isFull());
        System.out.println("Printing Tree 2 with pre-order traversial");
        t2.printTree();
        System.out.println();
        
        System.out.println("Is Tree 2's structure the same as Tree 1? " + t.compareStructure(t2));
        System.out.println("Is Tree 2 identical to Tree 1? "  + t.equals(t2) + '\n');
        
        System.out.println("*** Copying tree 1 to tree 3 ***\n");
        
        t.copy(t3);
        
        System.out.println("Tree 3 has " + t3.nodeCount() + " nodes"); 
        System.out.println("Printing Tree 3 with pre-order traversial");
        t3.printTree();
        System.out.println();
        
        System.out.println("Is Tree 3 identical to Tree 1? " + t3.equals(t) );
        System.out.println();
        
        
        System.out.println("*** Mirroring tree 1 to tree 4 ***\n");
        
        t.mirror(t4);
        
        System.out.println("Tree 4 has " + t4.nodeCount() + " nodes");
        System.out.println("Printing Tree 4 with pre-order traversial");
        t4.printTree();
        System.out.println();
        
        System.out.println("Is Tree 4 identical to Tree 1? " + t.equals(t4));
        System.out.println("Is tree 4 a mirror of Tree 1? " + t.isMirror(t4)); 
        System.out.println("Is tree 3 a mirror of Tree 1? " + t.isMirror(t3) + '\n'); 
        
        System.out.println("Printing Tree 3 with pre-order traversial");
        t3.printTree();
        System.out.println();
        
        
        System.out.println("Rotating tree 3 right at 100\n");
        
        t3.rotateRight(100);
        
        System.out.println("Printing Tree 3 with pre-order traversial");
        t3.printTree();
        System.out.println();
        
        
        System.out.println("Rotating tree 3 left at 50\n");
        
        t3.rotateLeft(50);
        
        System.out.println("Printing Tree 3 with pre-order traversial");
        t3.printTree();
        System.out.println();
        
        
        System.out.println("Level order traversal of tree 3 ");
        t3.printLevels();
        

        


        
    }
}

