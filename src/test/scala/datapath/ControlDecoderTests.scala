package datapath
import chisel3.iotesters.PeekPokeTester
class ControlDecoderTests(c:ControlDecoder) extends PeekPokeTester(c){
	poke(c.io.R_Format, 1)
	poke(c.io.Load, 0)
	poke(c.io.Store, 0)
	poke(c.io.Branch, 0)
	poke(c.io.I_Type, 0)
	poke(c.io.JALR, 0)
	poke(c.io.JAL, 0)
	poke(c.io.LUI, 0)
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
