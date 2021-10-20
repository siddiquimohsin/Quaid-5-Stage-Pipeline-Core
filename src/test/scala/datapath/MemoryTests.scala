package datapath
import chisel3.iotesters.PeekPokeTester
class MemoryTests(c:Memory) extends PeekPokeTester(c){
	poke(c.io.Address, 5)
	poke(c.io.Mem_Read, 1)
	poke(c.io.Mem_Write, 1)
	poke(c.io.Read_Data, -3)
	step(1)
	expect(c.io.Data, -3)
}
