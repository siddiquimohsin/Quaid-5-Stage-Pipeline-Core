package datapath
import chisel3.iotesters.PeekPokeTester
class BranchForwardTests(c:BranchForward) extends PeekPokeTester(c){
	step(1)
}
