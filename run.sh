#!/bin/bash

# Script to test build and run tests for Bai9.3

echo "Starting Maven build and tests for Bai9.3..."

# Run Maven clean and package (which includes tests)
mvn -B clean package --file pom.xml

# Check if the build was successful
if [ $? -eq 0 ]; then
    echo "BUILD SUCCESSFUL!"
    echo "The artifact can be found in: target"
else
    echo "BUILD FAILED!"
    echo "Please check the logs above for errors."
    exit 1
fi
