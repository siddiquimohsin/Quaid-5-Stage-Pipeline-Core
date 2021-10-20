package datapath
import chisel3.iotesters.PeekPokeTester
class InstructionMemoryTests(c:InstructionMemory) extends PeekPokeTester(c){
	poke(c.io.writeAddress, 0)
	step(1)
	step(1)
	step(1)
	step(1)
	step(1)
	step(1)
	step(1)
	step(1)
	step(1)
	step(1)
	step(1)
	
}

