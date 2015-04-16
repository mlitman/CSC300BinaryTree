
public class BinaryTree 
{
	private BinaryTree root;
	private boolean isEmpty;
	private int payload;
	private BinaryTree leftTree;
	private BinaryTree rightTree;
	private BinaryTree parent;
	private int depth;
	
	public BinaryTree()
	{
		this(0, null);
		this.root = this;
	}
	
	private BinaryTree(int depth, BinaryTree root)
	{
		this.isEmpty = true;
		this.leftTree = null;
		this.rightTree = null;
		this.depth = depth;
		this.parent = null;
		this.root = root;
	}
	
	private void updateDepths()
	{
		this.depth--;
		if(this.leftTree != null)
		{
			this.leftTree.depth = this.depth+1;
		}
		if(this.rightTree != null)
		{
			this.rightTree.depth = this.depth+1;
		}
	}
	
	private void rotateRight(BinaryTree pivot)
	{
		System.out.println("Rotate Right");
		BinaryTree pivRT = null;
		BinaryTree pivP = null;
		BinaryTree pivGP = null;
		if(pivot.rightTree != null)
		{
			pivRT = pivot.rightTree;
			pivot.rightTree = null;
		}
		pivP = pivot.parent;
		pivGP = (pivP == null?null:pivP.parent);
		//conditionally remove pivP from his parent if he had a parent
		if(pivGP != null)
		{
			if(pivGP.leftTree == pivP)
			{
				pivGP.leftTree = pivot;
			}
			else
			{
				pivGP.rightTree = pivot;
			}
			pivot.parent = pivGP;
			pivP.rightTree = null;
		}
		else
		{
			//pivot is the new root tree of the entire tree
			pivot.parent = null;
			pivP.rightTree = null;
		}
		
		//always remove pivot from his parent
		if(pivP == null)
		{
			System.err.println("I have no parent...should I be calling rotate right?");
			return;
		}
		else
		{
			//should always get to this else
			//always replace pivP's left tree with whatever pivRT points to
			pivP.leftTree = pivRT;
		}
		
		//finally connect pivP as the right child of pivot and notify pivP who his
		//new parent is
		pivot.rightTree = pivP;
		pivP.parent = pivot;
		
		//update all of the depths under pivot
		pivot.updateDepths();
	}
	
	private void rotateLeft(BinaryTree pivot)
	{
		System.out.println("Rotate Left");
		BinaryTree pivLT = null;
		BinaryTree pivP = null;
		BinaryTree pivGP = null;
		if(pivot.leftTree != null)
		{
			pivLT = pivot.leftTree;
			pivot.leftTree = null;
		}
		pivP = pivot.parent;
		pivGP = (pivP == null?null:pivP.parent);
		//conditionally remove pivP from his parent if he had a parent
		if(pivGP != null)
		{
			if(pivGP.leftTree == pivP)
			{
				pivGP.leftTree = pivot;
			}
			else
			{
				pivGP.rightTree = pivot;
			}
			pivot.parent = pivGP;
			pivP.leftTree = null;
		}
		else
		{
			//pivot is the new root tree of the entire tree
			pivot.parent = null;
			pivP.leftTree = null;
		}
		
		//always remove pivot from his parent
		if(pivP == null)
		{
			System.err.println("I have no parent...should I be calling rotate left?");
			return;
		}
		else
		{
			//should always get to this else
			//always replace pivP's right tree with whatever pivLT points to
			pivP.rightTree = pivLT;
		}
		
		//finally connect pivP as the left child of pivot and notify pivP who his
		//new parent is
		pivot.leftTree = pivP;
		pivP.parent = pivot;
		
		//update all of the depths under pivot
		pivot.updateDepths();
	}
	
	public boolean search(int value)
	{
		//return true if value is in the tree
		//return false if value is not in the tree
		if(this.isEmpty)
		{
			return false;
		}
		else
		{
			if(this.payload == value)
			{
				return true;
			}
			else
			{
				if(value < payload)
				{
					//check the left
					if(this.leftTree == null)
					{
						return false;
					}
					else
					{
						return this.leftTree.search(value);
					}
				}
				else
				{
					//check the right
					if(this.rightTree == null)
					{
						return false;
					}
					else
					{
						return this.rightTree.search(value);
					}
				}
			}
		}
	}
	
