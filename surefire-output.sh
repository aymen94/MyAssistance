#!/bin/bash
boldyellow="\e[33m\e[1m"
boldcyan="\e[36m\e[1m"
reset="\e[0m"

printf "\n\n$boldyellow""Test output\n\n"
for filename in target/surefire-reports/*.txt; do
	printf "\n$boldcyan""File: %s$reset\n" "$(basename "$filename")"
	cat "$filename"
done
