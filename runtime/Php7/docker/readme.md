The code in this folder exists for developers to set up a local testing 
environment for the PHP7 runtime. 

## Recommended workflow

### Initial setup

1. Start docker environment and terminal with: `docker/go.sh`
2. Create everything once with: `cd /opt/project/; mvn package -DskipTests=true;`
3. Set up aliases: `alias antlr4='java -Xmx500M -cp "/opt/project/tool/target/antlr4-4.7.1-SNAPSHOT-complete.jar:$CLASSPATH" org.antlr.v4.Tool'` (Update version# as appropriate)
4. Set up aliases: `alias grun='java -cp "/opt/project/tool/target/antlr4-4.7.1-SNAPSHOT-complete.jar:$CLASSPATH" org.antlr.v4.gui.TestRig'`
5. Find a temporary directory and put in a grammar file to test, e.g. `Hello.g4`.

### Tweaking loop

1. Edit the StringTemplate file in `tool/resources/org/antlr/v4/tool/templates/codegen/Php7/Php7.stg`
2. Rebuild the tool: `pushd /opt/project/tool/; mvn package -DskipTests=true; popd;`
3. Run the changed tool against your grammar file: `antlr4 Hello.g4 -package Example -Dlanguage=Php7`
4. Inspect the generated `*.php` files and see what comes up as a syntax error.

Known issues:

1. Files created by processes inside the Docker environment may set the wrong ownership.
2. Rebuilding tool step isn't super fast. Might try editing the STG files *inside* the JAR if impatient, but remember to save your changes elsewhere. 
