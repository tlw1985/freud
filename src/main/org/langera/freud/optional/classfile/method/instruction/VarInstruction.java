package org.langera.freud.optional.classfile.method.instruction;

public final class VarInstruction extends Instruction
{

    public VarInstruction(final int index, final Opcode opcode, final int currentLineNumber, final int varIndex)
    {
        super(index, opcode, currentLineNumber, null, null, null, null, -1, null, varIndex, null, null, null);
    }

}
