import org.apache.spark.rdd.RDD;

:load /home/leonardo/MioArchivio/MieiInvestimenti-Repository/MieiInvestimenti/EstraiAzioni.scala

val SAG : RDD[(String, Iterable[(Int, Double, 
                                Double, Double, 
                                Double, Int, 
                                String, String)])] = SimboliAscendenti.groupByKey;

val ElencoGruppi : RDD[String] = SAG.map(item => item._1.distinct);

val NumeroGruppi : Long = ElencoGruppi.count; 

val SAGList : RDD[(String, List[(Int, Double, 
                                 Double, Double, 
                                 Double, Int, 
                                 String, String)])] = SAG.map(item => (item._1,item._2.toList));

val listaStringheTitoli : RDD[String] = SAGList.map(item => "Lista_".concat(item._1));

/*
 *
 * Devo realizzare un metodo per il calcolo dell'indice di forza
 * relativa: Relative Strength Index (RSI).
 *
 * Questo viene calcolato con questa formula:
 *
 * RSI = 100 - (100) / (1+RS)
 *
 * Dove, a sua volta, RS, viene calcolato in questo modo:
 * Per ogni singolo giorno di contrattazione, di cui dispongo 
 * dei dati, calcolo la differenza tra apertura e chiusura 
 * (apertura - chiusura).
 * A questo punto, farò la media di tutte queste differenze 
 * calcolate, e farò questa media sul "numero di periodi", che
 * sono i giorni di contrattazione, ovvero i periodi sui quali 
 * calcolare le medie.
 *
 * Tendenzialmente, si usa un periodo di 14 giorni.
 *
*/

/*

I metodi qui sotto li lascio come template, se dovessi creare funzioni e variabili aggregati

val VettoreAzioni : Vector[(Int, Double,
                           Double, Double,
                           Double, Int,
                           String, String)] = GeneraVettore(SAG1AFM : Array[(Int, Double,
                                                                             Double, Double,
                                                                             Double, Int,
                                                                             String, String)]);

def GeneraVettore (FlattedArray : Array[(Int, Double,
                                         Double, Double,
                                         Double, Int,
                                         String, String)]) : Vector[(Int, Double,
                                                                     Double, Double,
                                                                     Double, Int,
                                                                     String, String)] = {

  val elementi = FlattedArray.toVector;

  return elementi;

}

*/
