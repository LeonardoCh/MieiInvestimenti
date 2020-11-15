
    /*
     * A questo punto comincerò ad implementare la strategia per
     * calcolare in quale modo voglio che i miei CW vengano ordinati.
     *
     * La prima domanda, e piu importante da farsi è: quale deve 
     * essere il mio guadagno desiderato?
     *
     * Voglio che il guadagno atteso sia in funzione del costo fisso 
     * del CW stesso, che è di 15 euro, ma che sia anche legato, in
     * qualche misura, alla volatilità ed all'andamento del sottostante.
     *
     * Come faccio dunque a valutare il rendimento puntuale di ogni CW
     * preso separatamente? Ho bisogno dell'andamento del sottostante e,
     * possibilmente della sua volatilità in un intervallo di tempo
     * passato.
     *
     * Devo quindi poter accedere a dati finanziari online, real time.
     */

     /*
      * Per quanto riguarda la dichiarazione e la sequenza delle varie 
      * classi e dei vari oggeti, questi vengono eseguiti in sequenza,
      * proprio come se fosse un programma Java.
      *
      * Questo significa, a sua volta, che se io definissi tutti i 
      * miei metodi, all'interno di classi ("class"), od oggetti 
      * ("object") separati ed indipendenti, allora il codice risultante
      * non genererebbe alcun output, perchè non ci sarebbe alcun metodo
      * principale a chiamare quando un oggetto, quando un altro.
      *
      * Per fare in modo che i vari metodi vengano chiamati, ed evocati,
      * lascerò all'inizio tutta una sezione che non è inclusa in alcuna
      * definizione class, def od object, proprio perchè, in questa 
      * sezione, chiamo semplicemente la sequenza principale.
      *
      * Da notare che, anche se questa sezione principale la includessi
      * nella definizione "main", non sortirebbe comunque alcun risultato
      * perchè, anche la stessa main, dovrebbe a sua volta venir invocata
      * da un ulteriore metodo a lei superiore.
     */

     /*
      *
      * Per quanto riguarda il progetto, devo ora decidere secondo quale 
      * algoritmo, e quale mia personalissima scelta, dovrò ordinare i CW
      * che sono parte di questo datasource.
      *
      * Il criterio fondamentale riguarda il guadagno atteso, che sarà 
      * funzione del capitasle investito, e del costo di ogni CW.
      *
      * Ammetto che ogni CW abbia un costo fisso di 15 euro.
      *
      * Vorrei che, ad esempio, fino a 200 euro di capitale investito, io 
      * debba vendere il CW, quando avrò realizzato un capitale di almeno 
      * 250 euro. 
      *
      * Quindi su 200 euro investiti, su un capitale risultate di 250 euro, 
      * avrò ottenuto un guadagno netto di 50 - 15 = 35 euro.
      *
      * La prima cosa da fare è capire se il mercato, per quel particolare
      * titolo, è di tipo rialzista, ribassista, o laterale, ed in funzione
      * di questo trend, applicare strategie diverse.
      *
      * La funzione che dovrò fare sarà, di impostare una specifica data
      * nel passato, e l'algoritmo dovrà analizzare tutti i valori assunti
      * da quello specifico sottostante ogni giorno nel passato, fino ad oggi,
      * ed in funzione di questi valori, dovrà calcolare quali sono i valori
      * di "supporto" e "resistenza" minimi, affinche quel sottostante possa
      * essere definito "laterale" fino a quella specifica data.
      *
      * Se, questi valori di supporto e resistenza, si trovano in un range 
      * percentuale, che sia sufficientemente prossimo al valore medio, 
      * assunto dal medesimo sottostante, nello stesso intervallo di tempo,
      * allora lo potrò definire laterale.
      *
      * Se invece, quello stesso sottostante si discosta in maniera
      * considerevole dal valore medio, allora, potrà essere definito in trend
      * rialzista, o ribassista, rispettivamente se si troverà al di sopra, od
      * al di sotto dello stesso valore medio.
      *
      * Devo prevedere delle funzioni che, nel calcolo di supporti e resistenze,
      * escludano vampate temporanee del titolo al di fuori del range 
      * calcolato, oppure che mi notifichino i valori di massimo e minimo
      * assoluto, nonche una stima del range di media.
      *
     */


     /*
      *
      * Mi sono iscritto a "MarketStack" per ottenere le quotazioni dei titoli.
      * La stringa di connessione per ottenere le quotazioni, ad esempio di 
      * Intesa San Paolo è:
      *
      * http://api.marketstack.com/v1/eod?access_key=22545ca5e0b41c4b6b693f5bf40ecb1f&symbols=ISP.XMIL
      *
      * Dove, la prima parte:  http://api.marketstack.com/v1/eod indica la 
      * richiesta dei dati di fine giornata (eod = end-of-day).
      *
      * ?access_key= indica la mia chiave personale di accesso, che è quella
      * che segue:
      *
      * 22545ca5e0b41c4b6b693f5bf40ecb1f
      *
      * Richiedo poi il simbolo, ovvero il titolo che mi interessa:
      *
      * &symbols= indica che richiedo uno specifico simbolo.
      *
      * ISP.XMIL indica proprio Intesa San Paolo.
      *
      * Il dominio "XMIL", sta ad indicare "London Stock Exchange", dove è
      * collocata borsa Italiana, e quindi il titolo Intesa.
      *
      * Ogni volta che sono interessato ad un particolare titolo, devo 
      * prima controllare su internet quale è il suo simbolo internazionale 
      * (ISP.XMIL per Intesa San Paolo), e quindi richiedere il JSON relativo.
      * 
     */

