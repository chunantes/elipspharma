#!/bin/bash
# Version : 1.0
# Author : Netapsys
#
#SOURCE="${BASH_SOURCE[0]}"
source /etc/environment

USER=$(whoami)
GRP=$(id -g -n)

SOURCE="`dirname \"$0\"`"

SOURCE="`( cd \"$SOURCE\" && pwd )`"
echo "Le script est executé depuis $SOURCE"
echo "Avec l'utilisateur $USER et le groupe $GRP"

# archives
jdk=jdk-6u41-linux-x64.bin
tomcat=apache-tomcat-6.0.36.tar.gz
postgres=postgresql-8.4.16.tar.gz
jsvc=commons-daemon-native.tar.gz
# installeurs decompressésudo r
_tomcat=apache-tomcat-6.0.36
_java=jdk1.6.0_41
_postgres=postgresql-8.4.16
_commons_daemon=commons-daemon-1.0.10-native-src



default=/tmp/elips


parametres="{--all|--postgres-server|--postgres-config|--java|--tomcat-server|--tomcat-config|--deploy|--help}"
# ====================================================================
# initialisation
init(){
    # Verification des droits de l'utilisateur
#    if [[ $UID != 0 ]]; then
#        echo "Ce script requiere le mode sudo:"
#        echo "sudo $0 $* $parametres"
#        exit 1
#    fi
    checkGcc
    init_home
    init_config
    init_backup
    init_log
    return 0
}

init_home(){
    # Si la variable d'environnement $ELIPS_HOME n'est pas définie
    if [ -z "$ELIPS_HOME" ]
    then
        ELIPS_HOME=$default
    fi
    # Définition du repertoire racine
    echo -ne "répertoire racine [\033[0;32m$ELIPS_HOME\033[0m]:"
    read tmp
    if [ ! -z "$tmp" ]
    then
        ELIPS_HOME=$tmp
    fi

    sudo bash -c "sed '/ELIPS_HOME/d' /etc/environment >> /etc/environment_tmp"
    sudo mv /etc/environment_tmp /etc/environment
    sudo bash -c "echo 'export ELIPS_HOME=$ELIPS_HOME' >> /etc/environment"
    # création du repertoire racine
    if [ ! -d $ELIPS_HOME ]
    then
        sudo mkdir -p $ELIPS_HOME
	sudo chown -R $USER:$GRP $ELIPS_HOME
	mkdir -p $ELIPS_HOME/script
        echo "le repertoire $ELIPS_HOME a été créé"
    fi
	mkdir -p $ELIPS_HOME/script
    cp $SOURCE/script/elips.sh $ELIPS_HOME/script/elips.sh
    cp $SOURCE/script/backup.sh $ELIPS_HOME/script/backup.sh
    cp $SOURCE/script/restore.sh $ELIPS_HOME/script/restore.sh
    chmod 744 $ELIPS_HOME/script/elips.sh
    return 0
}

init_backup(){
    if [ -z "$ELIPS_BACKUP" ]
    then
        ELIPS_BACKUP=$ELIPS_HOME/backup
    fi
    # Définition du repertoire racine
    echo -ne "répertoire de backup [\033[0;32m$ELIPS_BACKUP\033[0m]:"
    read tmp
    if [ ! -z "$tmp" ]
    then
        ELIPS_BACKUP=$tmp
    fi
    sudo bash -c "sed '/ELIPS_BACKUP/d' /etc/environment >> /etc/environment_tmp"
    sudo mv /etc/environment_tmp /etc/environment
    sudo bash -c "echo 'export ELIPS_BACKUP=$ELIPS_BACKUP' >> /etc/environment"
    # création du repertoire racine
    if [ ! -d $ELIPS_BACKUP ]
    then
        mkdir -p $ELIPS_BACKUP
        echo "le repertoire $ELIPS_BACKUP a été créé"
    fi
    return 0
}

init_log(){
    if [ -z "$ELIPS_LOG" ]
    then
        ELIPS_LOG=$ELIPS_HOME/log
    fi
    # Définition du repertoire racine
    echo -ne "répertoire de log [\033[0;32m$ELIPS_LOG\033[0m]:"
    read tmp
    if [ ! -z "$tmp" ]
    then
        ELIPS_LOG=$tmp
    fi
    sudo bash -c "sed '/ELIPS_LOG/d' /etc/environment >> /etc/environment_tmp"
    sudo mv /etc/environment_tmp /etc/environment
    sudo bash -c "echo 'export ELIPS_LOG=$ELIPS_LOG' >> /etc/environment"
    # création du repertoire racine
    if [ ! -d $ELIPS_LOG ]
    then
        mkdir -p $ELIPS_LOG
	chmod 777 $ELIPS_LOG
        echo "le repertoire $ELIPS_LOG a été créé"
    fi
    return 0
}

