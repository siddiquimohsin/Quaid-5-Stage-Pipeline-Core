package riscv
import chisel3.iotesters.PeekPokeTester
class Mux2easyTests(c: Mux2easy) extends PeekPokeTester(c){
	poke(c.io.a, 1)
	poke(c.io.b, 0)
	poke(c.io.c, 1)
	poke(c.io.d, 0)
	poke(c.io.sel, 2)
	step(1)
	expect(c.io.output, 1)

}
