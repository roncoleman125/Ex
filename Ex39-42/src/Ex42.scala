import scala.io.Source
import scala.collection.mutable.HashMap

/**
 * This class solves ex 4.2 in Scala for the Impatient by Cay S. Hortsmann.
 * Input files are Moby Dick and War and Peace
 * @author Ron Coleman, Ph.D.
 */
object Ex42 {
    val CHOP = ",.;:-^~/|(){}?!$%&*\"\'"
      
	def main(args: Array[String]): Unit = {
//      process("Moby Dick by Herman Melville","2701-moby-dick.txt")
//      process("War and Peace by Leo Tolstoy","2600-war-and-peace.txt")
        if(args.length != 2) {
          println("usage: ex42 title file")
          exit(1)
        }
        
        val title = args(0)
        
        val file = args(1)
        
        process(title,file)
    }
    
    /**
     * Processes input file.
     * @param title File title
     * @param input Input file name
     */
    def process(title: String,input: String) {
      // Initialize the dictionary
	  val dict = HashMap[String, Int]() withDefault (_ => 0)
	  
	  // Get lines from input
	  val lines = Source.fromFile(input).getLines
	  
	  // Process each line
	  lines.foreach { line =>
	    // Tokenize the line
	    line.split("\\s+").foreach { token =>
	      // Clean out each token of extraneous chars
	      val word = clean(token)
	      
	      // Re-split the word in case there were hyphens, etc. in word
	      val subwords = word.split("\\s+")
	      
	      // Count the words
	      subwords.foreach { w =>
	        if(w.length != 0) dict(w) += 1
	      }
	    }
	  }
	  
	  // Sort the dictionary into a list
	  val list = dict.toList.sortWith(_._2 > _._2)

	  // Output the header
	  println(title)
	  
	  println("%s,%s".format("Freq","Word"))
	  
	  // Output the stats
	  list.foreach { record =>
	    val (word,freq) = record
	    
	    println("%d,%s".format(freq,word))
	  }

	  println("")
	} 

    /**
     * Cleans a string of extraneous chars.
     */
    def clean(s: String): String = {
      val t = s.replaceAll("-"," ")
      
      val cleaned = for(c <- t; if(!CHOP.contains(c))) yield c
      
      cleaned.toLowerCase
    }
}