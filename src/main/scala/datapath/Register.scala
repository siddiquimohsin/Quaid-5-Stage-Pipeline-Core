package datapath
import chisel3._
class Register extends Module{
	val io = IO(new Bundle{
	val regwrite = Input(UInt(1.W))
	val rd_sel = Input(UInt(5.W))
	val rs1_sel = Input(UInt(5.W))
	val rs2_sel = Input(UInt(5.W))
	val writedata = Input(SInt(32.W))
	val rs1 = Output(SInt(32.W))
	val rs2 = Output(SInt(32.W))
})
	val reg = Reg(Vec(32,SInt(32.W)))
	reg(0):=0.S
	reg(2):=512.S
	io.rs1 := reg(io.rs1_sel)
	io.rs2 := reg(io.rs2_sel)
	when(io.regwrite === 1.U){
	 when(io.rd_sel === "b00000".U){
	  reg(io.rd_sel) := 0.S
}
	.otherwise{
	 reg(io.rd_sel) := io.writedata
}
}
}

