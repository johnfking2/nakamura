#!/bin/bash
TOOLSDIR=`dirname $0`
. ${TOOLSDIR}/version
java -Xmx3000m -XX:MaxPermSize=256m -server -jar app/target/org.sakaiproject.nakamura.app-${K2VERSION}.jar -f -$*

