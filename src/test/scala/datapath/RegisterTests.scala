package datapath
import chisel3.iotesters.PeekPokeTester
class RegisterTests(c:Register) extends PeekPokeTester(c){
	poke(c.io.rd_sel, 5)
	poke(c.io.rs1_sel, 16)
	poke(c.io.rs2_sel, 16)
	poke(c.io.writedata, 12)
	poke(c.io.regwrite, 1)
	step(1)
}
