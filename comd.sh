#!/usr/bin/env bash

basePath="/Users/apple/Documents/AgentJava/intellProject/LearnASM"

baseName="com/test/memoryModel"
className="TestFinal"
targetPath="./target/classes"

sourcePath="${basePath}/ideal-weaver-api/target/ideal-weaver-api.jar:${basePath}/ideal-api/target/ideal-api.jar:${basePath}/agent-bridge/target/agent-bridge.jar"


#javac -classpath ${sourcePath} ${basePath}/LearnWeb/src/main/java/${baseName}/${className} -d ${targetPath}
echo $!

#javac ./src/main/java/${baseName}/Test2.java ./src/main/java/${baseName}/${className}.java -d ${targetPath}

javap -v -p ${targetPath}/${baseName}/${className}.class