package riscv
import chisel3.iotesters.PeekPokeTester
class Subtractor32Tests(c: Subtractor32) extends PeekPokeTester(c){
	poke(c.io.a, 20)
	poke(c.io.b, 30)
	step(1)
	expect(c.io.sub, -10)

}
