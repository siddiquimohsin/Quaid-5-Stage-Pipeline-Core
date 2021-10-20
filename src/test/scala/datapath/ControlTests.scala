package datapath
import chisel3.iotesters.PeekPokeTester
class controlTests(c:Control) extends PeekPokeTester(c){
	poke(c.io.opcode, 51)
	step(1)
	expect(c.io.Memwrite, 0)
	expect(c.io.Branch1, 0)
	expect(c.io.MemRead, 0)
	expect(c.io.Regwrite, 1)
	expect(c.io.MemtoReg, 0)
	expect(c.io.ALUop, 0)
	expect(c.io.Oper_A_sel, 0)
	expect(c.io.Oper_B_sel, 0)
	expect(c.io.Extend_sel, 0)
	expect(c.io.Next_PC_sel, 0)
}
