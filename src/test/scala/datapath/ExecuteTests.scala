package datapath
import chisel3.iotesters.PeekPokeTester
class ExecuteTests(c:Execute) extends PeekPokeTester(c){
	poke(c.io.ALUop, 5)
	poke(c.io.Func7, 1)
	poke(c.io.Func3, 2)
	poke(c.io.A, -2)
	poke(c.io.B, 4)
	step(1)
	expect(c.io.output, 2)
	expect(c.io.Branch, 0)
}
