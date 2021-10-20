package datapath
import chisel3.iotesters.PeekPokeTester
class BranchUnitTests(c:BranchUnit) extends PeekPokeTester(c){
	step(1)
}
