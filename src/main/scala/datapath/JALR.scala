package datapath
import chisel3._
class JALR extends Module{
	val io = IO(new Bundle{
	val rs1 = Input(SInt(32.W))
	val rs2 = Input(SInt(32.W))
	val JALR = Output(SInt(32.W))
})
	val hex = 4294967294L.S
	val output = io.rs1 + io.rs2
	val output1 = hex & output
	io.JALR := output1  
}
