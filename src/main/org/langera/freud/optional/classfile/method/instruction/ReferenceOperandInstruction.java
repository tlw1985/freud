package org.langera.freud.optional.classfile.method.instruction;

import org.langera.freud.optional.classfile.method.ClassFileMethod;

public final class ReferenceOperandInstruction extends Instruction
{
    public ReferenceOperandInstruction(final ClassFileMethod method, final OperandStack currentOperandStack,
                                       final int index, final Opcode opcode, final int currentLineNumber, final String operandType)
    {
        this(method, currentOperandStack, index, opcode, currentLineNumber, operandType, -1);
    }

    public ReferenceOperandInstruction(final ClassFileMethod method, final OperandStack currentOperandStack,
                                       final int index, final Opcode opcode, final int currentLineNumber, final String operandType, final int varIndex)
    {
        super(method, currentOperandStack, index, opcode, currentLineNumber, null, null, operandType, null, -1, null, varIndex, null, null, null);
    }
}