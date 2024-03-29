package datapath
import chisel3._
class ID_Ex extends Module{
	val io = IO(new Bundle{
	val Instruction_in = Input(UInt(32.W))
	val rs1_sel_in = Input(UInt(5.W))
	val rs2_sel_in = Input(UInt(5.W))
	val rs1_in = Input(SInt(32.W))
	val rs2_in = Input(SInt(32.W))
	val rd_sel_in = Input(UInt(5.W))
	val Memwrite_in = Input(UInt(1.W))
	val Branch1_in = Input(UInt(1.W))
	val MemRead_in = Input(UInt(1.W))
	val Regwrite_in = Input(UInt(1.W))
	val MemtoReg_in = Input(UInt(1.W))
	val ALUop_in = Input(UInt(3.W))
	val Immediate_in = Input(SInt(32.W))
	val IF_ID_pc4_in = Input(UInt(32.W))
	val IF_ID_pc_in = Input(UInt(32.W))
	val Oper_A_sel_in = Input(UInt(2.W))
	val Oper_B_sel_in = Input(UInt(1.W))
	val Extend_sel_in = Input(UInt(2.W))
	val Next_PC_sel_in = Input(UInt(2.W))
	val Func3_in = Input(UInt(3.W))
	val Func7_in = Input(UInt(1.W))	

	val rs1_sel_out = Output(UInt(5.W))
	val rs2_sel_out = Output(UInt(5.W))
	val Next_PC_sel_out = Output(UInt(2.W))	
	val Instruction_out = Output(UInt(32.W))
	val IF_ID_pc4_out = Output(UInt(32.W))
	val IF_ID_pc_out = Output(UInt(32.W))
	val Oper_A_sel_out = Output(UInt(2.W))
	val Oper_B_sel_out = Output(UInt(1.W))
	val rs1_out = Output(SInt(32.W))
	val rs2_out = Output(SInt(32.W))
	val rd_sel_out = Output(UInt(5.W))
	val Memwrite_out = Output(UInt(1.W))
	val Branch1_out = Output(UInt(1.W))
	val MemRead_out = Output(UInt(1.W))
	val Regwrite_out = Output(UInt(1.W))
	val MemtoReg_out = Output(UInt(1.W))
	val ALUop_out = Output(UInt(3.W))
	val Func3_out = Output(UInt(3.W))
	val Func7_out = Output(UInt(1.W))
	val Extend_sel_out = Output(UInt(2.W))
	val Immediate_out = Output(SInt(32.W))
})
	val rs1_sel_reg = RegInit(0.U(5.W))
	val rs2_sel_reg = RegInit(0.U(5.W))
	val Instruction_reg = RegInit(0.U(32.W))
	val Extend_sel_reg = RegInit(0.U(2.W))
	val Next_PC_sel_reg = RegInit(0.U(2.W))	
	val IF_ID_pc_reg = RegInit(0.U(32.W))
	val Oper_A_sel_reg = RegInit(0.U(2.W))
	val Oper_B_sel_reg = RegInit(0.U(1.W))
	val IF_ID_pc4_reg = RegInit(0.U(32.W))
	val rd_sel_reg = RegInit(0.U(5.W))
	val rs1_reg = RegInit(0.S(32.W))
	val rs2_reg = RegInit(0.S(32.W))
	val Immediate_reg = RegInit(0.S(32.W))
	val Memwrite_reg = RegInit(0.U(1.W))
	val Branch1_reg = RegInit(0.U(1.W))
	val MemRead_reg = RegInit(0.U(1.W))
	val Regwrite_reg = RegInit(0.U(1.W))
	val MemtoReg_reg = RegInit(0.U(1.W))
	val ALUop_reg = RegInit(0.U(3.W))
	val Func3_reg = RegInit(0.U(3.W))
	val Func7_reg = RegInit(0.U(1.W))

	
	Extend_sel_reg := io.Extend_sel_in
	rs1_sel_reg := io.rs1_sel_in
	rs2_sel_reg := io.rs2_sel_in
	IF_ID_pc_reg := io.IF_ID_pc_in
	Next_PC_sel_reg := io.Next_PC_sel_in
	Oper_A_sel_reg := io.Oper_A_sel_in
	Oper_B_sel_reg := io.Oper_B_sel_in
	Instruction_reg := io.Instruction_in
	IF_ID_pc4_reg := io.IF_ID_pc4_in
	rd_sel_reg := io.rd_sel_in
	rs1_reg := io.rs1_in
	rs2_reg := io.rs2_in
	Memwrite_reg := io.Memwrite_in
	Branch1_reg := io.Branch1_in
	MemRead_reg := io.MemRead_in
	Regwrite_reg := io.Regwrite_in
	MemtoReg_reg := io.MemtoReg_in
	ALUop_reg := io.ALUop_in
	Func3_reg := io.Func3_in
	Func7_reg := io.Func7_in
	Immediate_reg := io.Immediate_in
	
	io.Extend_sel_out := Extend_sel_reg
	io.rs1_sel_out := rs1_sel_reg
	io.rs2_sel_out := rs2_sel_reg
	io.Next_PC_sel_out := Next_PC_sel_reg
	io.IF_ID_pc_out := IF_ID_pc_reg
	io.Oper_A_sel_out := Oper_A_sel_reg
	io.Oper_B_sel_out := Oper_B_sel_reg
	io.Instruction_out := Instruction_reg
	io.IF_ID_pc4_out := IF_ID_pc4_reg
	io.rd_sel_out := rd_sel_reg
	io.rs1_out := rs1_reg
	io.rs2_out := rs2_reg
	io.Memwrite_out := Memwrite_reg
	io.Branch1_out := Branch1_reg
	io.MemRead_out := MemRead_reg
	io.Regwrite_out := Regwrite_reg
	io.MemtoReg_out := MemtoReg_reg
	io.ALUop_out := ALUop_reg
	io.Func3_out := Func3_reg
	io.Func7_out := Func7_reg
	io.Immediate_out := Immediate_reg
}
