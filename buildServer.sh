sudo javac -cp spigot-api-1.14.4-R0.1-20191014.100901-74.jar:commons-lang3-3.9/commons-lang3-3.9.jar:spigot-api-1.14.4-R0.1-20191014.100901-74.jar:json-simple-1.1.1.jar:commons-lang3-3.9/commons-lang3-3.9:commons-io-2.8.0/commons-io-2.8.0.jar situatedDialogue/src/main/java/me/skywang/situatedDialogue/situatedDialogue.java
echo "Main-Class: situatedDialogue" > Manifest.txt
jar cfm situatedDialogue/out/artifacts/situatedDialogue_jar/situatedDialogue.jar Manifest.txt situatedDialogue/src/main/java/me/skywang/situatedDialogue/situatedDialogue.class 
