#!/usr/bin/env bash
cd /home/application/
./wait-for-it.sh "event-store:2113" -- echo "event-store 2113 is up"