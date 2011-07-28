package org.langera.freud.optional.classfile.method.instruction;

public final class ConstInstruction extends Instruction
{
    public ConstInstruction(final int index, final Opcode opcode, final int currentLineNumber, final Object constant)
    {
        super(index, opcode, currentLineNumber, null, null,
                AbstractInstructionVisitor.typeEncoding(constant.getClass()), constant, -1, null, -1, null, null, null);

    }
}
