
public class BinaryTree 
{
	private Node root;
	
	public BinaryTree()
	{
		this.root = null;
	}
	
	public void displayInOrder()
	{
		System.out.println("**** In Order ****");
		if(this.root == null)
		{
			System.out.println("Empty Tree");
		}
	}
	
	public void displayPostOrder()
	{
		System.out.println("**** Post Order ****");
		if(this.root == null)
		{
			System.out.println("Empty Tree");
		}
	}
	
	public void add(int value)
	{
		Node theNode = new Node(value);
		if(this.root == null)
		{
			this.root = theNode;
		}
		else
		{
			this.root.addNode(theNode);
		}
	}
}
