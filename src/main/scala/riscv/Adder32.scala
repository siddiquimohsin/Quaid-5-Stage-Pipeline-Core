package riscv
import chisel3._
class Adder32 extends Module{
	val io = IO(new Bundle{
	val a = Input(UInt(32.W))
	val b = Input(UInt(32.W))
	val sum = Output(UInt(32.W))
})
	io.sum := io.a + io.b
}
