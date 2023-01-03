class Tree
{
    var root : Node? = null

     val queue = LinkedList<Node>()

    fun insertValue(value: Any)
    {
        val node = Node(value)
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

    data class Node(val value:Any, var left : Node? = null, var right: Node? = null)
}
