package datapath
import chisel3.iotesters.PeekPokeTester
class StructureHazardTests(c:StructureHazard) extends PeekPokeTester(c){
	step(1)
}
