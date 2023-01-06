class AVLTree
{
    var root : Node? = null

    fun insert (value : Int ) {root = insertValue(root,value)}

    private fun insertValue(parent: Node?, value: Int): Node {
        parent ?: return Node(value)

        if (value < parent.value) { parent.left = insertValue(parent.left,value)}
        else {parent.right = insertValue(parent.right,value)}                      

        
          //after insertion  check the balance of each nood and if the node is not balanced balance it by left and right rotations
          // this may change the value of the node bacause of the rotations so you need to update
          
        val balancedNode = parent.balanced()
        balancedNode.height = max(balancedNode.leftHeight,balancedNode.rightHeight) +1
        return balancedNode
    }

    fun createTree(input: IntArray) {
        input.forEach {
            insert(it)
        }
    }

    fun contains(node : Node) : Boolean
    {
        var current = root
       while (current != null)
       {
           current = if (node.value == current.value) return  true
           else if (node.value < current.value) current.left
           else current.right
       }
        return false
    }

    fun remove(value:Node) {
        root = removeValue(parent = root, removeValue = value)
    }

    private fun removeValue(parent : Node?,removeValue : Node): Node? {
        parent ?: return null

        if (removeValue.value == parent.value)
        {
           if (parent.left == null && parent.right == null) return null
           if (parent.left == null) return  parent.right
           if (parent.right == null) return parent.left

             parent.right?.getMin()?.value?.let {
             parent.value = it
            }

        }
        else if (removeValue.value < parent.value) parent.left = removeValue(parent.left,removeValue)
        else  parent.right = removeValue(parent.right,removeValue)

        return  parent
    }

    private fun Node.getMin() : Node{ return left?.getMin() ?: this }


private fun Node.leftRotation() : Node
{
  
    val pivot = this.right!!

    this.right = pivot.left

    pivot.left = this

    this.height = max(leftHeight,rightHeight) +1

    pivot.height = max(pivot.leftHeight,pivot.rightHeight)

   return  pivot
}

    private fun Node.rightRotation() : Node
    {
        val pivot = this.left!!

        this.left = pivot.right

        pivot.right  = this

        this.height = max(leftHeight,rightHeight) +1

        pivot.height = max(pivot.leftHeight,pivot.rightHeight)

        return  pivot

    }

    private fun Node.rightLeftRotation() : Node
    {
        val rightNode = this.right ?: this

        this.right = rightNode.rightRotation()
      return  this.leftRotation()
    }

    private  fun Node.leftRightRotation () : Node
    {
        val leftNode = this.left ?: this
        this.left = leftNode.leftRotation()
        return  this.rightRotation()
    }

    private fun Node.balanced(): Node{
        return  when(this.balanceFactor)
        {
            2 ->  {if (this.left?.balanceFactor == -1) this.leftRightRotation() else this.rightRotation()} 


            -2 -> {if (this.right?.balanceFactor == 1) this.rightLeftRotation() else this.leftRotation()}

            else -> {this}
        }
    }



  class Node(var value:Int, var left : Node? = null, var right: Node? = null)
  {
      var height : Int = 0

      val leftHeight: Int get() = left?.height ?: -1

      val rightHeight: Int get() = right?.height ?: -1

      val balanceFactor: Int get() = leftHeight - rightHeight

  }


}
