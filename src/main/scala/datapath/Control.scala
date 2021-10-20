package datapath
import chisel3._
class Control extends Module{
	val io = IO(new Bundle{
	val opcode = Input(UInt(7.W))
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
	val Ins_Typ_Decode = Module(new InstructionTypeDecode())
	val Con_Decode = Module(new ControlDecoder())
	Ins_Typ_Decode.io.opcode := io.opcode
	Con_Decode.io.R_Format := Ins_Typ_Decode.io.R_Format
	Con_Decode.io.Load := Ins_Typ_Decode.io.Load
	Con_Decode.io.Store := Ins_Typ_Decode.io.Store 
	Con_Decode.io.Branch := Ins_Typ_Decode.io.Branch 
	Con_Decode.io.I_Type := Ins_Typ_Decode.io.I_Type
	Con_Decode.io.JALR := Ins_Typ_Decode.io.JALR
	Con_Decode.io.JAL := Ins_Typ_Decode.io.JAL
	Con_Decode.io.LUI := Ins_Typ_Decode.io.LUI
	io.Memwrite := Con_Decode.io.Memwrite
	io.Branch1 := Con_Decode.io.Branch1
	io.MemRead := Con_Decode.io.MemRead
	io.Regwrite := Con_Decode.io.Regwrite
	io.MemtoReg := Con_Decode.io.MemtoReg
	io.ALUop := Con_Decode.io.ALUop
	io.Oper_A_sel := Con_Decode.io.Oper_A_sel
	io.Oper_B_sel := Con_Decode.io.Oper_B_sel
	io.Extend_sel := Con_Decode.io.Extend_sel
	io.Next_PC_sel := Con_Decode.io.Next_PC_sel 
}
