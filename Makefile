JC = javac

.SUFFIXES: .java .class
.java.class:
	$(JC) $*.java
CLASSES = \
	Extensions.class \
	Exceptions.class \
	Expression.class \
	Instruction.class \
	Lexer.class \
	LookAhead1.class \
	Main.class \
	Token.class \


Main.class: Main.java $(CLASSES)

flex:
	@jflex lexer.java
