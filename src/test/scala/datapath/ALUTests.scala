package datapath
import chisel3.iotesters.PeekPokeTester
class ALUTests(c:ALU) extends PeekPokeTester(c){
	step(1)
}

