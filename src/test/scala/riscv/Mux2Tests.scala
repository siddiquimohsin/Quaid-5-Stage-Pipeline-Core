package riscv
import chisel3.iotesters.PeekPokeTester
class Mux2Tests(c: Mux2) extends PeekPokeTester(c){
	poke(c.io.a, 1)
	poke(c.io.b, 0)
	poke(c.io.sel, 0)
	step(1)
	expect(c.io.output, 1)

}