import org.apache.spark.rdd.RDD;

val CW = sc.textFile("/user/hadoop/Investimenti6Agosto2020");

val CostoCW: Int = 15; 

    println("Sono nel main, prima della separazione");
    val TuplaCW : RDD[(Int, String,
                       String, Double, 
                       Double, Double, 
                       Double, Double, 
                       Double, Double, 
                       Int, Int, Int, 
                       String)] = separaDati(CW : RDD[String]);
    println("Sono nel main dopo la separazione");

    println("Il numero di record, sembra essere: " + TuplaCW.count);

//----------------------------------------------------------------------------------------------------------//    
    val Isin : RDD[(String, (Int, String,
                    String, Double, Double,
                    Double, Double, Double,
                    Double, Double, Int,
                    Int, Int, String))] = ordinaIsinAscendente(TuplaCW : RDD[(Int, String,
                                                                              String, Double, Double,
                                                                              Double, Double, Double,
                                                                              Double, Double, Int,
                                                                              Int, Int, String)]);
//----------------------------------------------------------------------------------------------------------//    
//----------------------------------------------------------------------------------------------------------//    
    val Sottostante : RDD[(String, (Int, String,
                           String, Double, Double,
                           Double, Double, Double,
                           Double, Double, Int,
                           Int, Int, String))] = ordinaSottostanteAscendente(TuplaCW : RDD[(Int, String,
                                                                                            String, Double, Double,
                                                                                            Double, Double, Double,
                                                                                            Double, Double, Int,
                                                                                            Int, Int, String)]);
//----------------------------------------------------------------------------------------------------------//    
//----------------------------------------------------------------------------------------------------------//    
    val PrezzoSottostante : RDD[(Double, (Int, String,
                                 String, Double, Double,
                                 Double, Double, Double,
                                 Double, Double, Int,
                                 Int, Int, String))] = ordinaPrezzoSottostanteAscendente(TuplaCW : RDD[(Int, String,
                                                                                                        String, Double, Double,
                                                                                                        Double, Double, Double,
                                                                                                        Double, Double, Int,
                                                                                                        Int, Int, String)]);
