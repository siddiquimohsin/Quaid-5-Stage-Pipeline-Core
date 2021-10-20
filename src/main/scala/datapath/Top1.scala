package datapath
import chisel3._
class Top1 extends Module{
 	val io = IO(new Bundle{
	val Instruction = Output(UInt(32.W))
	val reg_out = Output(SInt(32.W))
})
	val control = Module(new Control())
	val ALU = Module(new ALU())
	val ALUcontrol = Module(new ALUcontrol())
	val Imm_Gen = Module(new ImmediateGeneration())
	val Register = Module(new Register())
	val JALR = Module(new JALR())
	val PC1 = Module(new PC())
	val Data_Mem = Module(new DataMemory())
	val InstructionMemory = Module(new InstructionMemory())
	val if_id = Module(new IF_ID())
	val id_ex = Module(new ID_Ex())
	val ex_mem = Module(new Ex_Mem())
	val mem_write = Module(new Mem_Write())
	val forward = Module(new Forwarding())
	val branchunit = Module(new BranchUnit())
	val branchforward = Module(new BranchForward())
	val stall = Module(new Stalling())
	val structurehazard = Module(new StructureHazard())
	io.reg_out := 0.S
	branchunit.io.rs1_in := 0.S
	PC1.io.input := 0.U
	Register.io.writedata := 0.S
	id_ex.io.Immediate_in := 0.S
	ex_mem.io.ID_Ex_rs2_in := 0.S
	id_ex.io.rs1_in := 0.S
	id_ex.io.rs2_in := 0.S
	branchunit.io.rs2_in := 0.S
	ALU.io.A := 0.S
	ALU.io.B := 0.S
	io.Instruction := InstructionMemory.io.readData
	if_id.io.Instruction_in := InstructionMemory.io.readData
	id_ex.io.Instruction_in := if_id.io.Instruction_out
	InstructionMemory.io.writeAddress := PC1.io.pc(11,2)
	if_id.io.pc_in := PC1.io.pc
	if_id.io.pc4_in := PC1.io.pc4

	control.io.opcode := if_id.io.Instruction_out(6,0)
	Register.io.rs1_sel := if_id.io.Instruction_out(19,15)
	Register.io.rs2_sel := if_id.io.Instruction_out(24,20)
	Register.io.regwrite := mem_write.io.Ex_Mem_Regwrite_out
	Register.io.rd_sel := mem_write.io.Ex_Mem_rd_sel_out

	id_ex.io.rd_sel_in := if_id.io.Instruction_out(11,7)
	id_ex.io.rs1_sel_in := if_id.io.Instruction_out(19,15)
	id_ex.io.rs2_sel_in := if_id.io.Instruction_out(24,20)
	

	/*id_ex.io.Memwrite_in := control.io.Memwrite
	id_ex.io.MemRead_in := control.io.MemRead
	id_ex.io.Branch1_in := control.io.Branch1
	id_ex.io.MemtoReg_in := control.io.MemtoReg
	id_ex.io.Regwrite_in := control.io.Regwrite
	id_ex.io.ALUop_in := control.io.ALUop
	id_ex.io.IF_ID_pc4_in := if_id.io.pc4_out
	id_ex.io.IF_ID_pc_in := if_id.io.pc_out
	id_ex.io.Oper_A_sel_in := control.io.Oper_A_sel
	id_ex.io.Oper_B_sel_in := control.io.Oper_B_sel
	id_ex.io.Extend_sel_in := control.io.Extend_sel
	id_ex.io.Next_PC_sel_in := control.io.Next_PC_sel*/

	ALUcontrol.io.Func7 := id_ex.io.Func7_out
	ALUcontrol.io.Func3 := id_ex.io.Func3_out
	ALUcontrol.io.ALUop := id_ex.io.ALUop_out
	Imm_Gen.io.Instruction := if_id.io.Instruction_out
	ALU.io.ALUcontrol := ALUcontrol.io.Ctrl
	Imm_Gen.io.PC := if_id.io.pc_out
	JALR.io.rs1 := 0.S




