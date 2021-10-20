package datapath
import chisel3._
class BranchForward extends Module{
	val io = IO(new Bundle{
	val con_branch = Input(UInt(1.W))
	val rs1_sel = Input(UInt(5.W))
	val rs2_sel = Input(UInt(5.W))
	val id_ex_rd_sel = Input(UInt(5.W))
	val id_ex_memread = Input(UInt(1.W))
	val id_ex_regwrite = Input(UInt(1.W))
	val ex_mem_rd_sel = Input(UInt(5.W))
	val ex_mem_memread = Input(UInt(1.W))
	val ex_mem_regwrite = Input(UInt(1.W))
	val mem_write_rd_sel = Input(UInt(5.W))
	val mem_write_memtoreg = Input(UInt(1.W))
	val mem_write_regwrite = Input(UInt(1.W))
	val forward_a = Output(UInt(4.W))
	val forward_b = Output(UInt(4.W))
})
	io.forward_a := "b0000".U
	io.forward_b := "b0000".U
/*----------------------- CONTROL BRANCH IS 1 --------------------------------------*/
	when(io.con_branch === 1.U){

	      /*-----------------------ID_EX PIPLINE--------------------------------------*/

		when((io.id_ex_rd_sel=/="b00000".U) && (io.id_ex_memread=/=1.U) && (io.id_ex_rd_sel===io.rs1_sel) &&      			(io.id_ex_rd_sel===io.rs2_sel)){
			io.forward_a := "b0001".U
			io.forward_b := "b0001".U}

		when((io.id_ex_rd_sel=/="b00000".U) && (io.id_ex_memread=/=1.U) && (io.id_ex_rd_sel===io.rs1_sel)){
			io.forward_a := "b0001".U}

		when((io.id_ex_rd_sel=/="b00000".U) && (io.id_ex_memread=/=1.U) && (io.id_ex_rd_sel===io.rs2_sel)){
			io.forward_b := "b0001".U}
	      /*-----------------------ID_EX PIPLINE--------------------------------------*/

/*XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX*/


	       /*-----------------------EX_MEM PIPLINE--------------------------------------*/
		
		when((io.ex_mem_rd_sel=/="b00000".U) && (io.ex_mem_memread=/=1.U) && (io.ex_mem_rd_sel===io.rs1_sel) && 		    			(io.ex_mem_rd_sel===io.rs2_sel) && ~((io.id_ex_rd_sel=/="b00000".U) && (io.id_ex_rd_sel===io.rs1_sel) && 	   			(io.id_ex_rd_sel===io.rs2_sel))){
			io.forward_a := "b0010".U
			io.forward_b := "b0010".U}

		when((io.ex_mem_rd_sel=/="b00000".U) && (io.ex_mem_memread=/=1.U) && (io.ex_mem_rd_sel===io.rs1_sel) &&  			~((io.id_ex_rd_sel=/="b00000".U) && (io.id_ex_rd_sel===io.rs1_sel))){
			io.forward_a := "b0010".U}

		when((io.ex_mem_rd_sel=/="b00000".U) && (io.ex_mem_memread=/=1.U) && (io.ex_mem_rd_sel===io.rs2_sel) &&  			~((io.id_ex_rd_sel=/="b00000".U) && (io.id_ex_rd_sel===io.rs2_sel))){
			io.forward_a := "b0010".U}

		when((io.ex_mem_rd_sel=/="b00000".U) && (io.ex_mem_memread===1.U) && (io.ex_mem_rd_sel===io.rs1_sel) && 		    			(io.ex_mem_rd_sel===io.rs2_sel) && ~((io.id_ex_rd_sel=/="b00000".U) && (io.id_ex_rd_sel===io.rs1_sel) && 	   			(io.id_ex_rd_sel===io.rs2_sel))){
			io.forward_a := "b0100".U
			io.forward_b := "b0100".U}

		when((io.ex_mem_rd_sel=/="b00000".U) && (io.ex_mem_memread===1.U) && (io.ex_mem_rd_sel===io.rs1_sel) &&  			~((io.id_ex_rd_sel=/="b00000".U) && (io.id_ex_rd_sel===io.rs1_sel))){
			io.forward_a := "b0100".U}

		when((io.ex_mem_rd_sel=/="b00000".U) && (io.ex_mem_memread===1.U) && (io.ex_mem_rd_sel===io.rs2_sel) &&  			~((io.id_ex_rd_sel=/="b00000".U) && (io.id_ex_rd_sel===io.rs2_sel))){
			io.forward_a := "b0100".U}
	       /*-----------------------EX_MEM PIPLINE--------------------------------------*/

/*XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX*/


		
	        /*-----------------------MEM_WRITE PIPLINE--------------------------------------*/

		when((io.mem_write_rd_sel=/="b00000".U) && (io.mem_write_memtoreg=/=1.U) && (io.mem_write_rd_sel===io.rs1_sel) && 
		(io.mem_write_rd_sel===io.rs2_sel) && ~((io.id_ex_rd_sel=/="b00000".U) && (io.id_ex_rd_sel===io.rs1_sel) && 	   			(io.id_ex_rd_sel===io.rs2_sel)) && ~((io.ex_mem_rd_sel=/="b00000".U) && (io.ex_mem_rd_sel===io.rs1_sel) && 	   			(io.ex_mem_rd_sel===io.rs2_sel))){
			io.forward_a := "b0011".U
        		io.forward_b := "b0011".U}

		when((io.mem_write_rd_sel=/="b00000".U) && (io.mem_write_memtoreg=/=1.U) && (io.mem_write_rd_sel===io.rs1_sel) && 
		~((io.id_ex_rd_sel=/="b00000".U) && (io.id_ex_rd_sel===io.rs1_sel)) && ~((io.ex_mem_rd_sel=/="b00000".U) && 			(io.ex_mem_rd_sel===io.rs1_sel))){
			io.forward_a := "b0011".U}

		when((io.mem_write_rd_sel=/="b00000".U) && (io.mem_write_memtoreg=/=1.U) && (io.mem_write_rd_sel===io.rs2_sel) && 
		~((io.id_ex_rd_sel=/="b00000".U) && (io.id_ex_rd_sel===io.rs2_sel)) && ~((io.ex_mem_rd_sel=/="b00000".U) && 			(io.ex_mem_rd_sel===io.rs2_sel))){
			io.forward_b := "b0011".U}

		when((io.mem_write_rd_sel=/="b00000".U) && (io.mem_write_memtoreg===1.U) && (io.mem_write_rd_sel===io.rs1_sel) && 
		(io.mem_write_rd_sel===io.rs2_sel) && ~((io.id_ex_rd_sel=/="b00000".U) && (io.id_ex_rd_sel===io.rs1_sel) && 	   			(io.id_ex_rd_sel===io.rs2_sel)) && ~((io.ex_mem_rd_sel=/="b00000".U) && (io.ex_mem_rd_sel===io.rs1_sel) && 	   			(io.ex_mem_rd_sel===io.rs2_sel))){
			io.forward_a := "b0101".U
        		io.forward_b := "b0101".U}

		when((io.mem_write_rd_sel=/="b00000".U) && (io.mem_write_memtoreg===1.U) && (io.mem_write_rd_sel===io.rs1_sel) && 
		~((io.id_ex_rd_sel=/="b00000".U) && (io.id_ex_rd_sel===io.rs1_sel)) && ~((io.ex_mem_rd_sel=/="b00000".U) && 			(io.ex_mem_rd_sel===io.rs1_sel))){
			io.forward_a := "b0101".U}

		when((io.mem_write_rd_sel=/="b00000".U) && (io.mem_write_memtoreg===1.U) && (io.mem_write_rd_sel===io.rs2_sel) && 
		~((io.id_ex_rd_sel=/="b00000".U) && (io.id_ex_rd_sel===io.rs2_sel)) && ~((io.ex_mem_rd_sel=/="b00000".U) && 			(io.ex_mem_rd_sel===io.rs2_sel))){
			io.forward_b := "b0101".U}
	       /*-----------------------MEM_WRITE PIPLINE--------------------------------------*/
}

/*XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX*/


 
 /*----------------------- CONTROL BRANCH IS 0--------------------------------------*/
		
