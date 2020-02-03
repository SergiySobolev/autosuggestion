Type ahead service based on Trie data structure.

1. Use Cases. 

    Service can be started in two modes: standalone and distributed.

    **1.1. Standalone**
    
    To start standalone service do:
        
            git clone https://github.com/SergiySobolev/autosuggestion.git
            cd autosuggestion
            gradlew build run
    
    1.2  Distributed

**2. Implementation details.**

    Current Trie supports next methods:
    

| Method                                             | Time Complexity |
| ---------------------------------------------------|:---------------:|
| insert(key:String)                                 | O(n)            |
| contains(key:String):Boolean                       | O(n)            |
| delete(key:String)                                 | O(n)            |
| getAllWords():List<String>                         |                 |
| getAllWordsByPrefix(prefix:String):List<String>    |                 |

 
   