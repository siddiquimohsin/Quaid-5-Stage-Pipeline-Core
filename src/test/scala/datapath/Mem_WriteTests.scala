package datapath
import chisel3.iotesters.PeekPokeTester
class Mem_WriteTests(c:Mem_Write) extends PeekPokeTester(c){
	step(1)
}
