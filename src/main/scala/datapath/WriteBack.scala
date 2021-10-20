package datapath
import chisel3._
class WriteBack extends Module{
	val io = IO(new Bundle{
	val Read_Data = Input(SInt(32.W))
	val Data = Input(SInt(32.W))
	val ID_Ex_MemtoReg = Input(UInt(1.W))
	val output = Output(SInt(32.W))
})
	io.output := 0.S
	when(io.ID_Ex_MemtoReg === 0.U){
	io.output := io.Read_Data
}
	.elsewhen(io.ID_Ex_MemtoReg === 1.U){
	io.output := io.Data
}
}
