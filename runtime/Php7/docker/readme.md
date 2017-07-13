The code in this folder exists for developers to set up a local testing 
environment for the PHP7 runtime. 

Recommended steps:

1. Start docker environment and terminal with: `docker/go.sh`
2. Create JAR with: `mvn package -DskipTests=true`
3. Set up aliases: `alias antlr4='java -Xmx500M -cp "/opt/project/tool/target/antlr4-4.7.1-SNAPSHOT-complete.jar:$CLASSPATH" org.antlr.v4.Tool'` (Update version# as appropriate)
4. Set up aliases: `alias grun='java -cp "/opt/project/tool/target/antlr4-4.7.1-SNAPSHOT-complete.jar:$CLASSPATH" org.antlr.v4.gui.TestRig'`
5. Create a simple grammar file (e.g. `Hello.g4`)
6. Generate .php files with `antlr4 -package Example -Dlanguage=Php7 Hello.g4`

Known issues:

1. Files created by processes inside the Docker environment may set the wrong ownership
2. Always have to repackage JAR on changes, need to tighten loop when editing STG files. 
