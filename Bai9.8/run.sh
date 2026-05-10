#!/bin/bash
echo "--- Dang dong goi ung dung (mvn clean package) ---"
mvn clean package

echo ""
echo "--- Dang chay file JAR doc lap (java -jar target/Bai9.8-1.0-SNAPSHOT.jar) ---"
java -jar target/Bai9.8-1.0-SNAPSHOT.jar
