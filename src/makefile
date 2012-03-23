#
# A simple makefile for compiling three java classes
#

# define a makefile variable for the java compiler
#
JCC = javac

# define a makefile variable for compilation flags
# the -g flag compiles with debugging information
#
JFLAGS = -g

# typing 'make' will invoke the first target entry in the makefile 
# (the default one in this case)
#
default: GlobalDataStore.class Scheme.class NormalScheme.class DiscountScheme.class GetOneFreeScheme.class NegativeValueException.class UnitException.class Grocery.class


# this target entry builds the Average class
# the Average.class file is dependent on the Average.java file
# and the rule associated with this entry gives the command to create it
#
GlobalDataStore.class: GlobalDataStore.java
		$(JCC) $(JFLAGS) GlobalDataStore.java

Scheme.class: Scheme.java
		$(JCC) $(JFLAGS) Scheme.java

GlobalDataStore.class: GlobalDataStore.java
		$(JCC) $(JFLAGS) GlobalDataStore.java

NormalScheme.class: NormalScheme.java
		$(JCC) $(JFLAGS) NormalScheme.java

DiscountScheme.class: DiscountScheme.java
		$(JCC) $(JFLAGS) DiscountScheme.java

GetOneFreeScheme.class: GetOneFreeScheme.java
		$(JCC) $(JFLAGS) GetOneFreeScheme.java

NegativeValueException.class: NegativeValueException.java
		$(JCC) $(JFLAGS) NegativeValueException.java

UnitException.class: UnitException.java
		$(JCC) $(JFLAGS) UnitException.java

Grocery.class: Grocery.java
		$(JCC) $(JFLAGS) Grocery.java

# To start over from scratch, type 'make clean'.  
# Removes all .class files, so that the next make rebuilds them
#
clean: 
		$(RM) *.class