	/*-------------------------------------------- HAZARD DETECTION -----------------------------------------------*/
		structurehazard.io.rs1_sel := if_id.io.Instruction_out(19,15)
		structurehazard.io.rs2_sel := if_id.io.Instruction_out(24,20)
		structurehazard.io.mem_write_rd_sel := mem_write.io.Ex_Mem_rd_sel_out
		structurehazard.io.mem_write_regwrite := mem_write.io.Ex_Mem_Regwrite_out

		when(structurehazard.io.forward_rs1 === 1.U){
			when(mem_write.io.Ex_Mem_MemtoReg_out === 1.U){
				id_ex.io.rs1_in := mem_write.io.Ex_Mem_Data_out
			}.elsewhen(mem_write.io.Ex_Mem_MemtoReg_out === 0.U){
				id_ex.io.rs1_in := mem_write.io.Ex_Mem_ALUout_out
			}
		}.elsewhen(structurehazard.io.forward_rs1 === 0.U) {
			id_ex.io.rs1_in := Register.io.rs1}

		when(structurehazard.io.forward_rs2 === 1.U){
			when(mem_write.io.Ex_Mem_MemtoReg_out === 1.U){
				id_ex.io.rs2_in := mem_write.io.Ex_Mem_Data_out
			}.elsewhen(mem_write.io.Ex_Mem_MemtoReg_out === 0.U){
				id_ex.io.rs2_in := mem_write.io.Ex_Mem_ALUout_out}
		}.elsewhen(structurehazard.io.forward_rs2 === 0.U) {
			id_ex.io.rs2_in := Register.io.rs2}
	/*-------------------------------------------- HAZARD DETECTION -----------------------------------------------*/






	/*-------------------------------------------- STALLING DETECTION -----------------------------------------------*/
		stall.io.pc_in := if_id.io.pc4_out
		stall.io.current_pc_in := if_id.io.pc_out
		stall.io.instruction_in := if_id.io.Instruction_out
		stall.io.ID_Ex_MemRead_in := id_ex.io.MemRead_out
		stall.io.IF_ID_rs1_sel := if_id.io.Instruction_out(19,15)
		stall.io.IF_ID_rs2_sel := if_id.io.Instruction_out(24,20)
		stall.io.IF_ID_MemRead := control.io.MemRead
		stall.io.ID_Ex_rd_sel := id_ex.io.rd_sel_out
		stall.io.branchlogic := branchunit.io.Branch_out
		stall.io.ctrl_branch := control.io.Branch1

