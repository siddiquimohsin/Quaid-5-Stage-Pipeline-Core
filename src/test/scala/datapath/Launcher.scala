// See LICENSE.txt for license details.
package datapath

import chisel3.iotesters.{Driver, TesterOptionsManager}
import utils.TutorialRunner

object Launcher {
  val examples = Map(
      "InstructionTypeDecode" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new InstructionTypeDecode(), manager) {
          (c) => new InstructionTypeDecodeTests(c)
        }
      },
	
	"ControlDecoder" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new ControlDecoder(), manager) {
          (c) => new ControlDecoderTests(c)
        }
      },
	"Control" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new Control(), manager) {
          (c) => new controlTests(c)
        }
      },
	"ALUcontrol" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new ALUcontrol(), manager) {
          (c) => new ALUcontrolTests(c)
        }
      },
	"ALU" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new ALU(), manager) {
          (c) => new ALUTests(c)
        }
      },
	"ImmediateGeneration" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new ImmediateGeneration(), manager) {
          (c) => new ImmediateGenerationTests(c)
        }
      },
	"Register" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new Register(), manager) {
          (c) => new RegisterTests(c)
        }
      },
	
	"JALR" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new JALR(), manager) {
          (c) => new JALRTests(c)
        }
      },
	"PC" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new PC(), manager) {
          (c) => new PCTests(c)
        }
      },
	"InstructionMemory" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new InstructionMemory(), manager) {
          (c) => new InstructionMemoryTests(c)
        }
      },
	"DataMemory" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new DataMemory(), manager) {
          (c) => new DataMemoryTests(c)
        }
      },
	"Fetch" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new Fetch(), manager) {
          (c) => new FetchTests(c)
        }
      },
	"Decode" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new Decode(), manager) {
          (c) => new DecodeTests(c)
        }
      },
	"Execute" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new Execute(), manager) {
          (c) => new ExecuteTests(c)
        }
      },
	"IF_ID" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new IF_ID(), manager) {
          (c) => new IF_IDTests(c)
        }
      },
	"ID_Ex" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new ID_Ex(), manager) {
          (c) => new ID_ExTests(c)
        }
      },
	"Ex_Mem" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new Ex_Mem(), manager) {
          (c) => new Ex_MemTests(c)
        }
      },
	"Memory" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new Memory(), manager) {
          (c) => new MemoryTests(c)
        }
      },
	"WriteBack" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new WriteBack(), manager) {
          (c) => new WriteBackTests(c)
        }
      },
	"Top1" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new Top1(), manager) {
          (c) => new Top1Tests(c)
        }
      },
	"Mem_Write" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new Mem_Write(), manager) {
          (c) => new Mem_WriteTests(c)
        }
      },
	"Forwarding" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new Forwarding(), manager) {
          (c) => new ForwardingTests(c)
        }
      },
	"Stalling" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new Stalling(), manager) {
          (c) => new StallingTests(c)
        }
      },
	"BranchUnit" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new BranchUnit(), manager) {
          (c) => new BranchUnitTests(c)
        }
      },
	"BranchForward" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new BranchForward(), manager) {
          (c) => new BranchForwardTests(c)
        }
      },
	"StructureHazard" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new StructureHazard(), manager) {
          (c) => new StructureHazardTests(c)
        }
      }
  )
  def main(args: Array[String]): Unit = {
    TutorialRunner("examples", examples, args)
  }
}

