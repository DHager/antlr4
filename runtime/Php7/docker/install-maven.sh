#!/bin/bash
set -e
#set -x
MVN_VERSION="3.5.0"
MVN_URL="ftp://apache.cs.utah.edu/apache.org/maven/maven-3/${MVN_VERSION}/binaries/apache-maven-${MVN_VERSION}-bin.tar.gz"


mkdir -p /usr/local/apache-maven/;
cd /usr/local/apache-maven/;
wget -q $MVN_URL -P /usr/local/apache-maven;
tar -xzf "apache-maven-${MVN_VERSION}-bin.tar.gz";

echo "export M2_HOME=/usr/local/apache-maven/apache-maven-${MVN_VERSION}
export M2=\$M2_HOME/bin
export MAVEN_OPTS=\"-Xms256m -Xmx512m\"
export PATH=\$M2:\$PATH" >> ~/.bashrc;

