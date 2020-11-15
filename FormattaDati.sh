#!/bin/bash

BasePath=$PWD/MieiInvestimenti;

if [ -f NuovoJson.txt ]; then rm NuovoJson.txt; fi;
if [ -f InsertIntoTitoliAzionari.sql ]; then rm InsertIntoTitoliAzionari.sql; fi;

cat "$BasePath/Quotazioni.txt" \
| awk '{print $1,$2,$3,$4,$5,$11,$13}' RS="},{" FS="," \
| sed -e 's/\}\]\}//g' \
| sed -e 's/pagination//g' \
| sed -e 's/limit//g' \
| sed -e 's/100//g' \
| sed -e 's/{//g' \
| sed -e 's/\"\"//' \
| sed -e 's/:\"\"//g' \
| sed -e 's/:$//g' \
| sed -e 's/T00:00:00+0000\"/\"£/g' \
| sed -e '1d' > NuovoJson.txt;

SentenzaSQL=$(echo "mysql --user="leonardo" \
      --password="Goldrake3" \
      --host="localhost" \
      --port="3306" \
      --database=Investimenti"); 

while read -r LINE; do

Apertura=$(echo "$LINE" \
	   | awk '{print $1}' RS="£" FS=" "\
	   | sed -e 's/\"open\"://g');


Massimo=$(echo "$LINE" \
	   | awk '{print $2}' RS="£" FS=" "\
	   | sed -e 's/\"high\"://g');


Minimo=$(echo "$LINE" \
	   | awk '{print $3}' RS="£" FS=" "\
	   | sed -e 's/\"low\"://g');


Chiusura=$(echo "$LINE" \
	   | awk '{print $4}' RS="£" FS=" "\
	   | sed -e 's/\"close\"://g');


Volume=$(echo "$LINE" \
	   | awk '{print $5}' RS="£" FS=" "\
	   | sed -e 's/\"volume\"://g');


Simbolo=$(echo "$LINE" \
	   | awk '{print $6}' RS="£" FS=" "\
	   | sed -e 's/\"symbol\"://g');


Data=$(echo "$LINE" \
	   | awk '{print $7}' RS="£" FS=" "\
	   | sed -e 's/\"date\"://g');

#echo "Apertura: " $Apertura;
#echo "Massimo: " $Massimo;
#echo "Minimo: " $Minimo;
#echo "Chiusura: " $Chiusura;
#echo "Volume: " $Volume;
#echo "Simbolo: " $Simbolo;
#echo "Data: " $Data;

echo "insert into TitoliAzionari (Apertura, Massimo, Minimo, Chiusura, Volume, Simbolo, Data) \
values (\"$Apertura\",\"$Massimo\",\"$Minimo\",\"$Chiusura\",\
\"$Volume\",$Simbolo,$Data);" >> InsertIntoTitoliAzionari.sql;

done < NuovoJson.txt;

$SentenzaSQL < InsertIntoTitoliAzionari.sql;
