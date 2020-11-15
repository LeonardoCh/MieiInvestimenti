#!/bin/bash

SimboliAzioni="/home/leonardo/MioArchivio/MieiInvestimenti";

if [ -f ProvaSimboli.txt ]; then rm ProvaSimboli.txt; fi;
if [ -f SimboliGenerati.txt ]; then rm SimboliGenerati.txt; fi;

MarketStackURL="http://api.marketstack.com/v1/eod";
MarketStackAccess="?access_key=";
MarketStackKey="22545ca5e0b41c4b6b693f5bf40ecb1f";
MarketStackSymbol="&symbols=";

#La stringa da comporre per ottenere le quotazioni è:
#curl "http://api.marketstack.com/v1/eod?access_key=22545ca5e0b41c4b6b693f5bf40ecb1f&symbols=SRS"

#Devo fare attenzione ad aggiungere anche i doppi apici, 
#e l'ultima parte indica il simbolo del quale voglio 
#scaricare i risultati.

sed 's/\;/\n/g' "$SimboliAzioni/Simboli-Titoli-Azionari" > ProvaSimboli.txt;

#echo "La lista dei simboli è: " $ListaSimboli;

while read -r LINE; do

	echo "curl-"'"'$MarketStackURL\
	$MarketStackAccess\
	$MarketStackKey\
	$MarketStackSymbol\
	$LINE\
	| sed -e 's/\ //g' \
	| sed -e 's/$/"/g'\
	| sed -e 's/-/\ /g' >> SimboliGenerati.txt;

done < ProvaSimboli.txt;

#LA SEGUENTE ISTRUZIONE LA COMMENTO, PER EVITARE DI INOLTRARE NUMEROSE 
#RICHIESTE CONTINUE A MARKETSHARE, VISTO CHE LE HO IN UMERO LIMITATO.

#UNA VOLTA CHE QUESTO SCRIPT SARA' ENTRATO A REGIME, POTRO' RIMUOVERE
#I COMMENTI.

# /bin/bash $SimboliAzioni/SimboliGenerati.txt >> Quotazioni.txt;
