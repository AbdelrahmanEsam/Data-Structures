class SegmentTree(index: Int =1,inputArray: IntArray, manipulationFunction : (Int,Int) -> Int) {

  private var segmentArray : IntArray? = null


    init {
        val height = ceil(ln(inputArray.size.toDouble()) / ln(2.0)).toInt()
        val maxSize = (2 * 2.0.pow(height.toDouble()) -1).toInt()
        segmentArray = IntArray(maxSize){0}

        inputArray.build(index,0, inputArray.lastIndex,manipulationFunction)
    }

    fun IntArray.build(index: Int=1, start: Int, end: Int, manipulationFunction : (Int,Int) -> Int)
    {

        if (start == end)
        {
            segmentArray!![index] = this[start]
            return
        }

        build(index*2,start,(start+end)/2,manipulationFunction)
        build(index*2+1,(start+end)/2+1,end,manipulationFunction)

        // manipulate it as you want, or you can pass a lambda expression to manipulate it as I did

        segmentArray!![index] = manipulationFunction(segmentArray!![index*2], segmentArray!![index*2+1])

    }


    fun update(segmentIndex: Int=1,start: Int, end: Int,updateIndex : Int,newValue : Int,manipulationFunction : (Int,Int) -> Int)
    {

        if (start == end) // equal update index too
        {
            segmentArray!![segmentIndex] = newValue
            return
        }

        if (updateIndex <= (start+end)/2) update(segmentIndex*2,start,(start+end)/2,updateIndex,newValue,manipulationFunction)
        else update(segmentIndex*2+1,(start+end)/2+1,end,updateIndex,newValue,manipulationFunction)

        segmentArray!![segmentIndex] = manipulationFunction(segmentArray!![segmentIndex*2], segmentArray!![segmentIndex*2+1])
    }


    fun get(segmentIndex: Int=1,start: Int,end: Int,rangeStart:Int,rangeEnd:Int,defaultReturn:Int,manipulationFunction: (Int, Int) -> Int) : Int
    {
        if (start >= rangeStart && end <=rangeEnd)
        { return segmentArray!![segmentIndex]}

       if (start > rangeEnd || end < rangeStart) return defaultReturn

        return manipulationFunction(get(segmentIndex*2,start,(start+end)/2,rangeStart,rangeEnd,defaultReturn = defaultReturn,manipulationFunction),
            get(segmentIndex*2+1,(start+end)/2+1,end,rangeStart,rangeEnd,defaultReturn = defaultReturn,manipulationFunction))

    }
}
