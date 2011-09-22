


BINDIR = bin/
SRCDIR = src/
P1PACKAGE = edu/jhu/crankshaw/cs/pp420/hw1/p1/
P2PACKAGE = edu/jhu/crankshaw/cs/pp420/hw1/p2/

PACKAGES = $(P1PACKAGE) $(P2PACKAGE)
COLON = :
REMOVE = rm -rf

JFLAGS = -g -cp .:$(SRCDIR):$(BINDIR) -d $(BINDIR)
JC = javac
MKDIR = mkdir -p
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $<

P1FILES = CoinFlipMain.java FlipRunner.java CoinFlipMainStartup.java
P1PATH = $(SRCDIR)$(P1PACKAGE)
P1SOURCES := $(addprefix $(P1PATH), $(P1FILES))

P2FILES = BruteForceDES.java DecryptionRunner.java SealedDES.java
P2PATH = $(SRCDIR)$(P2PACKAGE)
P2SOURCES := $(addprefix $(P2PATH), $(P2FILES))


all: directory part1 part2

part1: directory $(P1SOURCES:.java=.class)

part2: directory $(P2SOURCES:.java=.class)

print:;@echo $(P1SOURCES)

directory:
	$(MKDIR) $(BINDIR)

default: all

clean:
	$(REMOVE) $(BINDIR)