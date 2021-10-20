package datapath
import chisel3._
import chisel3.util.experimental.loadMemoryFromFile
class Fetch extends Module{
	val io = IO(new Bundle{
	val input = Input(UInt(32.W))
	val writeAddress = Input(UInt(10.W))

	val pc4 = Output(UInt(32.W))
	val pc = Output(UInt(32.W))
	val readData = Output(UInt(32.W))
})
	io.readData := 0.U
	io.pc4 := 0.U
	io.pc := 0.U
	val mem = Mem(1024,UInt(32.W))
	loadMemoryFromFile(mem,"/home/mohsin/data.txt")
	val reg = RegNext(0.U(32.W))
	reg := io.input
	io.readData := mem(io.writeAddress)
	io.pc4 := reg + 4.U
	io.pc := reg
}

