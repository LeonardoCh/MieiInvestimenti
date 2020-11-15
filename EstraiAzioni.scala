import org.apache.spark.rdd.RDD;

val Azioni = sc.textFile("/user/hadoop/TitoliAzionari6Settembre2020");

val TuplaAzioni : RDD[(Int, Double,
                       Double, Double,
                       Double, Int, 
                       String, String)] = SeparaDati(Azioni : RDD[String]);

val SimboliAscendenti : RDD[(String, (Int, Double,
                                      Double, Double,
                                      Double, Int,
                                      String, String))] = OrdinaSimboliAscendenti(TuplaAzioni : RDD[(Int, Double,
                                                                                                     Double, Double,
                                                                                                     Double, Int,
                                                                                                     String, String)]);

val SimboliDiscendenti : RDD[(String, (Int, Double,
                                       Double, Double,
                                       Double, Int,
                                       String, String))] = OrdinaSimboliDiscendenti(TuplaAzioni : RDD[(Int, Double,
                                                                                                      Double, Double,
                                                                                                      Double, Int,
                                                                                                      String, String)]);

val MinimiAscendenti : RDD[(Double, (Int, Double,
                                     Double, Double,
                                     Double, Int,
                                     String, String))] = OrdinaMinimiAscendenti(TuplaAzioni : RDD[(Int, Double,
                                                                                                   Double, Double,
                                                                                                   Double, Int,
                                                                                                   String, String)]);

val MinimiDiscendenti : RDD[(Double, (Int, Double,
                                      Double, Double,
                                      Double, Int,
                                      String, String))] = OrdinaMinimiDiscendenti(TuplaAzioni : RDD[(Int, Double,
                                                                                                     Double, Double,
                                                                                                     Double, Int,
                                                                                                     String, String)]);

val MassimiAscendenti : RDD[(Double, (Int, Double,
                                      Double, Double,
                                      Double, Int,
                                      String, String))] = OrdinaMassimiAscendenti(TuplaAzioni : RDD[(Int, Double,
                                                                                                     Double, Double,
                                                                                                     Double, Int,
                                                                                                     String, String)]);

val MassimiDiscendenti : RDD[(Double, (Int, Double,
                                       Double, Double,
                                       Double, Int,
                                       String, String))] = OrdinaMassimiDiscendenti(TuplaAzioni : RDD[(Int, Double,
                                                                                                       Double, Double,
                                                                                                       Double, Int,
                                                                                                       String, String)]);

val AperturaAscendenti : RDD[(Double, (Int, Double,
                                       Double, Double,
                                       Double, Int,
                                       String, String))] = OrdinaAperturaAscendenti(TuplaAzioni : RDD[(Int, Double,
                                                                                                       Double, Double,
                                                                                                       Double, Int,
                                                                                                       String, String)]);

val AperturaDiscendenti : RDD[(Double, (Int, Double,
                                        Double, Double,
                                        Double, Int,
                                        String, String))] = OrdinaAperturaDiscendenti(TuplaAzioni : RDD[(Int, Double,
                                                                                                         Double, Double,
                                                                                                         Double, Int,
                                                                                                         String, String)]);

val ChiusuraAscendenti : RDD[(Double, (Int, Double,
                                       Double, Double,
                                       Double, Int,
                                       String, String))] = OrdinaChiusuraAscendenti(TuplaAzioni : RDD[(Int, Double,
                                                                                                       Double, Double,
                                                                                                       Double, Int,
                                                                                                       String, String)]);

val ChiusuraDiscendenti : RDD[(Double, (Int, Double,
                                        Double, Double,
                                        Double, Int,
                                        String, String))] = OrdinaChiusuraDiscendenti(TuplaAzioni : RDD[(Int, Double,
                                                                                                         Double, Double,
                                                                                                         Double, Int,
                                                                                                         String, String)]);

