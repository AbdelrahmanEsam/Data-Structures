private fun constructTheAdjacencyMatrixGraph(edges : Int,vertices:Int) : Array<IntArray>
{
    val adjMat = Array(vertices+1) { IntArray(vertices+1) }
    repeat(edges)
    {
        val vertexOne = readInt()
        val vertexTwo = readInt()

        adjMat[vertexOne][vertexTwo] = 1
//      adjMat[vertexTwo][vertexOne] = 1  if this graph is undirected
    }
    return adjMat
}

private  fun constructTheAdjacencyListGraph(v:Int) : ArrayList<ArrayList<Int>>
{
    val adj = ArrayList<ArrayList<Int>>()
    repeat(v)
    {
        adj.add(arrayListOf())
    }
    return adj
}

private fun ArrayList<ArrayList<Int>>.addEdge(firstEdge : Int, secondEdge :Int)
{
this[firstEdge].add(secondEdge)
//  this[secondEdge].add(firstEdge) if this graph is undirected
}