	private void visitInOrder()
	{
		if(this.leftTree != null)
		{
			this.leftTree.visitInOrder();
		}
		System.out.println(this.payload + " : " + this.depth);
		if(this.rightTree != null)
		{
			this.rightTree.visitInOrder();
		}
	}

	public void displayInOrder()
	{
		System.out.println("**** In Order ****");
		if(this.isEmpty)
		{
			System.out.println("Empty Tree");
		}
		else
		{
			this.visitInOrder();
		}
	}
	
	private void visitPreOrder()
	{
		System.out.println(this.payload);
		if(this.leftTree != null)
		{
			this.leftTree.visitPreOrder();
		}
		if(this.rightTree != null)
		{
			this.rightTree.visitPreOrder();
		}
	}
	
	public void displayPreOrder()
	{
		System.out.println("**** Pre Order ****");
		if(this.isEmpty)
		{
			System.out.println("Empty Tree");
		}
		else
		{
			this.visitPreOrder();
		}
	}
	
	private void visitPostOrder()
	{
		if(this.leftTree != null)
		{
			this.leftTree.visitPostOrder();
		}
		if(this.rightTree != null)
		{
			this.rightTree.visitPostOrder();
		}
		System.out.println(this.payload);
	}
	
	public void displayPostOrder()
	{
		System.out.println("**** Post Order ****");
		if(this.isEmpty)
		{
			System.out.println("Empty Tree");
		}
		else
		{
			this.visitPostOrder();
		}
	}
	
	private int getMaxDepth()
	{
		if(this.leftTree == null && this.rightTree == null)
		{
			return this.depth;
		}
		else if(this.leftTree == null)
		{
			return this.rightTree.getMaxDepth();
		}
		else if(this.rightTree == null)
		{
			return this.leftTree.getMaxDepth();
		}
		else
		{
			return Math.max(this.leftTree.getMaxDepth(), this.rightTree.getMaxDepth());
		}
	}
	
	public boolean isBalanced()
	{
		if(this.isEmpty)
		{
			return true;
		}
		else
		{
			//boolean-expr?true-val:false-val
			int currMaxLeftDepth = this.leftTree == null?0:this.leftTree.getMaxDepth();
			int currMaxRightDepth = this.rightTree == null?0:this.rightTree.getMaxDepth();
			System.out.println("Max Left = " + currMaxLeftDepth);
			System.out.println("Max Right = " + currMaxRightDepth);
			return Math.abs(currMaxLeftDepth - currMaxRightDepth) <= 1;
		}
	}
	
	public void add(int value)
	{
		if(this.isEmpty)
		{
			this.payload = value;
			this.isEmpty = false;
			//ask is this entire tree out of balance?
			if(!this.root.isBalanced())
			{
				if(this.root.leftTree == null)
				{
					//we are on the right side of the parents tree
					if(this.parent.leftTree == this)
					{
						this.rotateRight(this);
						this.rotateLeft(this);
					}
					else
					{
						this.parent.rotateLeft(this.parent);
					}
				}
				else if(this.root.rightTree == null)
				{
					//the left tree is out of balance
					if(this.parent.rightTree == this)
					{
						this.rotateLeft(this);
						this.rotateRight(this);
					}
					else
					{
						this.parent.rotateRight(this.parent);
					}
				}
				else
				{
					//we know we have a left and a right tree
					if(this.root.leftTree.getMaxDepth() > this.root.rightTree.getMaxDepth())
					{
						//we are on the left side of the tree
						if(this.parent.rightTree == this)
						{
							this.rotateLeft(this);
							this.rotateRight(this);
						}
						else
						{
							this.parent.rotateRight(this.parent);
						}

					}
					else
					{
						//we are on the right side of the tree
						if(this.parent.leftTree == this)
						{
							this.rotateRight(this);
							this.rotateLeft(this);
						}
						else
						{
							this.parent.rotateLeft(this.parent);
						}
					}
				}
			}
		}
		else
		{
			if(value <= this.payload)
			{
				if(this.leftTree == null)
				{
					this.leftTree = new BinaryTree(this.depth+1, this.root);
					this.leftTree.parent = this;
				}
				this.leftTree.add(value);
			}
			else
			{
				if(this.rightTree == null)
				{
					this.rightTree = new BinaryTree(this.depth+1, this.root);
					this.rightTree.parent = this;
				}
				this.rightTree.add(value);
			}
		}
	}
}
