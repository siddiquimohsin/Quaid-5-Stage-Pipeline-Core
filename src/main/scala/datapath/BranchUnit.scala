package datapath
import chisel3._
class BranchUnit extends Module{
	val io = IO(new Bundle{
	val func3_in = Input(UInt(3.W))
	val rs1_in = Input(SInt(32.W))
	val rs2_in = Input(SInt(32.W))
	val Branch_out = Output(UInt(1.W))
	val ctrl_Branch = Input(UInt(1.W))
})
	io.Branch_out := 0.U
	when(io.func3_in === "b000".U){     	//<-- beq
		when(io.rs1_in === io.rs2_in){
			io.Branch_out := 1.U}
	}
	when(io.func3_in === "b001".U){     	//<-- bne
		when(io.rs1_in =/= io.rs2_in){
			io.Branch_out := 1.U}
	}
	when(io.func3_in === "b101".U){     	//<-- bge
		when(io.rs1_in === io.rs2_in || io.rs1_in > io.rs2_in){
			io.Branch_out := 1.U}
	}
	when(io.func3_in === "b111".U){  //<-- bgeU
		val rs1 = io.rs1_in.asUInt
		val rs2 = io.rs2_in.asUInt  
		when(rs1 === rs2 || rs1 > rs2){
			io.Branch_out := 1.U}
	}
	when(io.func3_in === "b100".U){     	//<-- blt
		when(io.rs1_in < io.rs2_in){
			io.Branch_out := 1.U}
	}
	when(io.func3_in === "b110".U){  //<-- bltU
		val rs1 = io.rs1_in.asUInt
		val rs2 = io.rs2_in.asUInt  
		when(rs1 === rs2 || rs1 < rs2){
			io.Branch_out := 1.U}
	}
}