val VolumeAscendenti : RDD[(Int, (Int, Double,
                                  Double, Double,
                                  Double, Int,
                                  String, String))] = OrdinaVolumeAscendenti(TuplaAzioni : RDD[(Int, Double,
                                                                                                Double, Double,
                                                                                                Double, Int,
                                                                                                String, String)]);

val VolumeDiscendenti : RDD[(Int, (Int, Double,
                                   Double, Double,
                                   Double, Int,
                                   String, String))] = OrdinaVolumeDiscendenti(TuplaAzioni : RDD[(Int, Double,
                                                                                                  Double, Double,
                                                                                                  Double, Int,
                                                                                                  String, String)]);

def SeparaDati (RawDatiAzioni : RDD[String]) : RDD[(Int, Double,
                                                    Double, Double,
                                                    Double, Int, 
                                                    String, String)] = {


    val Azione = RawDatiAzioni.map(Righe => {
                 val elemento = Righe.trim().split(",");
                 (
                   elemento(0).toInt, elemento(1).toDouble,
                   elemento(2).toDouble, elemento(3).toDouble,
                   elemento(4).toDouble, elemento(5).toInt,
                   elemento(6).toString, elemento(7).toString
                 )
    })

return Azione;

}

def OrdinaSimboliAscendenti (TuplaAzioni : RDD[(Int, Double,
                                                Double, Double,
                                                Double, Int,
                                                String, String)]) : RDD[(String, (Int, Double,
                                                                                  Double, Double,
                                                                                  Double, Int, 
                                                                                  String, String))] = {

    val SymbolsTupla = TuplaAzioni.map(elemento => {
                (elemento._7,elemento);
    })

    val SymbolsGroupedAscending = SymbolsTupla.sortByKey(ascending = true);

return SymbolsGroupedAscending;

}

def OrdinaSimboliDiscendenti (TuplaAzioni : RDD[(Int, Double,
                                                 Double, Double,
                                                 Double, Int,
                                                 String, String)]) : RDD[(String, (Int, Double,
                                                                                   Double, Double,
                                                                                   Double, Int, 
                                                                                   String, String))] = {

    val SymbolsTupla = TuplaAzioni.map(elemento => {
                (elemento._7,elemento);
    })

    val SymbolsGroupedDescending = SymbolsTupla.sortByKey(ascending = false);

return SymbolsGroupedDescending;

}

def OrdinaMinimiAscendenti (TuplaAzioni : RDD[(Int, Double,
                                               Double, Double,
                                               Double, Int,
                                               String, String)]) : RDD[(Double, (Int, Double,
                                                                                 Double, Double,
                                                                                 Double, Int, 
                                                                                 String, String))] = {

    val MinimiTupla = TuplaAzioni.map(elemento => {
                (elemento._4,elemento);
    })

    val MinimiGrouped = MinimiTupla.sortByKey(ascending = true);

return MinimiGrouped;

}

def OrdinaMinimiDiscendenti (TuplaAzioni : RDD[(Int, Double,
                                                Double, Double,
                                                Double, Int,
                                                String, String)]) : RDD[(Double, (Int, Double,
                                                                                  Double, Double,
                                                                                  Double, Int, 
                                                                                  String, String))] = {

    val MinimiTupla = TuplaAzioni.map(elemento => {
                (elemento._4,elemento);
    })

    val MinimiGrouped = MinimiTupla.sortByKey(ascending = false);

return MinimiGrouped;

}

def OrdinaMassimiAscendenti (TuplaAzioni : RDD[(Int, Double,
                                                Double, Double,
                                                Double, Int,
                                                String, String)]) : RDD[(Double, (Int, Double,
                                                                                  Double, Double,
                                                                                  Double, Int, 
                                                                                  String, String))] = {

    val MassimiTupla = TuplaAzioni.map(elemento => {
                (elemento._3,elemento);
    })

    val MassimiGrouped = MassimiTupla.sortByKey(ascending = true);

return MassimiGrouped;

}

