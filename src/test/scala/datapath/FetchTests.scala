package datapath
import chisel3.iotesters.PeekPokeTester
class FetchTests(c:Fetch) extends PeekPokeTester(c){
	poke(c.io.input, 4)	
	poke(c.io.writeAddress, 0)
	step(5)
	
}
