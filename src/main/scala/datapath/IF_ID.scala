package datapath
import chisel3._

class IF_ID extends Module {
    val io = IO(new Bundle {
        val pc_in = Input(UInt(32.W))
        val pc4_in = Input(UInt(32.W))
        val Instruction_in = Input(UInt(32.W))
        val pc_out = Output(UInt(32.W))
        val pc4_out = Output(UInt(32.W))
        val Instruction_out = Output(UInt(32.W))
    })

    val pc_reg = RegInit(0.U(32.W))
    val pc4_reg = RegInit(0.U(32.W))
    val Instruction_reg = RegInit(0.U(32.W))
    pc_reg := io.pc_in
    pc4_reg := io.pc4_in
    Instruction_reg := io.Instruction_in
    io.pc_out := pc_reg
    io.pc4_out := pc4_reg
    io.Instruction_out := Instruction_reg
}
