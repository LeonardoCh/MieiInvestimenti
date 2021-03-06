import org.apache.spark.rdd.RDD;

:load /home/leonardo/MioArchivio/MieiInvestimenti-Repository/MieiInvestimenti/EstraiAzioni.scala

val SAG : RDD[(String, Iterable[(Int, Double, 
                                Double, Double, 
                                Double, Int, 
                                String, String)])] = SimboliAscendenti.groupByKey;

val ElencoGruppi : RDD[String] = SAG.map(item => item._1.distinct);

val NumeroGruppi : Long = ElencoGruppi.count; 

val SAGList : RDD[List[(String, Int, 
                        Double, Double, 
                        Double, Double, 
                        Int, Int)]] = SAG.map(item => item._2.toList).map(item => item.map(item2 => ("Lista_".concat(item2._7.toString),
                                                                             item2._1.toInt,
                                                                             item2._2.toDouble,
                                                                             item2._3.toDouble,
                                                                             item2._4.toDouble,
                                                                             item2._5.toDouble,
                                                                             item2._6.toInt,
                                                                             item2._8.toString.replace("-","").toInt
                                                                                                       )
                                                                                             )
                                                                            );



RS(2,SAGList);

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
 *
 * Nell'ordine, le costanti numeriche che compaiono nei Simboli sono:
 *
 * Apertura - Massimo - Minimo - Chiusura - Volume - Simbolo - Data
 *
 * In particolare l'ultimo elemento, la data, è espressa senza trattini
 * e nel formato anno mese giorno: AAAAMMGG
 *
*/

def RS (Periodi : Int, SAGList : RDD[List[(String, Int,
                                           Double, Double,
                                           Double, Double,
                                           Int, Int)]]) : Double = {

  val TempValue : List[Double] = List();
  val RSOut : Double = 0;          
   
  /*
   * Per implementare il calcolo dell'RS uso una funzione scala 
   * che si chiama "sliding", e serve proprio a calcolare la somma
   * e la media attraverso una finestra di valori, scorrevole.
  */
  
  val apertura = SAGList.map(item => item.map(item2 => item2._2));
  val chiusura = SAGList.map(item => item.map(item2 => item2._5));

  val window = SAGList.map(item => item.sliding(2,1).toList);
  val apertura2 = window.map(item => item.map(item2 => item2.map(item3 => item3._2)));
  val chiusura2 = window.map(item => item.map(item2 => item2.map(item3 => item3._5)));

  return RSOut;
}