//----------------------------------------------------------------------------------------------------------//    
//----------------------------------------------------------------------------------------------------------//    
    val Strike : RDD[(Double, (Int, String,
                      String, Double, Double,
                      Double, Double, Double,
                      Double, Double, Int,
                      Int, Int, String))] = ordinaStrikeAscendente(TuplaCW : RDD[(Int, String,
                                                                                  String, Double, Double,
                                                                                  Double, Double, Double,
                                                                                  Double, Double, Int,
                                                                                  Int, Int, String)]);
//----------------------------------------------------------------------------------------------------------//    
//----------------------------------------------------------------------------------------------------------//    
    val PuntoPareggio : RDD[(Double, (Int, String,
                             String, Double, Double,
                             Double, Double, Double,
                             Double, Double, Int,
                             Int, Int, String))] = ordinaPuntoPareggioAscendente(TuplaCW : RDD[(Int, String,
                                                                                                String, Double, Double,
                                                                                                Double, Double, Double,
                                                                                                Double, Double, Int,
                                                                                                Int, Int, String)]);
//----------------------------------------------------------------------------------------------------------//    
//----------------------------------------------------------------------------------------------------------//    
    val Leva : RDD[(Double, (Int, String,
                    String, Double, Double,
                    Double, Double, Double,
                    Double, Double, Int,
                    Int, Int, String))] = ordinaLevaAscendente(TuplaCW : RDD[(Int, String,
                                                                              String, Double, Double,
                                                                              Double, Double, Double,
                                                                              Double, Double, Int,
                                                                              Int, Int, String)]);
//----------------------------------------------------------------------------------------------------------//    
//----------------------------------------------------------------------------------------------------------//    
    val DistanzaStrike : RDD[(Double, (Int, String,
                              String, Double, Double,
                              Double, Double, Double,
                              Double, Double, Int,
                              Int, Int, String))] = ordinaDistanzaStrikeAscendente(TuplaCW : RDD[(Int, String,
                                                                                                  String, Double, Double,
                                                                                                  Double, Double, Double,
                                                                                                  Double, Double, Int,
                                                                                                  Int, Int, String)]);
//----------------------------------------------------------------------------------------------------------//    
//----------------------------------------------------------------------------------------------------------//    
    val Denaro : RDD[(Double, (Int, String,
                      String, Double, Double,
                      Double, Double, Double,
                      Double, Double, Int,
                      Int, Int, String))] = ordinaDenaroAscendente(TuplaCW : RDD[(Int, String,
                                                                                  String, Double, Double,
                                                                                  Double, Double, Double,
                                                                                  Double, Double, Int,
                                                                                  Int, Int, String)]);
//----------------------------------------------------------------------------------------------------------//    
//----------------------------------------------------------------------------------------------------------//    
    val Lettera : RDD[(Double, (Int, String,
                       String, Double, Double,
                       Double, Double, Double,
                       Double, Double, Int,
                       Int, Int, String))] = ordinaLetteraAscendente(TuplaCW : RDD[(Int, String,
                                                                                    String, Double, Double,
                                                                                    Double, Double, Double,
                                                                                    Double, Double, Int,
                                                                                    Int, Int, String)]);
//----------------------------------------------------------------------------------------------------------//    
//----------------------------------------------------------------------------------------------------------//    
    val Tipo : RDD[(String, (Int, String,
                    String, Double, Double,
                    Double, Double, Double,
                    Double, Double, Int,
                    Int, Int, String))] = ordinaTipo(TuplaCW : RDD[(Int, String,
                                                                    String, Double, Double,
                                                                    Double, Double, Double,
                                                                    Double, Double, Int,
                                                                    Int, Int, String)]);
//----------------------------------------------------------------------------------------------------------//    
    println("Sono nel main dopo la chiamata all'ordinatore");

