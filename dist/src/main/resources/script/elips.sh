#! /bin/sh
# Gestion des arrêt / relance de l'application ELIPS'PHARMA
###
# chkconfig: 235 98 55
# description: Gestion du service elips
###
if [ -f /etc/init.d/functions ]; then
. /etc/rc.d/init.d/functions
else
. /lib/lsb/init-functions
fi

ENV_FILE=/etc/environment
. $ENV_FILE

cd $ELIPS_HOME


echo_success() {
  [ "$BOOTUP" = "color" ] && $MOVE_TO_COL
  echo -n "["
  [ "$BOOTUP" = "color" ] && $SETCOLOR_SUCCESS
  echo -n $"  OK  "
  [ "$BOOTUP" = "color" ] && $SETCOLOR_NORMAL
  echo -n "]"
  echo -ne "\r"
  return 0
}

echo_failure() {
  [ "$BOOTUP" = "color" ] && $MOVE_TO_COL
  echo -n "["
  [ "$BOOTUP" = "color" ] && $SETCOLOR_FAILURE
  echo -n $"FAILED"
  [ "$BOOTUP" = "color" ] && $SETCOLOR_NORMAL
  echo -n "]"
  echo -ne "\r"
  return 1
}

echo_passed() {
  [ "$BOOTUP" = "color" ] && $MOVE_TO_COL
  echo -n "["
  [ "$BOOTUP" = "color" ] && $SETCOLOR_WARNING
  echo -n $"PASSED"
  [ "$BOOTUP" = "color" ] && $SETCOLOR_NORMAL
  echo -n "]"
  echo -ne "\r"
  return 1
}

echo_warning() {
  [ "$BOOTUP" = "color" ] && $MOVE_TO_COL
  echo -n "["
  [ "$BOOTUP" = "color" ] && $SETCOLOR_WARNING
  echo -n $"WARNING"
  [ "$BOOTUP" = "color" ] && $SETCOLOR_NORMAL
  echo -n "]"
  echo -ne "\r"
  return 1
}

# Log that something succeeded
success() {
  [ "$BOOTUP" != "verbose" -a -z "${LSB:-}" ] && echo_success
  return 0
}

# Log that something failed
failure() {
  local rc=$?
  [ "$BOOTUP" != "verbose" -a -z "${LSB:-}" ] && echo_failure
  [ -x /bin/plymouth ] && /bin/plymouth --details
  return $rc
}

# Log that something passed, but may have had errors. Useful for fsck
passed() {
  local rc=$?
  [ "$BOOTUP" != "verbose" -a -z "${LSB:-}" ] && echo_passed
  return $rc
}

# Log a warning
warning() {
  local rc=$?
  [ "$BOOTUP" != "verbose" -a -z "${LSB:-}" ] && echo_warning
  return $rc
}



##------------------------
# Gestion de postgres
##------------------------
POSTGRES_PID=$ELIPS_DATA/psql/postmaster.pid


start_postgres() {
	pid=`cat $POSTGRES_PID 2>/dev/null`
	if [ -n "$pid" ]
	then 
		warning		
		echo "Postgres est déjà démarré (pid: $pid)"
	else
		$ELIPS_SERVER_POSTGRES/bin/pg_ctl -D $ELIPS_DATA/psql start
		if [ $? -eq 0 ]
		then 
			passed
		else
			echo_failure
		fi
		echo "Démarrage de postgres ..."
	fi
}

stop_postgres() {
	pid=`cat $POSTGRES_PID 2>/dev/null`
	if [ -n "$pid" ]
	then 
	
		$ELIPS_SERVER_POSTGRES/bin/pg_ctl -D $ELIPS_DATA/psql stop
		if [ $? -eq 0 ]
		then 
			passed
		else
			echo_failure
		fi
		echo "Arrêt de postgres ..."
	else
		warning
		echo "Postgres n'est pas démarré"
	fi
}