init_config(){
    if [ -z "$ELIPS_CONF" ]
    then
        ELIPS_CONF=$ELIPS_HOME/config
    fi
    # Définition du repertoire racine
    echo -ne "répertoire conf [\033[0;32m$ELIPS_CONF\033[0m]:"
    read tmp
    if [ ! -z "$tmp" ]
    then
        ELIPS_CONF=$tmp
    fi
    sudo bash -c "sed '/ELIPS_CONF/d' /etc/environment >> /etc/environment_tmp"
    sudo mv /etc/environment_tmp /etc/environment
    sudo bash -c "echo 'export ELIPS_CONF=$ELIPS_CONF' >> /etc/environment"
    # création du repertoire racine
    if [ ! -d $ELIPS_CONF ]
    then
        mkdir -p $ELIPS_CONF
        echo "le repertoire $ELIPS_CONF a été créé"
    fi
    return 0
}

checkGcc(){
    sudo which gcc &>/dev/null
    if [ ! "$?" -eq "0" ]
    then
        echo "Aucun compilateur gcc n'a été trouvé merci d'en installer un pour pouvoir poursuivre !"
        exit 1
    fi
}

# ====================================================================
# installation de java
java(){
    echo "-----------------------------------------------------"
    echo "installation de java"
    echo "-----------------------------------------------------"
    # Si la variable d'environnement $ELIPS_JAVA n'est pas définie
    if [ -z "$ELIPS_JAVA" ]
    then
        ELIPS_JAVA=$ELIPS_HOME/java
    fi
    # Définition du repertoire de java
    echo -ne "répertoire d'installation de java [\033[0;32m$ELIPS_JAVA\033[0m]:"
    read tmp
    if [ ! -z "$tmp" ]
    then
        ELIPS_JAVA=$tmp
    fi
    sudo bash -c "sed '/ELIPS_JAVA/d' /etc/environment >> /etc/environment_tmp"
    sudo mv /etc/environment_tmp /etc/environment
    sudo bash -c "echo 'export ELIPS_JAVA=$ELIPS_JAVA' >> /etc/environment"
    # création du repertoire racine
    if [ ! -d $ELIPS_JAVA ]
    then
        mkdir -p $ELIPS_JAVA
        echo "le repertoire $ELIPS_JAVA a été créé"
    elif [ ! -z $(ls $ELIPS_JAVA) ]
    then
        echo "Le répertoire $ELIPS_JAVA n'est pas vide !"
        echo -en "Voulez vous le vider et reinstaller java [\033[0;32mo/N\033[0m]?"

        read reponse
        case "$reponse" in
            [oO])
                rm -Rf $ELIPS_JAVA/*
                echo "suppression du repertoire $ELIPS_JAVA"
                ;;
            *)
                return
                ;;
        esac
    fi

    cp $SOURCE/installer/$jdk $ELIPS_JAVA
    chmod 777 $ELIPS_JAVA/$jdk
    cd $ELIPS_JAVA
    ./$jdk
    rm $jdk
    ln -s $ELIPS_JAVA/$_java $ELIPS_JAVA/java
    # JAVA_HOME
    sudo bash -c "sed '/JAVA_HOME/d' /etc/environment >> /etc/environment_tmp"
    sudo mv /etc/environment_tmp /etc/environment
    sudo bash -c "echo 'export JAVA_HOME=$ELIPS_JAVA/java' >> /etc/environment"

    sudo bash -c "sed '/JRE_HOME/d' /etc/environment >> /etc/environment_tmp"
    sudo mv /etc/environment_tmp /etc/environment
    sudo bash -c "echo 'export JRE_HOME=$ELIPS_JAVA/java/jre' >> /etc/environment"
    cd $SOURCE

    return 0
}

# ====================================================================
# installation de tomcat
tomcat_server(){
    echo "-----------------------------------------------------"
    echo "installation de tomcat"
    echo "-----------------------------------------------------"

    if [ -z "$ELIPS_SERVER_TOMCAT" ]
    then
        ELIPS_SERVER_TOMCAT=$ELIPS_HOME/tomcat
    fi
    # Définition du repertoire de tomcat
    echo -ne "répertoire d'installation de tomcat [\033[0;32m$ELIPS_SERVER_TOMCAT\033[0m]:"
    read tmp
    if [ ! -z "$tmp" ]
    then
        ELIPS_SERVER_TOMCAT=$tmp
    fi
    sudo bash -c "sed '/ELIPS_SERVER_TOMCAT/d' /etc/environment >> /etc/environment_tmp"
    sudo mv /etc/environment_tmp /etc/environment
    sudo bash -c "echo 'export ELIPS_SERVER_TOMCAT=$ELIPS_SERVER_TOMCAT' >> /etc/environment"

    # création du repertoire tomcat
    if [ ! -d $ELIPS_SERVER_TOMCAT ]
    then
        mkdir -p $ELIPS_SERVER_TOMCAT
        echo "le repertoire $ELIPS_SERVER_TOMCAT a été créé"
    elif [ ! -z $(ls $ELIPS_SERVER_TOMCAT) ]
    then
        echo "Le répertoire $ELIPS_SERVER_TOMCAT n'est pas vide !"
        echo -en "Voulez vous le vider et reinstaller tomcat [\033[0;32mo/N\033[0m]?"

        read reponse
        case "$reponse" in
            [oO])
                rm -Rf $ELIPS_SERVER_TOMCAT/*
                echo "suppression du repertoire $ELIPS_SERVER_TOMCAT "
                ;;
            *)
                return
                ;;
        esac
    fi

    cp $SOURCE/installer/$tomcat $ELIPS_SERVER_TOMCAT
    cd $ELIPS_SERVER_TOMCAT
    tar -xf $tomcat
    rm -Rf $tomcat
    ln -s $ELIPS_SERVER_TOMCAT/$_tomcat $ELIPS_SERVER_TOMCAT/tomcat
    cd tomcat/bin
    tar xzf $jsvc
    cd $_commons_daemon/unix
    ./configure --with-java=$ELIPS_JAVA/java
    make
    cp jsvc ../..
    cd $SOURCE

    return 0
}

# ====================================================================
# configuration de tomcat
tomcat_config(){
    echo "configuration de tomcat"

    sudo rm -Rf $ELIPS_SERVER_TOMCAT/tomcat/conf/server.xml
    sudo rm -Rf $ELIPS_SERVER_TOMCAT/tomcat/conf/tomcat-users.xml
    sudo cp $SOURCE/config/server.xml $ELIPS_CONF/server.xml
    sudo ln -sf $ELIPS_CONF/server.xml $ELIPS_SERVER_TOMCAT/tomcat/conf/server.xml
    sudo cp $SOURCE/config/tomcat-users.xml $ELIPS_CONF/tomcat-users.xml
    sudo ln -sf $ELIPS_CONF/tomcat-users.xml $ELIPS_SERVER_TOMCAT/tomcat/conf/tomcat-users.xml

    sudo mv $ELIPS_SERVER_TOMCAT/tomcat/logs $ELIPS_LOG/tomcat
    sudo ln -s $ELIPS_LOG/tomcat $ELIPS_SERVER_TOMCAT/tomcat/logs
    sudo chmod -R +X $ELIPS_SERVER_TOMCAT/tomcat/bin/
    return 0
}

# ====================================================================
# installation de postgres
postgres_server(){
    echo "-----------------------------------------------------"
    echo "installation de Postgresql"
    echo "-----------------------------------------------------"

    if [ -z "$ELIPS_SERVER_POSTGRES" ]
    then
        ELIPS_SERVER_POSTGRES=$ELIPS_HOME/postgresql
    fi
    # Définition du repertoire de postgres
    echo -ne "répertoire d'installation de postgres [\033[0;32m$ELIPS_SERVER_POSTGRES\033[0m]:"
    read tmp
    if [ ! -z "$tmp" ]
    then
        ELIPS_SERVER_POSTGRES=$tmp
    fi
    sudo bash -c "sed '/ELIPS_SERVER_POSTGRES/d' /etc/environment >> /etc/environment_tmp"
    sudo mv /etc/environment_tmp /etc/environment
    sudo bash -c "echo 'export ELIPS_SERVER_POSTGRES=$ELIPS_SERVER_POSTGRES' >> /etc/environment"

    # création du repertoire postgres
    if [ ! -d $ELIPS_SERVER_POSTGRES ]
    then
        mkdir -p $ELIPS_SERVER_POSTGRES
        echo "le repertoire $ELIPS_SERVER_POSTGRES a été créé"
    elif [ ! -z $(ls $ELIPS_SERVER_POSTGRES) ]
    then
        echo "Le répertoire $ELIPS_SERVER_POSTGRES n'est pas vide !"
        echo -en "Voulez vous le vider et reinstaller postgres [\033[0;32mo/N\033[0m]?"

        read reponse
        case "$reponse" in
            [oO])
                rm $ELIPS_SERVER_POSTGRES/*
                echo "suppression du repertoire $ELIPS_SERVER_POSTGRES"
                ;;
            *)
                return
                ;;
        esac
    fi

    cp $SOURCE/installer/$postgres $ELIPS_SERVER_POSTGRES
    cd $ELIPS_SERVER_POSTGRES
    tar -xf $postgres
    rm -Rf $postgres
    cd $_postgres
    ./configure --prefix=$ELIPS_SERVER_POSTGRES --without-readline --without-zlib
    make
    make install


    cd $SOURCE
    rm -Rf $ELIPS_SERVER_POSTGRES/postgresql-8.4.16
    return 0
}

# ====================================================================
# configuration de postgres
postgres_config(){

    echo "-----------------------------------------------------"
    echo "configuration de Postgres"
    echo "-----------------------------------------------------"
    # conf de base pg

    if [ -z "$ELIPS_DATA" ]
    then
        ELIPS_DATA=$ELIPS_HOME/data
    fi
    echo -ne "répertoire de stocage des données [\033[0;32m$ELIPS_DATA\033[0m]:"
    read tmp
    if [ ! -z "$tmp" ]
    then
        ELIPS_DATA=$tmp
    fi
    sudo bash -c "sed '/ELIPS_DATA/d' /etc/environment >> /etc/environment_tmp"
    sudo mv /etc/environment_tmp /etc/environment
    sudo bash -c "echo 'export ELIPS_DATA=$ELIPS_DATA' >> /etc/environment"

    # création du repertoire data
    if [ ! -d $ELIPS_DATA/psql ]
    then
        mkdir -p $ELIPS_DATA/psql
        echo "le repertoire $ELIPS_DATA/psql a été créé"
    elif [ ! -z $(ls $ELIPS_DATA/psql) ]
    then
        echo "Le répertoire $ELIPS_DATA/psql n'est pas vide !"
        echo -en "Voulez vous le vider [\033[0;32mo/N\033[0m]?"

        read reponse
        case "$reponse" in
            [oO])
                rm $ELIPS_DATA/psql/*
                echo "suppression du repertoire $ELIPS_DATA/psql"
                ;;
        esac
    fi


    sudo chown $USER:$GRP $ELIPS_DATA/psql
    cd $ELIPS_DATA/psql 
    $ELIPS_SERVER_POSTGRES/bin/initdb -D $ELIPS_DATA/psql -E UTF8 --locale=fr_FR.UTF-8

    rm -Rf $ELIPS_DATA/psql/pg_hba.conf
    rm -Rf $ELIPS_DATA/psql/postgresql.conf
    cp $SOURCE/config/pg_hba.conf $ELIPS_CONF/pg_hba.conf
    cp $SOURCE/config/postgresql.conf $ELIPS_CONF/postgresql.conf
    ln -sf $ELIPS_CONF/pg_hba.conf $ELIPS_DATA/psql/pg_hba.conf
    ln -sf $ELIPS_CONF/postgresql.conf $ELIPS_DATA/psql/postgresql.conf

    echo "Démarrage du serveur PostgreSQL"
    sudo chmod 700 -R $ELIPS_DATA/psql
    $ELIPS_HOME/script/elips.sh start postgres

    sleep 15
    echo "Initialisation du compte utilisateur elips "
    TPWD='netapsys'
    $ELIPS_SERVER_POSTGRES/bin/createuser -E -S -D -R elips;
    $ELIPS_SERVER_POSTGRES/bin/psql -d postgres -c "ALTER USER elips WITH ENCRYPTED PASSWORD '$TPWD';"
    mkdir -p $ELIPS_HOME/script
    echo $TPWD > $ELIPS_HOME/script/.elipsAccount

    echo "Création de la base de données"
    $ELIPS_SERVER_POSTGRES/bin/psql -d postgres -c "CREATE DATABASE elips owner elips;"

    return 0
}


# ====================================================================
# Déployement de Elypse
deploy(){
    echo "-----------------------------------------------------"
    echo "Déployement de Elypse"
    echo "-----------------------------------------------------"
    if [ -z "$ELIPS_SERVER_TOMCAT" ]
    then
        # Définition du repertoire de tomcat
        echo -ne "La variable ELIPS_SERVER_TOMCAT ne semble pas etre définie veuillez specifier l'emplacement de tomcat:"
        read tmp
        if [ ! -z "$tmp" ]	

        then
            ELIPS_SERVER_TOMCAT=$tmp
        fi
    else
        ELIPS_SERVER_TOMCAT=$ELIPS_SERVER_TOMCAT/tomcat
        echo -ne "le répertoire d'installation de Tomcat est $ELIPS_SERVER_TOMCAT [\033[0;32mO/n\033[0m]:"
        read tmp
        case "$tmp" in
            [nN])
                echo -ne "Emplacement du repertoire tomcat:"
                read tmp
                if [ ! -z "$tmp" ]
                then
                    ELIPS_SERVER_TOMCAT=$tmp
                fi
                ;;
            *)
                ELIPS_SERVER_TOMCAT=$ELIPS_SERVER_TOMCAT
                ;;
        esac

    fi

    if [[ ( -d "$ELIPS_SERVER_TOMCAT" ) && ! ( -z $(ls "$ELIPS_SERVER_TOMCAT") ) ]]
    then
        $ELIPS_HOME/script/elips.sh stop tomcat
        rm -Rf $ELIPS_SERVER_TOMCAT/webapps/elips/
        rm -Rf $ELIPS_SERVER_TOMCAT/webapps/elips-*.war
	mkdir -p $ELIPS_SERVER_TOMCAT/webapps
        cp $SOURCE/elips-*.war $ELIPS_SERVER_TOMCAT/webapps/elips.war
        cp $SOURCE/config/project.properties $ELIPS_CONF/project.properties.template
        cp $SOURCE/config/hibernate.properties $ELIPS_CONF/hibernate.properties.template
        cp $SOURCE/config/log4j.xml $ELIPS_CONF/log4j.xml.template
        mkdir -p $ELIPS_DATA/documents/
        mkdir -p $ELIPS_DATA/files/
        cp $SOURCE/files/* $ELIPS_DATA/files/
#        $ELIPS_HOME/script/elips.sh start tomcat
        cp $SOURCE/elips-*.war $ELIPS_HOME/
    else
        echo -e "\033[0;31mle répertoire d'installation de Tomcat ($ELIPS_SERVER_TOMCAT/tomcat) semble ne pas exister ou etre vide.\033[0m"
        echo -e "Vous pouvez installer tomcat avec :"
        echo -e "$0 $* --tomcat-server"
        exit 1
    fi
    return 0
}

# ====================================================================
# execution de toutes les fonctions
all(){
    postgres_server
    postgres_config
    java
    tomcat_server
    tomcat_config
    deploy
    exit 0
}

# ====================================================================
# Aide
help_install(){
    echo "--all                       Tout"
    echo "-pgs | --postgres-server    Installer le serveur Postgresql"
    echo "-pgc | --postgres-config    Configurer le serveur Postgresql"
    echo "-j   | --java               Installer la machine virtuelle Java (JVM)"
    echo "-ts  | --tomcat-server      Installer le serveur Tomcat"
    echo "-tc  | --tomcat-config      Configurer le serveur Tomcat"
    echo "-d   | --deploy             Deployer Elypse"
    echo "-h   | --help               Afficher l'aide"
    exit 0
}

# ====================================================================
# Dispatch
if [ $# = 0 ]
then
    echo "Usage : $0 $parametres"
    exit 1
else
    if [[ $@ == *"--help"* ]]||[[ $@ == *"-h"* ]] # si help
    then
        help_install
    elif [[ $@ == *"--all"* ]] # si all
    then
        init
        all
    else
        init
        for param in "$@"
        do
            case "$param" in
                --postgres-server|-pgs)
                    checkGcc
                    postgres_server
                    ;;
                --postgres-config|-pgc)
                    postgres_config
                    ;;
                --java|-j)
                    java
                    ;;
                --tomcat-server|-ts)
                    tomcat_server
                    ;;
                --tomcat-config|-tc)
                    tomcat_config
                    ;;
                --deploy|-d)
                    deploy
                    ;;
            esac
        done
    fi
fi

exit 0