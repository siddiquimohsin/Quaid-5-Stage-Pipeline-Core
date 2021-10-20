package datapath
import chisel3.iotesters.PeekPokeTester
class WriteBackTests(c:WriteBack) extends PeekPokeTester(c){
	poke(c.io.Read_Data,0)
	poke(c.io.Data,1)
	poke(c.io.ID_Ex_MemtoReg,0)
	step(1)
	expect(c.io.output,0)
}
