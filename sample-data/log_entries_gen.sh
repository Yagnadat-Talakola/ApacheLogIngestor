#!/bin/bash

ipAddr=$1
timeStamp=$2
status=$3
repeat=$4

sample='IP_ADDR - - [TIMESTAMP +0000] "GET / HTTP/1.0" STATUS 3557 "-" "Mozilla/3.01 (compatible;)"'
echo "started --->"

sample=$(sed -e "s/\IP_ADDR/$ipAddr/g" <<< $sample)
sample=$(sed -e "s/\TIMESTAMP/$timeStamp/g" <<< $sample)
sample=$(sed -e "s/\STATUS/$status/g" <<< $sample)

echo $sample | awk '{for(i=0;i<'"$repeat"';i++)print}' >> logs

echo "successfully generated data!!!"
