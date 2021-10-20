package datapath
import chisel3.iotesters.PeekPokeTester
class Ex_MemTests(c:Ex_Mem) extends PeekPokeTester(c){
	step(1)
}