def separaDati(RawCWDatas : RDD[String]) : RDD[(Int, String, 
                                                String, Double, 
                                                Double, Double, 
                                                Double, Double, 
                                                Double, Double, 
                                                Int, Int, Int, 
                                                String)] = {

//  val CoveredWarrant = CW.map(Righe => {
  val CoveredWarrant = RawCWDatas.map(Righe => {
  val elemento = Righe.split(",");

  val Id = elemento(0).trim().toInt;
  val Isin = elemento(1).trim().toString;
  val NomeSottostante = elemento(2).toString.replace(" S.p.A.","");
  val PrezzoSottostante = elemento(3).trim().toDouble;
  val Strike = elemento(4).trim().toDouble;
  val PuntoPareggio = elemento(5).trim().toDouble;
  val Leva = elemento(6).trim().toDouble;
  val DistanzaStrike = elemento(7).trim().toDouble;
  val Denaro = elemento(8).trim().toDouble;
  val Lettera = elemento(9).trim().toDouble;
  val anno = elemento(10).split(" ")(0).split("-")(0).toInt;
  val mese = elemento(10).split(" ")(0).split("-")(1).toInt;
  val giorno = elemento(10).split(" ")(0).split("-")(2).toInt;
  val Tipo = elemento(13).trim().toString;

    (
      Id, Isin, NomeSottostante, PrezzoSottostante,
      Strike, PuntoPareggio, Leva, DistanzaStrike,
      Denaro, Lettera, giorno, mese, anno, Tipo
    ) //Chiude la tupla
  })  //Chiude la map
  return CoveredWarrant;
} //Chiude la def SeparaDati

def ordinaIsinAscendente(TuplaCW : RDD[(Int, String,
                                        String, Double, 
                                        Double, Double, 
                                        Double, Double, 
                                        Double, Double, 
                                        Int, Int, Int,
                                        String)]) : RDD[(String, (Int, String,
                                                         String, Double, Double,
                                                         Double, Double, Double,
                                                         Double, Double, Int,
                                                         Int, Int, String))] = {

  val AscendingOrderedIsin = TuplaCW.map(elemento => (elemento._2,elemento)).sortByKey(ascending = true);

  return AscendingOrderedIsin;

}//Chiude la def OrdinaIsinAscendente

def ordinaIsinDiscendente(TuplaCW : RDD[(Int, String,
                                         String, Double, 
                                         Double, Double, 
                                         Double, Double, 
                                         Double, Double, 
                                         Int, Int, Int,
                                         String)]) : RDD[(String, (Int, String,
                                                          String, Double, Double,
                                                          Double, Double, Double,
                                                          Double, Double, Int,
                                                          Int, Int, String))] = {

  val DiscendingOrderedIsin = TuplaCW.map(elemento => (elemento._2,elemento)).sortByKey(ascending = false);

  return DiscendingOrderedIsin;

}//Chiude la def OrdinaIsinDiscendente

def ordinaSottostanteAscendente(TuplaCW : RDD[(Int, String,
                                               String, Double, 
                                               Double, Double, 
                                               Double, Double, 
                                               Double, Double, 
                                               Int, Int, Int,
                                               String)]) : RDD[(String, (Int, String,
                                                                String, Double, Double,
                                                                Double, Double, Double,
                                                                Double, Double, Int,
                                                                Int, Int, String))] = {

  val AscendingOrderedSottostante = TuplaCW.map(elemento => (elemento._3,elemento)).sortByKey(ascending = true);

  return AscendingOrderedSottostante;

}//Chiude la def OrdinaSottostanteAscendente

def ordinaSottostanteDiscendente(TuplaCW : RDD[(Int, String,
                                                String, Double, 
                                                Double, Double, 
                                                Double, Double, 
                                                Double, Double, 
                                                Int, Int, Int,
                                                String)]) : RDD[(String, (Int, String,
                                                                 String, Double, Double,
                                                                 Double, Double, Double,
                                                                 Double, Double, Int,
                                                                 Int, Int, String))] = {

  val DiscendingOrderedSottostante = TuplaCW.map(elemento => (elemento._3,elemento)).sortByKey(ascending = false);

  return DiscendingOrderedSottostante;

}//Chiude la def OrdinaSottostanteDiscendente

