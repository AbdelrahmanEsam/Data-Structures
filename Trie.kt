fun main() {
    val input = readLine()!!.split(" ")
    val root = Trie()
   input.forEach {
       root.insert(it)
   }
    val search = readLine()!!.split(" ")
    search.forEach {
        println(root.search(it))
    }

}


class Trie {
    val children : Array<Trie?> = Array(26){null}
    var isEndOfWord = false
}

private fun Trie.insert(word : String)

{
    var index :Int
    var currentNode = this
    for (char in word){
              index = char - 'a'
        if (currentNode.children[index] == null)  currentNode.children[index] = Trie()
        currentNode = currentNode.children[index]!!
    }
    currentNode.isEndOfWord = true

}

private fun Trie.search(word: String) : Boolean
{
    var index :Int
    var currentNode = this
    for (char in word){
        index = char - 'a'
        if (currentNode.children[index] == null)  return false
        currentNode = currentNode.children[index]!!
    }
    return currentNode.isEndOfWord
}
