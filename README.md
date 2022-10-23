# GenericBinarySearchTree
This is a Binary Search Tree using Java Generics. This is part two of my database project

This Binary Search Tree has methods such as:

- nodeCount
    - Recursively traverses the tree and returns the count of nodes

-  isFull 
    - Returns true if the tree is full.  A full tree has every node 
      as either a leaf or a parent with two children
      
- compareStructure 
    - Compares the structure of current tree to another tree and returns
      true if they match
    - For example, these two trees have the same structure:
             
             5           10
            / \         /  \
           3   8       5   15
          /     \      /     \
          1      4     2      7
               
               
- equals
    - Compares the current tree to another tree and returns true
      if they are identical
      
- copy
     - Creates and returns a new tree that is a copy of the original tree

- mirror
    - Creates and returns a new tree that is a mirror image of the original tree
      For example, for the tree on the left, the tree on the right is returned
           
            100                 100
           /   \               /   \
          50   150    -->     150  50
          /                           \
          40                           40
           \                           /
           45                         45
         
         
- isMirror 
    - Returns true if the tree is a mirror of the passed tree

- rotateRight
    - Performs a single rotation on the node having the passed value.
      If a RotateRight on 100 is performed:
        
            100                  50
           /   \                /   \
          50   150    -->      40   100
          /                      \     \
          40                      45    150
          \ 
          45

- rotateLeft 
    - As above but left rotation

- printLevels 
    - performs a level-by-level printing of the tree


