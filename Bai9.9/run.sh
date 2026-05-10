#!/bin/bash
echo "Dang chay Unit Tests (mvn clean test)"
mvn clean test

java -jar target/Bai9.9-1.0-SNAPSHOT.jar

echo "Noi dung file log vat ly (logs/app.log)"
if [ -f logs/app.log ]; then
    cat logs/app.log
else
    echo "Loi: Khong tim thay file logs/app.log"
fi
