import java.util.TimeZone

/**
 * This class solves ex 3.9 in "Scala for the Impatient" by Hortsmann (2012)
 * @author Ron Coleman, Ph.D. 
 */
object Ex39 {

  def main(args: Array[String]): Unit = {   
    // Get the time zones
    val zones = TimeZone.getAvailableIDs().toList
      
    // Filter non-American zones
    val america = zones.foldLeft(List[String]()) { (filterZones,zone) =>
      val regions = zone.split("/")

      val index = regions.size - 1
      
      if(regions(0) == "America") regions(index) :: filterZones else filterZones
    }
    
    // Sort and output cities
    val sortedAmerica = america.sortWith(_ < _)
    
    sortedAmerica.foreach(println(_))
  }

}