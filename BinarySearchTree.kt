class BinarySearchTree
{
    var root : Node? = null

    fun insert (value : Int ) {root = insertValue(root,value)}

    private fun insertValue(parent: Node?, value: Int): Node {
        parent ?: return Node(value)

        if (value < parent.value) { parent.left = insertValue(parent.left,value)}
        else {parent.right = insertValue(parent.right,value)}

        return parent
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


    data class Node(var value:Int, var left : Node? = null, var right: Node? = null)
}
