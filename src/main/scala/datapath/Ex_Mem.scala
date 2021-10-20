package datapath
import chisel3._
class Ex_Mem extends Module{
	val io = IO(new Bundle{
	val ALUout_in = Input(SInt(32.W))
	val ID_Ex_rs2_in = Input(SInt(32.W))
	val ID_Ex_Regwrite_in = Input(UInt(1.W))
	val ID_Ex_Memwrite_in = Input(UInt(1.W))
	val ID_Ex_MemRead_in = Input(UInt(1.W))
	val ID_Ex_MemtoReg_in = Input(UInt(1.W))
	val ID_Ex_rd_sel_in = Input(UInt(5.W))
	
	val ID_Ex_Regwrite_out = Output(UInt(1.W))
	val ID_Ex_rd_sel_out = Output(UInt(5.W))
	val ALUout_out = Output(SInt(32.W))
	val ID_Ex_rs2_out = Output(SInt(32.W))
	val ID_Ex_Memwrite_out = Output(UInt(1.W))
	val ID_Ex_MemRead_out = Output(UInt(1.W))
	val ID_Ex_MemtoReg_out = Output(UInt(1.W))
})
	val ID_Ex_Regwrite_reg = RegInit(0.U(1.W))
	val ID_Ex_rd_sel_reg = RegInit(0.U(5.W))
	val ALUout_reg = RegInit(0.S(32.W))
	val ID_Ex_rs2_reg = RegInit(0.S(32.W))
	val ID_Ex_Memwrite_reg = RegInit(0.U(1.W))
	val ID_Ex_MemRead_reg = RegInit(0.U(1.W))
	val ID_Ex_MemtoReg_reg = RegInit(0.U(1.W))

	ALUout_reg := io.ALUout_in
	ID_Ex_Regwrite_reg := io.ID_Ex_Regwrite_in
	ID_Ex_rd_sel_reg := io.ID_Ex_rd_sel_in 
	ID_Ex_rs2_reg := io.ID_Ex_rs2_in
	ID_Ex_Memwrite_reg := io.ID_Ex_Memwrite_in
	ID_Ex_MemRead_reg := io.ID_Ex_MemRead_in
	ID_Ex_MemtoReg_reg := io.ID_Ex_MemtoReg_in
	
	io.ALUout_out := ALUout_reg
	io.ID_Ex_Regwrite_out := ID_Ex_Regwrite_reg
	io.ID_Ex_rd_sel_out := ID_Ex_rd_sel_reg 
	io.ID_Ex_rs2_out := ID_Ex_rs2_reg
	io.ID_Ex_Memwrite_out := ID_Ex_Memwrite_reg
	io.ID_Ex_MemRead_out := ID_Ex_MemRead_reg
	io.ID_Ex_MemtoReg_out := ID_Ex_MemtoReg_reg
}
