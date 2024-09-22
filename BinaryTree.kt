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

// arrayList implementation

open class BinaryTree<T : Comparable<T>> {

    protected val nodes = ArrayList<T>()


    open fun insertValue(value: T) {
        nodes.add(value)
    }

    fun getLiftChildIndex(parentIndex: Int): Int {
        return (parentIndex * 2) + 1
    }

    fun getRightChildIndex(parentIndex: Int): Int {
        return (parentIndex * 2) + 2
    }

    fun getParentIndex(child: Int): Int {
        return (child - 1) / 2
    }

    fun getItemByIndex(index: Int): T = nodes[index]

    override fun toString(): String {
        val stringBuilder = StringBuilder()
        nodes.forEach {
            stringBuilder.append(it)
        }
        return stringBuilder.toString()
    }


}
