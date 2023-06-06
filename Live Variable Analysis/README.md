# CS502 Compiler Design - Assignment 4 : Are You Alive?
### Objective: Use javacc and jtb to write an Intraprocedural IDFA that performs live-variable analysis.

### Detailed Specification
You are provided with a grammar file `FriendsTJ.jj`. The task is to first identify the live variables (local
declarations and parameters) at each point of the program, and print them at the occurrences of the `/*
PRINTLIVEVARIABLES */` query, in lexicographically sorted order. In order to perform the analysis you
may use:
- **CFG generator:** A CFG Generator that can be used for various functionalities. 
  - `CommonUtils.getMethodCFG(declStmt)` - API to get the startnode of the CGF for the method with `MethodDeclaration`      statement "declStmt".
- **Predecessor/Successor Chains:** Once you have a node in the CFG use the APIs:
  - `node.getSuccessorNodes()` - to get the Successors of a node (return them as a list).
  - `node.getPredecessorNodes()` - to get the Predecessors of a node (returns them as a list).
- **Visualization Tool:** In-order the visualize the CFG for a method, you may use the below APIs.
  - `CommonUtils.createDotFile("<filename>", cfgStartNode)` - to generate a graphviz dot file.
  - `dot -Tpng <filename>.dot -o <filename>.png` - Use this command to generate a png file of the CFG.

### Public Testcase

![image](https://github.com/SKundawal/Live-Variable-Analysis/assets/61798659/6aefe8ba-b8f8-498b-ad4d-77c2e2bbfbac)

### Notes:
There are some general notes and assumptions that you can make regarding the test cases.
- Main method will not contain any livenessqueries hence you need not handle the same.
- There will be no invalid function calls i.e. calls to undeclared functions.
- While using the CFG API, ensure to check the type of the node. There are 4 types of nodes in a CFG.
  - `STARTNODE/ENDNODE` - These are dummy nodes to help you identify the start and end
points in the CFG. Note that they do not contain any statement info. Beware of NPE when you
access node (*getNode()*) info of such nodes.
  - `INTERMEDIATENODE` - Such nodes contain actual statement info from the grammar. Process
these nodes to perform the IDFA.
  - `RETURNNODE` - As per the grammar we have an explicit return statement inside each method.
This CFGNode handles the identifers inside return statements.

Use the API: `getType()` in CFGNode class to find the type of each node.


