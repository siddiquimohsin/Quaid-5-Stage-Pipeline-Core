package riscv
import chisel3._

class Mux2 extends Module{
	val io = IO(new Bundle{
	val a = Input(UInt(1.W))
	val b = Input(UInt(1.W))
	val sel = Input(UInt(1.W))
	val output = Output(UInt(1.W))
	})
	io.output := (io.a & ~io.sel) | (io.b & io.sel)
}
