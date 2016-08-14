#!/bin/bash
# Starts the Scalatron Server in debug mode, you can remote debug at port 5005.
#
# Bot-Jars are expected in $SCALATRON_DEV_HOME/bots

export SCALATRON_DEV_HOME=$(dirname $0)/../..
echo "================================================="
echo "STARTING SCALATRON : $SCALATRON_DEV_HOME"
echo "================================================="

# bot-war-options: See https://github.com/scalatron/scalatron/blob/master/Scalatron/doc/markdown/Scalatron%20Server%20Setup.md
# e.g.: export BOT_OPTIONS="-maxfps 15 -frameX=max -frameY=max -snorgs 0 -walls 10 -zugars 0 -toxifera 0 -fluppets 0"
export BOT_OPTIONS="-maxfps 25"

java -Xmx1g -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005 \
    -cp $SCALATRON_DEV_HOME/lib/Scalatron.jar scalatron.main.Main -plugins $SCALATRON_DEV_HOME/bots \
    -browser no \
    -webui $SCALATRON_DEV_HOME/server/dummy -users $SCALATRON_DEV_HOME/server/dummy \
    $BOT_OPTIONS

# Note:
# user01_2.11-1.0.jar must   n o t   be in the classpath, because plugin-loading wouldn't work for bot-code-changes
# => Not: java -Xmx1g -cp $SCALATRON_DEV_HOME/lib/Scalatron.jar:$SCALATRON_DEV_HOME/target/scala-2.11/user01_2.11-1.0.jar scalatron.main.Main ...

