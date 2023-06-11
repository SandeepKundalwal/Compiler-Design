# CS502 Compiler Design - Assignment 3 : The One Where Help Is Sought (NEEDS MODIFICATIONS...)
### Objective: Generate intermediate code for the given TempoJava grammar in which you have to remove all the objects and classes and make all the methods children of the main Java class such that all methods are siblings.

### Detailed Specification
You are provided with two grammar files `TempoJava.jj` and `TempNoJava.jj`. Your task is to convert the
program that is based on `TempoJava.jj` to `TempNoJava.jj` such that output of both the files is the same
when compiled and run. 

**The conversion process is as follows:**
1. The `TempoJava.jj` grammar program has various classes with fields and methods. The program uses objects for accessing these fields and methods.
2. The `TempNoJava.jj` grammar allows only one class declaration (which is essential in Java) and therefore all the methods are part of this single class. 

You are provided with a `MemMgr` API which mimics the process of memory allocation and getterssetters for member fields as well as functions. You can use this `MemMgr` to allocate memory to a program,
and store and load functions and fields from correct indices as taught in the class.

  **API Specs:**
  - `alloc(int n)`: takes an integer input and allocates `n` bytes.
  - `store(Object x, int index, Object value)`: stores value at given index of object `x`.
  - `load(Object x, int index)`: returns value at given index from object `x`.
  - `callFunc(fnName, Object x, (Object param)*)`: calls given fnName from object x and passes it parameters (a varargs argument).

### NOTE:
The file which is coded to generate the intermediate code :
`grammars -> input -> visitors -> GJNoArguDepthFirst.java`
