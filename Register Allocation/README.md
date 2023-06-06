# [CS502] Compiler Design - Assignment 5 : Spill the Beans
### Objective : Using Graph Coloring to Perform Register Allocation

### Detailed Specification
The job is to replace all the variable declarations using a suitable register or memory reference. Implementation of liveness analysis is was done in Assignment - 4. The same can be used to create an interference graph and we have to color it using the specified number of registers allowed for that program. The following files were provided as a part of this assignment:
```
assn/friendTJ.jj : The source grammar
validator/friendTJMem.jj : The target grammar
assn/* : Java project using friendTJ.jj that provides and API for liveness information
validator/* : Java project usinf friendTJMem.jj that validates the obtained output
testcase/* : Public test case input
testcase-output/* : Public test case output
```
### Liveness API
This api provides the result of liveness analysis as a hashmap `(See assn/Main.java)` that contains mapping from `Node` to `Set<String>`. The result contains the set of variables that are live at that node i.e. the `IN` set. All the **Statement Nodes** can be used to query the hashmap, namely, `PrintStatement`, `VarDeclaration`, `AssignmentStatement`, `ArrayAssignmentStatement`, `FieldAssignmentStatement`, `IfthentStatement`, `IfthenElsetStatement`, `WhileStatement` and `LivenessQueryStatement`. Additionally, for getting the liveness information at the **return statement** of a function, `MethodDeclaration` node must be used.

![returnnode](https://user-images.githubusercontent.com/61798659/235909144-005ae313-0433-45aa-b0f6-fd99917b104c.png)

*Note : The above image was directly taken from the assignment*

### Detailed Example
The below figure show the given input and the expected output for a given testcase(the changes are highlighted using different colors).

![example](https://user-images.githubusercontent.com/61798659/235908988-d0ecb533-6de0-406b-9769-a3e43f7d96a2.png)

*Note : The above image was directly taken from the assignment*

### Notes:
These are some general notes and assumptions that are made while doing the assignment:
- One cannot simply spill all the variables into the memory.
- The type of the register must be `Object`.
- For formal function paramenters **do not perform** register allocation.
- The `validator/Main.java` prints the number of spills in the resultant program if the parsing was successful.
- Have not handled ` fields or fields related operations`, `arrays or array related functions`, `this pointer` and `dead code`.