def OrdinaMassimiDiscendenti (TuplaAzioni : RDD[(Int, Double,
                                                 Double, Double,
                                                 Double, Int,
                                                 String, String)]) : RDD[(Double, (Int, Double,
                                                                                   Double, Double,
                                                                                   Double, Int, 
                                                                                   String, String))] = {

    val MassimiTupla = TuplaAzioni.map(elemento => {
                (elemento._3,elemento);
    })

    val MassimiGrouped = MassimiTupla.sortByKey(ascending = false);

return MassimiGrouped;

}

def OrdinaAperturaAscendenti (TuplaAzioni : RDD[(Int, Double,
                                                 Double, Double,
                                                 Double, Int,
                                                 String, String)]) : RDD[(Double, (Int, Double,
                                                                                   Double, Double,
                                                                                   Double, Int, 
                                                                                   String, String))] = {

    val AperturaTupla = TuplaAzioni.map(elemento => {
                (elemento._2,elemento);
    })

    val AperturaGrouped = AperturaTupla.sortByKey(ascending = true);

return AperturaGrouped;

}

def OrdinaAperturaDiscendenti (TuplaAzioni : RDD[(Int, Double,
                                                  Double, Double,
                                                  Double, Int,
                                                  String, String)]) : RDD[(Double, (Int, Double,
                                                                                    Double, Double,
                                                                                    Double, Int, 
                                                                                    String, String))] = {

    val AperturaTupla = TuplaAzioni.map(elemento => {
                (elemento._2,elemento);
    })

    val AperturaGrouped = AperturaTupla.sortByKey(ascending = false);

return AperturaGrouped;

}

def OrdinaChiusuraAscendenti (TuplaAzioni : RDD[(Int, Double,
                                                 Double, Double,
                                                 Double, Int,
                                                 String, String)]) : RDD[(Double, (Int, Double,
                                                                                   Double, Double,
                                                                                   Double, Int, 
                                                                                   String, String))] = {

    val ChiusuraTupla = TuplaAzioni.map(elemento => {
                (elemento._5,elemento);
    })

    val ChiusuraGrouped = ChiusuraTupla.sortByKey(ascending = true);

return ChiusuraGrouped;

}

def OrdinaChiusuraDiscendenti (TuplaAzioni : RDD[(Int, Double,
                                                  Double, Double,
                                                  Double, Int,
                                                  String, String)]) : RDD[(Double, (Int, Double,
                                                                                    Double, Double,
                                                                                    Double, Int, 
                                                                                    String, String))] = {

    val ChiusuraTupla = TuplaAzioni.map(elemento => {
                (elemento._5,elemento);
    })

    val ChiusuraGrouped = ChiusuraTupla.sortByKey(ascending = false);

return ChiusuraGrouped;

}

def OrdinaVolumeAscendenti (TuplaAzioni : RDD[(Int, Double,
                                               Double, Double,
                                               Double, Int,
                                               String, String)]) : RDD[(Int, (Int, Double,
                                                                              Double, Double,
                                                                              Double, Int, 
                                                                              String, String))] = {

    val VolumeTupla = TuplaAzioni.map(elemento => {
                (elemento._6,elemento);
    })

    val VolumeGrouped = VolumeTupla.sortByKey(ascending = true);

return VolumeGrouped;

}

def OrdinaVolumeDiscendenti (TuplaAzioni : RDD[(Int, Double,
                                                Double, Double,
                                                Double, Int,
                                                String, String)]) : RDD[(Int, (Int, Double,
                                                                               Double, Double,
                                                                               Double, Int, 
                                                                               String, String))] = {

    val VolumeTupla = TuplaAzioni.map(elemento => {
                (elemento._6,elemento);
    })

    val VolumeGrouped = VolumeTupla.sortByKey(ascending = false);

return VolumeGrouped;

}

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
