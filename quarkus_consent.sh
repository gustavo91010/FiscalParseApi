#!/bin/bash
./gradlew quarkusDev &
PID=$!
sleep 15
echo "y" > /proc/$PID/fd/0
wait $PID
