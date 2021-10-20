package datapath
import chisel3._
import chisel3.util.Fill
import chisel3. util.Cat
class Execute extends Module{
	val io = IO(new Bundle{
	val A = Input(SInt(32.W))
	val B = Input(SInt(32.W))
	val ALUop = Input(UInt(3.W))
	val Func7 = Input(UInt(1.W))
	val Func3 = Input(UInt(3.W))	

	val output = Output(SInt(32.W))
	val Branch  = Output(UInt(1.W))
})
	val ALUcontrol = Wire(UInt(5.W))
	io.output := 0.S
	io.Branch := "b0".U
	val com = "b10".U
	val com1 = "b1".U
	ALUcontrol := 0.U
	val sbt1 = ALUcontrol(4,3)

	/* ALUControl*/

	when(io.ALUop === "b000".U){
	ALUcontrol := Cat(0.U, io.Func7, io.Func3)
}
	.elsewhen(io.ALUop === "b001".U){
	when(io.Func3 === "b101".U){
	ALUcontrol := Cat("b0".U,io.Func7,io.Func3)
	}.otherwise{
	ALUcontrol := Cat("b00".U,io.Func3)

}
}
	.elsewhen(io.ALUop === "b101".U){
	ALUcontrol := "b00000".U
} 
	.elsewhen(io.ALUop === "b100".U){
	ALUcontrol	:= "b00000".U
}
	.elsewhen(io.ALUop === "b010".U){
	ALUcontrol := Cat("b10".U ,io.Func3)
}
	.elsewhen(io.ALUop === "b011".U){
	ALUcontrol := "b11111".U
}
	.elsewhen(io.ALUop === "b001".U){
	ALUcontrol := Cat("b0".U,io.Func7,io.Func3)
}
	.elsewhen(io.ALUop ==="b110".U){
	ALUcontrol := "b00000".U
}
	/* ALU */

	when(ALUcontrol === "b00000".U){
	io.output := io.A + io.B
}
	.elsewhen(ALUcontrol === "b00001".U){
	val sbt = io.B.asUInt
	val sbt3 = io.B(4,0)
	val sbt4 = io.A.asUInt
	val sbt5 = sbt4 << sbt3
	val sbt6 = sbt5.asSInt
	io.output := sbt6
}
	.elsewhen(ALUcontrol === "b00010".U){
	when(io.A < io.B){
	io.output := 1.S 
}
}
	.elsewhen(ALUcontrol === "b00011".U){
	val sbt = io.A.asUInt
	val sbt3 = io.B.asUInt
	when(sbt < sbt3){
	io.output := 1.S
}
	when(com === sbt1){
	io.Branch := "b1".U
}
}
	.elsewhen(ALUcontrol === "b00100".U){
	val sbt2 = io.A.asUInt
	val sbt3 = io.B.asUInt
	val sbt4 = sbt2 ^ sbt3
	val sbt5 = sbt4.asSInt
	io.output := sbt5
}
	.elsewhen(ALUcontrol === "b00101".U){
	val sbt = io.B.asUInt
	val sbt3 = io.B(4,0)
	val sbt4 = io.A.asUInt
	val sbt5 = sbt4 >> sbt3
	val sbt6 = sbt5.asSInt
	io.output := sbt6
}
	.elsewhen(ALUcontrol === "b00110".U){
	val sbt2 = io.A.asUInt
	val sbt3 = io.B.asUInt
	val sbt4 = sbt2 | sbt3
	val sbt5 = sbt4.asSInt
	io.output := sbt5
}
	.elsewhen(ALUcontrol === "b00111".U){
	val sbt2 = io.A.asUInt
	val sbt3 = io.B.asUInt
	val sbt4 = sbt2 & sbt3
	val sbt5 = sbt4.asSInt
	io.output := sbt5
}
	when(ALUcontrol === "b01000".U){
	io.output := io.A - io.B
}
	.elsewhen(ALUcontrol === "b01101".U){
	val sbt3 = io.B(4,0)
	io.output := io.A >> sbt3
}
	.elsewhen(ALUcontrol === "b10000".U){
	when(io.A === io.B){
	io.output := 1.S
	io.Branch := "b1".U 
}
}
	.elsewhen(ALUcontrol === "b10001".U){
	when(io.A =/= io.B){
	io.output := 1.S
	io.Branch := "b1".U 
}
}
	.elsewhen(ALUcontrol === "b10100".U){
	when(io.A < io.B){
	io.output := 1.S
	io.Branch := "b1".U 
}
}
	.elsewhen(ALUcontrol === "b10101".U){
	when(io.A > io.B){
	io.output := 1.S
	io.Branch := "b1".U 
}
}
	.elsewhen(ALUcontrol === "b10110".U){
	val sbt = io.A.asUInt
	val sbt3 = io.B.asUInt
	when(sbt < sbt3){
	io.output := 1.S
}
	when(com === sbt1 & io.output === 1.S){
	io.Branch := "b1".U
}
}
	.elsewhen(ALUcontrol === "b10111".U){
	val sbt = io.A.asUInt
	val sbt3 = io.B.asUInt
	when(sbt > sbt3){
	io.output := 1.S
}
	when(com === sbt1 & io.output === 1.S){
	io.Branch := "b1".U
}
}
	.elsewhen(ALUcontrol === "b11111".U){
	io.output := io.A
}	
	/* JALR */
	/*val hex = 4294967294L.S
	val output = io.IF_ID_rs1 + io.IF_ID_rs2
	val output1 = hex & output
	io.JALR := output1*/
	
}
