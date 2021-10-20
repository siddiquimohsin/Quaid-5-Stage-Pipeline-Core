package datapath
import chisel3._
import chisel3.util.Cat
import chisel3.util.Fill
class Decode extends Module{
	val io = IO(new Bundle{
	val Instruction = Input(UInt(32.W))
	val PC = Input(UInt(32.W))
	val regwrite = Input(UInt(1.W))
	val rd_sel = Input(UInt(5.W))
	val rs1_sel = Input(UInt(5.W))
	val rs2_sel = Input(UInt(5.W))
	val writedata = Input(SInt(32.W))
	val Immediate = Input(SInt(32.W))
	val Func3 = Input(UInt(3.W))
	val Func7 = Input(UInt(1.W))

	val rs1 = Output(SInt(32.W))
	val rs2 = Output(SInt(32.W))
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
	val I_Immediate = Output(SInt(32.W))
	val S_Immediate = Output(SInt(32.W))
	val U_Immediate = Output(SInt(32.W))
	val SB_Immediate = Output(SInt(32.W))
	val UJ_Immediate = Output(SInt(32.W))
})
	
	val reg = Reg(Vec(32,SInt(32.W)))
	val opcode = io.Instruction(6,0)
	val R_Format1 =Wire(UInt(1.W))
	val Load1 = Wire(UInt(1.W))
	val Store1 = Wire(UInt(1.W))
	val Branch2 = Wire(UInt(1.W))
	val I_Type1 =Wire(UInt(1.W))
	val JALR1 = Wire(UInt(1.W))
	val JAL1 = Wire(UInt(1.W))
	val LUI1 = Wire(UInt(1.W))
	

	R_Format1:=0.U
	Load1:=0.U
	Store1:=0.U
	Branch2:=0.U
	I_Type1:=0.U
	JALR1:=0.U
	JAL1:=0.U
	LUI1:=0.U
	io.rs1:=0.S
	io.rs2:=0.S
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
	io.I_Immediate := 0.S
	io.S_Immediate := 0.S
	io.SB_Immediate := 0.S
	io.U_Immediate := 0.S
	io.UJ_Immediate := 0.S
	/* Control*/

	when(opcode === "b0110011".U){
	R_Format1 := 1.U	
}
	.elsewhen(opcode === "b0000011".U){
	Load1 := 1.U
}
	.elsewhen(opcode === "b0100011".U){
	Store1 := 1.U
}
	.elsewhen(opcode === "b1100011".U){
	Branch2 := 1.U
}
	.elsewhen(opcode === "b0010011".U){
	I_Type1 := 1.U
}
	.elsewhen(opcode === "b1100111".U){
	JALR1 := 1.U
}
	.elsewhen(opcode === "b1101111".U){
	JAL1 := 1.U
}
	.elsewhen(opcode === "b110111".U){
	LUI1 := 1.U
}

	when(R_Format1 === 1.U){
	io.Regwrite := 1.U
}
	.elsewhen(Load1 === 1.U){
	io.MemRead := 1.U
	io.Regwrite := 1.U
	io.MemtoReg := 1.U
	io.ALUop := "b100".U
	io.Oper_B_sel := 1.U
}
	.elsewhen(Store1 === 1.U){
	io.Memwrite := 1.U
	io.ALUop := "b101".U
	io.Oper_B_sel := 1.U
	io.Extend_sel := "b10".U
}
	.elsewhen(Branch2 === 1.U){
	io.Branch1 := 1.U
	io.ALUop := "b010".U
	io.Next_PC_sel := "b01".U
}
	.elsewhen(I_Type1 === 1.U){
	io.Regwrite := 1.U
	io.ALUop := "b001".U
	io.Oper_B_sel := 1.U
}
	.elsewhen(JALR1 === 1.U){
	io.Regwrite := 1.U
	io.ALUop := "b011".U
	io.Oper_A_sel := "b10".U
	io.Next_PC_sel := "b11".U
}
	.elsewhen(JAL1 === 1.U){
	io.Regwrite := 1.U
	io.ALUop := "b011".U
	io.Oper_A_sel := "b10".U
	io.Next_PC_sel := "b10".U
}
	.elsewhen(LUI1 === 1.U){
	io.Regwrite := 1.U
	io.ALUop := "b110".U
	io.Oper_A_sel := "b11".U
	io.Oper_B_sel := 1.U
	io.Extend_sel := "b01".U
}

	/* Immediate Generation*/

	val sbt1 = io.Instruction(31,20)
	val output = Cat(Fill(19,sbt1(11)),sbt1)
	val output1 = output.asSInt 
	io.I_Immediate := output1

	val sbt2 = io.Instruction(11,7)
	val sbt3 = io.Instruction(31,25)
	val sbt4 = Cat(sbt3,sbt2)
	val output2 = Cat(Fill(19,sbt4(11)),sbt4)
	val output3 = output2.asSInt
	io.S_Immediate := output3

	val sbt5 = io.Instruction(31,12)
	val sbt6 = Cat(Fill(11,sbt5(19)),sbt5)
	val sbt7 = sbt6 << "hc".U
	val output4 = sbt7.asSInt
	io.U_Immediate := output4 

	val sbt8 = io.Instruction(7)
	val sbt9 = io.Instruction(11,8)
	val sbt10 = io.Instruction(30,25)
	val sbt11 = io.Instruction(31)
	val sbt12 = Cat(sbt11,sbt8,sbt10,sbt9,"b0".U)
	val sbt13 = Cat(Fill(19,sbt12(12)),sbt12)
	val output5 = sbt13 + io.PC
	val output6 = output5.asSInt
	io.SB_Immediate := output6

	val sbt14 = io.Instruction(19,12)
	val sbt15 = io.Instruction(20)
	val sbt16 = io.Instruction(30,21)
	val sbt17 = io.Instruction(31)
	val sbt18 = Cat(sbt17,sbt14,sbt15,sbt16,0.U)
	val sbt19 = Cat(Fill(11,sbt18(20)),sbt18)
	val output8 = sbt19 +io.PC
	val output9 = output8.asSInt
	io.UJ_Immediate := output9

	/*Register*/

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