		when(stall.io.instruction_for === "b1".U){
			if_id.io.Instruction_in := stall.io.instruction_out
		}.otherwise{
			if_id.io.Instruction_in := InstructionMemory.io.readData}
		when(stall.io.ctrl_for === "b1".U){
			id_ex.io.Memwrite_in := 0.U
			id_ex.io.MemRead_in := 0.U
			id_ex.io.Branch1_in := 0.U
			id_ex.io.MemtoReg_in := 0.U
			id_ex.io.Regwrite_in := 0.U
			id_ex.io.ALUop_in := 0.U
			id_ex.io.Func3_in := 0.U
			id_ex.io.Func7_in := 0.U
			id_ex.io.IF_ID_pc4_in := 0.U
			id_ex.io.IF_ID_pc_in := 0.U
			id_ex.io.Oper_A_sel_in := 0.U
			id_ex.io.Oper_B_sel_in := 0.U
			id_ex.io.Extend_sel_in := 0.U
			id_ex.io.Next_PC_sel_in := 0.U
		}.otherwise{
			id_ex.io.Memwrite_in := control.io.Memwrite
			id_ex.io.MemRead_in := control.io.MemRead
			id_ex.io.Branch1_in := control.io.Branch1
			id_ex.io.MemtoReg_in := control.io.MemtoReg
			id_ex.io.Regwrite_in := control.io.Regwrite
			id_ex.io.ALUop_in := control.io.ALUop
			id_ex.io.Func3_in := if_id.io.Instruction_out(14,12)
			id_ex.io.Func7_in := if_id.io.Instruction_out(30)
			id_ex.io.IF_ID_pc4_in := if_id.io.pc4_out
			id_ex.io.IF_ID_pc_in := if_id.io.pc_out
			id_ex.io.Oper_A_sel_in := control.io.Oper_A_sel
			id_ex.io.Oper_B_sel_in := control.io.Oper_B_sel
			id_ex.io.Extend_sel_in := control.io.Extend_sel
			id_ex.io.Next_PC_sel_in := control.io.Next_PC_sel}
		when(stall.io.pc_for === "b1".U){
			PC1.io.input := stall.io.pc_out
		}.otherwise{
			when(control.io.Next_PC_sel === "b10".U){
				PC1.io.input := Imm_Gen.io.UJ_Immediate.asUInt
				if_id.io.pc_in := 0.U
				if_id.io.pc4_in := 0.U
				if_id.io.Instruction_in := 0.U
			}.elsewhen(control.io.Next_PC_sel === "b11".U){
				 PC1.io.input := JALR.io.JALR.asUInt
				if_id.io.pc_in := 0.U
				if_id.io.pc4_in := 0.U
				if_id.io.Instruction_in := 0.U
			}.elsewhen(control.io.Next_PC_sel === "b01".U){
				when((branchunit.io.Branch_out===1.U) && (branchunit.io.ctrl_Branch===1.U)){
					val imm = Imm_Gen.io.SB_Immediate.asUInt
					PC1.io.input := imm
					if_id.io.pc_in := 0.U
					if_id.io.pc4_in := 0.U
					if_id.io.Instruction_in := 0.U
				}.otherwise{
					PC1.io.input := PC1.io.pc4}
			}.otherwise{
				when(id_ex.io.MemRead_out === "b1".U){
					PC1.io.input := if_id.io.pc4_out
				}.otherwise{
					PC1.io.input := PC1.io.pc4}}
		}
		
	/*-------------------------------------------- STALLING DETECTION -----------------------------------------------*/



	/*-------------------------------------------- BRANCH FORWARD AND BRANCH UNIT -----------------------------------------------*/
		branchunit.io.func3_in := if_id.io.Instruction_out(14,12)
		branchunit.io.ctrl_Branch := control.io.Branch1		

		branchforward.io.rs1_sel := if_id.io.Instruction_out(19,15)
		branchforward.io.rs2_sel := if_id.io.Instruction_out(24,20)
		branchforward.io.id_ex_regwrite := control.io.Regwrite
		branchforward.io.con_branch := control.io.Branch1
		branchforward.io.id_ex_rd_sel := id_ex.io.rd_sel_out
		branchforward.io.id_ex_memread := id_ex.io.MemRead_out
		branchforward.io.ex_mem_regwrite := ex_mem.io.ID_Ex_Regwrite_out
		branchforward.io.ex_mem_rd_sel := ex_mem.io.ID_Ex_rd_sel_out
		branchforward.io.ex_mem_memread := ex_mem.io.ID_Ex_MemRead_out
		branchforward.io.mem_write_rd_sel := mem_write.io.Ex_Mem_rd_sel_out
		branchforward.io.mem_write_regwrite := mem_write.io.Ex_Mem_Regwrite_out
		branchforward.io.mem_write_memtoreg := mem_write.io.Ex_Mem_MemtoReg_out
		
		JALR.io.rs2 := Imm_Gen.io.I_Immediate