def ordinaPrezzoSottostanteAscendente(TuplaCW : RDD[(Int, String,
                                                     String, Double, 
                                                     Double, Double, 
                                                     Double, Double, 
                                                     Double, Double, 
                                                     Int, Int, Int,
                                                     String)]) : RDD[(Double, (Int, String,
                                                                      String, Double, Double,
                                                                      Double, Double, Double,
                                                                      Double, Double, Int,
                                                                      Int, Int, String))] = {

  val AscendingOrderedPrezzoSottostante = TuplaCW.map(elemento => (elemento._4,elemento)).sortByKey(ascending = true);

  return AscendingOrderedPrezzoSottostante;

}//Chiude la def OrdinaPrezzoSottostanteAscendente

def ordinaPrezzoSottostanteDiscendente(TuplaCW : RDD[(Int, String,
                                                      String, Double, 
                                                      Double, Double, 
                                                      Double, Double, 
                                                      Double, Double, 
                                                      Int, Int, Int,
                                                      String)]) : RDD[(Double, (Int, String,
                                                                       String, Double, Double,
                                                                       Double, Double, Double,
                                                                       Double, Double, Int,
                                                                       Int, Int, String))] = {

  val DiscendingOrderedPrezzoSottostante = TuplaCW.map(elemento => (elemento._4,elemento)).sortByKey(ascending = false);

  return DiscendingOrderedPrezzoSottostante;

}//Chiude la def OrdinaPrezzoSottostanteDiscendente

def ordinaStrikeAscendente(TuplaCW : RDD[(Int, String,
                                          String, Double, 
                                          Double, Double, 
                                          Double, Double, 
                                          Double, Double, 
                                          Int, Int, Int,
                                          String)]) : RDD[(Double, (Int, String,
                                                           String, Double, Double,
                                                           Double, Double, Double,
                                                           Double, Double, Int,
                                                           Int, Int, String))] = {

  val AscendingOrderedStrike = TuplaCW.map(elemento => (elemento._5,elemento)).sortByKey(ascending = true);

  return AscendingOrderedStrike;

}//Chiude la def OrdinaStrikeAscendente

def ordinaStrikeDiscendente(TuplaCW : RDD[(Int, String,
                                           String, Double, 
                                           Double, Double, 
                                           Double, Double, 
                                           Double, Double, 
                                           Int, Int, Int,
                                           String)]) : RDD[(Double, (Int, String,
                                                            String, Double, Double,
                                                            Double, Double, Double,
                                                            Double, Double, Int,
                                                            Int, Int, String))] = {

  val DiscendingOrderedStrike = TuplaCW.map(elemento => (elemento._5,elemento)).sortByKey(ascending = false);

  return DiscendingOrderedStrike;

}//Chiude la def OrdinaStrikeDiscendente

def ordinaPuntoPareggioAscendente(TuplaCW : RDD[(Int, String,
                                                 String, Double, 
                                                 Double, Double, 
                                                 Double, Double, 
                                                 Double, Double, 
                                                 Int, Int, Int,
                                                 String)]) : RDD[(Double, (Int, String,
                                                                  String, Double, Double,
                                                                  Double, Double, Double,
                                                                  Double, Double, Int,
                                                                  Int, Int, String))] = {

  val AscendingOrderedPuntoPareggio = TuplaCW.map(elemento => (elemento._6,elemento)).sortByKey(ascending = true);

  return AscendingOrderedPuntoPareggio;

}//Chiude la def OrdinaPuntoPareggioAscendente

