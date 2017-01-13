#!/bin/sh
### ------------------------------------------------------------
# script de sauvegarde complet de la base de donnée "ELIPS"
#
# Le script s'appui sur les variables système suivantes:
#       - ELIPS_BACKUP: dépot des sauvegardes généré
#       - ELIPS_HOME: répertoire des script fournit avec l'application
#       - ELIPS_SERVER_POSTGRES: répertoire dinstallation de la base de donnée
### ------------------------------------------------------------
#. /etc/rc.d/init.d/functions

source /etc/environment

DATE=$(date +%Y-%m-%d)
# récupération des différents pwd
PWD_ELIPS_BDD=$(cat $ELIPS_HOME/script'/.elipsAccount')

# préparation des répertoires de destination
mkdir -p $ELIPS_BACKUP
BASE_DAILY=$ELIPS_BACKUP'/daily'
BASE_WEEKLY=$ELIPS_BACKUP'/weekly'
mkdir -p $BASE_DAILY
mkdir -p $BASE_WEEKLY


# Vérifier que l'utilisateur correspond au compte elips et non root ou autre.
#####TODO
# Verification des droits de l'utilisateur
if [[ $UID = 0 ]]; then
	echo "Ce script ne doit pas être executer en root ou privilège sudoers"
	exit 1
fi


purge_daily(){
	echo '*** Purge daily début'

	find $BASE_DAILY -mtime +15 -exec rm -f '{}' \;
	if [ $? -eq 0 ]; then
		success
	else
		failure
	fi
	echo "*** Purge daily fin"

}

backup_base_elips(){
	# récupération du pwd de base	
	cd /tmp
	export PGPASSWORD=$PWD_ELIPS_BDD
        echo '*** backup_base_elips debut'
    	$ELIPS_SERVER_POSTGRES/bin/pg_dump -U elips elips > $BASE_DAILY/${DATE}'_elips.sql'
	if [ $? -eq 0 ]; then
		success
	else
		failure
	fi	
        echo '*** backup_base_elips fin'


	cat $BASE_DAILY/${DATE}'_elips.sql' | gzip --best -> $BASE_DAILY/${DATE}'_elips.sql.gz'
	rm -f $BASE_DAILY/${DATE}'_elips.sql'
}


backup_document_elips(){
        echo '*** backup_document_elips debut'
	cd / 
	tar czf $BASE_DAILY/${DATE}_elips_data.tar.gz $ELIPS_DATA
	if [ $? -eq 0 ]; then
		success
	else
		failure
	fi
        echo '*** backup_data_ged fin'
}

purge_weekly(){
	echo '*** Purge weekly début'
	find $BASE_WEEKLY -mtime +30 -exec rm -f '{}' \;
	if [ $? -eq 0 ]; then
		success
	else
		failure
	fi
	echo "*** Purge weekly fin"

}

backup_appli(){
	echo "*** Backup appli début"
	cd / && tar czf $BASE_WEEKLY/${DATE}_elips.tar.gz $ELIPS_HOME/config $ELIPS_HOME/script $ELIPS_SERVER_TOMCAT/tomcat $ELIPS_SERVER_POSTGRES $ELIPS_DATA
	if [ $? -eq 0 ]; then
		success
	else
		failure
	fi
	echo "*** Backup appli fin"
}


daily(){
	$ELIPS_HOME/script/elips.sh stop tomcat
	backup_base_elips
	backup_document_elips
	$ELIPS_HOME/script/elips.sh stop postgres
	$ELIPS_HOME/script/elips.sh start
	purge_daily
}

weekly(){
	$ELIPS_HOME/script/elips.sh stop tomcat
	backup_base_elips
	backup_document_elips
	$ELIPS_HOME/script/elips.sh stop postgres
	backup_appli
	$ELIPS_HOME/script/elips.sh start
	purge_weekly
}

case $1 in
weekly)
	weekly
;;
daily)
	daily
;;
*)
	exit 1
;;
esac
exit 0

