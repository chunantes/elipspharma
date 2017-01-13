#!/bin/bash
echo "++++++++++++++++++ Start++++++++++++++++"
export EASYDORE_HOME=/docker-entrypoint-initenv.d
echo " EASYDORE_HOME: " $EASYDORE_HOME
ls -alh $EASYDORE_HOME
export JAVA_OPTS="$JAVA_OPTS -DEASYDORE_HOME=$EASYDORE_HOME -Xms512m -Xmx1024m -XX:MaxPermSize=512m"
echo " JAVA_OPTS: " $JAVA_OPTS
cp $EASYDORE_HOME/*.war /usr/local/tomcat/webapps/

sleep 1m





