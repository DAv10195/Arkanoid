compile:
	mkdir bin
	javac -d bin -cp biuoop-1.4.jar:. src/*/*.java
jar:
	mkdir uber-jar
	unzip biuoop-1.4.jar -d uber-jar
	rm -rf uber-jar/META-INF
	cp -r bin/* uber-jar/
	jar -cfm arkanoid.jar Manifest.mf -C uber-jar/ . -C resources .
	rm -rf uber-jar
run:
	java -cp biuoop-1.4.jar:bin:resources game/Ass6Game