		when(branchforward.io.forward_a === "b0001".U){
			branchunit.io.rs1_in := ALU.io.output
			JALR.io.rs1 := Register.io.rs1
		}.elsewhen(branchforward.io.forward_a === "b0010".U){
			branchunit.io.rs1_in := ex_mem.io.ALUout_out
			JALR.io.rs1 := Register.io.rs1
		}.elsewhen(branchforward.io.forward_a === "b0100".U){
			branchunit.io.rs1_in := Data_Mem.io.Data
			JALR.io.rs1 := Register.io.rs1
		}.elsewhen(branchforward.io.forward_a === "b0011".U){
			branchunit.io.rs1_in := Register.io.writedata
			JALR.io.rs1 := Register.io.rs1
		}.elsewhen(branchforward.io.forward_a === "b0101".U){
			branchunit.io.rs1_in := Register.io.writedata
			JALR.io.rs1 := Register.io.writedata
		}.elsewhen(branchforward.io.forward_a === "b0110".U){
			branchunit.io.rs1_in := Register.io.rs1
			JALR.io.rs1 := ALU.io.output
		}.elsewhen(branchforward.io.forward_a === "b0111".U){
			branchunit.io.rs1_in := Register.io.rs1
			JALR.io.rs1 := ex_mem.io.ALUout_out
		}.elsewhen(branchforward.io.forward_a === "b1001".U){
			branchunit.io.rs1_in := Register.io.rs1
			JALR.io.rs1 := Data_Mem.io.Data
		}.elsewhen(branchforward.io.forward_a === "b1000".U){
			branchunit.io.rs1_in := Register.io.rs1
			JALR.io.rs1 := Register.io.writedata
		}.elsewhen(branchforward.io.forward_a === "b1010".U){
			branchunit.io.rs1_in := Register.io.rs1
			JALR.io.rs1 := Register.io.writedata
		}.otherwise{
			branchunit.io.rs1_in := Register.io.rs1
			JALR.io.rs1 := Register.io.rs1}


		when(branchforward.io.forward_b === "b0001".U){
			branchunit.io.rs2_in := ALU.io.output
		}.elsewhen(branchforward.io.forward_b === "b0010".U){
			branchunit.io.rs2_in := ex_mem.io.ALUout_out
		}.elsewhen(branchforward.io.forward_b === "b0100".U){
			branchunit.io.rs2_in := Data_Mem.io.Data
		}.elsewhen(branchforward.io.forward_b === "b0011".U){
			branchunit.io.rs2_in := Register.io.writedata
		}.elsewhen(branchforward.io.forward_b === "b0101".U){
			branchunit.io.rs2_in := Register.io.writedata
		}.otherwise{
			branchunit.io.rs2_in := Register.io.rs2}

	/*-------------------------------------------- BRANCH FORWARD AND BRANCH UNIT -----------------------------------------------*/





	
	
	when(control.io.Extend_sel === "b00".U){
		id_ex.io.Immediate_in := Imm_Gen.io.I_Immediate

	}.elsewhen(control.io.Extend_sel === "b10".U){
		id_ex.io.Immediate_in := Imm_Gen.io.S_Immediate

	}.elsewhen(control.io.Extend_sel===1.U){
			when(control.io.Oper_A_sel===3.U){
				when(control.io.Oper_B_sel===1.U){
					id_ex.io.Immediate_in:=Imm_Gen.io.U_Immediate
				}
			}
	}
	/*when(control.io.Next_PC_sel === "b00".U){
		when(control.io.MemRead === "b1".U){
			PC1.io.input := id_ex.io.IF_ID_pc4_out
		}.otherwise{
			PC1.io.input := PC1.io.pc4
		}
	}.elsewhen(control.io.Next_PC_sel === "b10".U){
		PC1.io.input := Imm_Gen.io.UJ_Immediate.asUInt
	}.elsewhen(control.io.Next_PC_sel === "b11".U){
		val imm = JALR.io.JALR.asUInt
		PC1.io.input := imm
	}.elsewhen(control.io.Next_PC_sel === "b01".U){
		when(branchunit.io.Branch_out === control.io.Branch1){
			val imm = Imm_Gen.io.SB_Immediate.asUInt
			PC1.io.input := imm
		}.otherwise{
			when(control.io.MemRead === "b1".U){
				PC1.io.input := id_ex.io.IF_ID_pc4_out
			}.otherwise{PC1.io.input := PC1.io.pc4}}
	}*/




/*-------------------------------------------- FORWARDING HAZARD-----------------------------------------------*/
	forward.io.id_ex_rs1_sel := id_ex.io.rs1_sel_out
	forward.io.id_ex_rs2_sel := id_ex.io.rs2_sel_out
	forward.io.ex_mem_regwrite := ex_mem.io.ID_Ex_Regwrite_out
	forward.io.ex_mem_rd := ex_mem.io.ID_Ex_rd_sel_out
	forward.io.mem_write_regwrite := mem_write.io.Ex_Mem_Regwrite_out
	forward.io.mem_write_rd := mem_write.io.Ex_Mem_rd_sel_out	
	
