#!/bin/sh
### ------------------------------------------------------------
#   Script de restauration de la base de données ELIPS
#	parametre 1: type de restauration {daily, weekly, base, appli, GED} 
#	paramètre 2: date au format YYYY-mm-dd
### ------------------------------------------------------------
. /etc/rc.d/init.d/functions

ENV_FILE=/etc/environment
source $ENV_FILE



if test $# -ne 2
then
	COMMAND=''
else
	COMMAND=$1
	TDATE=$2
fi

# Verification des droits de l'utilisateur
if [[ $UID = 0 ]]; then
	echo "Ce script ne doit pas être executer en root ou privilège sudoers"
	exit 1
fi

if [ -f $ELIPS_SERVER_TOMCAT/temp/catalina.pid ];
then
	echo "TOmcat doit être éteint pour lancer la restauration"
	exit 1
fi



restore_base(){
	echo "*** Restauration base début"
	$ELIPS_HOME/script/elips.sh start postgres
	sleep 2
	export PGPASSWORD=$(cat $ELIPS_HOME/script'/.elipsAccount')
	cd $ELIPS_BACKUP/daily
	$ELIPS_SERVER_POSTGRES/bin/psql -d postgres -c "drop database elips;"
	if  [ $? -eq 0 ]
	then	
                $ELIPS_SERVER_POSTGRES/bin/psql -d postgres -c "CREATE DATABASE elips owner elips;"
		zcat  $ELIPS_BACKUP/daily/${TDATE}_elips.sql.gz | $ELIPS_SERVER_POSTGRES/bin/psql -U elips elips
		success
	else 
		echo "des utilisateus sont connecté sur la base ou erreur d'accès à la base; restauration impossile."
		failure
	fi
	echo "*** Restauration base fin"
}
restore_appli(){
	echo "*** Restauration appli début"
	rm -rf $ELIPS_HOME/config $ELIPS_HOME/script $ELIPS_SERVER_TOMCAT $ELIPS_SERVER_POSTGRES $ELIPS_DATA
	cd /
	tar -zxvf $ELIPS_BACKUP/weekly/${TDATE}_elips.tar.gz
	if [ $? -eq 0 ]; then
		success
	else
		failure
	fi
	echo "*** Restauration base fin"	
}

restore_documents_elips(){
	# Restauration des documents
	echo "*** Restauration des documents début"
	mkdir -p ${ELIPS_DATA}/files
	rm -Rf ${ELIPS_DATA}/files/*
	mkdir -p ${ELIPS_DATA}/documents
	rm -Rf ${ELIPS_DATA}/documents/*
	cd /
	tar -xzf ${ELIPS_BACKUP}/daily/${TDATE}_elips_data.tar.gz
	if [ $? -eq 0 ]; then
		success
	else
		failure
	fi
	echo "*** Restauration des documents fin"
}

daily(){
	restore_base
	restore_documents_elips
}

weekly(){
	restore_appli
	daily
}


case $COMMAND in
base)
	$ELIPS_HOME/script/elips.sh stop tomcat
	restore_base
	$ELIPS_HOME/script/elips.sh start tomcat

;;
document)
	$ELIPS_HOME/script/elips.sh stop tomcat
	restore_documents_elips
	$ELIPS_HOME/script/elips.sh start tomcat
;;
appli)
	$ELIPS_HOME/script/elips.sh stop
	restore_appli
	$ELIPS_HOME/script/elips.sh start
;;
daily)
	$ELIPS_HOME/script/elips.sh stop tomcat
	daily
	$ELIPS_HOME/script/elips.sh start tomcat
;;
weekly)
	$ELIPS_HOME/script/elips.sh stop
	weekly
;;
*)
	echo "Script de restauration de la base de données ELIPS" 1>&2
	echo "	parametre 1: type de restauration {daily, weekly, base, appli, document}" 1>&2
	echo "	paramètre 2: date au format YYYY-mm-dd" 1>&2
	exit 1
;;
esac