status_postgres() {
	$ELIPS_SERVER_POSTGRES/bin/pg_ctl -D $ELIPS_DATA/psql status
}

##------------------------
# Gestion du tomcat
##------------------------

CATALINA_HOME=$ELIPS_SERVER_TOMCAT/tomcat
DAEMON_HOME=$CATALINA_HOME/bin
TOMCAT_PID=$ELIPS_SERVER_TOMCAT/tomcat/jsvc.pid
CATALINA_BASE=$CATALINA_HOME


#
# Start Tomcat
#
start_tomcat(){
	pid=$(cat $TOMCAT_PID 2>/dev/null)

	JAVA_HOME=$ELIPS_JAVA/java
	TOMCAT_USER=$(whoami)
	# for multi instances adapt those lines.
	TMP_DIR=/var/tmp
	# CATALINA_OPTS="-Djava.library.path=/home/jfclere/jakarta-tomcat-connectors/jni/native/.libs"
	CATALINA_OPTS="-Xms512m -Xmx1280m -XX:MaxPermSize=1024m -DELIPS_HOME=$ELIPS_HOME -DELIPS_LOG=$ELIPS_LOG -Djava.awt.headless=true"
	CLASSPATH=$JAVA_HOME/lib/tools.jar:$CATALINA_HOME/bin/commons-daemon.jar:$CATALINA_HOME/bin/bootstrap.jar

	if [ -n "$pid" ]
	then
		warning
		echo "Elypse tomcat déjà démarré (pid: $pid)"
	else	

		$DAEMON_HOME/jsvc -user $TOMCAT_USER -home $JAVA_HOME -Dcatalina.home=$CATALINA_HOME -Dcatalina.base=$CATALINA_BASE -Djava.io.tmpdir=$TMP_DIR -wait 10 -pidfile $TOMCAT_PID -outfile $ELIPS_LOG/tomcat/catalina.out -errfile '&1' $CATALINA_OPTS -cp $CLASSPATH org.apache.catalina.startup.Bootstrap
		#
		# To get a verbose JVM
		#-verbose \
        		# To get a debug of jsvc.
		#-debug \
		if [ $? -eq 1 ]
		then 
			passed
		else
			echo_failure
		fi
		echo "Démarrage du tomcat elips ..."	
	fi
}

#
# Stop Tomcat
#
stop_tomcat(){
	pid=$(cat $TOMCAT_PID 2>/dev/null)

	if [ -n "$pid" ]
	then
		$DAEMON_HOME/jsvc -stop -pidfile $TOMCAT_PID org.apache.catalina.startup.Bootstrap
		if [ $? -eq 0 ]
		then 
			passed
		else
			echo_failure
		fi
		echo "Arrêt de Tomcat Elypse ..."
		rm -f $TOMCAT_PID
	else
		warning
		echo "Tomcat elips n'est pas démarré"
	fi	
}


if test $# -ge 2
then
	APP=$2
	if test $# -ge 3
	then
	param=$3
	else
	param="all"
	fi
else
	APP='all'
        param="all"
fi

case "$1" in
        start)
                echo  "Starting ElipsPharma"
                if [ "postgres" = $APP -o "all" = $APP ]; then start_postgres; fi
                if [ "tomcat" = $APP -o "all" = $APP ]; then start_tomcat; fi
                echo "."
                ;;
        stop)
                echo "Stopping ElipsPharma"
                if [ "tomcat" = $APP -o "all" = $APP ]; then stop_tomcat; fi
                if [ "postgres" = $APP -o "all" = $APP ]; then stop_postgres; fi
                echo "."
                ;;
        status)
                echo "Status ElipsPharma"
                if [ "tomcat" = $APP -o "all" = $APP ]; then stop_tomcat; fi
		if [ "postgres" = $APP -o "all" = $APP ]; then stop_postgres; fi
                echo "."
                ;;
        *)
                echo "Usage: //sbin/service elips {start|stop|status} ?{all|postgres|tomcat}"
                exit 1
        esac

exit 0
