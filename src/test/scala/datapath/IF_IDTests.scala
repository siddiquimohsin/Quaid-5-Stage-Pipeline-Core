package datapath
import chisel3.iotesters.PeekPokeTester
class IF_IDTests(c:IF_ID) extends PeekPokeTester(c){
	poke(c.io.pc_in,0)
	poke(c.io.pc4_in,4)
	poke(c.io.Instruction_in,123)
	step(1)
}
