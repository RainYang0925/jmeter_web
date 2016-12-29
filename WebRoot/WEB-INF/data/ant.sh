#!/bin/bash
echo "ant will be launched 10. 9. 8. 7..."

ant -buildfile /home/jmeter/script/build.xml -DIPvalue1=$1 -DIPvalue2=$2 -DIPvalue3=$3 -DIPvalue4=$4 -Dfilename=$5 -Dremote=$6
