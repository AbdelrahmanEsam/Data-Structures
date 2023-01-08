interface Graph<T>
{
    fun createVertex(data : T) : Vertex<T>

    fun addDirectedEdge(source : Vertex<T>,destination: Vertex<T>,weight: Double?)


    fun addUndirectedEdge(source : Vertex<T>,destination: Vertex<T>,weight: Double?)

    fun add(edgeType: EdgeType,source : Vertex<T>,destination: Vertex<T>,weight: Double?)
    {
        when(edgeType)
        {
            EdgeType.DIRECTED -> {addDirectedEdge(source,destination,weight)}
            EdgeType.UNDIRECTED -> {addUndirectedEdge(source, destination, weight)}
        }
    }

    fun edges(source:Vertex<T>) : ArrayList<Edge<T>>

    fun weight(source: Vertex<T>, destination: Vertex<T>): Double?
    
    data class Vertex<T>(val index : Int ,val data :T)
    data class Edge<T>(val source : Vertex<T>, val destination: Vertex<T>,val weight : Double? = null )
    enum class EdgeType { DIRECTED, UNDIRECTED }
    
}







class AdjacencyList<T> : Graph<T>
{

    private val neighbours : HashMap<Graph.Vertex<T>, ArrayList<Graph.Edge<T>>> = HashMap()


    override fun createVertex(data: T): Graph.Vertex<T> {
    val vertex =  Graph.Vertex(neighbours.count(), data)
        neighbours[vertex] = ArrayList()
        return vertex
    }

    override fun addDirectedEdge(source: Graph.Vertex<T>, destination: Graph.Vertex<T>, weight: Double?) {
        neighbours[source]?.add(Graph.Edge(source,destination,weight))
    }

    override fun addUndirectedEdge(source: Graph.Vertex<T>, destination: Graph.Vertex<T>, weight: Double?) {
       addDirectedEdge(source,destination,weight)
        addDirectedEdge(destination,source,weight)
    }

    override fun add(edgeType: Graph.EdgeType,source: Graph.Vertex<T>, destination: Graph.Vertex<T>, weight: Double?) {
       when(edgeType)
       {
           Graph.EdgeType.DIRECTED -> {addDirectedEdge(source,destination,weight)}
           Graph.EdgeType.UNDIRECTED -> {addUndirectedEdge(source, destination, weight)}
       }
    }

    override fun edges(source: Graph.Vertex<T>): ArrayList<Graph.Edge<T>> = neighbours[source] ?: arrayListOf()

    override fun weight(source: Graph.Vertex<T>, destination: Graph.Vertex<T>): Double? {
     return neighbours[source]?.firstOrNull { it.destination == destination }?.weight
    }


    override fun toString(): String {
        return buildString { // 1
            neighbours.forEach { (vertex, edges) ->
                val edgeString = edges.joinToString {
                    it.destination.data.toString() } // 3
                append("${vertex.data} ---> [ $edgeString ]\n") // 4
            }
        }
    }
}