def ordinaPuntoPareggioDiscendente(TuplaCW : RDD[(Int, String,
                                                  String, Double, 
                                                  Double, Double, 
                                                  Double, Double, 
                                                  Double, Double, 
                                                  Int, Int, Int,
                                                  String)]) : RDD[(Double, (Int, String,
                                                                   String, Double, Double,
                                                                   Double, Double, Double,
                                                                   Double, Double, Int,
                                                                   Int, Int, String))] = {

  val DiscendingOrderedPuntoPareggio = TuplaCW.map(elemento => (elemento._6,elemento)).sortByKey(ascending = false);

  return DiscendingOrderedPuntoPareggio;

}//Chiude la def OrdinaPuntoPareggioDiscendente

def ordinaLevaAscendente(TuplaCW : RDD[(Int, String,
                                        String, Double, 
                                        Double, Double, 
                                        Double, Double, 
                                        Double, Double, 
                                        Int, Int, Int,
                                        String)]) : RDD[(Double, (Int, String,
                                                         String, Double, Double,
                                                         Double, Double, Double,
                                                         Double, Double, Int,
                                                         Int, Int, String))] = {

  val AscendingOrderedLeva = TuplaCW.map(elemento => (elemento._7,elemento)).sortByKey(ascending = true);

  return AscendingOrderedLeva;

}//Chiude la def OrdinaLevaAscendente

def ordinaLevaDiscendente(TuplaCW : RDD[(Int, String,
                                         String, Double, 
                                         Double, Double, 
                                         Double, Double, 
                                         Double, Double, 
                                         Int, Int, Int,
                                         String)]) : RDD[(Double, (Int, String,
                                                          String, Double, Double,
                                                          Double, Double, Double,
                                                          Double, Double, Int,
                                                          Int, Int, String))] = {

  val DiscendingOrderedLeva = TuplaCW.map(elemento => (elemento._7,elemento)).sortByKey(ascending = false);

  return DiscendingOrderedLeva;

}//Chiude la def OrdinaLevaDiscendente

def ordinaDistanzaStrikeAscendente(TuplaCW : RDD[(Int, String,
                                                  String, Double, 
                                                  Double, Double, 
                                                  Double, Double, 
                                                  Double, Double, 
                                                  Int, Int, Int,
                                                  String)]) : RDD[(Double, (Int, String,
                                                                   String, Double, Double,
                                                                   Double, Double, Double,
                                                                   Double, Double, Int,
                                                                   Int, Int, String))] = {

  val AscendingOrderedDistanzaStrike = TuplaCW.map(elemento => (elemento._8,elemento)).sortByKey(ascending = true);

  return AscendingOrderedDistanzaStrike;

}//Chiude la def OrdinaDistanzaStrikeAscendente

def ordinaDistanzaStrikeDiscendente(TuplaCW : RDD[(Int, String,
                                                   String, Double, 
                                                   Double, Double, 
                                                   Double, Double, 
                                                   Double, Double, 
                                                   Int, Int, Int,
                                                   String)]) : RDD[(Double, (Int, String,
                                                                    String, Double, Double,
                                                                    Double, Double, Double,
                                                                    Double, Double, Int,
                                                                    Int, Int, String))] = {

  val DiscendingOrderedDistanzaStrike = TuplaCW.map(elemento => (elemento._8,elemento)).sortByKey(ascending = false);

  return DiscendingOrderedDistanzaStrike;

}//Chiude la def OrdinaDistanzaStrikeDiscendente

def ordinaDenaroAscendente(TuplaCW : RDD[(Int, String,
                                          String, Double, 
                                          Double, Double, 
                                          Double, Double, 
                                          Double, Double, 
                                          Int, Int, Int,
                                          String)]) : RDD[(Double, (Int, String,
                                                           String, Double, Double,
                                                           Double, Double, Double,
                                                           Double, Double, Int,
                                                           Int, Int, String))] = {

  val AscendingOrderedDenaro = TuplaCW.map(elemento => (elemento._9,elemento)).sortByKey(ascending = true);

  return AscendingOrderedDenaro;

}//Chiude la def OrdinaDenaroAscendente

