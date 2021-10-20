package riscv
import chisel3._
class Subtractor32 extends Module{
	val io = IO(new Bundle{
	val a = Input(SInt(32.W))
	val b = Input(SInt(32.W))
	val sub = Output(SInt(32.W))
})
	io.sub := io.a - io.b
}