	when(id_ex.io.Oper_B_sel_out === 1.U){
		ALU.io.B := id_ex.io.Immediate_out
		 when(forward.io.forward_b === "b10".U){
		        ex_mem.io.ID_Ex_rs2_in := ex_mem.io.ALUout_out
		}.elsewhen(forward.io.forward_b === "b01".U){
			ex_mem.io.ID_Ex_rs2_in := Register.io.writedata}
		    .elsewhen(forward.io.forward_b === "b00".U){

			ex_mem.io.ID_Ex_rs2_in:= id_ex.io.rs2_out}
}
	.otherwise{
		 when(forward.io.forward_b === "b10".U){
		        ALU.io.B := ex_mem.io.ALUout_out
		        ex_mem.io.ID_Ex_rs2_in := ex_mem.io.ALUout_out}
		    .elsewhen(forward.io.forward_b === "b01".U){
			ALU.io.B := Register.io.writedata
			ex_mem.io.ID_Ex_rs2_in := Register.io.writedata
			
		     }
		    .elsewhen(forward.io.forward_b === "b00".U){
			ALU.io.B := id_ex.io.rs2_out
			ex_mem.io.ID_Ex_rs2_in:= id_ex.io.rs2_out}
}
	
	
	when(id_ex.io.Oper_A_sel_out === "b10".U){
		ALU.io.A := id_ex.io.IF_ID_pc4_out.asSInt
	}
	.otherwise{
		 when(forward.io.forward_a === "b10".U){
			ALU.io.A := ex_mem.io.ALUout_out}
		 .elsewhen(forward.io.forward_a === "b01".U){
			ALU.io.A := Register.io.writedata
		      }
		 .elsewhen(forward.io.forward_a === "b00".U){
			ALU.io.A := id_ex.io.rs1_out}
}
	/*-------------------------------------------- FORWARDING HAZARD-----------------------------------------------*/



	
	
	ex_mem.io.ALUout_in := ALU.io.output
	ex_mem.io.ID_Ex_Regwrite_in := id_ex.io.Regwrite_out
	ex_mem.io.ID_Ex_Memwrite_in := id_ex.io.Memwrite_out
	ex_mem.io.ID_Ex_MemRead_in := id_ex.io.MemRead_out
	ex_mem.io.ID_Ex_MemtoReg_in := id_ex.io.MemtoReg_out
	ex_mem.io.ID_Ex_rd_sel_in := id_ex.io.rd_sel_out	


	mem_write.io.Ex_Mem_rd_sel_in := ex_mem.io.ID_Ex_rd_sel_out
	mem_write.io.Ex_Mem_ALUout_in := ex_mem.io.ALUout_out
	mem_write.io.Ex_Mem_Regwrite_in := ex_mem.io.ID_Ex_Regwrite_out
	mem_write.io.Ex_Mem_MemtoReg_in := ex_mem.io.ID_Ex_MemtoReg_out
	mem_write.io.Ex_Mem_Data_in := Data_Mem.io.Data	


	Data_Mem.io.Address := ex_mem.io.ALUout_out(9,2).asUInt
	Data_Mem.io.Read_Data := ex_mem.io.ID_Ex_rs2_out
	Data_Mem.io.Mem_Read := ex_mem.io.ID_Ex_MemRead_out
	Data_Mem.io.Mem_Write := ex_mem.io.ID_Ex_Memwrite_out
	when(mem_write.io.Ex_Mem_MemtoReg_out === 1.U){
	 	Register.io.writedata := mem_write.io.Ex_Mem_Data_out
	}.elsewhen(mem_write.io.Ex_Mem_MemtoReg_out === 0.U){
	  	Register.io.writedata := mem_write.io.Ex_Mem_ALUout_out
	}

}


