class BinaryTree
{
    var root : BinaryNode? = null

     val queue = LinkedList<BinaryNode>()

    fun insertValue(value: Any)
    {
        val node = BinaryNode(value)
        if (root == null)
        {
            root = node
        }else if (queue.peek().left == null)
        {
            queue.peek().left = node
        }else
        {
            queue.peek().right = node
            queue.remove()
        }

        queue.add(node)
    }

    fun createTree(values : IntArray)
    {
        values.forEach { value ->
            insertValue(value)
        }
    }

    data class BinaryNode(val value:Any, var left : Node? = null, var right: Node? = null)
}
