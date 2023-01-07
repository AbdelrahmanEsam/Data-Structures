class Trie
{

    val root = TrieNode(null , null)

    class  TrieNode (val data : Char? , val parent : TrieNode?)
    {
        val children : HashMap<Char,TrieNode?> = HashMap()
        var isTerminating = false

    }
}

fun Trie.insert(word : String)
{
    var current = root

    word.forEach {char ->
        if ( current.children[char] == null)
        {
            current.children[char] = Trie.TrieNode(char, current)
        }
        current = current.children[char]!!
    }
    current.isTerminating = true

}
fun Trie.insert(listOfWord : List<String>)
{
    listOfWord.forEach {
        insert(it)
    }
}

fun Trie.contains(word: String): Boolean {
    var current = root
    word.forEach {char ->
        val child = current.children[char] ?: return false
        current = child
    }

    return current.isTerminating
}

fun Trie.remove(word: String)
{
    var current = root

    word.forEach { char ->
        val child = current.children[char] ?: return
        current = child
    }

    if (!current.isTerminating) return

    current.isTerminating = false

    val parent = current.parent

    while (current.children.isEmpty() && !current.isTerminating)
    {
        parent?.let {
         it.children[current.data!!] = null
            current = it
        }
    }
}

fun Trie.getAllWordsWithPrefix(prefix : String) : List<String>
{

    var current = root

    prefix.forEach { char ->
        val child = current.children[char] ?: return  emptyList()
        current = child
    }

    return current.getAllWordsWithThisPrefix(prefix)
}

fun Trie.TrieNode?.getAllWordsWithThisPrefix(prefix: String): List<String> {

    val results = mutableListOf<String>()
    if (this?.isTerminating == true)
    {
        results.add(prefix)
    }

    this?.children?.forEach { (char , node) ->

        node?.let { results.addAll(it.getAllWordsWithThisPrefix(prefix+char)) }

    }

return results
}
