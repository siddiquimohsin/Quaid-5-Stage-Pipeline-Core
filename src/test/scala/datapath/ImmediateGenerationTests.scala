package datapath
import chisel3.iotesters.PeekPokeTester
class ImmediateGenerationTests(c:ImmediateGeneration) extends PeekPokeTester(c){
	poke(c.io.Instruction, 1283)
	poke (c.io.PC, 12)
	step(1)
	expect(c.io.S_Immediate, 10)
	expect(c.io.SB_Immediate, 22)
	expect(c.io.UJ_Immediate, 12)
	expect(c.io.U_Immediate, 0)
	expect(c.io.I_Immediate, 0)
}
