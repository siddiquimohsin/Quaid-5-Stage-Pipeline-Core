package datapath
import chisel3._
class StructureHazard extends Module{
	val io = IO(new Bundle{
	val rs1_sel = Input(UInt(5.W))
	val rs2_sel = Input(UInt(5.W))
	val mem_write_regwrite = Input(UInt(1.W))
	val mem_write_rd_sel = Input(UInt(5.W))
	val forward_rs1 = Output(UInt(1.W))
	val forward_rs2 = Output(UInt(1.W))
})
	io.forward_rs1 := 0.U
	io.forward_rs2 := 0.U
	when((io.mem_write_regwrite === 1.U) && (io.mem_write_rd_sel =/= "b00000".U) && (io.mem_write_rd_sel === io.rs1_sel) && 	  		(io.mem_write_rd_sel === io.rs2_sel)){
		io.forward_rs1 := 1.U
		io.forward_rs2 := 1.U}
	when((io.mem_write_regwrite === 1.U) && (io.mem_write_rd_sel =/= "b00000".U) && (io.mem_write_rd_sel === io.rs1_sel)){
		io.forward_rs1 := 1.U}
	when((io.mem_write_regwrite === 1.U) && (io.mem_write_rd_sel =/= "b00000".U) && (io.mem_write_rd_sel === io.rs2_sel)){
		io.forward_rs2 := 1.U}
}
