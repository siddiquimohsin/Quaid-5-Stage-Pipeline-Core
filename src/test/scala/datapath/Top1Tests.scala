package datapath
import chisel3.iotesters.PeekPokeTester
class Top1Tests(c:Top1) extends PeekPokeTester(c){	
	step(5000)
	
}