def ordinaDenaroDiscendente(TuplaCW : RDD[(Int, String,
                                           String, Double, 
                                           Double, Double, 
                                           Double, Double, 
                                           Double, Double, 
                                           Int, Int, Int,
                                           String)]) : RDD[(Double, (Int, String,
                                                            String, Double, Double,
                                                            Double, Double, Double,
                                                            Double, Double, Int,
                                                            Int, Int, String))] = {

  val DiscendingOrderedDenaro = TuplaCW.map(elemento => (elemento._9,elemento)).sortByKey(ascending = false);

  return DiscendingOrderedDenaro;

}//Chiude la def OrdinaDenaroDiscendente

def ordinaLetteraAscendente(TuplaCW : RDD[(Int, String,
                                           String, Double, 
                                           Double, Double, 
                                           Double, Double, 
                                           Double, Double, 
                                           Int, Int, Int,
                                           String)]) : RDD[(Double, (Int, String,
                                                            String, Double, Double,
                                                            Double, Double, Double,
                                                            Double, Double, Int,
                                                            Int, Int, String))] = {

  val AscendingOrderedLettera = TuplaCW.map(elemento => (elemento._9,elemento)).sortByKey(ascending = true);

  return AscendingOrderedLettera;

}//Chiude la def OrdinaLetteraAscendente

def ordinaLetteraDiscendente(TuplaCW : RDD[(Int, String,
                                            String, Double, 
                                            Double, Double, 
                                            Double, Double, 
                                            Double, Double, 
                                            Int, Int, Int,
                                            String)]) : RDD[(Double, (Int, String,
                                                             String, Double, Double,
                                                             Double, Double, Double,
                                                             Double, Double, Int,
                                                             Int, Int, String))] = {

  val DiscendingOrderedLettera = TuplaCW.map(elemento => (elemento._9,elemento)).sortByKey(ascending = false);

  return DiscendingOrderedLettera;

}//Chiude la def OrdinaLetteraDiscendente

def ordinaTipo(TuplaCW : RDD[(Int, String,
                              String, Double, 
                              Double, Double, 
                              Double, Double, 
                              Double, Double, 
                              Int, Int, Int,
                              String)]) : RDD[(String, (Int, String,
                                               String, Double, Double,
                                               Double, Double, Double,
                                               Double, Double, Int,
                                               Int, Int, String))] = {

  val AscendingOrderedTipo = TuplaCW.map(elemento => (elemento._14,elemento)).distinct;

  return AscendingOrderedTipo;

}//Chiude la def OrdinaTipo







def getPercentOfIntero(Intero : Double, Percentuale : Double) : Double = {

//In questa funzione calcolo a quanto corrisponde la percentuale 
////passata come parametro, in funzione dell'intero, anche questo
////passato come parametro.
//
//In particolare, se passassi come parametri, ad esempio, i valori
//Intero = 100 e Percentuale = 3, vorrebbe dire che io voglio calcolare
//a quale percentuale corrisponde 3, su un intero di 100.
//La funzione restituisce 3. Vuol dire che è il 3%

  val Appoggio : Double = Intero / 100;
  val Perc : Double = Appoggio * Percentuale;

  return Perc; 
}

def getInteroOfPercent(Fattore1 : Double, Fattore2 : Double) : Double = {

//In questa funzione, vengono invece passati due interi.
//Si vuole sapere ognuno dei due interi, quale percentuale 
//compone rispetto alla somma dei due.

  val Somma : Double = Fattore1 + Fattore2;
  val Appoggio : Double = Fattore1 / Somma;
  val PrimaPercent  : Double = Appoggio * 100;

  return PrimaPercent;
}
