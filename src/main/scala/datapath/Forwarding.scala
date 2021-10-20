package datapath
import chisel3._
class Forwarding extends Module{
	val io = IO(new Bundle{
	val ex_mem_regwrite = Input(UInt(1.W))
	val mem_write_regwrite = Input(UInt(1.W))
	val ex_mem_rd = Input(UInt(5.W))
	val mem_write_rd = Input(UInt(5.W))
	val id_ex_rs1_sel = Input(UInt(5.W))
	val id_ex_rs2_sel = Input(UInt(5.W))

	val forward_a = Output(UInt(2.W))
	val forward_b = Output(UInt(2.W))
}) 
	io.forward_a := "b00".U
	io.forward_b := "b00".U
	when(io.ex_mem_regwrite === "b1".U && io.ex_mem_rd =/= "b00000".U){
	 when((io.ex_mem_rd === io.id_ex_rs1_sel) && (io.ex_mem_rd === io.id_ex_rs2_sel)){
	   io.forward_a := "b10".U
	   io.forward_b := "b10".U
}
	 .elsewhen(io.ex_mem_rd === io.id_ex_rs1_sel){
	   io.forward_a := "b10".U
}
	 .elsewhen(io.ex_mem_rd === io.id_ex_rs2_sel){
	   io.forward_b := "b10".U
}
}
	when(io.mem_write_regwrite === "b1".U & io.mem_write_rd =/= "b00000".U){
	  when(((io.mem_write_rd === io.id_ex_rs1_sel) && (io.mem_write_rd === io.id_ex_rs2_sel)) && ~((io.ex_mem_regwrite === "b1".U) && 		(io.ex_mem_rd =/= "b00000".U) && (io.id_ex_rs1_sel === io.ex_mem_rd) && (io.id_ex_rs2_sel === io.ex_mem_rd))){
	   io.forward_a := "b01".U
	   io.forward_b := "b01".U
}
	 .elsewhen(io.mem_write_rd === io.id_ex_rs1_sel && ~((io.ex_mem_regwrite === "b1".U) && (io.ex_mem_rd =/= "b00000".U) && (io.id_ex_rs1_sel === io.ex_mem_rd))){
	   io.forward_a := "b01".U
}
	 .elsewhen(io.mem_write_rd === io.id_ex_rs2_sel && ~((io.ex_mem_regwrite === "b1".U) && (io.ex_mem_rd =/= "b00000".U) && 			  (io.id_ex_rs2_sel === io.ex_mem_rd))){
	   io.forward_b := "b01".U
}	
}
}
