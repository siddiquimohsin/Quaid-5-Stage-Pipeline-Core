package datapath
import chisel3.iotesters.PeekPokeTester
class JALRTests(c:JALR) extends PeekPokeTester(c){
	poke(c.io.rs1, 10)
	poke(c.io.rs2, 10)
	step(1)
	expect(c.io.JALR, 21)
}
