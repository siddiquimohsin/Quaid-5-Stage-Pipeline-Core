package datapath 
import chisel3.iotesters.PeekPokeTester
class ALUcontrolTests(c:ALUcontrol) extends PeekPokeTester(c){
	poke(c.io.ALUop, 5)
	poke(c.io.Func7, 1)
	poke(c.io.Func3, 2)
	step(1)
	expect(c.io.Ctrl, 0)
}
