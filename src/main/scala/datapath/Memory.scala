package datapath
import chisel3._
class Memory extends Module{
	val io = IO(new Bundle{
	val Address = Input(UInt(8.W))
	val Mem_Read = Input(Bool())
	val Mem_Write = Input(Bool())
	val Read_Data = Input(SInt(32.W))

	val Data = Output(SInt(32.W))
})
	io.Data := 0.S
	val mem = Mem(1024,SInt(32.W))
	when(io.Mem_Read === 1.U){
	 io.Data := mem.read(io.Address)

	}
	when(io.Mem_Write === 1.U){
	  mem.write(io.Address,io.Read_Data)
}
}
