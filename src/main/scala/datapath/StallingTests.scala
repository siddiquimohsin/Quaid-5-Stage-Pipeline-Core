package datapath
import chisel3.iotesters.PeekPokeTester
class StallingTests(c:Stalling) extends PeekPokeTester(c){
	step(1)
}
