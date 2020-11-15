import org.apache.spark.rdd.RDD;

:load /home/leonardo/MioArchivio/MieiInvestimenti/EstraiAzioni.scala

val SAG : RDD[(String, Iterable[(Int, Double, 
                                Double, Double, 
                                Double, Int, 
                                String, String)])] = SimboliAscendenti.groupByKey;


val SAG1 : Array[(String, Iterable[(Int, Double, 
                                    Double, Double, 
                                    Double, Int, 
                                    String, String)])] = SAG.take(1);

val SAG1A : Array[Array[(Int, Double, Double, 
                       Double, Double, Int, 
                       String, String)]] = SAG1.map(item => item._2.toArray);

val SAG1AFM : Array[(Int, Double, Double, Double, 
               Double, Int, String, String)] = SAG1A.flatMap(item => item);

val VettoreElementi : Vector[(Int, Double, 
                              Double, Double, 
                              Double, Int, 
                              String, String)] = SAG1AFM.toVector;

val VettoreElementiRDD = sc.parallelize(VettoreElementi);

val SAV : RDD[String] = SimboliAscendenti.map(item => item._1.distinct);
                              
val SACOUNT : Int = SAV.distinct.count.toInt;

val SAG1ACOUNT : Int = SAG1A(0).size;
                        

/*
 *
 * Vorrei ottenere, tramite un ciclo for, una serie di variabili.
 * Ognuna di queste variabili deve avere un nome diveros.
 *
 * Questo nome deve essere composto, nella sua prima parte in tutte
 * uguale: SAGA, piu una seconda parte variabile, che corrisponde al
 * nome dello stesso titolo in analisi.
 *
 * Ad esempio per il titolo "BG", avrò una variabile chiamata 
 * "SAGABG", allo stesso modo, per il titolo "AMP", avrò: "SAGAAMP",
 * e cosi via.
 *
 * Ognuna di queste variabili, prese separatamente, deve essere del
 * medesimo tipo di quella che, in questo codice è la variabile 
 * "VettoreElementi", oppure "VettoreElementiRDD".
 *
 * Dovrà cioè essere una distinta variabile, di tipo "Vector", ivi 
 * contenete tutti gli elementi associati a quello specifico 
 * titolo.
 *
*/

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
