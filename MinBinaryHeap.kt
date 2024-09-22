// this class depends on the BinaryTree with arrayList Implementation


class MinBinaryHeap<T : Comparable<T>> : BinaryTree<T>() {

    override fun insertValue(value: T) {
        super.insertValue(value)
        siftUp(nodes.size - 1)
    }


    private fun siftUp(index: Int) {
        var currentIndex = index
        while (currentIndex > 0 && nodes[currentIndex] < nodes[getParentIndex(currentIndex)]) {
            nodes[currentIndex] =
                nodes[getParentIndex(currentIndex)].also { nodes[getParentIndex(currentIndex)] = nodes[currentIndex] }
            currentIndex = getParentIndex(currentIndex)
        }
    }

    private fun siftDown(index: Int) {
        var currentIndex = index
        while (getLiftChildIndex(currentIndex) < nodes.size) {
            var smallestChildIndex = getLiftChildIndex(currentIndex)

            if (getRightChildIndex(currentIndex) < nodes.size - 1
                && smallestChildIndex < nodes.size - 1 &&
                getItemByIndex(getRightChildIndex(currentIndex)) < getItemByIndex(smallestChildIndex)
            ) {
                smallestChildIndex = getRightChildIndex(currentIndex)
            }
            if (nodes[currentIndex] <= nodes[smallestChildIndex]) break // the current item is smaller than the child

            nodes[currentIndex] = nodes[smallestChildIndex].also { nodes[smallestChildIndex] = nodes[currentIndex] }
            currentIndex = smallestChildIndex
        }
    }

    fun extractRoot(): T {
        nodes[0] = nodes[nodes.size - 1].also { nodes[nodes.size - 1] = nodes[0] }
        val extracted = nodes.removeLast()
        siftDown(0)
        return extracted
    }
}
