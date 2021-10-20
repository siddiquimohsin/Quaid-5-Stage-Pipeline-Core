package datapath
import chisel3._
class Mem_Write extends Module{
	val io = IO(new Bundle{
	val Ex_Mem_rd_sel_in = Input(UInt(5.W))
	val Ex_Mem_ALUout_in = Input(SInt(32.W))
	val Ex_Mem_Regwrite_in = Input(UInt(1.W))
	val Ex_Mem_MemtoReg_in = Input(UInt(1.W))
	val Ex_Mem_Data_in = Input(SInt(32.W))
	
	val Ex_Mem_rd_sel_out = Output(UInt(5.W))
	val Ex_Mem_ALUout_out = Output(SInt(32.W))
	val Ex_Mem_Regwrite_out = Output(UInt(1.W))
	val Ex_Mem_MemtoReg_out = Output(UInt(1.W))
	val Ex_Mem_Data_out = Output(SInt(32.W))
		
})
	val Ex_Mem_rd_sel_reg = RegInit(0.U(5.W))
	val Ex_Mem_ALUout_reg = RegInit(0.S(32.W))
	val Ex_Mem_Regwrite_reg = RegInit(0.U(1.W))
	val Ex_Mem_MemtoReg_reg = RegInit(0.U(1.W))
	val Ex_Mem_Data_reg = RegInit(0.S(32.W))

	Ex_Mem_rd_sel_reg := io.Ex_Mem_rd_sel_in
	Ex_Mem_ALUout_reg := io.Ex_Mem_ALUout_in
	Ex_Mem_Regwrite_reg := io.Ex_Mem_Regwrite_in
	Ex_Mem_MemtoReg_reg := io.Ex_Mem_MemtoReg_in
	Ex_Mem_Data_reg := io.Ex_Mem_Data_in

	io.Ex_Mem_rd_sel_out := Ex_Mem_rd_sel_reg
	io.Ex_Mem_ALUout_out := Ex_Mem_ALUout_reg
	io.Ex_Mem_Regwrite_out := Ex_Mem_Regwrite_reg
	io.Ex_Mem_MemtoReg_out := Ex_Mem_MemtoReg_reg
	io.Ex_Mem_Data_out := Ex_Mem_Data_reg
	
}

