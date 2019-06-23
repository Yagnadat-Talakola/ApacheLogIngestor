#!/bin/bash

#normal traffic
./../log_entries_gen.sh 10.90.80.100 '25\/May\/2015:23:11:53' 200 1000
./../log_entries_gen.sh 10.90.80.101 '25\/May\/2015:23:11:54' 200 20
./../log_entries_gen.sh 10.90.80.102 '25\/May\/2015:23:11:54' 200 20
./../log_entries_gen.sh 10.90.80.103 '25\/May\/2015:23:11:55' 200 100
./../log_entries_gen.sh 10.90.80.104 '25\/May\/2015:23:11:56' 503 10