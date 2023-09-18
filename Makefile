main: 
	javac src/Main.java
	mv src/Main.class Main.class
	java Main

clean:
	rm *.class