	when(io.con_branch === 0.U){

	      /*----------------------- ID_EX PIPLINE --------------------------------------*/	
	
		when((io.id_ex_regwrite === 1.U) && (io.id_ex_rd_sel=/="b00000".U) && (io.id_ex_memread=/=1.U) &&     			  			(io.id_ex_rd_sel===io.rs1_sel)){
			io.forward_a := "b0110".U}

	     /*----------------------- ID_EX PIPLINE --------------------------------------*/

/*XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX*/



	      /*----------------------- EX_MEM PIPLINE --------------------------------------*/
		

		when((io.ex_mem_regwrite === 1.U) && (io.ex_mem_rd_sel=/="b00000".U) && (io.ex_mem_memread=/=1.U) &&    		   			(io.ex_mem_rd_sel===io.rs1_sel) && ~((io.id_ex_rd_sel=/="b00000".U) && (io.id_ex_rd_sel===io.rs1_sel))){
			io.forward_a := "b0111".U}
		
		when((io.ex_mem_regwrite === 1.U) && (io.ex_mem_rd_sel=/="b00000".U) && (io.ex_mem_memread===1.U) && 			   			(io.ex_mem_rd_sel===io.rs1_sel) && ~((io.id_ex_rd_sel=/="b00000".U) && (io.id_ex_rd_sel===io.rs1_sel))){
			io.forward_a := "b1001".U}


	       /*----------------------- EX_MEM PIPLINE --------------------------------------*/


/*XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX*/



	       /*----------------------- MEM_WRITE PIPLINE --------------------------------------*/
		
		when((io.mem_write_regwrite === 1.U) && (io.mem_write_rd_sel=/="b00000".U) && (io.mem_write_memtoreg=/=1.U) &&     			(io.mem_write_rd_sel===io.rs1_sel) && ~((io.ex_mem_rd_sel=/="b00000".U) && (io.ex_mem_rd_sel===io.rs1_sel)) &&    			~((io.id_ex_rd_sel=/="b00000".U) && (io.id_ex_rd_sel===io.rs1_sel))){
			io.forward_a := "b1000".U}
		
		when((io.mem_write_regwrite === 1.U) && (io.mem_write_rd_sel=/="b00000".U) && (io.mem_write_memtoreg===1.U) &&      			(io.mem_write_rd_sel===io.rs1_sel) && ~((io.ex_mem_rd_sel=/="b00000".U) && (io.ex_mem_rd_sel===io.rs1_sel)) && 	        		~((io.id_ex_rd_sel=/="b00000".U) && (io.id_ex_rd_sel===io.rs1_sel))){
			io.forward_a := "b1010".U}
}
	       /*----------------------- MEM_WRITE PIPLINE --------------------------------------*/

		

}
