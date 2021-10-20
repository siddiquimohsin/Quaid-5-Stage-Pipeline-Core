package datapath
import chisel3.iotesters.PeekPokeTester
class ForwardingTests(c:Forwarding) extends PeekPokeTester(c){
	step(1)
}
