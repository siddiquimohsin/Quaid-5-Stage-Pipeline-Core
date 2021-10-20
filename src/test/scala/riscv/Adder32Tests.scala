package riscv
import chisel3.iotesters.PeekPokeTester
class Adder32Tests(c: Adder32) extends PeekPokeTester(c){
	poke(c.io.a, 10)
	poke(c.io.b, 20)
	step(1)
	expect(c.io.sum, 30)

}
