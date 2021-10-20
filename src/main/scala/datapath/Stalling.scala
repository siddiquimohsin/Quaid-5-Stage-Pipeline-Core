package datapath
import chisel3._
class Stalling extends Module{
	val io = IO(new Bundle{
	val instruction_in = Input(UInt(32.W))
	val ID_Ex_MemRead_in = Input(UInt(1.W))
	val pc_in = Input(UInt(32.W))
	val current_pc_in = Input(UInt(32.W))
	val IF_ID_rs1_sel = Input(UInt(5.W))
	val IF_ID_rs2_sel = Input(UInt(5.W))
	val ID_Ex_rd_sel = Input(UInt(5.W))
	val IF_ID_MemRead = Input(UInt(1.W))
	val branchlogic = Input(UInt(1.W))
	val ctrl_branch = Input(UInt(1.W))
	
	val instruction_for = Output(UInt(1.W))
	val pc_for = Output(UInt(1.W))
	val pc_out = Output(UInt(32.W))
	val current_pc_out = Output(UInt(32.W))
	val instruction_out = Output(UInt(32.W))
	val ctrl_for = Output(UInt(1.W))
})
	io.instruction_for := 0.U
	io.ctrl_for := 0.U
	io.pc_for := 0.U
	io.pc_out := 0.U
	io.current_pc_out := 0.U
	io.instruction_out := 0.U
	when(io.ID_Ex_MemRead_in === 1.U && ((io.ID_Ex_rd_sel === io.IF_ID_rs1_sel) || (io.ID_Ex_rd_sel === io.IF_ID_rs2_sel))){
		io.instruction_for := 1.U
		io.pc_for := 1.U
		io.ctrl_for := 1.U
		io.pc_out := io.pc_in
		io.current_pc_out := io.current_pc_in
		io.instruction_out := io.instruction_in
	}
}
