package datapath
import chisel3.iotesters.PeekPokeTester
class InstructionTypeDecodeTests(c:InstructionTypeDecode) extends PeekPokeTester(c){
	poke(c.io.opcode, 51)
	step(1)
	expect(c.io.R_Format, 1)
}
