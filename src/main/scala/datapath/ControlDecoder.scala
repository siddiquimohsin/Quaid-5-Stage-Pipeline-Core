package datapath
import chisel3._
class ControlDecoder extends Module{
	val io = IO(new Bundle{
	val R_Format = Input(UInt(1.W))
	val Load = Input(UInt(1.W))
	val Store = Input(UInt(1.W)) 
	val Branch = Input(UInt(1.W)) 
	val I_Type = Input(UInt(1.W))
	val JALR = Input(UInt(1.W))
	val JAL = Input(UInt(1.W))
	val LUI = Input(UInt(1.W))
	val Memwrite = Output(UInt(1.W))
	val Branch1 = Output(UInt(1.W))
	val MemRead = Output(UInt(1.W))
	val Regwrite = Output(UInt(1.W))
	val MemtoReg = Output(UInt(1.W))
	val ALUop = Output(UInt(3.W))
	val Oper_A_sel = Output(UInt(2.W))
	val Oper_B_sel = Output(UInt(1.W))
	val Extend_sel = Output(UInt(2.W))
	val Next_PC_sel = Output(UInt(2.W))
})

	io.Memwrite := 0.U
	io.Branch1 := 0.U
	io.MemRead := 0.U
	io.Regwrite := 0.U
	io.MemtoReg := 0.U
	io.ALUop := "b000".U
	io.Oper_A_sel := "b00".U
	io.Oper_B_sel := 0.U
	io.Extend_sel := "b00".U
	io.Next_PC_sel := "b00".U
	when(io.R_Format === 1.U){
	io.Regwrite := 1.U
}
	.elsewhen(io.Load === 1.U){
	io.MemRead := 1.U
	io.Regwrite := 1.U
	io.MemtoReg := 1.U
	io.ALUop := "b100".U
	io.Oper_B_sel := 1.U
}
	.elsewhen(io.Store === 1.U){
	io.Memwrite := 1.U
	io.ALUop := "b101".U
	io.Oper_B_sel := 1.U
	io.Extend_sel := "b10".U
}
	.elsewhen(io.Branch === 1.U){
	io.Branch1 := 1.U
	io.ALUop := "b010".U
	io.Next_PC_sel := "b01".U
}
	.elsewhen(io.I_Type === 1.U){
	io.Regwrite := 1.U
	io.ALUop := "b001".U
	io.Oper_B_sel := 1.U
}
	.elsewhen(io.JALR === 1.U){
	io.Regwrite := 1.U
	io.ALUop := "b011".U
	io.Oper_A_sel := "b10".U
	io.Next_PC_sel := "b11".U
}
	.elsewhen(io.JAL === 1.U){
	io.Regwrite := 1.U
	io.ALUop := "b011".U
	io.Oper_A_sel := "b10".U
	io.Next_PC_sel := "b10".U
}
	.elsewhen(io.LUI === 1.U){
	io.Regwrite := 1.U
	io.ALUop := "b110".U
	io.Oper_A_sel := "b11".U
	io.Oper_B_sel := 1.U
	io.Extend_sel := "b01".U
}

}
