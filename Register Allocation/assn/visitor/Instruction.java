package visitor;
import syntaxtree.*;

import java.util.HashSet;
import java.util.Set;

public class Instruction {
    public Node instructionNode;
    Set<String> inSet;
    Set<String> outSet;

    public Instruction(Node instruction) {
        instructionNode = instruction;
        inSet = new HashSet<>();
        outSet = new HashSet<>();
    }

    public Set<String> getInSet() {
        return inSet;
    }

    public Set<String> getOutSet() {
        return outSet;
    }
}
